package ru.academits.pozharov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] lists;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public HashTable() {
        //noinspection unchecked
        lists = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть > 0, текущее значение: " + initialCapacity);
        }

        //noinspection unchecked
        lists = new ArrayList[initialCapacity];
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
        int index = GetIndex(o);

        if (lists[index] == null) {
            return false;
        }

        return lists[index].contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int arrayCurrentIndex = 0;
        private int listCurrentIndex = -1;
        private int count;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементы в коллекции закончились.");
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("В коллекции были добавлены/удалены элементы.");
            }

            while (arrayCurrentIndex < lists.length) {
                if (lists[arrayCurrentIndex] != null) {
                    listCurrentIndex++;

                    //noinspection LoopStatementThatDoesntLoop
                    while (listCurrentIndex < lists[arrayCurrentIndex].size()) {
                        count++;
                        return lists[arrayCurrentIndex].get(listCurrentIndex);
                    }
                }

                arrayCurrentIndex++;
                listCurrentIndex = -1;
            }

            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[size];
        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    resultArray[i] = element;
                    i++;
                }
            }
        }

        return resultArray;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        //noinspection unchecked
        T[] resultArray = (T[]) toArray();

        if (array.length < size) {
            return resultArray;
        }

        System.arraycopy(resultArray, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E e) {
        int index = GetIndex(e);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(e);

        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = GetIndex(o);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(o)) {
            size--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        boolean isRemove = false;

        for (Object element: c) {
            while (contains(element)){
                isRemove = remove(element);
            }
        }

        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    stringBuilder.append(element).append(", ");
                }
            }
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private int GetIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }
}