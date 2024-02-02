package ru.academits.pozharov.singly_linked_list;

import java.util.Arrays;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public int size() {
        return count;
    }

    public Object getFirstElement() {
        checkOnEmpty();
        return head.getData();
    }

    public E getElementOfIndex(int index) {
        checkIndex(index);
        return getNodeOfIndex(index).getData();
    }

    public E setElementOfIndex(int index, E element) {
        checkIndex(index);
        ListItem<E> p = getNodeOfIndex(index);
        E removedElement = p.getData();
        p.setData(element);
        return removedElement;
    }

    public E removeElementOfIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirstElement();
        }

        ListItem<E> p = getNodeOfIndex(index - 1);
        E removedElement = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        count--;
        return removedElement;
    }

    public void addElementToStart(E element) {
        head = new ListItem<>(element, head);
        count++;
    }

    public void addElementOfIndex(int index, E element) {
        checkIndex(index);

        if (index == 0) {
            head = new ListItem<>(element, head);
        } else {
            ListItem<E> p = getNodeOfIndex(index - 1);
            p.setNext(new ListItem<>(element, p.getNext()));
        }

        count++;
    }

    public boolean removeElement(E element) {
        if (count == 0) {
            return false;
        }

        int i = 0;
        ListItem<E> p = head;
        boolean isRemoved = false;

        if (element == null) {
            while (p.getNext() != null) {
                if (p.getData() == null) {
                    removeElementOfIndex(i);
                    isRemoved = true;
                    break;
                }

                p = p.getNext();
                i++;
            }

        } else {
            while (p != null) {
                if (element.equals(p.getData())) {
                    removeElementOfIndex(i);
                    isRemoved = true;
                    break;
                }

                p = p.getNext();
                i++;
            }
        }

        return isRemoved;
    }

    public E removeFirstElement() {
        checkOnEmpty();
        E removedElement = head.getData();
        head = head.getNext();
        count--;
        return removedElement;
    }

    public void revert() {
        if (count <= 1) {
            return;
        }

        ListItem<E> p = head;
        ListItem<E> lastNode = head;
        p = p.getNext();

        while (p != null) {
            head = new ListItem<>(p.getData(), head);
            p = p.getNext();
        }

        lastNode.setNext(null);
    }

    public SinglyLinkedList<E> copy() {
        if (count == 0) {
            return new SinglyLinkedList<>();
        }

        ListItem<E> p = head;
        SinglyLinkedList<E> resultList = new SinglyLinkedList<>();
        resultList.head = new ListItem<>(p.getData());
        ListItem<E> pNew = resultList.head;
        resultList.count++;
        p = p.getNext();

        while (p != null) {
            pNew.setNext(new ListItem<>(p.getData()));
            p = p.getNext();
            pNew = pNew.getNext();
            resultList.count++;
        }

        return resultList;
    }

    @Override
    public String toString() {
        Object[] elements = new Object[count];
        int index = 0;

        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            elements[index] = p.getData();
            index++;
        }

        return Arrays.toString(elements);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < размера списка: " + count
                    + ". Текущее значение индекса: " + index);
        }
    }

    private void checkOnEmpty() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("Список не должен быть пустым");
        }
    }

    private ListItem<E> getNodeOfIndex(int index) {
        int i = 0;
        ListItem<E> p = head;

        while (p.getNext() != null) {
            if (i == index) {
                break;
            }

            p = p.getNext();
            i++;
        }
        return p;
    }
}