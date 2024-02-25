package solution;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import starter.Set;

/**
 * Set implemented using a doubly linked list, no sentinels.
 *
 * <p>Since the implementation of this is pretty boring, we decided to add an
 * example for a "fail-fast" iterator, just to spice things up. If you want
 * to understand these beasts in detail, you need to read up on (and think
 * through) the issues yourself.</p>
 *
 * <p>To make an iterator "fail-fast" we need to be able to tell that the data
 * structure has been modified since the iteration started. We use a version
 * number in the LinkedSet class to achieve this. The number starts at 0 and
 * is incremented whenever a Set operation modifies the internal list. Each
 * iterator also "remembers" the version number it was created for. We can
 * then check for modifications by comparing version numbers in the Iterator
 * operations: If we notice a mismatch, we raise an exception.</p>
 *
 * <p>(If you read up on this, you'll see that while we can *test* for the
 * ConcurrentModificationException being thrown, we should not
 * rely on it for program control.)</p>
 *
 * @param <T> Element type.
 */
public class LinkedSet<T> implements Set<T> {
  private Node<T> head;
  private int version;
  private int numElements;

  private Node<T> find(T t) {
    for (Node<T> n = head; n != null; n = n.next) {
      if (n.data.equals(t)) {
        return n;
      }
    }
    return null;
  }

  @Override
  public int size() {
    return numElements;
  }

  // Pre: has(t) == false
  private void prepend(T t) {
    if (head == null) {
      head = new Node<T>(t);
    } else {
      Node<T> node = new Node<T>(t);
      node.next = head;
      head.prev = node;
      head = node;
    }
  }

  @Override
  public void insert(T t) {
    if (has(t)) {
      return;
    }
    prepend(t);
    version++;
    numElements++;
  }

  // Pre: target != null
  private void remove(Node<T> target) {
    Node<T> prevNode = target.prev;
    Node<T> nextNode = target.next;

    if (prevNode != null) {
      prevNode.next = nextNode;
    }

    if (nextNode != null) {
      nextNode.prev = prevNode;
    }

    if (head == target) {
      head = target.next;
    }
  }

  @Override
  public void remove(T t) {
    Node<T> target = find(t);
    if (target == null) {
      return;
    }
    remove(target);
    version++;
    numElements--;
  }

  @Override
  public boolean has(T t) {
    return find(t) != null;
  }

  @Override
  public Iterator<T> iterator() {
    return new SetIterator();
  }

  private static class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    Node(T data) {
      this.data = data;
    }
  }

  private class SetIterator implements Iterator<T> {
    private Node<T> current;
    private final int version;

    SetIterator() {
      current = LinkedSet.this.head;
      version = LinkedSet.this.version;
    }

    @Override
    public boolean hasNext() {
      checkVersion();
      return current != null;
    }

    @Override
    public T next() {
      checkVersion();
      T t = current.data;
      current = current.next;
      return t;
    }

    private void checkVersion() throws ConcurrentModificationException {
      if (version != LinkedSet.this.version) {
        throw new ConcurrentModificationException();
      }
    }
  }
}
