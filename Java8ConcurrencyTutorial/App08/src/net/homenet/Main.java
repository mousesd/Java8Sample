package net.homenet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runAtomicIntegerSample1();
        System.out.println();

        app.runAtomicIntegerSample2();
        System.out.println();

        app.runAtomicIntegerSample3();
    }

    private void runAtomicIntegerSample1() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        ConcurrentUtils.stop(executor);
        System.out.println("AtomicInteger: " + atomicInt.get());
    }

    private void runAtomicIntegerSample2() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10)
            .forEach(i -> executor.submit(() -> {
                int currentValue = atomicInt.updateAndGet(n -> n + 2);
                System.out.println("Current value: " + currentValue + " updated by " + Thread.currentThread().getName());
            }));

        ConcurrentUtils.stop(executor);
        System.out.println("AtomicInteger: " + atomicInt.get());
    }

    private void runAtomicIntegerSample3() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 100)
            .forEach(i -> executor.submit(() -> {
                int currentValue = atomicInt.accumulateAndGet(i, (n, m) -> n + m);
                System.out.println("Current value: " + currentValue + " updated by " + Thread.currentThread().getName());
            }));

        ConcurrentUtils.stop(executor);
        System.out.println("AtomicInteger: " + atomicInt.get());
    }
}
