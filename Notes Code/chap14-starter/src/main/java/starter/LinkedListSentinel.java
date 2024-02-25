package starter;

import exceptions.EmptyException;
import exceptions.PositionException;

import java.util.Iterator;

// implement with and without sentinel nodes

/**
 * A doubly linked list implementation of the List ADT.
 *
 * @param <T> Element type.
 */
public class LinkedListSentinel<T> implements List<T> {
  private Node<T> head;
  private Node<T> tail;
  private int numElements;

  /**
   * Create an empty list.
   */
  public LinkedListSentinel() {
    head = new Node<>(null,this);
    tail = new Node<>(null,this);
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

    Node<T> newNode = new Node<>(data,this);
    Node<T> currFront = head;
    currFront.next = newNode;
    newNode.prev = currFront;
    numElements++;
    return newNode;
  }

  @Override
  public Position<T> insertBack(T data) {
    Node<T> newNode = new Node<>(data,null);
    Node<T> currBack = tail;
    currBack.next = newNode;
    newNode.prev = currBack;
    numElements++;
    return newNode;
  }

  @Override
  public Position<T> insertBefore(Position<T> position, T data)
          throws PositionException {
    Node<T> targetNode = convert(position);
    Node<T> prevNode = targetNode.prev;

    Node<T> newNode = new Node<>(data,this);
    newNode.next = targetNode;
    targetNode.prev = newNode;
    prevNode.next = newNode;
    newNode.prev = prevNode;
    return newNode;
  }

  @Override
  public Position<T> insertAfter(Position<T> position, T data)
          throws PositionException {
    Node<T> targetNode = (Node<T>) position;
    Node<T> newNode = new Node<>(data,null);
    Node<T> afterTarget = targetNode.next;
    newNode.prev = targetNode;
    targetNode.next = newNode;

    newNode.next = afterTarget;
    afterTarget.prev = newNode;
    return null;
  }

  @Override
  public void remove(Position<T> position) throws PositionException {
    Node<T> targetNode = (Node<T>) position;
    Node<T> beforeTarget = targetNode.prev;
    Node<T> afterTarget = targetNode.next;
    beforeTarget.next = afterTarget;
    afterTarget.prev = beforeTarget;
  }

  @Override
  public void removeFront() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    Node<T> newFirst = head.next;
    head = newFirst;
    newFirst.prev = null;
  }

  @Override
  public void removeBack() throws EmptyException{
    if (empty()) {
      throw new EmptyException();
    }
    Node<T> newBack = tail.prev;
    tail = newBack;
    newBack.next = null;
  }

  public Position<T> front() throws EmptyException{
    if (empty()) {
      throw new EmptyException();
    }
    return head;
  }

  @Override
  public Position<T> back() throws EmptyException{
    if (empty()) {
      throw new EmptyException();
    }
    return tail;
  }

  @Override
  public boolean first(Position<T> p) throws PositionException{
    //what else constitutes as invalid?
    if (p == null){
      throw new PositionException();
    }

    return front().equals(p);
  }

  @Override
  public boolean last(Position<T> p) throws PositionException{
    //what else constitutes as invalid?
    if (p == null){
      throw new PositionException();
    }

    return back().equals(p);
  }

  @Override
  public Position<T> next(Position<T> position) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Position<T> previous(Position<T> position) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterator<T> forward() {
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterator<T> backward() {
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Implement me!
    return null;
  }

  private static class Node<E> implements Position<E> {
    Node<E> next;
    Node<E> prev;
    E data;
    List<E> owner; //what is owner?

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
