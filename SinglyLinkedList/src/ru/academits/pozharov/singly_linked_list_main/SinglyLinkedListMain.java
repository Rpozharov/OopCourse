package ru.academits.pozharov.singly_linked_list_main;

import ru.academits.pozharov.singly_linked_list.SinglyLinkedList;

public class SinglyLinkedListMain {
    public static void main(String[] args) {

        SinglyLinkedList<Integer> numbersList = new SinglyLinkedList<>();
        numbersList.addFirst(7);
        numbersList.addFirst(10);
        numbersList.addFirst(3);
        numbersList.addFirst(5);
        numbersList.addFirst(8);

        System.out.println("Размер списка numbersList = " + numbersList.size());
        System.out.println("Первый элемент списка numbersList = " + numbersList.getFirst());
        System.out.println("numbersList = " + numbersList);
        System.out.println("Элемент списка numbersList по индексу 1 = " + numbersList.get(1));
        System.out.println(numbersList.set(3, 17));
        System.out.println("numbersList = " + numbersList);
        Integer removedElement = numbersList.remove(3);
        System.out.println("Удаленный элемент списка numbersList = " + removedElement);
        System.out.println("numbersList = " + numbersList);
        numbersList.add(3, 23);
        System.out.println("numbersList = " + numbersList);
        System.out.println(numbersList.removeData(23));
        System.out.println("numbersList = " + numbersList);
        numbersList.revert();
        System.out.println("numbersList = " + numbersList);

        SinglyLinkedList<String> stringsList1 = new SinglyLinkedList<>();
        stringsList1.addFirst(null);
        stringsList1.addFirst("test");
        stringsList1.addFirst("text");
        stringsList1.addFirst("May");
        stringsList1.addFirst("Hello");
        System.out.println("stringsList1 = " + stringsList1);
        SinglyLinkedList<String> stringsList2 = stringsList1.copy();
        System.out.println("stringsList2 = " + stringsList2);
    }
}