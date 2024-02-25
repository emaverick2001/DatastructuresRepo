package starter;

import exceptions.EmptyException;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Linked implementation of Queue ADT.
 *
 * @param <T> base type.
 */
public class LinkedQueue<T> implements Queue<T> {

  private int numElements;
  Node<T> head;
  Node<T> tail;

  LinkedQueue (){
    head = null;
    tail = null;
    numElements = 0;
  }

  @Override
  public void enqueue(T value) {
    if (empty()){
      head = new Node<>(value);
      tail = head;
    } else {
      tail.next = new Node<T>(value);
      tail = tail.next;

    }
    numElements++;
  }

  @Override
  public void dequeue() throws EmptyException {
    if (empty()){
      throw new EmptyException();
    }
    head = head.next;
    numElements--;
  }

  @Override
  public T front() throws EmptyException {
    if (empty()){
      throw new EmptyException();
    }

    return head.data;
  }

  @Override
  public boolean empty() {
    return numElements == 0;
  }

  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T val){
      data = val;
      next = null;
    }
  }
}
