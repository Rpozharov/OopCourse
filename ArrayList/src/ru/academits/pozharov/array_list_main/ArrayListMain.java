package ru.academits.pozharov.array_list_main;

import ru.academits.pozharov.array_list.ArrayList;

import java.util.Arrays;
import java.util.LinkedList;

public class ArrayListMain {
    public static void main(String[] args) {
        LinkedList<String> LinkedList1 = new LinkedList<>(Arrays.asList("text", "may", null, "hello", "may", null));
        LinkedList<Integer> LinkedList2 = new LinkedList<>(Arrays.asList(5, 8, 4, null, 4, 4, 10));

        ArrayList<String> arrayList1 = new ArrayList<>(50);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        ArrayList<Integer> arrayList3 = new ArrayList<>();

        arrayList1.addAll(LinkedList1);
        arrayList2.addAll(LinkedList2);
        arrayList3.add(2);
        arrayList3.add(10);
        arrayList3.add(7);
        arrayList3.add(4);

        System.out.println("arrayList1 = " + arrayList1);
        System.out.println("arrayList2 = " + arrayList2);
        System.out.println("arrayList3 = " + arrayList3);
        System.out.println("Размер списка arrayList1 = " + arrayList1.size());

        arrayList1.remove("may");
        System.out.println("arrayList1 = " + arrayList1);

        arrayList1.add(1, "Monday");
        System.out.println("arrayList1 = " + arrayList1);

        arrayList1.remove(2);
        System.out.println("arrayList1 = " + arrayList1);

        System.out.println("Элемент по индексу 2 списка arrayList1 = " + arrayList1.get(3));

        System.out.println("arrayList2 = " + arrayList2);
        ArrayList<Integer> arrayList4 = new ArrayList<>();
        arrayList4.addAll(arrayList2);
        System.out.println("arrayList4 = " + arrayList4);
        System.out.println("arrayList3 = " + arrayList3);
        arrayList2.removeAll(arrayList3);
        System.out.println("arrayList2 = " + arrayList2);
        arrayList4.retainAll(arrayList3);
        System.out.println("arrayList4 = " + arrayList4);

        arrayList4.clear();
        System.out.println("arrayList4 = " + arrayList4);

        arrayList1.ensureCapacity(75);
        arrayList3.trimToSize();
    }
}