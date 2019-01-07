package net.homenet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runSynchronizedSample1();
        System.out.println();

        app.runSynchronizedSample2();
    }

    private int count = 0;

    private void increment() {
        count = count + 1;
    }

    private void runSynchronizedSample1() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(this::increment));

        ConcurrentUtils.stop(executor);
        System.out.println("Count=" + count);
    }

    synchronized private void incrementSync() {
        count = count + 1;
    }

    private void runSynchronizedSample2() {
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(this::incrementSync));

        ConcurrentUtils.stop(executor);
        System.out.println("Count=" + count);
    }
}
