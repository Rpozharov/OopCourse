package ru.academits.pozharov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] listsArray;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public HashTable() {
        //noinspection unchecked
        listsArray = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть > 0, текущее значение: " + capacity);
        }

        //noinspection unchecked
        listsArray = new ArrayList[capacity];
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
        int index = getIndex(o);

        return listsArray[index] != null && listsArray[index].contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int arrayIndex;
        private int listIndex = -1;
        private int issuedElementsCount;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return issuedElementsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементы в коллекции закончились.");
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("В коллекции были добавлены/удалены элементы.");
            }

            listIndex++;

            while (listsArray[arrayIndex] == null || listIndex >= listsArray[arrayIndex].size()) {
                arrayIndex++;
                listIndex = 0;
            }

            issuedElementsCount++;
            return listsArray[arrayIndex].get(listIndex);
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

        for (ArrayList<E> list : listsArray) {
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
            //noinspection unchecked
            return (T[]) Arrays.copyOf(resultArray, size, array.getClass());
        }

        System.arraycopy(resultArray, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (listsArray[index] == null) {
            listsArray[index] = new ArrayList<>();
        }

        listsArray[index].add(e);

        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (listsArray[index] == null) {
            return false;
        }

        if (listsArray[index].remove(o)) {
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

        boolean isRemoved = false;

        for (Object element : c) {
            while (remove(element)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int resultSize = 0;
        boolean isRemoved = false;

        for (ArrayList<E> list : listsArray) {
            if (list != null) {
                if (list.retainAll(c)) {
                    isRemoved = true;
                }

                resultSize += list.size();
            }
        }

        size = resultSize;

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        Arrays.fill(listsArray, null);

        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (ArrayList<E> list : listsArray) {
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

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % listsArray.length);
    }
}