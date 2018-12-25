package net.homenet;

import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runEmptyAndIsPresent();
    }

    /**
     * Optional.empty(), isPresent() sample
     */
    private void runEmptyAndIsPresent() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());
    }
}
