package ru.academits.pozharov.my_array_list_main;

import ru.academits.pozharov.my_array_list.MyArrayList;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> names = new MyArrayList<>();
        names.add("test0");
        names.add("test1");
        names.add("test2");
        names.add("test3");
        names.add("test4");
        names.add("test5");

        System.out.println(names);
        System.out.println(names.size());

        System.out.println(names.remove(5));
        System.out.println(names);
        System.out.println(names.size());
    }
}
