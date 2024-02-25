package starter;

import exceptions.EmptyException;
import exceptions.PositionException;

import java.util.Iterator;
import java.util.NoSuchElementException;

// implement with and without sentinel nodes

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
      // actual position is not a node throw error
      // actual position is a node but not from same datastructure
      // position is null
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
    if (head == null){
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
    return newNode;
  }

  @Override
  public Position<T> insertBack(T data) {
    if (head == null){
      tail = new Node<>(data,this);
      head = tail;
      numElements++;
      return tail;
    }
    Node<T> newNode = new Node<>(data,this);
    tail.next = newNode;
    newNode.prev = tail;
    tail = newNode;

    numElements++;
    return newNode;
  }


  @Override
  public Position<T> insertBefore(Position<T> position, T data)
      throws PositionException {
    Node<T> targetNode = convert(position) ;
    if (targetNode.prev == null){
      Node<T> newNode = new Node<>(data,this);
      newNode.next = targetNode;
      targetNode.prev = newNode;
      head = newNode;
      numElements++;
      return newNode;
    }
    Node<T> newNode = new Node<>(data,this);
    Node<T> beforeTarget = targetNode.prev;

    newNode.prev = beforeTarget;
    newNode.next = targetNode;

    targetNode.prev = newNode;
    beforeTarget.next = newNode;
    numElements++;
    return newNode;
  }

  @Override
  public Position<T> insertAfter(Position<T> position, T data)
      throws PositionException {
    Node<T> targetNode = convert(position);
    if (targetNode.next == null){
      Node<T> newNode = new Node<>(data,this);
      targetNode.next = newNode;
      newNode.prev = targetNode;
      tail = newNode;
      numElements++;
      return newNode;
    }
    Node<T> newNode = new Node<>(data,this);
    Node<T> afterTarget = targetNode.next;
    newNode.prev = targetNode;
    targetNode.next = newNode;
    newNode.next = afterTarget;
    afterTarget.prev = newNode;
    numElements++;
    return newNode;
  }

  @Override
  public void remove(Position<T> position) throws PositionException {
    Node<T> targetNode = convert(position);
    Node<T> beforeTarget = targetNode.prev;
    Node<T> afterTarget = targetNode.next;
    beforeTarget.next = afterTarget;
    afterTarget.prev = beforeTarget;
    numElements--;
  }

  @Override
  public void removeFront() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }

    // if there is only 1 node
    if (head.next == null){
      head = head.next;
      tail = head;
      numElements--;
      return;
    }
    Node<T> newFirst = head.next;
    head = newFirst;
    newFirst.prev = null;
    numElements--;
  }


  @Override
  public void removeBack() throws EmptyException{
    if (empty()) {
      throw new EmptyException();
    }
    if (head.next == null){
      tail = tail.prev;
      head = tail;
      numElements--;
      return;
    }
    Node<T> newBack = tail.prev;
    tail = newBack;
    newBack.next = null;
    numElements--;
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
    Node<T> targetNode = convert(p);
    return front().equals(targetNode);
  }

  @Override
  public boolean last(Position<T> p) throws PositionException{
    //what else constitutes as invalid?
    Node<T> targetNode = convert(p);
    return back().equals(targetNode);
  }

  @Override
  public Position<T> next(Position<T> position) throws PositionException {
    Node<T> targetNode = convert(position);

    if(head.next == null){
      throw new PositionException();
    }

    return targetNode.next;
  }

  @Override
  public Position<T> previous(Position<T> position) throws PositionException {
    Node<T> targetNode = convert(position);

    if (head.next == null){
      throw new PositionException();
    }
    return targetNode.prev;
  }

  @Override
  public Iterator<T> forward() {
    return new ForwardLinkedListIterator();
  }

  @Override
  public Iterator<T> iterator() {
    return new ForwardLinkedListIterator();
  }

  private class ForwardLinkedListIterator implements Iterator<T> {
    Node<T> cursor;

    ForwardLinkedListIterator() {
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
      Node<T> current = cursor;
      cursor = cursor.next;
      return current.data;
    }
  }

  @Override
  public Iterator<T> backward() {
    return new BackwardListIterator();
  }

  private class BackwardListIterator implements Iterator<T> {
    private Node<T> cursor;
    BackwardListIterator(){
      cursor = tail;
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
      Node<T> current = cursor;
      cursor = cursor.prev;
      return current.data;
    }

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
