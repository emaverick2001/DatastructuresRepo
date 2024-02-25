package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An implementation of an IndexedList designed for cases where
 * only a few positions have distinct values from the initial value.
 *
 * @param <T> Element type.
 */
public class SparseIndexedList<T> implements IndexedList<T> {

  private final T defaultVal;



  private final int length;
  private final Node<T> head;


  /**
   * Constructs a new SparseIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  public SparseIndexedList(int size, T defaultValue) throws LengthException {
    if (size <= 0) {
      throw new LengthException();
    }
    length = size;
    // using sentinel node
    head = new Node<>(null,-1);
    defaultVal = defaultValue;
  }

  private boolean isValid(int index) {
    return index < 0 || index >= length();
  }

  @Override
  public int length() {
    return length;
  }
  
  private Node<T> findBefore(int index) throws IndexException {
    if (isValid(index)) {
      throw new IndexException();
    }
    
    Node<T> curr = head;
    
    //if prevNode.next doesnt equal target index
    while (curr.next != null && curr.next.index < index) {
      curr = curr.next;
    }
    return curr;
  }

  @Override
  public T get(int index) throws IndexException {
    if (isValid(index)) {
      throw new IndexException();
    }

    Node<T> targetBefore = findBefore(index);

    if (targetBefore.next == null) {
      return defaultVal;
    }

    if (targetBefore.next.index != index) {
      return defaultVal;
    }

    return targetBefore.next.data;
  }

  private void delete(Node<T> nodeBefore, int index) throws IndexException {

    if (index < 0) {
      throw new IndexException();
    }

    nodeBefore.next = nodeBefore.next.next;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    if (isValid(index)) {
      throw new IndexException();
    }

    // search for node to see if present
    Node<T> targetBefore = findBefore(index);

    if (targetBefore.next == null || targetBefore.next.index > index) {
      Node<T> newNode = new Node<>(value, index);
      newNode.next = targetBefore.next;
      targetBefore.next = newNode;
    }

    if (targetBefore.next.index == index) {
      if (value == defaultVal) {
        delete(targetBefore, index);
        return;
      }
      targetBefore.next.data = value;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }

  private class SparseIndexedListIterator implements Iterator<T> {
    Node<T> cursor;
    int index;

    SparseIndexedListIterator() {
      cursor = head.next;
      index = 0;
    }

    @Override
    public boolean hasNext() {

      return index < length;
    }


    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      // when list is empty
      if (cursor == null || index != cursor.index) {
        index++;
        return defaultVal;
      }

      T val = cursor.data;
      cursor = cursor.next;
      index++;
      return val;
    }
  }

  // Node does not have access to members of LinkedIndexedList
  // because it is static
  private static class Node<T> {
    T data;
    int index;
    Node<T> next;

    Node(T data, int index) {
      this.data = data;
      this.index = index;
      this.next = null;
    }
  }
}
