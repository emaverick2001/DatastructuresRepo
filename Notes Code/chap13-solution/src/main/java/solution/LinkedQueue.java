package solution;

import exceptions.EmptyException;
import starter.Queue;

/**
 * Linked implementation of Queue ADT.
 *
 * @param <T> base type.
 */
public class LinkedQueue<T> implements Queue<T> {
  private int numElements;
  private Node<T> head;
  private Node<T> tail;

  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }
  }

  /**
   * Construct a LinkedQueue.
   */
  public LinkedQueue() {
    numElements = 0;
    head = null;
    tail = null;
  }

  @Override
  public void enqueue(T value) {
    if (empty()) {
      head = new Node<>(value, null);
      tail = head;
    } else {
      tail.next = new Node<>(value, null);
      tail = tail.next;
    }
    numElements++;
  }

  @Override
  public void dequeue() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    numElements--;
    head = head.next;
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
