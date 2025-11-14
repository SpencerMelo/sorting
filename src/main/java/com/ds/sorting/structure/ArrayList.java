package com.ds.sorting.structure;

import java.util.Arrays;

public class ArrayList<T> {

    private Object[] items;
    private int capacity;
    private int size;

    public ArrayList() {
        this.capacity = 8;
        this.size = 0;
        this.items = new Object[capacity];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.items = new Object[capacity];
    }

    public void add(T item) {
        resizeIfNecessary();
        items[size++] = item;
    }

    public void add(int index, T item) {
        checkOutOfBounds(index);
        resizeIfNecessary();
        pushAllToRightFromIndex(index);
        items[index] = item;
    }

    public void remove(int index) {
        checkOutOfBounds(index);
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[--size] = null;
    }

    public void remove(T item) {
        int index = indexOf(item);
        if (index != -1) {
            remove(index);
        }
    }

    public void set(int index, T item) {
        checkOutOfBounds(index);
        items[index] = item;
    }

    public T get(int index) {
        checkOutOfBounds(index);
        return itemData(index);
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public T last() {
        return itemData(size - 1);
    }

    public T first() {
        return itemData(0);
    }

    public int size() {
        return this.size;
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.items, size);
    }

    private void resizeIfNecessary() {
        if (this.capacity - this.size <= this.capacity * 0.5) {
            this.capacity *= 1.5;
            this.items = Arrays.copyOf(items, capacity);
        }
    }

    private void checkOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void pushAllToRightFromIndex(int index) {
        for (int i = size - 1; i >= index; i--) {
            items[i + 1] = items[i];
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    private T itemData(int index) {
        return (T) items[index];
    }
}
