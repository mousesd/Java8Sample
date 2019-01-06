package net.homenet;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Max", 18)
            , new Person("Peter", 23)
            , new Person("Pamela", 23)
            , new Person("David", 12));

        Main app = new Main();
        app.runCollectSample1(persons);
        System.out.println();

        app.runCollectSample2(persons);
        System.out.println();

        app.runCollectSample3(persons);
        System.out.println();

        app.runCollectSample4(persons);
        System.out.println();

        app.runCollectSample5(persons);
        System.out.println();

        app.runCollectSample6(persons);
        System.out.println();

        app.runCollectSample7(persons);
    }

    /**
     * Collectors.toList() method sample
     */
    private void runCollectSample1(List<Person> persons) {
        List<Person> filtered = persons.stream()
            .filter(person -> person.getName().startsWith("P"))
            .collect(Collectors.toList());

        System.out.println(filtered);
    }

    /**
     * Collectors.groupingBy() method sample
     */
    private void runCollectSample2(List<Person> persons) {
        //# 1.
        //Map<Integer, List<Person>> personsByAge = persons.stream()
        //    .collect(Collectors.groupingBy(Person::getAge));
        //
        //personsByAge.forEach((key, value) -> System.out.printf("age %s: %s\n", key, value));

        //# 2.
        persons.stream()
            .collect(Collectors.groupingBy(Person::getAge))
            .forEach((key, value) -> System.out.printf("age %s: %s\n", key, value));
    }

    /**
     * Collectors.averagingInt() method sample
     */
    private void runCollectSample3(List<Person> persons) {
        Double averageAge = persons.stream()
            .collect(Collectors.averagingInt(Person::getAge));
        System.out.println(averageAge);
    }

    /**
     * Collectors.summarizingInt() method sample
     */
    private void runCollectSample4(List<Person> persons) {
        IntSummaryStatistics ageSummary = persons.stream()
            .collect(Collectors.summarizingInt(Person::getAge));
        System.out.println(ageSummary);
    }

    /**
     * Collectors.joining() method sample
     */
    private void runCollectSample5(List<Person> persons) {
        String phrase = persons.stream()
            .filter(person -> person.getAge() >= 18)
            .map(Person::getName)
            .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);
    }

    /**
     * Collectors.toMap() method sample
     */
    private void runCollectSample6(List<Person> persons) {
        Map<Integer, String> map = persons.stream()
            .collect(Collectors.toMap(Person::getAge
                , Person::getName
                , (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
    }

    /**
     * Collector.of() method sample
     */
    private void runCollectSample7(List<Person> persons) {
        Collector<Person, StringJoiner, String> personNameCollector = Collector.of(
            () -> new StringJoiner(" | ")                                       // supplier
            , (joiner, person) -> joiner.add(person.getName().toUpperCase())    // accumulator
            , StringJoiner::merge                                               // combiner
            , StringJoiner::toString);                                          // finisher

        String names = persons.stream()
            .collect(personNameCollector);
        System.out.println(names);
    }
}
