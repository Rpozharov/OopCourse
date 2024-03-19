package ru.academits.pozharov.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] elements;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть >= 0, текущее значение: " + initialCapacity);
        }

        //noinspection unchecked
        elements = (E[]) new Object[initialCapacity];
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
        return indexOf(o) >= 0;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("В коллекции были добавлены/удалены элементы.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Элементы в коллекции закончились.");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) < 0) {
            return false;
        }

        removeElement(indexOf(o));
        return true;
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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и <= размеру списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size());

        if (index != size) {
            System.arraycopy(elements, index, elements, index + c.size(), size - index);
        }

        for (E element : c) {
            elements[index] = element;
            index++;
        }

        size += c.size();
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemove = false;

        for (Object element : c) {
            while (indexOf(element) >= 0) {
                isRemove = remove(element);
            }
        }

        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemove = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                isRemove = remove(elements[i]);
            }
        }

        return isRemove;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и <= размеру списка: " + size
                    + ". Текущее значение индекса: " + index);
        }

        if (size >= elements.length) {
            increaseCapacity();
        }

        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = elements[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, elements[i])) {
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
        if (minCapacity <= elements.length) {
            return;
        }

        elements = Arrays.copyOf(elements, minCapacity);
    }

    public void trimToSize() {
        if (size == elements.length) {
            return;
        }

        elements = Arrays.copyOf(elements, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }

    private void increaseCapacity() {
        if (elements.length == 0) {
            //noinspection unchecked
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        }

        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private void removeElement(int index) {
        modCount++;

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;
        size--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < размера списка: " + size
                    + ". Текущее значение индекса: " + index);
        }
    }
}