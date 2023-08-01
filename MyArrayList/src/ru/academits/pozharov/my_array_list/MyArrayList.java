package ru.academits.pozharov.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new NegativeArraySizeException("Вместимость должна быть >= 0, текущее значение: " + initialCapacity);
        }
        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return true;
                }
            }

            return false;
        }

        for (int i = 0; i < size; i++)
            if (o.equals(items[i])) {
                return true;
            }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        //noinspection unchecked
        return (T[]) Arrays.copyOf(items, size, array.getClass());
    }

    @Override
    public boolean add(E element) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < размера списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < размера списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        return items[index] = element;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < размера списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        E removedElement = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items [size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, size));
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }
}