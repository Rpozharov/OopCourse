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
        return getNodeOfIndex(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index);

        ListNode<E> Node = getNodeOfIndex(index);
        E oldData = Node.getData();
        Node.setData(data);
        return oldData;
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListNode<E> node = getNodeOfIndex(index - 1);
        E oldData = node.getNext().getData();
        node.setNext(node.getNext().getNext());
        size--;
        return oldData;
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
            ListNode<E> node = getNodeOfIndex(index - 1);
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

        for (ListNode<E> currentNode = head, prevNode = null; currentNode != null; prevNode = currentNode, currentNode = currentNode.getNext()) {
            if (Objects.equals(data, currentNode.getData())) {
                if (prevNode != null) {
                    prevNode.setNext(currentNode.getNext());
                }

                size--;
                return true;
            }
        }

        return false;
    }

    public E removeFirst() {
        checkIsEmpty();
        E oldData = head.getData();
        head = head.getNext();
        size--;
        return oldData;
    }

    public void revert() {
        if (size <= 1) {
            return;
        }

        ListNode<E> currentNode = head;
        ListNode<E> prevNode = null;
        ListNode<E> nextNode = currentNode.getNext();

        while (nextNode != null) {
            currentNode.setNext(prevNode);
            prevNode = currentNode;
            currentNode = nextNode;
            nextNode = currentNode.getNext();
        }

        currentNode.setNext(prevNode);
        head = currentNode;
    }

    public SinglyLinkedList<E> copy() {
        if (size == 0) {
            return new SinglyLinkedList<>();
        }

        ListNode<E> node = head;
        SinglyLinkedList<E> resultList = new SinglyLinkedList<>();
        resultList.head = new ListNode<>(node.getData());
        ListNode<E> resultNode = resultList.head;
        node = node.getNext();

        while (node != null) {
            resultNode.setNext(new ListNode<>(node.getData()));
            node = node.getNext();
            resultNode = resultNode.getNext();
        }

        resultList.size = size;
        return resultList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (ListNode<E> Node = head; Node != null; Node = Node.getNext()) {
            stringBuilder.append(Node.getData()).append(", ");
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

    private ListNode<E> getNodeOfIndex(int index) {
        ListNode<E> Node = head;

        for (int i = 0; i != index; i++) {
            Node = Node.getNext();
        }

        return Node;
    }
}