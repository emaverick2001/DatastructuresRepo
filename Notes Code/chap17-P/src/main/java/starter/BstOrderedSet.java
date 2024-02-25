package starter;

import java.util.Iterator;
import java.util.Stack;

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
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);
    bst.insert(4);
    bst.insert(6);
    bst.insert(14);
    bst.insert(16);

    bst.printInOrder(bst.root);
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

  private Node<T> findSmallest(Node<T> rootNode) {
    Node<T> curr = rootNode;
    while (curr.left != null) {
      curr = curr.left;
    }
    return curr;
  }

  private Node<T> remove(Node<T> rootNode,T data) {
    if (rootNode == null) {
      return null;
    }
    int cmp = data.compareTo(rootNode.data);
    if (cmp < 0) {
      rootNode.left =  remove(rootNode.left,data);
    } else if (cmp > 0) {
      rootNode.right =  remove(rootNode.right,data);
    } else {
      if (rootNode.left == null) {
        numElements--;
        return rootNode.right;
      } else if (rootNode.right == null) {
        numElements--;
        return rootNode.left;
      }
      Node<T> smallest = findSmallest(rootNode.right);
      rootNode.data = smallest.data;
      rootNode.right = remove(rootNode.right, smallest.data);

    }
    return rootNode;
  }

  public void printInOrder(Node<T> node) {
    if (node == null) {
      return;
    }
    printInOrder(node.left);
    System.out.print(node.data + " ");
    printInOrder(node.right);
  }

  @Override
  public void remove(T t) {
    root = remove(root, t);
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
      pushLeft(top.right); // moves to next element // if its a leaf or has no right node does nothing and moves to next element in stack
      return top.data;
    }
  }

//  @Override
//  public Iterator<T> iterator() {
//    return new PreorderIterator();
//  }
//
//  private class PreorderIterator implements Iterator<T> {
//    private final Stack<Node<T>> stack;
//
//    PreorderIterator() {
//      stack = new Stack<>();
//      if (root != null) {
//        stack.push(root);
//      }
//    }
//
//    @Override
//    public boolean hasNext() {
//      return !stack.isEmpty();
//    }
//
//    @Override
//    public T next() {
//      if (!hasNext()) {
//        throw new java.util.NoSuchElementException();
//      }
//
//      Node<T> top = stack.pop();
//
//      if (top.right != null) {
//        stack.push(top.right); // Push the right child first
//      }
//
//      if (top.left != null) {
//        stack.push(top.left); // Push the left child
//      }
//
//      return top.data;
//    }
//  }

  private static class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;

    Node(E data) {
      this.data = data;
    }
  }
}
