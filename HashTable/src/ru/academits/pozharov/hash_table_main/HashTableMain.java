package ru.academits.pozharov.hash_table_main;

import ru.academits.pozharov.hash_table.HashTable;

import java.util.Arrays;

public class HashTableMain {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>();
        HashTable<String> hashTable2 = new HashTable<>(25);
        HashTable<Integer> hashTable3 = new HashTable<>();
        hashTable1.add(5);
        hashTable1.add(7);
        hashTable1.add(3);
        hashTable1.add(3);
        hashTable1.add(3);
        hashTable1.add(9);
        hashTable1.add(10);
        hashTable1.add(12);
        hashTable1.add(null);
        hashTable1.add(null);

        hashTable2.add("test");
        hashTable2.add("text");
        hashTable2.add("hello");
        hashTable2.add(null);
        hashTable2.add("may");
        hashTable2.add(null);
        hashTable2.add(null);

        hashTable3.add(10);
        hashTable3.add(2);
        hashTable3.add(7);
        hashTable3.add(7);
        hashTable3.add(4);
        hashTable3.add(null);
        hashTable3.add(null);
        hashTable3.add(null);

        System.out.println("hashTable1 = " + hashTable1);
        System.out.println("hashTable2 = " + hashTable2);
        System.out.println("hashTable3 = " + hashTable3);

        System.out.println("Размер списка hashTable1 = " + hashTable1.size());

        hashTable1.remove(5);
        System.out.println("hashTable1 = " + hashTable1);

        HashTable<Integer> hashTable4 = new HashTable<>();
        hashTable4.addAll(Arrays.asList(2, 7, 5, 9, 0, 3));

        System.out.println("hashTable4 = " + hashTable4);

        hashTable1.clear();
        System.out.println("hashTable1 = " + hashTable1);

        System.out.println("hashTable2 = " + hashTable2);
        hashTable2.retainAll(Arrays.asList("text", "may", "Text", null));
        System.out.println("hashTable2 = " + hashTable2);

        System.out.println("hashTable3 = " + hashTable3);
        hashTable3.removeAll(Arrays.asList(null, 7, 5));
        System.out.println("hashTable3 = " + hashTable3);
    }
}