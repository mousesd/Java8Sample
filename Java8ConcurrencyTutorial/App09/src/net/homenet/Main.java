package net.homenet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runLongAdderSample1();
        System.out.println();

        app.runLongAdderSample2();
    }

    private void runLongAdderSample1() {
        LongAdder adder = new LongAdder();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(adder::increment));

        ConcurrentUtils.stop(executor);
        System.out.println("LongAdder: " + adder.sumThenReset());
    }

    private void runLongAdderSample2() {
        LongAdder adder = new LongAdder();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(() -> adder.add(i)));

        ConcurrentUtils.stop(executor);
        System.out.println("LongAdder: " + adder.sumThenReset());
    }
}
