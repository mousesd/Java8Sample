package net.homenet;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runThreadsAndRunnables1();
        System.out.println();

        app.runThreadsAndRunnables2();
    }

    private void runThreadsAndRunnables1() {
        Runnable task = () -> System.out.println("Hello " + Thread.currentThread().getName());
        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }

    private void runThreadsAndRunnables2() {
        Thread thread = new Thread(() -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("Foo " + threadName);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
