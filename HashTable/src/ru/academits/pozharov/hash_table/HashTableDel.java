package ru.academits.pozharov.hash_table;

import java.util.*;

public class HashTableDel<E> implements Collection<E> {
    private ArrayList<E>[] lists;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public HashTableDel() {
        //noinspection unchecked
        lists = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTableDel(int initialCapacity) {
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
        return convertMyHashTableToArray();
    }

    @Override
    public boolean add(Object o) {
        int index = GetIndex(o);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        //noinspection unchecked
        lists[index].add((E) o);

        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            if (lists[0] == null) {
                return false;
            }

            if (lists[0].remove(null)) {
                size--;
                return true;
            }

            return false;
        }

        int index = GetIndex(o);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(o)) {
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
        lists = new ArrayList[lists.length];

    }

    @Override
    public boolean retainAll(Collection c) {
        HashTableDel<Object> hashTableDel = new HashTableDel<>();
        hashTableDel.addAll(this);
        hashTableDel.removeAll(c);
        return removeAll(hashTableDel);
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
    public Object[] toArray(Object[] array) {
        Object[] resultArray = convertMyHashTableToArray();

        if (resultArray.length < size) {
            return resultArray;
        }

        System.arraycopy(resultArray, 0, resultArray, 0, size);
        return resultArray;
    }

    @Override
    public String toString() {
        if (size == 0) {
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

    private Object[] convertMyHashTableToArray() {
        Object[] resultArray = new Object[size];
        int j = 0;
        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    resultArray[j] = element;
                    j++;
                }
            }
        }

        return resultArray;
    }

    private int GetIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }
}