package starter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedDoublyLinkedSet<T extends Comparable<T>> implements OrderedSet<T> {
  Node<T> head;
  Node<T> tail;
  int numElements;

  private static class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    Node(T data) {
      this.data = data;
    }
  }

  OrderedDoublyLinkedSet() {
    head = null;
    tail = null;
    numElements = 0;
  }

  private boolean lessThan(T a, T b) {
    return a.compareTo(b) < 0;
  }

  private void prepend(T t) {
    Node<T> newNode = new Node<>(t);
    newNode.next = head;
    head.prev = newNode;
    head = newNode;
    numElements++;
  }

  private void addNewNode(Node<T> target, Node<T> pNode, Node<T> nNode,T t) {
    if (pNode == null && nNode == null) {
      if (lessThan(t,target.data)) {
        prepend(t);
      }
    } else if (pNode == null) {
      if (lessThan(t,target.data)) {
        prepend(t);
      }
    } else if (nNode == null) {
      if (lessThan(t,target.data)) {
        Node<T> newNode = new Node<>(t);
        newNode.next = tail;
        tail.prev = newNode;
        pNode.next = newNode;
        newNode.prev = tail;
        numElements++;
      }
    }
  }

  private void append(T t) {
    Node<T> newNode = new Node<>(t);
    newNode.prev = tail;
    tail.next = newNode;
    tail = newNode;
    numElements++;
  }

  // returns next largest node compared to t
  // if t is smallest element return null
  private Node<T> findNextLargest(T t) {
    // 1 node
    Node<T> curr = head;
    while (lessThan(t,curr.data) && curr.next != null) {
      curr = curr.next;
    }
    //
    if (curr == head) {
      if (lessThan(t, curr.data)) {
        return null;
      }
      return head;
    }
    return curr;
  }

  @Override
  public void insert(T t) {
    if (has(t)) {
      return;
    }
    Node<T> target = find(t);
    if (target == null) {
      if (head == null) {
        head = new Node<>(t);
        tail = head;
        numElements++;
        return;
      }
      // find where to insert node
      Node<T> nextLargest = findNextLargest(t); // returns null if no largest
      if (nextLargest == null) {
        // t is smaller than head.data (1 node)
        prepend(t);
      } else if (nextLargest == tail && !lessThan(t, tail.data)) {
        append(t);
      } else if (nextLargest == head) {
        // t is larger than head.data (1 node)
        Node<T> newNode = new Node<>(t);
        newNode.next = head.next;
        newNode.prev = head;
        Node<T> after = head.next;
        after.prev = newNode;
        head.next = newNode;
        numElements++;
      } else {
        Node<T> newNode = new Node<>(t);
        Node<T> beforeNextLargest = nextLargest.prev;
        newNode.next = nextLargest;
        nextLargest.prev = newNode;
        beforeNextLargest.next = newNode;
        newNode.prev = beforeNextLargest;
        numElements++;
      }
    } else {
      Node<T> pNode = target.prev;
      Node<T> nNode = target.next;

      addNewNode(target, pNode, nNode, t);
    }

  }

  private void removeNode(Node<T> pNode,Node<T> nNode,T t) {
    if (pNode == null && nNode == null) {
      head = null;
      tail = head;
      numElements--;
    } else if (pNode == null) {
      head = head.next;
      nNode.prev = null;
      numElements--;
    } else if (nNode == null) {
      tail = tail.prev;
      pNode.next = null;
      numElements--;
    }
  }

  @Override
  public void remove(T t) {
    Node<T> target = find(t);
    if (target == null) { //target not in list + list is empty
      return;
    }
    Node<T> pNode = target.prev;
    Node<T> nNode = target.next;
    removeNode(pNode,nNode,t);
  }

  private Node<T> find(T t) {
    for (Node<T> curr = head; curr != null; curr = curr.next) {
      if (curr.data.equals(t)) {
        return curr;
      }
    }
    return null;
  }

  @Override
  public boolean has(T t) {
    return find(t) != null;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    return new OrderedDoublyLinkedSetIterator();
  }

  private class OrderedDoublyLinkedSetIterator implements Iterator<T> {

    Node<T> cursor;

    OrderedDoublyLinkedSetIterator() {
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

      T tmp = cursor.data;
      cursor = cursor.next;
      return tmp;
    }
  }
}
