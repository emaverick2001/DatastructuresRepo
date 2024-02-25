package starter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedSet<T> implements Set<T> {
  Node<T> head;
  int numElements;

  SinglyLinkedSet() {
    head = null;
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

  private void prepend(T t) {
    if (head == null) {
      head = new Node<>(t);
      numElements++;
      return;
    }
    Node<T> newNode = new Node<>(t);
    newNode.next = head;
    head = newNode;
    numElements++;
  }

  @Override
  public void insert(T t) {
    if (has(t)) {
      return;
    }
    prepend(t);
  }

  @Override
  public void remove(T t) {
    if (!has(t)) {
      return;
    }

    if (head.data.equals(t)) {
      head = head.next;
      numElements--;
      return;
    }
    Node<T> prevNode = findBefore(t);
    if (prevNode == null) {
      return;
    }
    prevNode.next = prevNode.next.next;
    numElements--;
  }

  private Node<T> findBefore(T t) {

    for (Node<T> current = head; current.next != null; current = current.next) {
      if (current.next.data.equals(t)) {
        return current;
      }
    }

    return null;
  }

  private Node<T> find(T t) {
    for (Node<T> current = head; current != null; current = current.next) {
      if (current.data.equals(t)) {
        return current;
      }
    }
    return null;
  }

  @Override
  public boolean has(T t) throws NullPointerException {
    // find node with value
    // if null then false
    return find(t) != null;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    return new SinglyLinkedSetIterator();
  }

  private class SinglyLinkedSetIterator implements Iterator<T> {
    Node<T> cursor;

    SinglyLinkedSetIterator() {
      cursor = null;
    }

    @Override
    public boolean hasNext() {
      return cursor != null;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T t = cursor.data;
      cursor = cursor.next;
      return t;
    }
  }
}
