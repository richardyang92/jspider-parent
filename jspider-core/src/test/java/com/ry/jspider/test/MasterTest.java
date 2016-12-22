package com.ry.jspider.test;

import com.ry.jspider.core.spider.master.Master;

import java.io.IOException;

/**
 * Created by yangyang on 2016/12/22.
 */
public class MasterTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Master master = new Master();
        master.init();
        master.bind("127.0.0.1", 38888);

        Thread.sleep(10000L);
        master.destroy();
    }
}
