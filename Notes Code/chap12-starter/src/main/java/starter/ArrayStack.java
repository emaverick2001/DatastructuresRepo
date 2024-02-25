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
    if (empty()){
      throw new EmptyException();
    }


    return data[numElements-1];
  }

  @Override
  public void pop() throws EmptyException {
    if (empty()){
      throw new EmptyException();
    }
    numElements--;
  }

  @Override
  public void push(T value) {
    data[numElements++] = value;
  }
}
