package net.homenet;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Max", 18)
            , new Person("Peter", 23)
            , new Person("Pamela", 23)
            , new Person("David", 12));

        Main app = new Main();
        app.runReduceSample1(persons);
        System.out.println();

        app.runReduceSample2(persons);
        System.out.println();

        app.runReduceSample3(persons);
        System.out.println();

        app.runReduceSample4(persons);
    }

    /**
     * reduce() method sample1
     */
    private void runReduceSample1(List<Person> persons) {
        persons.stream()
            .reduce((person1, person2) -> person1.getAge() > person2.getAge() ? person1 : person2)
            .ifPresent(System.out::println);
    }

    /**
     * reduce() method sample2
     */
    private void runReduceSample2(List<Person> persons) {
        Person newPerson = persons.stream()
            .reduce(new Person("", 0), (person1, person2) -> {
                person1.setAge(person1.getAge() + person2.getAge());
                person1.setName(person1.getName() + person2.getName());
                return person1;
            });

        System.out.printf("name: %s, age: %s\n", newPerson.getName(), newPerson.getAge());
    }

    /**
     * reduce() method sample3
     */
    private void runReduceSample3(List<Person> persons) {
        //Integer ageSum = persons.stream()
        //    .reduce(0
        //        , (sum, person) -> sum += person.getAge()
        //        , (sum1, sum2) -> sum1 + sum2);

        Integer ageSum = persons.stream()
            .reduce(0
                , (sum, person) -> {
                    System.out.printf("accumulator: sum=%s; person=%s\n", sum, person);
                    return sum += person.getAge();
                }
                , (sum1, sum2) -> {
                    System.out.printf("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                    return sum1 + sum2;
                });

        System.out.println(ageSum);
    }

    /**
     * reduce() method sample4
     */
    private void runReduceSample4(List<Person> persons) {
        Integer ageSum = persons.parallelStream()
            .reduce(0
                , (sum, person) -> {
                    System.out.printf("accumulator: sum=%s; person=%s\n", sum, person);
                    return sum += person.getAge();
                }
                , (sum1, sum2) -> {
                    System.out.printf("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                    return sum1 + sum2;
                });

        System.out.println(ageSum);
    }
}







































