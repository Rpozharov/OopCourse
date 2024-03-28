package ru.academits.pozharov.hash_table_main;

import ru.academits.pozharov.hash_table.HashTableDel;

import java.util.Arrays;

public class HashTableMainDel {
    public static void main(String[] args) {
        HashTableDel<Integer> hashTableDel1 = new HashTableDel<>();
        HashTableDel<String> hashTableDel2 = new HashTableDel<>(25);
        HashTableDel<Integer> hashTableDel3 = new HashTableDel<>();
        hashTableDel1.add(5);
        hashTableDel1.add(5);
        hashTableDel1.add(5);
        hashTableDel1.add(7);
        hashTableDel1.add(9);
        hashTableDel1.add(10);
        hashTableDel1.add(null);
        hashTableDel1.add(null);
        hashTableDel1.add(null);

        hashTableDel2.add("test");
        hashTableDel2.add("text");
        hashTableDel2.add("hello");
        hashTableDel2.add(null);
        hashTableDel2.add("may");
        hashTableDel2.add(null);
        hashTableDel2.add(null);

        hashTableDel3.add(2);
        hashTableDel3.add(10);
        hashTableDel3.add(7);
        hashTableDel3.add(7);
        hashTableDel3.add(4);
        hashTableDel3.add(null);
        hashTableDel3.add(null);
        hashTableDel3.add(null);

        System.out.println("hashTable1 = " + hashTableDel1);
        System.out.println("hashTable2 = " + hashTableDel2);
        System.out.println("hashTable3 = " + hashTableDel3);

        System.out.println("Размер списка hashTable1 = " + hashTableDel1.size());

        hashTableDel1.remove(5);
        System.out.println("hashTable1 = " + hashTableDel1);

        HashTableDel<Integer> hashTableDel4 = new HashTableDel<>();
        hashTableDel4.addAll(Arrays.asList(2, 7, 5, 9, 0, 3));

        System.out.println("hashTable4 = " + hashTableDel4);

        hashTableDel1.clear();
        System.out.println("hashTable1 = " + hashTableDel1);

        System.out.println("hashTable2 = " + hashTableDel2);
        hashTableDel2.retainAll(Arrays.asList("text", "may", "Text", null));
        System.out.println("hashTable2 = " + hashTableDel2);

        System.out.println("hashTable3 = " + hashTableDel3);
        hashTableDel3.removeAll(Arrays.asList(null, 7, 5));
        System.out.println("hashTable3 = " + hashTableDel3);

        System.out.println("--------------------");

        hashTableDel1.add(5);
        hashTableDel1.add(5);
        hashTableDel1.add(5);
        hashTableDel1.add(7);

        System.out.println("hashTable1 = " + hashTableDel1);
        System.out.println("hashTable3 = " + hashTableDel3);



    }
}