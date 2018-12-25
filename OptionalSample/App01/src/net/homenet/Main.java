package net.homenet;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runIfPresentSample();
    }

    private void runIfPresentSample() {
        List<String> listOfStrings = Arrays.asList("Mark", "Howard", "Anthony D'Cornian");

        listOfStrings.stream()
            .filter(string -> string.length() > 10)
            .findAny()
            .ifPresent(System.out::println);

        listOfStrings.stream()
            .filter(string -> string.length() > 20)
            .findAny()
            .ifPresent(System.out::println);
    }
}
