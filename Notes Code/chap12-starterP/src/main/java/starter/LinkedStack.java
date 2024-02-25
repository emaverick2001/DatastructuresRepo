package starter;

import exceptions.EmptyException;

/**
 * Stack ADT implemented using linked nodes.
 *
 * @param <T> base type.
 */
public class LinkedStack<T> implements Stack<T> {

  private Node<T> list;
  private int numElements;

  /**
   * Construct an ListStack.
   */
  public LinkedStack() {
    list = null;
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
    return list.data;
  }

  @Override
  public void pop() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    list = list.next;
    numElements--;
  }

  @Override
  public void push(T t) {
    Node<T> newNode = new Node<>();
    newNode.data = t;
    newNode.next = list;
    list = newNode;
    numElements++;
  }

  private static class Node<T> {
    T data;
    Node<T> next;
  }
}
