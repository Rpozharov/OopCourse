package ru.academits.pozharov.my_hash_table_main;

import ru.academits.pozharov.my_hash_table.MyHashTable;

import java.util.Arrays;

public class MyHashTableMain {
    public static void main(String[] args) {
        MyHashTable<Integer> myHashTable1 = new MyHashTable<>();
        MyHashTable<String> myHashTable2 = new MyHashTable<>(25);
        MyHashTable<Integer> myHashTable3 = new MyHashTable<>();
        myHashTable1.add(5);
        myHashTable1.add(5);
        myHashTable1.add(5);
        myHashTable1.add(7);
        myHashTable1.add(9);
        myHashTable1.add(10);
        myHashTable1.add(null);
        myHashTable1.add(null);
        myHashTable1.add(null);

        myHashTable2.add("test");
        myHashTable2.add("text");
        myHashTable2.add("hello");
        myHashTable2.add(null);
        myHashTable2.add("may");
        myHashTable2.add(null);
        myHashTable2.add(null);

        myHashTable3.add(2);
        myHashTable3.add(10);
        myHashTable3.add(7);
        myHashTable3.add(7);
        myHashTable3.add(4);
        myHashTable3.add(null);
        myHashTable3.add(null);
        myHashTable3.add(null);

        System.out.println("myHashTable1 = " + myHashTable1);
        System.out.println("myHashTable2 = " + myHashTable2);
        System.out.println("myHashTable3 = " + myHashTable3);

        System.out.println("Размер списка myHashTable1 = " + myHashTable1.size());

        myHashTable1.remove(5);
        System.out.println("myHashTable1 = " + myHashTable1);

        MyHashTable<Integer> myHashTable4 = new MyHashTable<>();
        myHashTable4.addAll(Arrays.asList(2, 7, 5, 9, 0, 3));

        System.out.println("myHashTable4 = " + myHashTable4);

        myHashTable1.clear();
        System.out.println("myHashTable1 = " + myHashTable1);

        System.out.println("myHashTable2 = " + myHashTable2);
        myHashTable2.retainAll(Arrays.asList("text", "may", "Text", null));
        System.out.println("myHashTable2 = " + myHashTable2);

        System.out.println("myHashTable3 = " + myHashTable3);
        myHashTable3.removeAll(Arrays.asList(null, 7, 5));
        System.out.println("myHashTable3 = " + myHashTable3);
    }
}