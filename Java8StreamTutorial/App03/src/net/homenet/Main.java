package net.homenet;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runReusingStreamSample1();
        System.out.println();

        app.runReusingStreamSample2();
    }

    private void runReusingStreamSample1() {
        Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> s.startsWith("a"));

        //# OK
        System.out.println(stream.anyMatch(s -> true));

        //# Exception
        // Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        // at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:229)
        // at java.util.stream.ReferencePipeline.noneMatch(ReferencePipeline.java:459)
        // at net.homenet.Main.runReusingStreamSample1(Main.java:17)
        // at net.homenet.Main.main(Main.java:9)
        //System.out.println(stream.noneMatch(s -> true));
    }

    private void runReusingStreamSample2() {
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> s.startsWith("a"));

        System.out.println(streamSupplier.get().anyMatch(s -> true));
        System.out.println(streamSupplier.get().noneMatch(s -> true));
    }
}
