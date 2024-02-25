package starter;

import java.util.EmptyStackException;
import java.util.Iterator;

public class LinkedSet<T> implements Set<T> {
  private Node<T> head;
  private int numElements;

  LinkedSet() {
    head = null;
    numElements = 0;
  }

  private static class Node<T> {
    Node<T> next;
    T data;

    Node(T data) {
      this.next = null;
      this.data = data;
    }
  }

  private void prepend(T t) {
    if (head == null) {
      head = new Node<>(t);
    } else {
      Node<T> newNode = new Node<>(t);
      newNode.next = head;
      head = newNode;
    }
    numElements++;
  }

  @Override
  public void insert(T t) {
    if (has(t)) {
      return;
    }
    prepend(t);
  }


  private Node<T> findBefore(T t) {
    Node<T> prevNode = head;
    for (Node<T> target = head.next;target != null; target = target.next) {
      if (target.data.equals(t)) { //use .equals since we assume T can something other than integer
        return prevNode;
      }
      prevNode = prevNode.next;
    }
    return null;
  }

  @Override
  public void remove(T t) {
    if (head == find(t)) {
      head = head.next;
      numElements--;
      return;
    }

    Node<T> beforeNode = findBefore(t);
    if (beforeNode == null) {
      return;
    }
    beforeNode.next = beforeNode.next.next;
    numElements--;
  }

  private Node<T> find(T t) {
    for (Node<T> target = head;target != null; target = target.next) {
      if (target.data.equals(t)) { //use .equals since we assume T can something other than integer
        return target;
      }
    }
    // not found
    return null;
  }

  @Override
  public boolean has(T t) {
    //find Node in list
    return find(t) != null;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    return new SetIterator();
  }

  private class SetIterator implements Iterator<T> {
    private Node<T> cursor;

    SetIterator() {
      cursor = head;
    }

    @Override
    public boolean hasNext() {
      return cursor != null;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new EmptyStackException();
      }
      T t = cursor.data;
      cursor = cursor.next;
      return t;
    }

  }
}
