package io.jiache.raft;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {
    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    @Test
    public void helloTest() throws InterruptedException {
        Object lock = new Object();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.notify();
        }).start();
        synchronized (lock) {
            lock.wait();
        }

    }
}
