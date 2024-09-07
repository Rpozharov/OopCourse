package ru.academits.pozharov.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<E extends Comparable<E>> {
    private Node<E> root;
    private int count;

    public int elementsCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void add(E data) {
        if (root == null) {
            root = new Node<>(data);
            count++;
            return;
        }

        Node<E> currentNode = root;

        while (true) {
            if (data.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new Node<>(data));
                    count++;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode = currentNode.getRight();
                    currentNode.setRight(new Node<>(data));
                    count++;
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        Node<E> currentNode = root;

        Queue<E> queue = new LinkedList<>();

        queue.add (currentNode.getData());

        while (true) {

        }







    }
}