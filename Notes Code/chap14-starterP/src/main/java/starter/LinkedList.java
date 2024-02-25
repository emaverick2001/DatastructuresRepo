package starter;

import exceptions.EmptyException;
import exceptions.PositionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list implementation of the List ADT.
 *
 * @param <T> Element type.
 */
public class LinkedList<T> implements List<T> {
  private Node<T> head;
  private Node<T> tail;
  private int numElements;

  /**
   * Create an empty list.
   */
  public LinkedList() {
    head = null;
    tail = null;
    numElements = 0;
  }

  // Convert a Position back into a Node.
  // Guards against null positions, positions from other data structures,
  // and positions that belong to other LinkedList objects.
  private Node<T> convert(Position<T> position) throws PositionException {
    try {
      Node<T> node = (Node<T>) position;
      if (node.owner != this) {
        throw new PositionException();
      }
      return node;
    } catch (NullPointerException | ClassCastException e) {
      throw new PositionException();
    }
  }

  @Override
  public int length() {
    return numElements;
  }

  @Override
  public boolean empty() {
    return numElements == 0;
  }

  @Override
  public Position<T> insertFront(T data) {
    if (empty()) {
      head = new Node<>(data,this);
      tail = head;
      numElements++;
      return head;
    }
    Node<T> newNode = new Node<>(data,this);
    newNode.next = head;
    head.prev = newNode;
    head = newNode;
    numElements++;
    return head;
  }

  @Override
  public Position<T> insertBack(T data) {
    if (empty()) {
      tail = new Node<>(data,this);
      head = tail;
      numElements++;
      return tail;
    }
    Node<T> newNode = new Node<>(data,this);
    newNode.prev = tail;
    tail.next = newNode;
    tail = newNode;
    numElements++;
    return tail;
  }

  @Override
  public Position<T> insertBefore(Position<T> position, T data)
      throws PositionException {
    Node<T> target = convert(position);
    if (target.prev == null || head == null) {
      insertFront(data);
      return head;
    }
    Node<T> prevNode = target.prev;
    Node<T> newNode = new Node<>(data,this);
    newNode.prev = prevNode;
    prevNode.next = newNode;
    newNode.next = target;
    target.prev = newNode;
    numElements++;
    return newNode;
  }

  @Override
  public Position<T> insertAfter(Position<T> position, T data)
      throws PositionException {
    Node<T> target = convert(position);
    if (target.next == null || head == null) {
      insertBack(data);
      return tail;
    }
    Node<T> nextNode = target.next;
    Node<T> newNode = new Node<>(data,this);
    newNode.next = nextNode;
    nextNode.prev = newNode;
    newNode.prev = target;
    target.next = newNode;
    numElements++;
    return newNode;
  }

  @Override
  public void remove(Position<T> position) throws PositionException,EmptyException {
    Node<T> target = convert(position);
    if (target.prev == null) {
      removeFront();
    }
    if (target.next == null) {
      removeBack();
    }
    Node<T> prevNode = target.prev;
    Node<T> nextNode = target.next;
    prevNode.next = nextNode;
    nextNode.prev = prevNode;
    numElements--;
  }

  @Override
  public void removeFront() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    if (head.next == null) {
      head = head.next;
      tail = head;
      numElements--;
      return;
    }
    head = head.next;
    head.prev = null;
    numElements--;
  }

  @Override
  public void removeBack() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    if (tail.prev == null) {
      tail = tail.prev;
      head = tail;
      numElements--;
      return;
    }
    tail = tail.prev;
    tail.next = null;
    numElements--;
  }

  @Override
  public Position<T> front() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return head;
  }

  @Override
  public Position<T> back() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return tail;
  }

  @Override
  public boolean first(Position<T> position) throws PositionException,EmptyException {
    Node<T> target = convert(position);
    if (empty()) {
      throw new EmptyException();
    }
    return target == head;
  }

  @Override
  public boolean last(Position<T> position) throws PositionException,EmptyException {
    Node<T> target = convert(position);
    if (empty()) {
      throw new EmptyException();
    }
    return target == tail;
  }

  @Override
  public Position<T> next(Position<T> position) throws PositionException,EmptyException {
    Node<T> target = convert(position);
    if (empty()) {
      throw new EmptyException();
    }
    if (target.next == null) {
      throw new PositionException();
    }
    return target.next;
  }

  @Override
  public Position<T> previous(Position<T> position) throws PositionException,EmptyException {
    Node<T> target = convert(position);
    if (empty()) {
      throw new EmptyException();
    }
    if (target.prev == null) {
      throw new PositionException();
    }
    return target.prev;
  }

  @Override
  public Iterator<T> forward() {
    return new ListIterator(true);
  }

  private class ListIterator implements Iterator<T> {
    Node<T> cursor;
    boolean forward;

    ListIterator(boolean forward) {
      this.forward = forward;
      if (this.forward) {
        cursor = LinkedList.this.head;
      } else {
        cursor = LinkedList.this.tail;
      }
    }

    @Override
    public boolean hasNext() {
      return cursor != null;
    }

    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T temp = cursor.data;
      cursor = forward ? cursor.next : cursor.prev;
      return temp;
    }
  }

  @Override
  public Iterator<T> backward() {
    return new ListIterator(false);
  }

  @Override
  public Iterator<T> iterator() {
    return forward();
  }

  private static class Node<E> implements Position<E> {
    Node<E> next;
    Node<E> prev;
    E data;
    List<E> owner;

    Node(E data, List<E> owner) {
      this.data = data;
      this.owner = owner;
    }

    @Override
    public E get() {
      return data;
    }
  }
}
