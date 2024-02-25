package starter;

import exceptions.EmptyException;

/**
 * Array Implementation of the Queue ADT.
 *
 * @param <T> base type.
 */
public class ArrayQueue<T> implements Queue<T> {
  private int front;
  private int back;

  private int capacity;
  private int numElements;
  private T[] data;

  ArrayQueue() {
    capacity = 3;
    data = (T[]) new Object[capacity];
    front = 0;
    back = 0;
    numElements = 0;
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
  public void enqueue(T value) {
    data[back] = value;
    back = ++back % capacity;
    numElements++;
    if (numElements == capacity) {
      grow();
    }

  }

  @Override
  public void dequeue() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    front = ++front % capacity;
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
