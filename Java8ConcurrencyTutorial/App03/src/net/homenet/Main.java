package net.homenet;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runCallableAndFutureSample();
        System.out.println();

        app.runTimeoutSample();
        System.out.println();

        app.runInvokeAllSample();
        System.out.println();

        app.runInvokeAnySample();
    }

    /**
     * Callable and Future sample
     */
    private void runCallableAndFutureSample() {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Callable thread: " + Thread.currentThread().getName());
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);
        System.out.println("Future done? " + future.isDone());

        try {
            Integer result = future.get();
            System.out.println("Future done? " + future.isDone());
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }

    /**
     * Timeout sample
     */
    private void runTimeoutSample() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Task interrupted", e);
            }
        });

        try {
            //# Exception in thread "main" java.util.concurrent.TimeoutException
            //      at java.util.concurrent.FutureTask.get(FutureTask.java:205)
            //      at net.homenet.Main.runTimeoutSample(Main.java:51)
            //      at net.homenet.Main.main(Main.java:11)
            //future.get(1, TimeUnit.SECONDS);
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }

    /**
     * invokeAll() method sample
     */
    private void runInvokeAllSample() {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> tasks = Arrays.asList(() -> "task1"
            , () -> "task2"
            , () -> "task3");

        try {
            executor.invokeAll(tasks).stream()
                .map(future -> {
                    String taskResult = null;
                    try {
                        taskResult = future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return taskResult;
                })
                .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }

    private Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    /**
     * invokeAny() method sample
     */
    private void runInvokeAnySample() {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(callable("task1", 2)
            , callable("task2", 1)
            , callable("task3", 3));

        try {
            //# invokeAny(): Instead of returning future objects this method blocks until the first callable terminates
            //# and returns the result of that callable.
            String result = executor.invokeAny(callables);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }
}





























