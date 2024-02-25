package starter;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Linked (singly linked-list) implementation of IndexedList.
 *
 * @param <T> Element type.
 */
public class LinkedIndexedList<T> implements IndexedList<T> {

  private Node<T> head;
  private int length;

  /**
   * Constructs a new LinkedIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  public LinkedIndexedList(int size, T defaultValue) throws LengthException {
    // create linked list of size, size
    if (size <= 0) {
      throw new LengthException();
    }

    head = null;
    length = size;

    for (int i = 0; i < length; i++){
      prepend(defaultValue);
    }
  }

  public void prepend( T value){
    Node<T> node = new Node<>();
    node.data = value;
    node.next = head;
    head = node;
  }

  private boolean isValid(int index) {
    return index >= 0 && index < length();
  }

  public Node<T> insertAtPosition(Node<T> head, T value, int position){
    if (head == null) {
      head = new Node<>();
      head.data = value;
      return head;
    }

    if (position == 0) {
      Node<T> newNode = new Node<>();
      newNode.data = value;
      newNode.next = head;
      head = newNode;
      return newNode;
    }

    Node<T> nodeBefore = head;
    int i = 0;

    while(i<position){
      nodeBefore = nodeBefore.next;
      i++;
    }
//    if (nodeBefore.next == null){
//      Node<T> newNode = new Node<>();
//      newNode.data = value;
//      newNode.next = nodeBefore.next;
//      nodeBefore.next = newNode;
//    }
    Node<T> newNode = new Node<>();
    newNode.data = value;
    newNode.next = nodeBefore.next;
    nodeBefore.next = newNode;
    return newNode;
  }

  public Node<T> reverseLinkedList(Node<T> head){
    
  }
  private Node<T> find(int index) throws IndexException{
    if (index < 0 || index >= length){
      throw new IndexException();
    }

    Node<T> target = head;
    int count = 0;

    while (target != null && count < index ){
      target = target.next;
      count++;
    }

    // goes directly to this if index is 0
    return target;
  }

  @Override
  public void put(int index, T value) throws IndexException {

    Node<T> target = find(index);
    target.data = value;
  }

  @Override
  public T get(int index) throws IndexException {
    Node<T> target = find(index);
    return target.data;
  }

  @Override
  public int length() {
    return length;
  }

  @Override
  public Iterator<T> iterator() {
    return new LinkedIndexListIterator();
  }

  // Node does not have access to members of LinkedIndexedList
  // because it is static
  private static class Node<T> {
    T data;
    Node<T> next;
  }

  // An iterator to traverse the linked list from front (head) to back.
  private class LinkedIndexListIterator implements Iterator<T> {
    private Node<T> cursor;

    LinkedIndexListIterator(){
      cursor = head;
    }

    @Override
    public boolean hasNext() {
      return cursor != null;
    }

    @Override
    public T next() {
      if (!hasNext()){
        throw new NoSuchElementException();
      }

      T temp_data = cursor.data;
      cursor = cursor.next;
      return temp_data;
    }
  }
}
