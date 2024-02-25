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
    bst.remove(18);

    bst.printInOrder();
    bst.printPreOrder();
    bst.printPostOrder();
  }

  /* recursive find */
  private Node<T> find(Node<T> node, T data) {
    if (node == null) {
      return node;
    }

    int cmp = node.data.compareTo(data);
    if (cmp > 0){
      return find(node.left,data);
    } else if (cmp < 0) {
      return find(node.right,data);
    } else {
      return  node;
    }
  }
  public void printInOrder() {
    printInOrder(root);
  }

  private void printInOrder(Node<T> node) {
    // start at left subtree
    // print root
    // go to right subtree
    if (node == null) {
      return;
    }

    printInOrder(node.left);
    System.out.print(node.data + " ");
    printInOrder(node.right);
  }

  public void printPreOrder() {
    printPreOrder(root);
  }

  private void printPreOrder(Node<T> node) {
    // start at left subtree
    // print root
    // go to right subtree
    if (node == null) {
      return;
    }

    System.out.print(node.data + " ");
    printPreOrder(node.left);
    printPreOrder(node.right);
  }

  public void printPostOrder() {
    printPostOrder(root);
  }

  private void printPostOrder(Node<T> node) {
    // start at left subtree
    // print root
    // go to right subtree
    if (node == null) {
      return;
    }

    printPostOrder(node.left);
    printPostOrder(node.right);
    System.out.print(node.data + " ");
  }

  // implement iteratively
  @Override
  public void insert(T t) {
    // Uses a recursive (private) helper insert
    root = insert(root, t);
  }

  /* inserts given value into subtree rooted at given node
      and returns changed subtree with new node added. */
  private Node<T> insert(Node<T> node, T data) {
    if (node == null) {
      numElements++;
      return new Node<>(data);
    }

    int cmp = node.data.compareTo(data);
    if (cmp > 0) {
      node.left = insert(node.left,data);
    } else if (cmp < 0) {
      node.right = insert(node.right,data);
    }
    return node;
  }

  @Override
  public void remove(T t) {
    root = remove(root, t);
  }

  private Node<T> remove(Node<T> node, T t) {
    if (node == null) {
      return node;
    }

    int cmp = node.data.compareTo(t);
    if (cmp > 0) {
      node.left = remove(node.left,t);
    } else if (cmp < 0) {
      node.right = remove(node.right,t);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      } else {
        Node<T> smallestNode = findSmallest(node.right);
        node.data = smallestNode.data;
        remove(node.right,smallestNode.data);
      }
    }
    return node;
  }

  private Node<T> findSmallest(Node<T> rootSmallest) {
    while(rootSmallest.left != null){
      rootSmallest = rootSmallest.left;
    }
    return rootSmallest;
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
}
