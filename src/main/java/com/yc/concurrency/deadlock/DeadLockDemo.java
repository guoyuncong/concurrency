package com.yc.concurrency.deadlock;

/**
 * @author: yagena
 * @date: 2021-04-14
 */
public class DeadLockDemo {
    // ❶ 创建资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();
    public static void main(String[] args) {
        // ❷ 创建线程 A
        Thread threadA = createThreadA();
        // ❸ 创建线程 B
        Thread threadB = createThreadB();
        // ❹ 启动线程
        threadA.start();
        threadB.start();
    }

    private static Thread createThreadA() {
        Thread threadA = new Thread(() -> {
            // 尝试获取资源 A
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + " got ResourceA");
                try {
                    // 休眠 1s
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get ResourceB");
                // 尝试获取资源 B
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " got ResourceB");
                }
            }
        }, "ThreadA");
        return threadA;
    }

    private static Thread createThreadB() {
        Thread threadB = new Thread(() -> {
            // 尝试获取资源 A
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + " got ResourceA");
                try {
                    // 休眠 1s
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get ResourceB");
                // 尝试获取资源 B
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " got ResourceB");
                }
            }
        }, "ThreadB");
        return threadB;
    }
}
