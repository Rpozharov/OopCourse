package ru.academits.pozharov.my_array_list_main;

import ru.academits.pozharov.my_array_list.MyArrayList;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> names1 = new MyArrayList<>();
        MyArrayList<String> names2 = new MyArrayList<>(5);
        names1.add("test0");
        names1.add("test1");
        names1.add("test2");
        names1.add("test3");
        names1.add("test4");
        names1.add("test5");

        System.out.println(names1);
        System.out.println(names1.size());

        System.out.println(names1.remove(5));
        System.out.println(names1);
        System.out.println(names1.size());
    }
}
