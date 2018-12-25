package net.homenet;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runFunctionInterface();
    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    @SuppressWarnings("Convert2MethodRef")
    private void runFunctionInterface() {
        Converter<String, Integer> converter = from -> Integer.valueOf(from);
        System.out.println(converter.convert("123"));
    }
}
