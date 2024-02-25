package starter;

import exceptions.EmptyException;

import java.lang.reflect.Array;

/**
 * Array Implementation of the Queue ADT.
 *
 * @param <T> base type.
 */
public class ArrayQueue<T> implements Queue<T> {
  private int numElements;
  private int capacity;
  private int front;
  private int back;
  private T[] data;

  ArrayQueue () {
    numElements = 0;
    front = 0;
    back = 0;
    capacity = 4;
    data = (T[]) new Object[capacity];
  }

  @Override
  public void enqueue(T value) {
    data[back] = value;
    back++;
    back %= capacity; //dont want to go over array capacity
    numElements++;

  }

  @Override
  public void dequeue() throws EmptyException {
    if(empty()){
      throw new EmptyException();
    }
    front++;
    front %= capacity;
    numElements--;
  }

  @Override
  public T front() throws EmptyException {
    if (empty()){
      throw new EmptyException();
    }
    return data[front];
  }

  @Override
  public boolean empty() {
    return numElements == 0;
  }
}
