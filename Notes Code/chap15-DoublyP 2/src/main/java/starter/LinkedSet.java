package starter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedSet<T> implements Set<T> {

  private Node<T> head;
  private Node<T> tail;
  private int numElements;


  private void prepend(T t) {
    if (head == null) {
      head = new Node<>(t);
      tail = head;
    } else {
      Node<T> newNode = new Node<T>(t);
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
  }

  @Override
  public void insert(T t) {
    if (has(t)) {
      return;
    }
    prepend(t);
    numElements++;
  }

  private static class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    Node(T data) {
      this.data = data;
    }
  }

  private void remove(Node<T> target) {
    Node<T> pNode = target.prev;
    Node<T> nNode = target.next;
    //pNode null means head is target or list in empty
    if (pNode == null && nNode == null) {
      head = null;
      tail = null;
    } else if (pNode == null) { //nNode must be !null
      nNode.prev = null;
      head = head.next;
    } else if (nNode == null) { //pNode must be !null
      pNode.next = null;
      tail = tail.prev;
    } else { // pNode and nNode are !null
      pNode.next = nNode;
      nNode.prev = pNode;
    }
  }

  /**
   * Remove a value.
   * Set doesn't change if we try to remove a non-existent value.
   * Post: has(t) == false.
   *
   * @param t Value to remove.
   */
  @Override
  public void remove(T t) {
    // if target not in list or list is null return
    Node<T> target = find(t);
    if (target == null) {
      return;
    }
    remove(target);
    numElements--;

  }

  private Node<T> find(T t) {
    for (Node<T> curr = head; curr != null; curr = curr.next) {
      if (curr.data.equals(t)) {
        return curr;
      }
    }
    return null;
  }

  /* checks if list contains a certain node*/
  // if list empty returns null
  @Override
  public boolean has(T t) {
    return find(t) != null;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    return new setIterator();
  }

  private class setIterator implements Iterator<T> {
    Node<T> cursor;

    setIterator() {
      cursor = head;
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

      T temp = cursor.data;
      cursor = cursor.next;
      return temp;
    }
  }
}
