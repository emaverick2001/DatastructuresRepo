package starter;

import exceptions.IndexException;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO some of the sanity checks are convoluted and need to be refactored!
public class LinkedList<T> implements Iterable<T> {
  private Node<T> head;
  private int numElements;

  public LinkedList() {
    head = null;
    numElements = 0;
  }

  public void addFirst(T t) {
    Node<T> curr = head;
    if (curr == null){
      head = new Node<>(t);
      return;
    }

    Node<T> node = new Node<>(t);
    node.next = curr;
    head = node;
  }

  public void addLast(T t) {
    Node<T> curr = head;

    if (curr == null){
      head = new Node<>(t);
      return;
    }
    while(curr.next != null) {
      curr = curr.next;
    }
    curr.next = new Node<>(t);
  }

  public void traverse() {
    Node<T> curr = head;
    if (curr == null){
      System.out.println("List is empty");
      return;
    }

    while (curr != null){
      System.out.println(curr.data);
      curr = curr.next;
    }
  }

  public T get(int index) throws IndexException {
    if (index < 0) {
      throw new IndexException();
    }

    Node<T> nodeBefore = head;
    int i = 0;

    if (index == 0){
      return nodeBefore.data;
    }

    while(i < index - 1){
      nodeBefore = nodeBefore.next;
      i++;
    }
    return nodeBefore.data;
  }


  public void insert(int index, T t) throws IndexException {
    Node<T> nodeBefore = head;
    int i = 0;

    if (index < 0){
      throw new IndexException();
    }

    if (index == 0){
      Node<T> newnode = new Node<>(t);
      newnode.next = head;
      head = newnode;
      return;
      // could also do addFirst(t);
    }

    while(i < index - 1){
      nodeBefore = nodeBefore.next;
      i++;
    }
    Node<T> newnode = new Node<>(t);
    newnode.next = nodeBefore.next;
    nodeBefore.next = newnode;
  }

  public void delete(int index) {
    Node<T> nodeBefore = head;
    int i = 0;

    if (i == 0){
      head = head.next;
      return;
    }

    while(i < index - 1){
      nodeBefore = nodeBefore.next;
      i++;
    }
    nodeBefore.next = nodeBefore.next.next;
  }

  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  public int length() {
    return numElements;
  }

  private static class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
      this.data = data;
    }
  }

  private class LinkedListIterator implements Iterator<T> {
    Node<T> cursor;
    LinkedListIterator (){
      cursor = head;
    }

    @Override
    public T next() throws NoSuchElementException{
      if (!hasNext()){
        throw new NoSuchElementException();
      }

      T temp = cursor.data;
      cursor = cursor.next;
      return temp;
    }

    @Override
    public boolean hasNext() {
      return cursor != null;
    }
  }

}
