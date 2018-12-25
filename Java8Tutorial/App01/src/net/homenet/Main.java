package net.homenet;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.runDefaultMethodForInterface();
    }

    interface Formula {
        double calculate(int num);

        default double sqrt(int num) {
            return Math.sqrt(num);
        }
    }

    private void runDefaultMethodForInterface() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int num) {
                return sqrt(num * 100);
            }
        };

        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }
}
