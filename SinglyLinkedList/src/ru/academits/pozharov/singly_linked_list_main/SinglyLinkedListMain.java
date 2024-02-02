package ru.academits.pozharov.singly_linked_list_main;

import ru.academits.pozharov.singly_linked_list.SinglyLinkedList;

public class SinglyLinkedListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList1 = new SinglyLinkedList<>();
        singlyLinkedList1.addElementToStart(null);
        singlyLinkedList1.addElementToStart(10);
        singlyLinkedList1.addElementToStart(3);
        singlyLinkedList1.addElementToStart(5);
        singlyLinkedList1.addElementToStart(8);

        System.out.println("Размер списка singlyLinkedList1 = " + singlyLinkedList1.size());
        System.out.println("Первый элемент списка singlyLinkedList1 = " + singlyLinkedList1.getFirstElement());
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);
        System.out.println("Элемент списка singlyLinkedList1 по индексу 1 = " + singlyLinkedList1.getElementOfIndex(1));
        System.out.println(singlyLinkedList1.setElementOfIndex(3, 17));
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);
        singlyLinkedList1.addElementOfIndex(4, 23);
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);
        System.out.println(singlyLinkedList1.removeFirstElement());
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);
        Integer removedElement = singlyLinkedList1.removeElementOfIndex(3);
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);
        System.out.println("Удаленный элемент списка singlyLinkedList1 = " + removedElement);
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);
        singlyLinkedList1.revert();
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);
        System.out.println(singlyLinkedList1.removeElement(5));
        System.out.println("singlyLinkedList1 = " + singlyLinkedList1);

        SinglyLinkedList<String> singlyLinkedList2 = new SinglyLinkedList<>();
        singlyLinkedList2.addElementToStart(null);
        singlyLinkedList2.addElementToStart("test");
        singlyLinkedList2.addElementToStart("text");
        singlyLinkedList2.addElementToStart("May");
        singlyLinkedList2.addElementToStart("Hello");

        SinglyLinkedList<String> singlyLinkedList3 = new SinglyLinkedList<>();
        System.out.println("singlyLinkedList2 = " + singlyLinkedList2);
        System.out.println("singlyLinkedList3 = " + singlyLinkedList3);
        singlyLinkedList3 = singlyLinkedList2.copy();
        System.out.println("singlyLinkedList3 = " + singlyLinkedList3);
    }
}