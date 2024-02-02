package ru.academits.pozharov.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount = 0;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть >= 0, текущее значение: " + initialCapacity);
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

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return (currentIndex + 1) < size;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if ((currentIndex + 1) >= size) {
                throw new NoSuchElementException();
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);
        return array;
    }

    @Override
    public boolean add(E element) {

        modCount++;

        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    removeElement(i);
                    return true;
                }
            }

            return false;
        }

        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])) {
                removeElement(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }

        modCount++;
        int freeElements = items.length - size;

        if (freeElements < c.size()) {
            items = Arrays.copyOf(items, items.length + (c.size() - freeElements));
        }

        //noinspection unchecked
        E[] array = c.toArray((E[]) new Object[c.size()]);
        System.arraycopy(array, 0, items, size, array.length);
        size += c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и <= размеру списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        if (c.size() == 0) {
            return false;
        }

        modCount++;
        //noinspection unchecked
        E[] array = c.toArray((E[]) new Object[c.size()]);
        int destinationStartIndex = index + array.length;
        int copiedElementsCount = size - index;
        int arrayLength = destinationStartIndex + copiedElementsCount;

        if (items.length < arrayLength) {
            items = Arrays.copyOf(items, arrayLength);
        }

        if (index != size) {
            System.arraycopy(items, index, items, destinationStartIndex, copiedElementsCount);
        }

        System.arraycopy(array, 0, items, index, array.length);
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
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
    public boolean retainAll(Collection<?> c) {
        MyArrayList<Object> myArrayList = new MyArrayList<>();
        myArrayList.addAll(this);
        myArrayList.removeAll(c);
        return removeAll(myArrayList);
    }

    @Override
    public void clear() {
        modCount++;
        //noinspection unchecked
        items = (E[]) new Object[items.length];
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = items[index];
        items[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и <= размеру списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        modCount++;

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = items[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }

            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (items[i] == null) {
                    return i;
                }
            }

            return -1;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
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

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            if (minCapacity > items.length * 2) {
                items = Arrays.copyOf(items, minCapacity);
                return;
            }

            increaseCapacity();
        }
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, size));
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private void removeElement(int index) {
        modCount++;

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < размера списка: " + size
                    + ". Текущее значение индекса: " + index);
        }
    }
}