package net.homenet;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        Map<Integer, String> map = new HashMap<>();
        for (int index = 0; index < 10; index++) {
            map.putIfAbsent(index, "val" + index);
        }

        app.runForEachSample(map);
        System.out.println();

        app.runComputeSample(map);
        System.out.println();

        app.runRemoveSample(map);
        System.out.println();

        app.runGetOrDefaultSample(map);
        System.out.println();

        app.runMergeSample(map);
    }

    /**
     * forEach() method sample
     */
    private void runForEachSample(Map<Integer, String> map) {
        map.forEach((key, value) -> System.out.println(value));
    }

    /**
     * computeIfPresent(), computeIfAbsent() method sample
     */
    @SuppressWarnings("ExcessiveLambdaUsage")
    private void runComputeSample(Map<Integer, String> map) {
        map.computeIfPresent(3, (key, value) -> value + key);
        System.out.println("3 value: " + map.get(3));

        map.computeIfPresent(9, (key, value) -> null);
        System.out.println("Contain 9 key: " + map.containsKey(9));

        map.computeIfAbsent(23, key -> "val" + key);
        System.out.println("Contain 23 key: " + map.containsKey(23));

        map.computeIfAbsent(4, key -> "bam");
        System.out.println("4 value: " + map.get(4));
    }

    /**
     * remove() method sample
     */
    private void runRemoveSample(Map<Integer, String> map) {
        map.remove(3, "val3");
        System.out.println("3 value: " + map.get(3));

        map.remove(3, "val33");
        System.out.println("3 value: " + map.get(3));
    }

    /**
     * getOrDefault() method sample
     */
    private void runGetOrDefaultSample(Map<Integer, String> map) {
        System.out.println("42 value: " + map.getOrDefault(42, "Not found"));
    }

    /**
     * merge() method sample
     */
    @SuppressWarnings("Convert2MethodRef")
    private void runMergeSample(Map<Integer, String> map) {
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println("9 value: " + map.get(9));

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println("9 value: " + map.get(9));
    }
}


























