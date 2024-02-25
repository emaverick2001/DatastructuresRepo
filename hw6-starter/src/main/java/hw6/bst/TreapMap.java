package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Map implemented as a Treap.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class TreapMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'rand'. ***/
  private static Random rand;
  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;

  private int size;

  /**
   * Make a TreapMap.
   */
  public TreapMap() {
    // seed is 10
    rand = new Random();
    rand.setSeed(40);
  }

  private Node<K,V> rotateRight(Node<K,V> rootNode) {
    Node<K,V> middleNode = rootNode.left;
    if (middleNode.right != null) {
      Node<K,V> middleNodeRightChild = middleNode.right;
      middleNode.right = rootNode;
      rootNode.left = middleNodeRightChild;
      return middleNode;
    } else {
      middleNode.right = rootNode;
      rootNode.left = null;
      return middleNode;
    }
  }


  private Node<K,V> rotateLeft(Node<K,V> rootNode) {
    Node<K, V> middleNode = rootNode.right;
    if (middleNode.left != null) {
      Node<K,V> middleNodeLeftChild = middleNode.left;
      middleNode.left = rootNode;
      rootNode.right = middleNodeLeftChild;
      return middleNode;
    } else {
      middleNode.left = rootNode;
      rootNode.right = null;
      return middleNode;
    }
  }


  // Insert given key and value into subtree rooted at given node;
  // return changed subtree with a new node added.
  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new Node<>(k, v);
    }

    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }

    // check if root has smallest priority compared to left and right nodes
    // rotate right
    if (n.left != null && n.priority > n.left.priority) {
      n = rotateRight(n);
    }
    // rotate left
    if (n.right != null && n.priority > n.right.priority) {
      n = rotateLeft(n);
    }

    return n;
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    root = insert(root, k, v);
    size++;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    Node<K, V> node = findForSure(k);
    V value = node.value;
    root = remove(root, node);
    size--;
    return value;
  }

  // Remove node with given key from subtree rooted at given node;
  // Return changed subtree with given key missing.
  private Node<K, V> remove(Node<K, V> subtreeRoot, Node<K, V> toRemove) {
    int cmp = subtreeRoot.key.compareTo(toRemove.key);
    if (cmp == 0) {
      return remove(subtreeRoot);
    } else if (cmp > 0) {
      subtreeRoot.left = remove(subtreeRoot.left, toRemove);
    } else {
      subtreeRoot.right = remove(subtreeRoot.right, toRemove);
    }

    return subtreeRoot;
  }

  // Remove given node and return the root of remaining tree (structural change).
  private Node<K, V> remove(Node<K, V> node) {
    //rebalance tree
    // Easy if the node has 0 or 1 child.
    if (node.right == null) {
      return node.left;
    } else if (node.left == null) {
      return node.right;
    }

    // If it has two children, find the predecessor (max in left subtree),
    Node<K, V> toReplaceWith = max(node);
    // then copy its data to the given node (value change),
    node.key = toReplaceWith.key;
    node.value = toReplaceWith.value;
    node.priority = toReplaceWith.priority;
    // then remove the predecessor node (structural change).
    node.left = remove(node.left, toReplaceWith);

    // rebalance tree
    return rebalance(node);
  }

  private Node<K, V> rebalance(Node<K, V> node) {
    // check if either child is null
    if (node.left == null || node.right == null) {
      return balance(node);
    } else {
      // both children are not null
      // check which child has smaller priority
      if (node.left.priority < node.right.priority) {
        node = rotateRight(node);
        // balance the right child
        node.right = rebalance(node.right);
      } else {
        node = rotateLeft(node);
        // balance the left child
        node.left = rebalance(node.left);
      }
    }
    return node;
  }

  private Node<K, V> balance(Node<K, V> node) {
    // if both are leaf then we reached end of tree
    if (node.left == null && node.right == null) {
      return node;
    } else if (node.left == null) {
      // if root priority already smallest or equal to right priorty no need to balance
      if (node.priority > node.right.priority) {
        node = rotateLeft(node);
        node.left = rebalance(node.left);
      } else {
        return node;
      }
    } else if (node.right == null) {
      if (node.priority > node.left.priority) {
        node = rotateRight(node);
        node.right = rebalance(node.right);
      } else {
        return node;
      }
    }
    return node;
  }

  // Return a node with maximum key in subtree rooted at given node.
  private Node<K, V> max(Node<K, V> node) {
    Node<K, V> curr = node.left;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    n.value = v;
  }

  // Return node for given key,
  // throw an exception if the key is not in the tree.
  private Node<K, V> findForSure(K k) {
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    return n.value;
  }

  // Return node for given key.
  private Node<K, V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    Node<K, V> n = root;
    while (n != null) {
      int cmp = k.compareTo(n.key);
      if (cmp < 0) {
        n = n.left;
      } else if (cmp > 0) {
        n = n.right;
      } else {
        return n;
      }
    }
    return null;
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }

  // Iterative in-order traversal over the keys
  private class InorderIterator implements Iterator<K> {
    private final Stack<Node<K, V>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(Node<K, V> curr) {
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
    public K next() {
      Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }


  /**
   * Feel free to add whatever you want to the Node class (e.g. new fields).
   * Just avoid changing any existing names, deleting any existing variables,
   * or modifying the overriding methods.
   * Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers. Since this is
   * a node class for a Treap we also include a priority field.
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int priority;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      priority = generateRandomInteger();
    }

    // Use this function to generate random values
    // to use as node priorities as you insert new
    // nodes into your TreapMap.
    private int generateRandomInteger() {
      // Note: do not change this function!
      return rand.nextInt();
    }

    @Override
    public String toString() {
      return key + ":" + value + ":" + priority;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }
  }
}
