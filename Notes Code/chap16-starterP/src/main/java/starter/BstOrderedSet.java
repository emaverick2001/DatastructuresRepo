package starter;

import java.util.Iterator;

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

  private Node<T> insert(Node<T> rootNode, T data) {
    if (root == null) {
      numElements++;
      return new Node<T>(data);
    }
    int cmp = data.compareTo(rootNode.data);
    if (cmp < 0) {
      rootNode.left = insert(rootNode.left,data);
    } else if (cmp > 0) {
      rootNode.right = insert(rootNode.right,data);
    }
    return root;
  }
  @Override
  public void insert(T t) {
    root = insert(root,t);
  }

  private Node<T> findSmallest(Node<T> rootNode) {
    Node<T> curr = rootNode;
    while (curr != null) {
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
      numElements--;

    }
    return rootNode;
  }

  @Override
  public void remove(T t) {
    root = remove(root, t);
  }

  private Node<T> find(Node<T> rootNode,T data) {
    if (rootNode == null) {
      return null;
    }
    int cmp = data.compareTo(rootNode.data);
    if (cmp < 0) {
      return find(rootNode.left,data);
    } else if (cmp > 0) {
      return find(rootNode.right,data);
    } else {
      return rootNode;
    }
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
    // TODO Implement me!
    return null;
  }

  private static class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;

    Node(E data) {
      this.data = data;
    }
  }

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    BstOrderedSet<Integer> bst = new BstOrderedSet<>();
    bst.insert(7);
    bst.insert(4);
    bst.insert(13);
    bst.insert(1);
    bst.insert(6);
    bst.insert(10);
    bst.insert(15);
    bst.insert(3);

    System.out.println(bst.size());
    System.out.println(bst.has(13));
    System.out.println(bst.has(23));
  }
}
