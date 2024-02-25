package starter;

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
    Node<T> current = new Node<>(t);
    current.next = head;
    head = current;
    numElements++;
  }

  public void addLast(T t) {

    Node<T> tail = head;
    Node<T> last = new Node(t);

    // if list is empty
    if (head == null){
      head = last;
      return;
    }

    // goes to last node
    while(tail.next != null){
      tail = tail.next;
    }
    tail.next = last;
    numElements++;

  }

  //if we used head ref, then that would change the head pointer and delete the linked list
  public void traverse() {
    Node<T> current = head;
    if (current == null) {
      System.out.println("Error no nodes found");
    }

    // goes to null
    while (current != null) {
      System.out.println(current.data);
      current = current.next;
    }
  }

  // precond 0 <= index < numElements
//  public T get(int index) {
//    return find(index).data;
//  }

//  public T get(int index) {
//    Node<T> target = head;
//    int count = 0;
//    while ( count < index) {
//      target = target.next;
//      count++;
//    }
//    return target.data;
//  }

  public T get(int index) {
    return find(index).data;
  }

  // finds node @ index
  private Node<T> find(int index) {
    Node<T> target = head;
    int count = 0;

    // using != index has the same effect
    while (count < index) {
      target = target.next;
      count++;
    }
    return target;
  }


  // asuming element at index exists
  public void insert(int index, T t) {
    Node<T> current = find(index);
    Node<T> newNode = new Node<>(t);
    newNode.next = current.next;
    current.next = newNode;
    numElements++;
  }

  public void delete(int index) {
    int index_before_del = index - 1;
    Node<T> before_delete = find(index_before_del);
    before_delete.next = before_delete.next.next;
    numElements--;
  }
  // using get


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

  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator implements Iterator<T> {
    private Node<T> current = head;

    @Override
    public T next() {
      T current_data;
      if (!hasNext()){
        throw new NoSuchElementException();
      }
      current_data = current.next.data;
      current = current.next;
      return current_data;
    }

    // shouldn't this look at the next index not the current?
    @Override
    public boolean hasNext() {
      return current.next != null;
    }
  }

}
