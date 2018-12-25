package net.homenet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runSortListOfStrings();
        app.runSortListOfStringsWithLambda();
    }

    @SuppressWarnings({"Java8ListSort", "Convert2Lambda"})
    private void runSortListOfStrings() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        System.out.println(names);
    }

    @SuppressWarnings({"ComparatorCombinators", "Java8ListSort"})
    private void runSortListOfStringsWithLambda() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (o1, o2) -> o2.compareTo(o1));
        System.out.println(names);
    }
}
