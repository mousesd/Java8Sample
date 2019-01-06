package net.homenet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runExecutorsSample1();
    }

    private void runExecutorsSample1() {
        //# The class 'Executors' provides convenient factory methods for creating different kind of executor services.
        //# Executors have to be stopped explicitly - otherwise they keep listening for new tasks.
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        System.out.println("Attempt to shutdown executor");
        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executor.isTerminated()) {
                System.out.println("Cancel non-finished tasks");
            }

            executor.shutdownNow();
            System.out.println("Shutdown finished");
        }
    }
}

















