package ru.academits.pozharov.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class PersonMain {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
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
        );

        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.println("Список уникальных имен: " + uniqueNamesList);

        System.out.println(uniqueNamesList.stream().collect(Collectors.joining(", ", "Имена: ", ".")));

        List<Person> personsUnder18Years = persons.stream()
                .filter(p -> p.getAge() < 18)
                .toList();
        System.out.println("Список людей младше 18 = " + personsUnder18Years);

        OptionalDouble averageAge = personsUnder18Years.stream()
                .mapToInt(Person::getAge)
                .average();

        if (averageAge.isPresent()) {
            System.out.println("Средний возраст людей младше 18 = " + averageAge.getAsDouble());
        }

        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("средний возраст по именам = " + averageAgeByName);

        List<Person> personsOf20To45Years = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .toList();
        System.out.println("Список людей, возраст которых от 20 до 45 = " + personsOf20To45Years);

        String personsOf20To45YearsNames = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((person1, person2) -> Integer.compare(person2.getAge(), person1.getAge()))
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Имена людей в порядке убывания возраста, от 20 до 45 = ", "."));
        System.out.println(personsOf20To45YearsNames);
    }
}