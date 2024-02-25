package starter;

import exceptions.EmptyException;

/**
 * Linked implementation of Queue ADT.
 *
 * @param <T> base type.
 */
public class LinkedQueue<T> implements Queue<T> {
  private Node<T> head;
  private Node<T> tail;

  private int numElements;

  LinkedQueue(){
    head = null;
    tail = null;
    numElements = 0;
  }

  private static class Node<T> {
    T data;
    Node<T> next;
    Node(T data) {
      this.data = data;
      next = null;
    }
  }
  @Override
  public void enqueue(T value) {
    if (empty()) {
      head = new Node<>(value);
      tail = head;
    } else {
      tail.next = new Node<>(value);
      tail = tail.next;
    }
    numElements++;
  }

  @Override
  public void dequeue() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }

    head = head.next;
    if (numElements == 1){
      tail = null;
    }
    numElements--;

  }

  @Override
  public T front() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return head.data;
  }

  @Override
  public boolean empty() {
    return numElements == 0;
  }
}
