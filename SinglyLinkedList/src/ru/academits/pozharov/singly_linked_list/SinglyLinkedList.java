package ru.academits.pozharov.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListNode<E> head;
    private int size;

    public int size() {
        return size;
    }

    public E getFirst() {
        checkIsEmpty();
        return head.getData();
    }

    public E get(int index) {
        checkIndex(index);
        return getNode(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index);

        ListNode<E> node = getNode(index);
        E oldData = node.getData();
        node.setData(data);

        return oldData;
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListNode<E> node = getNode(index - 1);
        E removedData = node.getNext().getData();
        node.setNext(node.getNext().getNext());
        size--;

        return removedData;
    }

    public void addFirst(E data) {
        head = new ListNode<>(data, head);
        size++;
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и <= размеру списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        if (index == 0) {
            head = new ListNode<>(data, head);
        } else {
            ListNode<E> node = getNode(index - 1);
            node.setNext(new ListNode<>(data, node.getNext()));
        }

        size++;
    }

    public boolean removeData(E data) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            head = head.getNext();
            size--;
            return true;
        }

        for (ListNode<E> currentNode = head, previousNode = null; currentNode != null; previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (Objects.equals(data, currentNode.getData())) {
                Objects.requireNonNull(previousNode).setNext(currentNode.getNext());
                size--;
                return true;
            }
        }

        return false;
    }

    public E removeFirst() {
        checkIsEmpty();

        E removedData = head.getData();
        head = head.getNext();
        size--;

        return removedData;
    }

    public void revert() {
        if (size <= 1) {
            return;
        }

        ListNode<E> currentNode = head;
        ListNode<E> previousNode = null;
        ListNode<E> nextNode = currentNode.getNext();

        while (nextNode != null) {
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
            nextNode = currentNode.getNext();
        }

        currentNode.setNext(previousNode);
        head = currentNode;
    }

    public SinglyLinkedList<E> copy() {
        if (size == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> resultList = new SinglyLinkedList<>();
        resultList.head = new ListNode<>(head.getData());
        resultList.size = size;

        ListNode<E> resultNode = resultList.head;

        for (ListNode<E> node = head.getNext(); node != null; node = node.getNext()) {
            resultNode.setNext(new ListNode<>(node.getData(), node.getNext()));
            resultNode = resultNode.getNext();
        }

        return resultList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        if (size == 0) {
            stringBuilder.append('}');
            return stringBuilder.toString();
        }

        for (ListNode<E> node = head; node != null; node = node.getNext()) {
            stringBuilder.append(node.getData()).append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < размера списка: " + size
                    + ". Текущее значение индекса: " + index);
        }
    }

    private void checkIsEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст");
        }
    }

    private ListNode<E> getNode(int index) {
        ListNode<E> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }
}