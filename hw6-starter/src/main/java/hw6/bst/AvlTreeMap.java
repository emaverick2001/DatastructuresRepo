package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;
import java.util.Stack;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;

  private int size;

  private int height(Node<K,V> node) {
    // if null height is -1
    if (node == null) {
      return -1;
    }

    int rightSubTreeHeight;
    int leftSubTreeHeight;

    // calculate height by recurring down tree
    rightSubTreeHeight = 1 + height(node.right);
    leftSubTreeHeight = 1 + height(node.left);

    return Math.max(rightSubTreeHeight, leftSubTreeHeight);
  }

  private void updateNodeHeight(Node<K,V> rootNode) {
    Node<K,V> nl = rootNode.left;
    Node<K,V> nr = rootNode.right;
    if (height(nl) > height(nr)) {
      rootNode.height = 1 + height(nl);
    } else if (height(nl) < height(nr)) {
      rootNode.height = 1 + height(nr);
    } else {
      rootNode.height = 1 + height(nr);
    }
  }

  private Node<K,V> performRotation(Node<K,V> rootNode) {
    // right rot
    if (rootNode.bf == 2) {
      // left right rotation
      if (rootNode.left.bf == -1) {
        return rotateLeftRight(rootNode);
      }
      // right rot
      return rotateRight(rootNode);
    }
    // left rot
    if (rootNode.bf  == -2) {
      // right left rotation
      if (rootNode.right.bf == 1) {
        return rotateRightLeft(rootNode);
      }
      // left rotation
      return rotateLeft(rootNode);
    }
    return rootNode;
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

  private Node<K,V> rotateLeftRight(Node<K,V> rootNode) {
    Node<K,V> middleNode = rootNode.left;
    Node<K,V> middleNodeRightChild = middleNode.right;
    // left rot
    // middleNodeRightChild become middle node
    rootNode.left = middleNodeRightChild;
    // middle node right child has a left child
    if (middleNodeRightChild.left != null) {
      middleNode.right = middleNodeRightChild.left;
      middleNodeRightChild.left = middleNode;
    } else {
      middleNodeRightChild.left = middleNode;
      middleNode.right = null;
    }
    // right rot
    // middle node right child has a right child
    if (middleNodeRightChild.right != null) {
      rootNode.left = middleNodeRightChild.right;
      middleNodeRightChild.right = rootNode;
      return middleNodeRightChild;
    }
    middleNodeRightChild.right = rootNode;
    rootNode.left = null;
    return middleNodeRightChild;
  }


  private Node<K,V> rotateRightLeft(Node<K,V> rootNode) {
    Node<K,V> middleNode = rootNode.right;
    Node<K,V> middleNodeLeftChild = middleNode.left;
    // right rot
    // middleNodeLeftChild become middle node
    rootNode.right = middleNodeLeftChild;
    // middle node left child has a right child
    if (middleNodeLeftChild.right != null) {
      middleNode.left = middleNodeLeftChild.right;
      middleNodeLeftChild.right = middleNode;
    } else {
      middleNodeLeftChild.right = middleNode;
      middleNode.left = null;
    }
    // left rot
    // middle node left child has a left child
    if (middleNodeLeftChild.left != null) {
      rootNode.right = middleNodeLeftChild.left;
      middleNodeLeftChild.left = rootNode;
      return middleNodeLeftChild;
    }
    middleNodeLeftChild.left = rootNode;
    rootNode.right = null;
    return middleNodeLeftChild;
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
    // find largest of the two subtrees heights
    updateNodeHeight(n);

    // calculate bf for node
    n.bf = height(n.left) - height(n.right);
    return performRotation(n);
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    root = insert(root, k, v);
    size++;
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

  // Return a node with maximum key in subtree rooted at given node.
  // finds max key value in left subtree of given root node
  private Node<K, V> max(Node<K, V> node) {
    Node<K, V> curr = node.left;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
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

    // find largest of the two subtrees heights
    updateNodeHeight(subtreeRoot);

    // calculate bf for node
    subtreeRoot.bf = height(subtreeRoot.left) - height(subtreeRoot.right);

    return performRotation(subtreeRoot);
  }

  // Remove given node and return the remaining tree (structural change).
  private Node<K, V> remove(Node<K, V> node) {
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
    // then remove the predecessor node (structural change).
    node.left = remove(node.left, toReplaceWith);

    // check if root is balanced
    // find largest of the two subtrees heights
    updateNodeHeight(node);
    // calculate bf for node
    node.bf = height(node.left) - height(node.right);

    return performRotation(node);
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    // make sure node exists (no null return value)
    Node<K, V> n = findForSure(k);
    n.value = v;
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
   *
   * <p>Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers.</p>
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    int height;
    int bf;
    K key;
    V value;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      bf = 0;
      height = 0;
      key = k;
      value = v;
    }

    @Override
    public String toString() {
      return key + ":" + value;
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
