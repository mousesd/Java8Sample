package net.homenet;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runStaticMethodReference();
        System.out.println();

        app.runInstanceMethodReference();
        System.out.println();

        app.runConstructorReference();
    }

    interface Converter<F, T> {
        T convert(F from);
    }

    private void runStaticMethodReference() {
        Converter<String, Integer> converter = Integer::valueOf;
        System.out.println(converter.convert("123"));
    }

    class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    private void runInstanceMethodReference() {
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        System.out.println(converter.convert("Java"));
    }

    class Person {
        String firstName;
        String lastName;

        @SuppressWarnings("unused")
        Person() { }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
        }
    }

    @FunctionalInterface
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    private void runConstructorReference() {
        PersonFactory<Person> personPersonFactory = Person::new;
        Person person = personPersonFactory.create("Peter", "Parker");
        System.out.println(person);

        //PersonFactory<Person> personPersonFactory = new PersonFactory<Person>() {
        //    @Override
        //    public Person create(String firstName, String lastName) {
        //        return new Person("Peter", "Parker");
        //    }
        //};
    }
}
