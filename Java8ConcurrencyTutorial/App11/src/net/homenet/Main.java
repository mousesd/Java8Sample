package net.homenet;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        Main app = new Main();
        app.runConcurrentMapSample1(map);
        System.out.println();

        app.runConcurrentMapSample2(map);
        System.out.println();

        app.runConcurrentMapSample3(map);
        System.out.println();

        app.runConcurrentMapSample4(map);
        System.out.println();

        app.runConcurrentMapSample5(map);
        System.out.println();

        app.runConcurrentMapSample6(map);
    }

    /**
     * ConcurrentMap.forEach() method sample
     */
    private void runConcurrentMapSample1(ConcurrentMap<String, String> map) {
        map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));
    }

    /**
     * ConcurrentMap.putIfAbsent() method sample
     */
    private void runConcurrentMapSample2(ConcurrentMap<String, String> map) {
        String value = map.putIfAbsent("c3", "p1");
        System.out.printf("Value: %s, Key c3 value is %s\n", value, map.get("c3"));
    }

    /**
     * ConcurrentMap.getOrDefault() method sample
     */
    private void runConcurrentMapSample3(ConcurrentMap<String, String> map) {
        String value = map.getOrDefault("hi", "there");
        System.out.printf("Value: %s, Key hi value is %s\n", value, map.get("hi"));
    }

    /**
     * ConcurrentMap.replaceAll() method sample
     */
    private void runConcurrentMapSample4(ConcurrentMap<String, String> map) {
        map.replaceAll((key, value) -> key.equals("r2") ? "d3" : value);
        System.out.printf("Key r2 value is %s\n", map.get("r2"));
    }

    /**
     * ConcurrentMap.compute() method sample
     */
    private void runConcurrentMapSample5(ConcurrentMap<String, String> map) {
        map.compute("foo", (key, value) -> value + value);
        System.out.printf("Key foo value is %s\n", map.get("foo"));
    }

    private void runConcurrentMapSample6(ConcurrentMap<String, String> map) {
        map.merge("foo", "boo", (oldValue, newValue) -> newValue + " was " + oldValue);
        System.out.printf("Key foo value is %s\n", map.get("foo"));
    }
}
