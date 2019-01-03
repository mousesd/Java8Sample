package net.homenet;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        List<String> strings = new ArrayList<>();
        strings.add("bbb3");
        strings.add("aaa2");
        strings.add("bbb2");
        strings.add("ccc2");
        strings.add("ddd2");
        strings.add("aaa1");
        strings.add("bbb1");
        strings.add("ccc1");
        strings.add("ddd1");

        app.runFilterSample(strings);
        System.out.println();

        app.runSortedSample(strings);
        System.out.println();

        app.runMapSample(strings);
        System.out.println();

        app.runMatchSample(strings);
        System.out.println();

        app.runCountSample(strings);
        System.out.println();

        app.runReduceSample(strings);
    }

    /**
     * filter() method sample
     */
    private void runFilterSample(List<String> strings) {
        strings.stream()
            .filter(s -> s.startsWith("a"))
            .forEach(System.out::println);
    }

    /**
     * sorted() method sample
     */
    private void runSortedSample(List<String> strings) {
        strings.stream()
            .sorted()
            .filter(s -> s.startsWith("a"))
            .forEach(System.out::println);
    }

    /**
     * map() method sample
     */
    @SuppressWarnings("ComparatorCombinators")
    private void runMapSample(List<String> strings) {
        strings.stream()
            .map(String::toUpperCase)
            .sorted((a, b) -> b.compareTo(a))
            .forEach(System.out::println);
    }

    /**
     * anyMatch(), allMatch(), noneMatch() method sample
     */
    private void runMatchSample(List<String> strings) {
        boolean anyStartsWithA = strings.stream()
            .anyMatch(s -> s.startsWith("a"));
        System.out.println("anyMatch()=" + anyStartsWithA);

        boolean allStartsWithA = strings.stream()
            .allMatch(s -> s.startsWith("a"));
        System.out.println("allMatch()=" + allStartsWithA);

        boolean noneStartsWithZ = strings.stream()
            .noneMatch(s -> s.startsWith("z"));
        System.out.println("noneMatch()=" + noneStartsWithZ);
    }

    /**
     * count() method sample
     */
    private void runCountSample(List<String> strings) {
        long startsWithBCount = strings.stream()
            .filter(s -> s.startsWith("b"))
            .count();
        System.out.println("Count of starts with 'b'=" + startsWithBCount);
    }

    /**
     * reduce() method sample
     * This operation performs a reduction on the elements of the stream with the given the function. The result is an
     * Optional holding the reduced value.
     */
    private void runReduceSample(List<String> strings) {
        strings.stream()
            .sorted()
            .reduce((a, b) -> a + "#" + b)
            .ifPresent(System.out::println);
    }
}
