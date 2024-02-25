package starter;

import exceptions.EmptyException;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Priority queue implemented as a binary heap.
 *
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<T>>
        implements PriorityQueue<T> {

  private List<T> data;
  private int size;
  private int end;

  private Comparator<T> cmp;

  /**
   * A binary heap using the "natural" ordering of T.
   */
  public BinaryHeapPriorityQueue() {
    cmp = new DefaultComparator<>();
    data = new ArrayList<>();
    size = 0;
    end = size + 1;
  }

  /**
   * A binary heap using the given comparator for T.
   *
   * @param cmp Comparator to use.
   */
  public BinaryHeapPriorityQueue(Comparator<T> cmp) {
    this.cmp = cmp;
    data = new ArrayList<>();
    size = 0;
    end = size + 1;
  }

  private void swimUp(int index) {
    int parent = index / 2;

    if (parent > 0 && data.get(index).compareTo(data.get(parent)) < 0) {
      swap(parent, index);
      swimUp(parent);
    }
  }

  @Override
  public void insert(T t) {
    data.add(end,t);
    size++;
    swimUp(size);
  }

  private void swimDown(int index) {
    int leftChild = 2 * index;
    int rightChild = 2 * index + 1;
    int smallest = index;

    if (leftChild <= size && cmp.compare(data.get(leftChild), data.get(smallest)) < 0) {
      smallest = leftChild;
    }

    if (rightChild <= size && cmp.compare(data.get(rightChild), data.get(smallest)) < 0) {
      smallest = rightChild;
    }

    if (smallest != index) {
      // Swap the element at 'index' with the smallest child
      swap(index, smallest);

      // Recursively call swimDown on the swapped position
      swimDown(smallest);
    }

  }

  // Helper method to swap elements in the 'data' list
  private void swap(int i, int j) {
    T temp = data.get(i);
    data.set(i, data.get(j));
    data.set(j, temp);
  }

  @Override
  public void remove() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }

    swap(1,size);
    data.remove(size);
    size--;

    swimDown(1);

  }

  @Override
  public T best() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return data.get(0);
  }

  @Override
  public boolean empty() {
    return size != 0;
  }

  // The default comparator uses the "natural" ordering.
  private static class DefaultComparator<T extends Comparable<T>>
          implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }

}

