package solution;

import exceptions.EmptyException;
import exceptions.PositionException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import starter.List;
import starter.Position;

/**
 * A sentinel-based doubly linked list implementation of the List ADT.
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
    head = new Node<>(null, this);
    tail = new Node<>(null, this);
    head.next = tail;
    tail.prev = head;
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
  public boolean empty() {
    return numElements == 0;
  }

  @Override
  public int length() {
    return numElements;
  }

  @Override
  public boolean first(Position<T> position) throws PositionException {
    Node<T> node = convert(position);
    return head.next == node;
  }

  @Override
  public boolean last(Position<T> position) throws PositionException {
    Node<T> node = convert(position);
    return tail.prev == node;
  }

  @Override
  public Position<T> front() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return head.next;
  }

  @Override
  public Position<T> back() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return tail.prev;
  }

  @Override
  public Position<T> next(Position<T> position) throws PositionException {
    if (last(position)) {
      throw new PositionException();
    }
    return convert(position).next;
  }

  @Override
  public Position<T> previous(Position<T> position) throws PositionException {
    if (first(position)) {
      throw new PositionException();
    }
    return convert(position).prev;
  }

  @Override
  public Position<T> insertFront(T data) {
    Node<T> newFront = new Node<T>(data, this);

    Node<T> currFront = head.next;
    head.next = newFront;
    newFront.prev = head;
    newFront.next = currFront;
    currFront.prev = newFront;

    numElements += 1;
    return newFront;
  }

  @Override
  public Position<T> insertBack(T data) {
    Node<T> newBack = new Node<T>(data, this);

    Node<T> currBack = tail.prev;
    newBack.next = tail;
    tail.prev = newBack;
    newBack.prev = currBack;
    currBack.next = newBack;

    numElements += 1;
    return newBack;
  }

  @Override
  public void removeFront() throws EmptyException {
    Node<T> currFront = convert(front());
    Node<T> newFront = currFront.next;

    head.next = newFront;
    newFront.prev = head;

    numElements -= 1;
    currFront.owner = null; // invalidate position
  }

  @Override
  public void removeBack() throws EmptyException {
    Node<T> currBack = convert(back());
    Node<T> newBack = currBack.prev;

    tail.prev = newBack;
    newBack.next = tail;

    numElements -= 1;
    currBack.owner = null; // invalidate position
  }

  @Override
  public void remove(Position<T> position) throws PositionException {
    Node<T> nodeToRemove = convert(position);
    Node<T> before = nodeToRemove.prev;
    Node<T> after = nodeToRemove.next;

    before.next = after;
    after.prev = before;

    numElements -= 1;
    nodeToRemove.owner = null; // invalidate position
  }

  @Override
  public Position<T> insertBefore(Position<T> position, T data)
      throws PositionException {
    Node<T> target = convert(position);
    Node<T> beforeTarget = target.prev;
    Node<T> newBefore = new Node<>(data, this);

    newBefore.prev = beforeTarget;
    beforeTarget.next = newBefore;
    newBefore.next = target;
    target.prev = newBefore;

    numElements += 1;
    return newBefore;
  }

  @Override
  public Position<T> insertAfter(Position<T> position, T data)
      throws PositionException {
    Node<T> target = convert(position);
    Node<T> afterTarget = target.next;
    Node<T> newAfter = new Node<>(data, this);

    newAfter.prev = target;
    target.next = newAfter;
    newAfter.next = afterTarget;
    afterTarget.prev = newAfter;

    numElements += 1;
    return newAfter;
  }

  @Override
  public Iterator<T> forward() {
    return new ListIterator(true);
  }

  @Override
  public Iterator<T> backward() {
    return new ListIterator(false);
  }

  @Override
  public Iterator<T> iterator() {
    return forward();
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[");
    for (Node<T> n = head.next; n != tail; n = n.next) {
      s.append(n.data);
      if (n.next != tail) {
        s.append(", ");
      }
    }
    s.append("]");
    return s.toString();
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

  /**
   * This iterator can be used to create either a forward
   * iterator, or a backwards one.
   */
  private class ListIterator implements Iterator<T> {
    Node<T> current;
    boolean forward;

    ListIterator(boolean forward) {
      this.forward = forward;
      if (this.forward) {
        current = LinkedList.this.head.next;
      } else {
        current = LinkedList.this.tail.prev;
      }
    }

    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T value = current.get();
      current = forward ? current.next : current.prev;
      return value;
    }

    @Override
    public boolean hasNext() {
      return current != null && current != head && current != tail;
    }
  }
}
