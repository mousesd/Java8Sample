package net.homenet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        LongBinaryOperator operator = (left, right) -> 2 * left + right;
        LongAccumulator accumulator = new LongAccumulator(operator, 1L);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10)
            .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        ConcurrentUtils.stop(executor);
        System.out.println("LongAccumulator: " + accumulator.getThenReset());
    }
}
