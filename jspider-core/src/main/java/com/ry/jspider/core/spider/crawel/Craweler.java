package com.ry.jspider.core.spider.crawel;

import com.ry.jspider.core.api.Service;
import com.ry.jspider.core.log.Log;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * Created by yangyang on 2016/12/22.
 */
public class Craweler implements Service {
    private static Log log = Log.getLogger(Craweler.class);
    private IoConnector connector;
    private IoSession session;

    private int bothIdleTime;
    private int sendBufferSize;
    private int readBufferSize;
    private int receiveBufferSize;
    private int connectTimeout;


    {
        bothIdleTime = 10;
        sendBufferSize = 2048;
        readBufferSize = 2048;
        receiveBufferSize = 5000;
        connectTimeout = 1000;
    }

    public Craweler() {
        connector = new NioSocketConnector();

        DefaultIoFilterChainBuilder chain =
                new DefaultIoFilterChainBuilder();
        chain.addLast("default", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connector.setFilterChainBuilder(chain);
        connector.setHandler(new IoHandlerAdapter() {
            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                super.messageReceived(session, message);
            }
        });
    }

    public void init() {
        connector.setConnectTimeoutMillis(connectTimeout);
        SocketSessionConfig scfg = (SocketSessionConfig) connector.getSessionConfig();
        scfg.setBothIdleTime(bothIdleTime);
        scfg.setSendBufferSize(sendBufferSize);
        scfg.setReadBufferSize(readBufferSize);
        scfg.setReceiveBufferSize(receiveBufferSize);
    }

    public boolean connect(String host, int port) {
        return connect0(new InetSocketAddress(host, port));
    }

    private boolean connect0(InetSocketAddress address) {
        ConnectFuture cf = connector.connect(address);
        cf.awaitUninterruptibly();

        if (cf.isConnected()) {
            session = cf.getSession();
            return true;
        } else {
            log.warn("create session failed");
        }

        return false;
    }

    public void send(String message) {
        WriteFuture writeFuture = session.write(message);
        writeFuture.awaitUninterruptibly();
    }

    public void destroy() {
        session.close(true);
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }
}
