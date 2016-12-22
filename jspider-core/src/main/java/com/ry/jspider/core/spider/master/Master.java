package com.ry.jspider.core.spider.master;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ry.jspider.core.api.Service;
import com.ry.jspider.core.log.Log;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by yangyang on 2016/12/22.
 */
public class Master implements Service {
    private static Log log = Log.getLogger(Master.class);

    private IoAcceptor acceptor;

    private int bothIdleTime;
    private int sendBufferSize;
    private int readBufferSize;
    private int receiveBufferSize;

    {
        bothIdleTime = 10;
        sendBufferSize = 2048;
        readBufferSize = 2048;
        receiveBufferSize = 5000;
    }

    public Master() {
        acceptor = new NioSocketAcceptor();

        DefaultIoFilterChainBuilder chain =
                new DefaultIoFilterChainBuilder();
        chain.addLast("default", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.setFilterChainBuilder(chain);
        acceptor.setHandler(new IoHandlerAdapter() {
            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                String messageBody = (String) message;
                JSONObject jsonObject = JSON.parseObject(messageBody);
                log.info("received message from TaskWorker {}", jsonObject.get("name"));
            }
        });
    }

    public void init() {
        SocketSessionConfig scfg = (SocketSessionConfig) acceptor.getSessionConfig();
        scfg.setBothIdleTime(bothIdleTime);
        scfg.setSendBufferSize(sendBufferSize);
        scfg.setReadBufferSize(readBufferSize);
        scfg.setReceiveBufferSize(receiveBufferSize);
        log.info("ISocketAcceptor init complete");
    }

    public void bind(String host, int port) throws IOException {
        acceptor.bind(new InetSocketAddress(host, port));
    }

    public void destroy() {
        acceptor.unbind();
        acceptor.dispose();
    }
}
