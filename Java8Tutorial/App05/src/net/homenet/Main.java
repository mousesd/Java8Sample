package net.homenet;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runAccessingLocalVariable();
        System.out.println();

        app.runAccessingFieldAndStaticVariable();
        System.out.println();

        app.runAccessingDefaultInterfaceMethod();
    }

    interface Converter<F, T> {
        T convert(F from);
    }

    /**
     * Accessing local variables sample
     */
    private void runAccessingLocalVariable() {
        //# 1.Anonymous class
        //final int num = 3;    // final 선언하지 않더라도 문제 없음
        //Converter<Integer, String> converter = new Converter<Integer, String>() {
        //    @Override
        //    public String convert(Integer from) {
        //        return String.valueOf(from + num);
        //    }
        //};
        //System.out.println(converter.convert(2));

        //# 2.Lambda expressions
        int num = 3;
        Converter<Integer, String> converter = from -> String.valueOf(from + num);
        System.out.println(converter.convert(2));
    }

    private static int outerStaticNum;
    private int outerNum;

    /**
     * Accessing fields and static variables sample
     */
    private void runAccessingFieldAndStaticVariable() {
        Converter<Integer, String> converter1 = from -> {
            outerStaticNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> converter2 = from -> {
            outerNum = 72;
            return String.valueOf(from);
        };

        System.out.println(converter1.convert(100));
        System.out.println(converter2.convert(200));
        System.out.println("outerStaticNum=" + outerStaticNum);
        System.out.println("outerNum=" + outerNum);
    }

    interface Formula {
        double calculate(int num);

        default double sqrt(int num) {
            return Math.sqrt(num);
        }
    }

    /**
     * Accessing default interface methods sample
     */
    @SuppressWarnings("Duplicates")
    private void runAccessingDefaultInterfaceMethod() {
        //# 1.Anonymous class
        Formula formula = new Formula() {
            @Override
            public double calculate(int num) {
                return sqrt(num * 100);     // access the default method 'sqrt()'
            }
        };
        System.out.println(formula.calculate(100));

        //# 2.Lambda expression. compile error
        //Formula formula = num -> sqrt(num * 100);
    }
}



























