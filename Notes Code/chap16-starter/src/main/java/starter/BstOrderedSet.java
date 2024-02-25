package starter;

import java.util.Iterator;

/**
 * A Binary Search Tree implementation of OrderedSet ADT.
 *
 * @param <T> Element type.
 */
public class BstOrderedSet<T extends Comparable<T>> implements OrderedSet<T> {

  private final Node<T> root;
  private final int numElements;

  /**
   * Construct an empty BstOrderedSet.
   */
  public BstOrderedSet() {
    root = null;
    numElements = 0;
  }

//  @Override
//  public void insert(T t) {
//    Node<T> curr = root;
//    while(curr != null){
//      int cmp = t.compareTo(curr.data);
//      if (cmp < 0) {
//        if (curr.left == null){
//          curr.left = new Node<>(t);
//          curr = null;
//        }
//        curr = curr.left;
//      } else if (cmp > 0){
//        if( curr.right == null){
//          curr.right = new Node<>(t);
//          curr = null;
//        }
//        curr = curr.right;
//      } else {
//        // no duplicates allowed
//        return;
//      }
//    }
//  }

  private Node<T> insert(Node<T> rootNode,T t){
    Node<T> curr = rootNode;
    if (curr == null){
      return new Node<>(t);
    }
    int cmp = curr.data.compareTo(t);
    if (cmp < 0){
      curr.left = insert(curr.left,t);
    } else if (cmp > 0){
      curr.right = insert(curr.right,t);
    }
    // exit newest recursive stack
    return curr;
  }
  @Override
  public void insert(T t) {
    Node<T> curr = root;
    curr = insert(root,t);
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
  private Node<T> remove(Node<T> rootNode,T t){
    Node<T> curr = rootNode;

    if (curr == null){
      return curr;
    }
    int cmp = curr.data.compareTo(t);
    if (cmp < 0){
      curr.left = remove(curr.left,t);
    } else if (cmp > 0) {
      curr.right = remove(curr.right,t);
    } else { // found it; let's remove it!
      // zero or one child
      if (curr.right == null) {
        return curr.left;
      } else if (curr.left == null) {
        return curr.right;
      }
    // two children
    Node<T> next = findSmallest(curr.right);
    curr.data = next.data;
    // removes tree/ node with node smallest transferred to curr
    curr.right = remove(curr.right, next.data);
  }
    return curr;
  }
  @Override
  public void remove(T t) {
    Node<T> curr = root;
    curr = remove(root,t);
  }

  private Node<T> find(T t){
    Node<T> curr = root;
    while(curr != null){
      int cmp = t.compareTo(curr.data);
      if (cmp > 0) {
        curr = curr.right;
      } else if (cmp < 0) {
        curr = curr.left;
      } else {
        //target found
        return curr;
      }
    }
    return null;
  }

  private Node<T> findRecursive(Node<T> rootNode,T t){
    if (rootNode == null){
      return rootNode;
    }
    Node<T> curr = rootNode;
    int cmp = t.compareTo(curr.data);
    if (cmp < 0){
      return findRecursive(curr.left,t);
    } else if (cmp > 0){
        return findRecursive(curr.right,t);
    } else {
      return rootNode;
    }
  }

  private Node<T> findRecursivePractice(Node<T> rootNode,T t){
    if (rootNode == null){
      return rootNode;
    }
    Node<T> curr = rootNode;
    int cmp = t.compareTo(curr.data);
    if (cmp < 0){
      return findRecursivePractice(curr.left,t);
    } else if (cmp > 0){
      return findRecursivePractice(curr.right,t);
    }
    return curr;
  }

  @Override
  public boolean has(T t) {
    Node<T> curr = root;
    while (curr != null) {
      int cmp = t.compareTo(curr.data);
      if (cmp > 0) {
        curr = curr.right;
      } else if (cmp < 0) {
        //target smaller
        //visit left subtree
        curr = curr.left;
      } else {
        //target found
        return true;
      }
    }
    return false;
  }

  public boolean hasWfind(T t) {
    Node<T> target = find(t);
    return target != null;
  }

  public boolean hasRecursiveWFind(Node<T> rootnode,T t) {
    Node<T> curr = findRecursive(rootnode,t);
    return curr != null;

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
