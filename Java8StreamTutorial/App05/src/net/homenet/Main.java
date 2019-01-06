package net.homenet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runFlatMapSample1();
        System.out.println();

        app.runFlatMapSample2();
    }

    /**
     * flatMap() method sample
     */
    private void runFlatMapSample1() {
        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4)
            .forEach(i -> foos.add(new Foo("Foo" + i)));

        foos.forEach(foo -> IntStream
            .range(1, 4)
            .forEach(i -> foo.getBars().add(new Bar("Bar" + i + " <- " + foo.getName()))));

        foos.stream()
            .flatMap(foo -> foo.getBars().stream())
            .forEach(bar -> System.out.println(bar.getName()));
    }

    private void runFlatMapSample2() {
        IntStream.range(1, 4)
            .mapToObj(i -> new Foo("Foo" + i))
            .peek(foo -> IntStream.range(1, 4)
                .mapToObj(i -> new Bar("Bar" + i + " <- " + foo.getName()))
                .forEach(foo.getBars()::add))
            .flatMap(foo -> foo.getBars().stream())
            .forEach(bar -> System.out.println(bar.getName()));
    }
}
