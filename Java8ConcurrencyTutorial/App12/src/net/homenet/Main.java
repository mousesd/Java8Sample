package net.homenet;

import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        Main app = new Main();
        app.runConcurrentHashMapSample1(map);
        System.out.println();

        app.runConcurrentHashMapSample2(map);
        System.out.println();

        app.runConcurrentHashMapSample3(map);
        System.out.println();

        app.runConcurrentHashMapSample4(map);
    }

    /**
     * ConcurrentHashMap.forEach() method sample
     */
    private void runConcurrentHashMapSample1(ConcurrentHashMap<String, String> map) {
        map.forEach(1, (key, value) ->
            System.out.printf("Key: %s; Value: %s; Thread: %s\n", key, value, Thread.currentThread().getName()));
    }

    /**
     * ConcurrentHashMap.search() method sample
     */
    private void runConcurrentHashMapSample2(ConcurrentHashMap<String, String> map) {
        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if (key.equals("foo")) {
                return value;
            }
            return null;
        });
        System.out.println("Result: " + result);
    }

    /**
     * ConcurrentHashMap.searchValue() method sample
     */
    private void runConcurrentHashMapSample3(ConcurrentHashMap<String, String> map) {
        String result = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });
        System.out.println("Result: " + result);
    }

    /**
     * ConcurrentHashMap.reduce() method sample
     */
    private void runConcurrentHashMapSample4(ConcurrentHashMap<String, String> map) {
        String result = map.reduce(1
            , (key, value) -> {
                System.out.println("Transform: " + Thread.currentThread().getName());
                return key + "=" + value;
            }
            , (first, second) -> {
                System.out.println("Reduce: " + Thread.currentThread().getName());
                return first + ", " + second;
            });
        System.out.println("Result: " + result);
    }
}
