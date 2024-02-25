package starter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArraySet<T> implements Set<T> {
  T[] data;
  int capacity;
  int back;
  int numElements;

  ArraySet() {
    capacity = 3;
    data = (T[]) new Object[capacity];
    numElements = 0;
    back = 0;
  }

  private void grow() {
    T[] tmp = data;
    capacity *= 2;
    data = (T[]) new Object[capacity];
    for (int i = 0; i < numElements; i++) {
      data[i] = tmp[i];
    }
  }

  @Override
  public void insert(T t) {
    if (has(t)) {
      return;
    }

    if (numElements == capacity) {
      grow();
    }
    data[back++] = t;
    numElements++;
  }

  @Override
  public void remove(T t) {
    if (!has(t)) {
      return;
    }
    int targetIndex = find(t);
    data[targetIndex] = data[numElements - 1];
    data[numElements - 1] = null;
    back--;
    numElements--;
  }

  //return index of found element else returns -1
  private int find(T t) {
    for (int i = 0; i < numElements; i++) {
      if (data[i].equals(t)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean has(T t) {
    return find(t) != -1;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    return new setIterator();
  }

  private class setIterator implements Iterator<T> {
    int cursor;

    setIterator() {
      cursor = 0;
    }

    @Override
    public boolean hasNext() {
      return cursor < numElements;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T t = data[cursor];
      cursor++;
      return t;
    }
  }
}
