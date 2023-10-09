package ru.academits.pozharov.person_main;

import ru.academits.pozharov.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PersonMain {
    public static void main(String[] args) {
        List <Person> list = new ArrayList<>(Arrays.asList(new Person("Иван", 29)));

        System.out.println(list);

        List<String> myList1 = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        List <String> myList2 = new LinkedList<>();
        myList2 = Arrays.asList("a1", "a2", "b1", "c2", "c1");
    }
}