package solution;

import exceptions.EmptyException;
import starter.Queue;

/**
 * Array Implementation of the Queue ADT.
 *
 * @param <T> base type.
 */
public class ArrayQueue<T> implements Queue<T> {
  private int numElements;
  private T[] data;
  private int capacity;
  private int front;
  private int back;

  /**
   * Construct an ArrayQueue.
   */
  public ArrayQueue() {
    front = 0;
    back = 0;
    numElements = 0;
    capacity = 4;
    data = (T[]) new Object[capacity];
  }

  @Override
  public void enqueue(T value) {
    data[back] = value;
    back++;
    back %= capacity;
    numElements++;
    // TODO: grow the array if needed.
  }

  @Override
  public void dequeue() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    front++;
    front %= capacity;
    numElements--;
  }

  @Override
  public T front() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return data[front];
  }

  @Override
  public boolean empty() {
    return numElements == 0;
  }
}
