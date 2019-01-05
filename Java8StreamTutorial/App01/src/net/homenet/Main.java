package net.homenet;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runCreateStreamSample();
        System.out.println();

        app.runIntStreamSample();
        System.out.println();

        app.runStreamToPrimitiveStream();
    }

    /**
     * Create a stream sample, stream(), Stream.of()
     */
    @SuppressWarnings("SimplifyStreamApiCallChains")
    private void runCreateStreamSample() {
        Arrays.asList("a1", "a2", "a3").stream()
            .findFirst()
            .ifPresent(System.out::println);

        Stream.of("a1", "a2", "a3")
            .findFirst()
            .ifPresent(System.out::println);
    }

    /**
     * IntStream.range(), map(), average() method sample
     */
    private void runIntStreamSample() {
        IntStream.range(0, 10)
            .forEach(System.out::println);

        IntStream.of(1, 2, 3)
            .map(n -> 2 * n + 1)
            .average()
            .ifPresent(System.out::println);
    }

    /**
     * Stream <-> Primitive stream
     */
    private void runStreamToPrimitiveStream() {
        Stream.of("a1", "a2", "a3")
            .map(s -> s.substring(1))
            .mapToInt(Integer::parseInt)
            .max()
            .ifPresent(System.out::println);

        IntStream.range(1, 4)
            .mapToObj(n -> "a" + n)
            .forEach(System.out::println);

        Stream.of(1.0, 2.0, 3.0)
            .mapToInt(Double::intValue)
            .mapToObj(i -> "a" + i)
            .forEach(System.out::println);
    }
}
