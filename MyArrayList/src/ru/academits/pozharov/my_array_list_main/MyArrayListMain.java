package ru.academits.pozharov.my_array_list_main;

import ru.academits.pozharov.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayListMain {
    public static void main(String[] args) {
        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList("text", "may", null, "hello", "may", null));
        ArrayList<Integer> arrayList2 = new ArrayList<>(Arrays.asList(5, 8, 4, null, 4, 4, 10));

        MyArrayList<String> myArrayList1 = new MyArrayList<>(50);
        MyArrayList<Integer> myArrayList2 = new MyArrayList<>();
        MyArrayList<Integer> myArrayList3 = new MyArrayList<>();

        myArrayList1.addAll(arrayList1);
        myArrayList2.addAll(arrayList2);
        myArrayList3.add(2);
        myArrayList3.add(10);
        myArrayList3.add(7);
        myArrayList3.add(4);

        System.out.println("myArrayList1 = " + myArrayList1);
        System.out.println("myArrayList2 = " + myArrayList2);
        System.out.println("myArrayList3 = " + myArrayList3);
        System.out.println("Размер списка myArrayList1 = " + myArrayList1.size());

        myArrayList1.remove("may");
        System.out.println("myArrayList1 = " + myArrayList1);

        myArrayList1.add(1, "Monday");
        System.out.println("myArrayList1 = " + myArrayList1);

        myArrayList1.remove(2);
        System.out.println("myArrayList1 = " + myArrayList1);

        System.out.println("Элемент по индексу 2 списка myArrayList1 = " + myArrayList1.get(3));

        System.out.println("myArrayList2 = " + myArrayList2);
        MyArrayList<Integer> myArrayList4 = new MyArrayList<>();
        myArrayList4.addAll(myArrayList2);
        System.out.println("myArrayList4 = " + myArrayList4);
        System.out.println("myArrayList3 = " + myArrayList3);
        myArrayList2.removeAll(myArrayList3);
        System.out.println("myArrayList2 = " + myArrayList2);
        myArrayList4.retainAll(myArrayList3);
        System.out.println("myArrayList4 = " + myArrayList4);

        myArrayList4.clear();
        System.out.println("myArrayList4 = " + myArrayList4);

        myArrayList1.ensureCapacity(75);
        myArrayList3.trimToSize();
    }
}