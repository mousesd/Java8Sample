package net.homenet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int loop = 0; loop < max; loop++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        app.runSequentialSort(values);
        System.out.println();

        app.runParallelSort(values);
    }

    /**
     * Sequential sort sample
     */
    private void runSequentialSort(List<String> values) {
        long start = System.nanoTime();

        List<String> orderValues = values.stream()
            .sorted()
            .collect(Collectors.toList());

        long end = System.nanoTime();

        System.out.println("Count: " + orderValues.size());
        System.out.printf("Sequential sort took: %d ms\r\n", TimeUnit.NANOSECONDS.toMillis(end - start));
    }

    /**
     * Parallel sort sample
     */
    private void runParallelSort(List<String> values) {
        long start = System.nanoTime();

        List<String> orderValues = values.parallelStream()
            .sorted()
            .collect(Collectors.toList());

        long end = System.nanoTime();

        System.out.println("Count: " + orderValues.size());
        System.out.printf("Parallel sort took: %d ms\r\n", TimeUnit.NANOSECONDS.toMillis(end - start));
    }
}
