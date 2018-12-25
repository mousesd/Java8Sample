package net.homenet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runGet();
        app.runOrElse();
        app.runOrElseGet();
    }

    /**
     * Optional.get() sample
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private void runGet() {
        List<String> strings = Arrays.asList("mousesd1", "mousesd2", "mousesd3");
        Optional<String> endWith1String = strings.stream()
            .filter(string -> string.endsWith("1"))
            .findAny();

        System.out.println(endWith1String.get());

        //Optional<String> empty = Optional.empty();
        //System.out.println(empty.get());    // throws NoSuchElementException
    }

    /**
     * Optional.orElse() sample
     */
    private void runOrElse() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.orElse("Default value"));
    }

    /**
     * Optional.orElseGet() sample
     */
    @SuppressWarnings("ExcessiveLambdaUsage")
    private void runOrElseGet() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.orElseGet(() -> "Default value"));

    }
}
