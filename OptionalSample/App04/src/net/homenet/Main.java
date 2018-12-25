package net.homenet;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runOfAndFilter();
    }

    /**
     * Optional.of(), filter() sample
     */
    private void runOfAndFilter() {
        Optional<Integer> integer = Optional.of(34);

        int value = integer.filter(i -> i % 2 == 0).orElse(0);
        System.out.println("Value=" + value);

        value = integer.filter(i -> i % 2 != 0).orElse(0);
        System.out.println("Value=" + value);
    }
}
