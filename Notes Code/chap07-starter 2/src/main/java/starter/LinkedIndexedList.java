package starter;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Linked (singly linked-list) implementation of IndexedList.
 *
 * @param <T> Element type.
 */
public class LinkedIndexedList<T> implements IndexedList<T> {

  private Node<T> head;

  private int numElem;
  private T defaultVal;

  /**
   * Constructs a new LinkedIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  public LinkedIndexedList(int size, T defaultValue) throws LengthException {

    if (size <= 0){
      throw new LengthException();
    }
    numElem = size;
    defaultVal = defaultValue;
    for (int i = 0; i <size; i++){
      //prepend node to head to create list
      put(i,defaultValue);
    }


  }

  private Node <T> findNodeBefore (int index)  {
    Node<T> target = head;
    int i = 0;

    if(index == 0){
      return target;
    }
    while (i < index - 1){
      target = target.next;
      i++;
    }

    return target;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    if (index < 0 || index >= numElem){
      throw new IndexException();
    }

    if (head == null){
      head = new Node<T>(value);
      return;
    }

    Node<T> NodeBefore = findNodeBefore(index);

    if (index == 0){
      Node<T> newnode= new Node<>(value);
      newnode.next = NodeBefore;
      head = newnode;
      return;
    }

    Node<T> newnode = new Node<>(value);
    newnode.next = NodeBefore.next;
    NodeBefore.next = newnode;

  }

private Node <T> findNode (int index) {
    Node<T> target = head;
    int i = 0;

    while (i < index){
      target = target.next;
      i++;
    }

    return target;
  }

  @Override
  public T get(int index) throws IndexException {
    if (index < 0 || index >= numElem){
      throw new IndexException();
    }

    Node<T> target =  findNode(index);
    return target.data;

  }

  @Override
  public int length() {
    return numElem;
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
    Node (T data){
      this.data = data;
      next = null;
    }
  }

  // An iterator to traverse the linked list from front (head) to back.
  private class LinkedIndexListIterator implements Iterator<T> {
    Node<T> cursor;

    LinkedIndexListIterator() {
      cursor = head;
    }

    @Override
    public boolean hasNext() {
      return cursor != null;
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
  }
}
