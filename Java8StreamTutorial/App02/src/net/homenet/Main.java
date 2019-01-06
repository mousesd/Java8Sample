package net.homenet;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runProcessingOrder1();
        System.out.println();

        app.runProcessingOrder2();
        System.out.println();

        app.runProcessingOrder3();
        System.out.println();

        app.runProcessingOrder4();
    }

    /**
     * Processing order sample 1
     */
    private void runProcessingOrder1() {
        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            })
            .forEach(s -> System.out.println("forEach: " + s));

        //# Results
        // filter: d2
        // forEach: d2
        // filter: a2
        // forEach: a2
        // filter: b1
        // forEach: b1
        // filter: b3
        // forEach: b3
        // filter: c
        // forEach: c
    }

    /**
     * Processing order sample 1
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void runProcessingOrder2() {
        Stream.of("d2", "a2", "b2", "b3", "c")
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .anyMatch(s -> {
                System.out.println("anyMatch: " + s);
                return s.startsWith("A");
            });

        //# Results
        // map: d2
        // anyMatch: D2
        // map: a2
        // anyMatch: A2
    }

    private void runProcessingOrder3() {
        Stream.of("d2", "a2", "b1", "b3", "c")
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("A");
            })
            .forEach(s -> System.out.println("forEach: " + s));

        //# Results
        // map: d2
        // filter: D2
        // map: a2
        // filter: A2
        // forEach: A2
        // map: b1
        // filter: B1
        // map: b3
        // filter: B3
        // map: c
        // filter: C

        System.out.println();
        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("a");
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));

        //# Results
        // filter: d2
        // filter: a2
        // map: a2
        // forEach: A2
        // filter: b1
        // filter: b3
        // filter: c
    }

    @SuppressWarnings("EqualsWithItself")
    private void runProcessingOrder4() {
        Stream.of("d2", "a2", "b1", "b3", "c")
            .sorted((s1, s2) -> {
                System.out.printf("sorted: %s; %s\n", s1, s2);
                return s1.compareTo(s1);
            })
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("a");
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));

        //# Results
        // sorted: a2; d2
        // sorted: b1; a2
        // sorted: b3; b1
        // sorted: c; b3
        // filter: d2
        // filter: a2
        // map: a2
        // forEach: A2
        // filter: b1
        // filter: b3
        // filter: c

        System.out.println();
        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("a");
            })
            .sorted((s1, s2) -> {
                System.out.printf("sorted: %s; %s\n", s1, s2);
                return s1.compareTo(s1);
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));

        //# Results
        // filter: d2
        // filter: a2
        // filter: b1
        // filter: b3
        // filter: c
        // map: a2
        // forEach: A2
    }
}

































