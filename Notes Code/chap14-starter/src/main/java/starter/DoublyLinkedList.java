package starter;

import exceptions.EmptyException;
import exceptions.PositionException;

import java.util.Enumeration;

public class DoublyLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int numElements;

  DoublyLinkedList() {
    head = null;
    tail = null;
    numElements = 0;
  }

  private static class Node<E> implements Position<E> {
    Node<E> next;
    Node<E> prev;
    E data;
    DoublyLinkedList<E> owner;

    Node(E data, DoublyLinkedList<E> owner) {
      this.data = data;
      this.owner = owner;
    }

    @Override
    public E get() {
      return data;
    }
  }

  public int length() {
    return numElements;
  }

  /**
   * List is empty or not.
   *
   * @return True if empty, false otherwise.
   */
  boolean empty() {
    return numElements == 0;
  }

  public boolean first(Position<T> position) throws PositionException {
    Node<T> node = (Node<T>) position;
    if (node.owner != this) {
      throw new PositionException();
    }

    return head == node;
  }


  // Constructor not shown

  public Position<T> addFirst(T data) {
    Node<T> newNode = new Node<T>(data, this);
    newNode.next = head;
    head.prev = newNode;
    head = newNode;

    numElements++;
    return newNode;
  }

  public Position<T> addLast(T data) {
    Node<T> newNode = new Node<>(data, this);
    tail.next = newNode;
    newNode.prev = tail;
    tail = newNode;

    numElements++;
    return newNode;
  }

  public Position<T> get(int index) throws PositionException {
    if (index < 0 || index >= numElements) {
      throw new PositionException();
    }
    Node<T> curr = tail;
    for (int i = numElements; i > index; i--) {
      curr = curr.prev;
    }
    return curr;
  }

  public void delete(Position<T> target) throws EmptyException {
    if ((Node<T>) target == null){
      throw new EmptyException();
    }
    if (((Node<T>) target).next == null){
      Node<T> targetNode = (Node<T>) target;
      Node<T> beforeTarget = targetNode.prev;
      beforeTarget.next = targetNode.next;
    }
    Node<T> targetNode = (Node<T>) target;
    Node<T> beforeTarget = targetNode.prev;
    Node<T> afterTarget = targetNode.next;

    beforeTarget.next = afterTarget;
    afterTarget.prev = beforeTarget;
  }

  public void insertAfter(Position<T> target, T data) {
    Node<T> targetNode = (Node<T>) target;
    Node<T> newNode = new Node<>(data, this);
    Node<T> afterTarget = targetNode.next;
    newNode.prev = targetNode;
    targetNode.next = newNode;

    newNode.next = afterTarget;
    afterTarget.prev = newNode;
  }

  public void insertBefore(Position<T> target, T data) {
    Node<T> targetNode = (Node<T>) target;
    Node<T> newNode = new Node<>(data, this);
    Node<T> beforeTarget = targetNode.prev;

    newNode.prev = beforeTarget;
    newNode.next = targetNode;

    targetNode.prev = newNode;
    beforeTarget.next = newNode;
  }
}


