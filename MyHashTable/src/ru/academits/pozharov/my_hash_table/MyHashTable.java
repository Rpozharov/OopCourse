package ru.academits.pozharov.my_hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {

    private ArrayList<E>[] items;
    private int size;

    public MyHashTable() {
        //noinspection unchecked
        items = new ArrayList[10];
    }

    public MyHashTable(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть >= 0, текущее значение: " + initialCapacity);
        }

        //noinspection unchecked
        items = new ArrayList[initialCapacity];
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
            if (items[0] == null) {
                return false;
            }

            return items[0].contains(null);
        }

        int index = calculateIndex(o);

        if (items[index] == null) {
            return false;
        }

        return items[index].contains(o);
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int currentIndex = -1;

        Object[] array = convertMyHashTableToArray();

        @Override
        public boolean hasNext() {
            return (currentIndex + 1) < array.length;
        }

        @Override
        public E next() {
            currentIndex++;
            //noinspection unchecked
            return (E) array[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        return convertMyHashTableToArray();
    }

    @Override
    public boolean add(Object o) {
        if (o == null) {
            if (items[0] == null) {
                items[0] = new ArrayList<>();
                items[0].add(null);
            } else {
                items[0].add(null);
            }

            size++;
            return true;
        }

        int index = calculateIndex(o);
        if (items[index] == null) {
            items[index] = new ArrayList<>();
            //noinspection unchecked
            items[index].add((E) o);
        } else {
            //noinspection unchecked
            items[index].add((E) o);
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            if (items[0] == null) {
                return false;
            }

            if (items[0].remove(null)) {
                size--;
                return true;
            }

            return false;
        }

        int index = calculateIndex(o);

        if (items[index] == null) {
            return false;
        }

        if (items[index].remove(o)) {
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }

        Object[] array = c.toArray();

        for (Object o : array) {
            add(o);
        }

        return true;
    }

    @Override
    public void clear() {
        size = 0;
        //noinspection unchecked
        items = new ArrayList[items.length];

    }

    @Override
    public boolean retainAll(Collection c) {
        MyHashTable<Object> myHashTable = new MyHashTable<>();
        myHashTable.addAll(this);
        myHashTable.removeAll(c);
        return removeAll(myHashTable);
    }

    @Override
    public boolean removeAll(Collection c) {
        Object[] array = c.toArray();
        boolean isRemove = false;

        for (int i = 0; i < array.length; i++) {
            if (remove(array[i])) {
                i--;
                isRemove = true;
            }
        }

        return isRemove;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray(Object[] a) {
        Object[] array = convertMyHashTableToArray();

        if (a.length < size) {
            return array;
        }

        System.arraycopy(array, 0, a, 0, size);
        return a;
    }

    @Override
    public String toString() {
        return Arrays.toString(convertMyHashTableToArray());
    }

    private Object[] convertMyHashTableToArray() {
        Object[] resultArray = new Object[size];
        int j = 0;
        for (ArrayList<E> item : items) {
            if (item != null) {
                for (E element : item) {
                    resultArray[j] = element;
                    j++;
                }
            }
        }

        return resultArray;
    }

    private int calculateIndex(Object o) {
        return Math.abs(o.hashCode() % items.length);
    }
}