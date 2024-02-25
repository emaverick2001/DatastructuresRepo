package solution;

import java.util.Iterator;
import java.util.Stack;
import starter.OrderedSet;

/**
 * A Binary Search Tree implementation of OrderedSet ADT.
 *
 * @param <T> Element type.
 */
public class BstOrderedSet<T extends Comparable<T>> implements OrderedSet<T> {

  private Node<T> root;
  private int numElements;

  /**
   * Construct an empty BstOrderedSet.
   */
  public BstOrderedSet() {
    root = null;
    numElements = 0;
  }

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    BstOrderedSet<Integer> bst = new BstOrderedSet<>();
    bst.insert(7);
    bst.insert(2);
    bst.insert(13);
    bst.insert(4);
    bst.insert(10);
    bst.insert(15);
    bst.insert(5);
    bst.insert(8);
    bst.insert(11);
    bst.insert(14);
    bst.insert(17);

    bst.remove(5);
    bst.remove(2);
    bst.remove(13);
  }

  /* recursive find */
  private Node<T> find(Node<T> node, T data) {
    if (node == null) {
      return null;
    }
    if (node.data.compareTo(data) > 0) {
      return find(node.left, data);
    } else if (node.data.compareTo(data) < 0) {
      return find(node.right, data);
    } else {
      return node;
    }
  }

  @Override
  public void insert(T t) {
    // Uses a recursive (private) helper insert
    root = insert(root, t);
  }

  /* inserts given value into subtree rooted at given node
      and returns changed subtree with new node added. */
  private Node<T> insert(Node<T> node, T data) {
    if (node == null) {
      /* If the subtree is empty, return a new node */
      numElements++;
      return new Node<>(data);
    }

    /* Otherwise, recur down the tree */
    if (node.data.compareTo(data) > 0) {
      node.left = insert(node.left, data);
    } else if (node.data.compareTo(data) < 0) {
      node.right = insert(node.right, data);
    }

    /* return the (unchanged) node pointer */
    return node;
  }

  @Override
  public void remove(T t) {
    // Uses a recursive (private) helper insert
    root = remove(root, t);
  }

  /* removes node with given value in the subtree rooted
     at given node and returns modified subtree. */
  private Node<T> remove(Node<T> node, T t) {
    if (node == null) { // base case
      return node;
    }
    // find the node that contains "t"
    if (node.data.compareTo(t) > 0) {
      node.left = remove(node.left, t);
    } else if (node.data.compareTo(t) < 0) {
      node.right = remove(node.right, t);
    } else { // found it; let's remove it!
      
      // zero or one child
      if (node.right == null) {
        numElements--;
        return node.left;
      } else if (node.left == null) {
        numElements--;
        return node.right;
      }
      // two children
      Node<T> next = findSmallest(node.right);
      node.data = next.data;
      node.right = remove(node.right, next.data);
    }

    return node;
  }

  // find the smallest value in subtree rooted at node
  // Pre: node != null
  private Node<T> findSmallest(Node<T> node) {
    Node<T> small = node;
    while (small.left != null) {
      // go left as far as we can
      small = small.left;
    }
    return small;
  }

  @Override
  public boolean has(T t) {
    return find(root, t) != null;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    return new InorderIterator();
  }

  private static class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;

    Node(E data) {
      this.data = data;
    }
  }

  private class InorderIterator implements Iterator<T> {
    private final Stack<Node<T>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(Node<T> curr) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public T next() {
      Node<T> top = stack.pop();
      pushLeft(top.right);
      return top.data;
    }
  }
}
