package hw6;

import hw6.bst.AvlTreeMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  // test insertRightRotation with 3 nodes

  @Test
  public void insertRightRotation() {
    map.insert("c", "a");


    map.insert("b", "b");


    map.insert("a", "c"); // it must do a left rotation here!


    String[] expected = new String[]{
            "b:b",
            "a:c c:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertRightRotation with multiple nodes
  @Test
  public void insertRightRotationTwice() {
    map.insert("i", "a");

    map.insert("f", "b");

    map.insert("d", "c"); // it must do a right rotation here!

    map.insert("c", "d");

    map.insert("b","e"); // must do another right rotation


    String[] expected = new String[]{
            "f:b",
            "c:d i:a",
            "b:e d:c null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertRightRotation with multiple nodes
  @Test
  public void insertRightRotationThrice() {
    map.insert("i", "a");

    map.insert("f", "b");

    map.insert("d", "c"); // it must do a right rotation here!

    map.insert("c", "d");

    map.insert("b","e"); // must do another right rotation

    map.insert("a","f");


    String[] expected = new String[]{
            "c:d",
            "b:e f:b",
            "a:f null d:c i:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertLeftRotation with 3 nodes
  @Test
  public void insertLeftRotation() {
    map.insert("a", "a");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a
     */

    map.insert("b", "b");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("c", "c"); // it must do a left rotation here!
    // System.out.println(avl.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
            "b:b",
            "a:a c:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  // test isnertLeftRotation with multiple nodes
  @Test
  public void insertLeftRotationTwice() {
    map.insert("i", "a");

    map.insert("l", "b");

    map.insert("n", "c"); // it must do a left rotation here!

    map.insert("o", "d");

    map.insert("p","e"); // must do another left rotation


    String[] expected = new String[]{
            "l:b",
            "i:a o:d",
            "null null n:c p:e"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertRightRotation with multiple nodes
  @Test
  public void insertLeftRotationThrice() {
    map.insert("i", "a");

    map.insert("l", "b");

    map.insert("n", "c"); // it must do a right rotation here!

    map.insert("o", "d");

    map.insert("p","e"); // must do another right rotation

    map.insert("q","f");


    String[] expected = new String[]{
            "o:d",
            "l:b p:e",
            "i:a n:c null q:f"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertRightLeftRotation with multiple nodes
  @Test
  public void insertRightLeftRotationSimple() {
    map.insert("f", "a");
    map.insert("h", "b");
    map.insert("g", "c");

    String[] expected = new String[] {
            "g:c",
            "f:a h:b"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertRightLeftRotation with multiple nodes
  @Test
  public void insertRightLeftRotationMedium() {
    map.insert("f", "a");
    map.insert("a","b");
    map.insert("g", "c"); // perform right left rotation
    map.insert("c", "d");
    map.insert("b", "e"); // perform right left rotation
    String[] expected = new String[] {
            "f:a",
            "b:e g:c",
            "a:b c:d null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // write out tree when middleNodeLeftChild has child nodes

  // test insertLeftRightRotation with 3 nodes
  @Test
  public void insertLeftRightRotationSimple() {
    map.insert("f", "a");
    map.insert("a","b");
    map.insert("b", "c"); // perform left right rotation
    String[] expected = new String[] {
            "b:c",
            "a:b f:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertLeftRightRotation with multiple nodes
  @Test
  public void insertLeftRightRotationMedium() {
    map.insert("f", "a");
    map.insert("c","b");
    map.insert("i", "c"); // perform right left rotation
    map.insert("g", "d");
    map.insert("h", "e"); // perform right left rotation
    String[] expected = new String[] {
            "f:a",
            "c:b h:e",
            "null null g:d i:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // maybe add a complex method for left right

  // test remove leaf node from list
  @Test
  public void removeRightRotationLeaf() {
    map.insert("f", "a");
    map.insert("c", "b");
    map.insert("i", "c");
    map.insert("b", "d");
    map.remove("i");
    String[] expected = new String[] {
            "c:b",
            "b:d f:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeLeftRotationLeaf() {
    map.insert("f", "a");
    map.insert("c", "b");
    map.insert("i", "c");
    map.insert("j", "d");
    map.remove("c");
    String[] expected = new String[] {
            "i:c",
            "f:a j:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeLeftRightRotationLeaf() {
    map.insert("f", "a");
    map.insert("c", "b");
    map.insert("j", "c");
    map.insert("e", "d");
    map.remove("j");
    String[] expected = new String[] {
            "e:d",
            "c:b f:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeRightLeftRotationLeaf() {
    map.insert("f", "a");
    map.insert("c", "b");
    map.insert("j", "c");
    map.insert("h", "d");
    map.remove("c");
    String[] expected = new String[] {
            "h:d",
            "f:a j:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeParentRightRotation() {
    map.insert("f", "a");
    map.insert("c", "b");
    map.insert("j", "c");
    map.insert("b", "d");
    map.insert("e","e");
    map.insert("k","f");
    map.insert("a","g");
    map.remove("k");
    String[] expected = new String[] {
            "c:b",
            "b:d f:a",
            "a:g null e:e j:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeParentLeftRotation() {
    map.insert("f", "a");
    map.insert("c","b");
    map.insert("j", "c");
    map.insert("b", "d");
    map.insert("g", "e");
    map.insert("k","f");
    map.insert("l","g");
    map.remove("b");
    String[] expected = new String[] {
            "j:c",
            "f:a k:f",
            "c:b g:e null l:g"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // right left rotation due to deletion of a parent TODO: do for LeftRight
  @Test
  public void removeRootRightLeftRotationMiddleLeftChildHasLeftChild() {
    map.insert("k", "a");
    map.insert("d","b");
    map.insert("s","c");
    map.insert("g","d");
    map.insert("m","e");
    map.insert("t","f");
    map.insert("l","g");
    map.remove("k");

    String[] expected = new String[] {
            "m:e",
            "g:d s:c",
            "d:b l:g null t:f"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeRootRightLeftRotationMiddleLeftChildHasRightChild() {
    map.insert("k", "a");
    map.insert("d","b");
    map.insert("s","c");
    map.insert("g","d");
    map.insert("m","e");
    map.insert("t","f");
    map.insert("n","g");
    map.remove("k");

    String[] expected = new String[] {
            "m:e",
            "g:d s:c",
            "d:b null n:g t:f"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeParentRightLeftRotationMiddleLeftChildHasLeftChild() {
    map.insert("k", "a");
    map.insert("d","b");
    map.insert("o","c");
    map.insert("b","d");
    map.insert("g","e");
    map.insert("l","f");
    map.insert("s","g");
    map.insert("c","h");
    map.insert("f","i");
    map.insert("h","j");
    map.insert("p","k");
    map.insert("t","l");
    map.insert("e","m");
    map.remove("d");
    String[] expected = new String[] {
            "k:a",
            "f:i o:c",
            "c:h g:e l:f s:g",
            "b:d e:m null h:j null null p:k t:l"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeParentRightLeftRotationMiddleLeftChildHasRightChild() {
    map.insert("k", "a");
    map.insert("d","b");
    map.insert("o","c");
    map.insert("b","d");
    map.insert("g","e");
    map.insert("l","f");
    map.insert("s","g");
    map.insert("c","h");
    map.insert("e","i");
    map.insert("h","j");
    map.insert("p","k");
    map.insert("t","l");
    map.insert("f","m");
    map.remove("d");
    String[] expected = new String[] {
            "k:a",
            "e:i o:c",
            "c:h g:e l:f s:g",
            "b:d null f:m h:j null null p:k t:l"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeParentRightLeftRotationMiddleLeftChildHasBothChildren() {
    map.insert("k", "a");
    map.insert("d","b");
    map.insert("o","c");
    map.insert("b","d");
    map.insert("i","e");
    map.insert("l","f");
    map.insert("s","g");
    map.insert("c","h");
    map.insert("g","i");
    map.insert("j","j");
    map.insert("p","k");
    map.insert("t","l");
    map.insert("f","m");
    map.insert("h","n");
    map.remove("d");
    String[] expected = new String[] {
            "k:a",
            "g:i o:c",
            "c:h i:e l:f s:g",
            "b:d f:m h:n j:j null null p:k t:l"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // test insertRotation with all rotation types (RL LR L R)

}
