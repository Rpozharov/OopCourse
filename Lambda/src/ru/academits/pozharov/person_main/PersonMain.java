package ru.academits.pozharov.person_main;

import ru.academits.pozharov.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class PersonMain {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Иван", 27),
                new Person("Сергей", 14),
                new Person("Марина", 18),
                new Person("Иван", 25),
                new Person("Анна", 18),
                new Person("Анна", 16),
                new Person("Анна", 23),
                new Person("Александр", 11),
                new Person("Мария", 35),
                new Person("Максим", 15),
                new Person("Денис", 45),
                new Person("Свелана", 20)
        ));

        String uniqueNamesString = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));
        System.out.println("Имена: " + uniqueNamesString);

        OptionalDouble averageAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average();
        System.out.println(averageAge);

        Map<String, Double> personsByAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(personsByAge);

        String personsString = persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((person1, person2) -> Integer.compare(person2.getAge(), person1.getAge()))
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Имена: " + personsString);
    }
}