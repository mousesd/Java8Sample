package net.homenet;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runParallelStreamSample1();
        System.out.println();

        app.runParallelStreamSample2();
    }

    private void runParallelStreamSample1() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            .parallelStream()
            .filter(s -> {
                System.out.printf("filter: %s [%s]\n", s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.printf("map: %s [%s]\n", s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .forEach(s -> System.out.printf("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }

    private void runParallelStreamSample2() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            .parallelStream()
            .filter(s -> {
                System.out.printf("filter: %s [%s]\n", s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.printf("map: %s [%s]\n", s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .sorted((s1, s2) -> {
                System.out.printf("sorted: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
                return s1.compareTo(s2);
            })
            .forEach(s -> System.out.printf("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }
}
