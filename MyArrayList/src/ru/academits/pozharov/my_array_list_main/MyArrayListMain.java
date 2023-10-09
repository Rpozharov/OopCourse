package ru.academits.pozharov.my_array_list_main;

import ru.academits.pozharov.my_array_list.MyArrayList;

import java.util.Arrays;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> names1 = new MyArrayList<>();
        MyArrayList<String> names2 = new MyArrayList<>();
        names1.add("text1");
        names1.add("text2");
        names1.add("text3");
        names1.add("text4");
        names1.add("text5");
        names1.add("text1");
        names1.add("text2");
        names1.add("text3");
        names1.add("text4");
        names1.add("text5");
        names1.add("text1");
        names1.add("text2");
        names1.add("text3");
        names1.add("text4");
        names1.add("text5");

        System.out.println(names1);

        System.out.println(names1.remove("text4"));

        names1.remove("text4");

        System.out.println(names1);

        String [] array1 = {"hello","text", "may", "name"};
        String [] array2 = new String[2];
        int [] array3 = new int[5];

        names2.add("Monday");
        names2.add("Tuesday");
        names2.add("Wednesday");
        names2.add("Thursday");
        names2.add("Friday");
        names2.add("Saturday");
        names2.add("Sunday");

        System.out.println(names2.size());

        System.out.println(Arrays.toString(names1.toArray(array2)));






    }
}
