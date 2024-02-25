package solution;

import java.util.Iterator;
import java.util.NoSuchElementException;
import starter.Set;

/**
 * Set implemented using plain Java arrays.
 *
 * @param <T> Element type.
 */
public class ArraySet<T> implements Set<T> {
  private int numElements;
  private T[] data;

  /**
   * Make a set.
   */
  public ArraySet() {
    data = (T[]) new Object[1];
    numElements = 0;
  }

  private boolean full() {
    return numElements >= data.length;
  }

  private void grow() {
    T[] bigger = (T[]) new Object[2 * numElements];
    System.arraycopy(data, 0, bigger, 0, numElements);
    data = bigger;
  }

  private int find(T t) {
    for (int i = 0; i < numElements; i++) {
      if (t.equals(data[i])) {
        return i;
      }
    }

    return -1;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public void insert(T t) {
    if (!has(t)) {
      data[numElements++] = t;
    }
    if (full()) {
      grow();
    }
  }

  @Override
  public void remove(T t) {
    int i = find(t);
    if (i != -1) {
      data[i] = data[--numElements];
    }
  }

  @Override
  public boolean has(T t) {
    return find(t) != -1;
  }

  @Override
  public Iterator<T> iterator() {
    return new SetIterator();
  }

  private class SetIterator implements Iterator<T> {
    private int current;

    SetIterator() {
      current = 0;
    }

    @Override
    public boolean hasNext() {
      return current < numElements;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return data[current++];
    }
  }
}
