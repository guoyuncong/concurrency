package com.yc.concurrency.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author: yagena
 * @date: 2021-02-07
 */
public class InitValueInThreadLocal {

    private static final StringBuilder INIT_VALUE = new StringBuilder("init");

    private static final ThreadLocal<StringBuilder> builder = ThreadLocal.withInitial(() -> INIT_VALUE);

    private static class AppendStringThread extends Thread {
        @Override
        public void run() {
            StringBuilder inThread = builder.get();
            for (int i=0; i<10;i++) {
                inThread.append("-" + i);
            }
            System.out.println(inThread.toString());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i<10;i++) {
            new AppendStringThread().start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
