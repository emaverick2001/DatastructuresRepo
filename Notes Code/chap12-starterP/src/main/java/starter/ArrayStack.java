package starter;

import exceptions.EmptyException;

/**
 * Stack ADT implemented using an array.
 *
 * @param <T> base type.
 */
public class ArrayStack<T> implements Stack<T> {
  private T[] data;
  private int capacity;
  private int numElements;

  /**
   * Construct an ArrayStack.
   */
  public ArrayStack() {
    capacity = 10;
    data = (T[]) new Object[capacity];
    numElements = 0;
  }

  @Override
  public boolean empty() {
    return numElements == 0;
  }

  @Override
  public T top() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return data[numElements - 1]; // TODO: Implement me!
  }

  @Override
  public void pop() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    numElements--;
  }

  private void grow() {
    T[] temp = data;
    capacity *= 2;
    data = (T[]) new Object[capacity];
    for (int i = 0; i < numElements; i++) {
      data[i] = temp[i];
    }
  }
  @Override
  public void push(T value) {
    data[numElements++] = value;
    if (numElements == capacity) {
      grow();
    }
  }
}
