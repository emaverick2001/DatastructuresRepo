package hw6;

import hw6.bst.TreapMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>();
  }

  @Test
  public void insertRightRotation() {
    map.insert("c", "a");


    map.insert("b", "b");


    map.insert("a", "c");


    String[] expected = new String[]{
            "b:b:-1749212617",
            "a:c:95830475 c:a:-1170874532"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertRightThenLeftRotation() {
    map.insert("c", "a");


    map.insert("b", "b");


    map.insert("a", "c");

    map.insert("d","d");

    String[] expected = new String[]{
            "b:b:-1749212617",
            "a:c:95830475 d:d:-1502686769",
            "null null c:a:-1170874532 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertLeftRotation() {
    map.insert("a","a");
    map.insert("b", "b");
    map.insert("c","c");

    String[] expected = new String[] {
            "b:b:-1749212617",
            "a:a:-1170874532 c:c:95830475"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertLeftThenLeftRotation() {
    map.insert("a","a");
    map.insert("b", "b");
    map.insert("c","c");
    map.insert("d","d");

    String[] expected = new String[] {
            "b:b:-1749212617",
            "a:a:-1170874532 d:d:-1502686769",
            "null null c:c:95830475 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertManyNodes() {
    map.insert("a","a");
    map.insert("b", "b");
    map.insert("c","c");
    map.insert("d","d");
    map.insert("e","e");
    map.insert("f","f");
    map.insert("g","g");


    String[] expected = new String[] {
            "b:b:-1749212617",
            "a:a:-1170874532 d:d:-1502686769",
            "null null c:c:95830475 g:g:-680157218",
            "null null null null null null f:f:1702710456 null",
            "null null null null null null null null null null null null e:e:1929790192 null null null"

    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  @Test
  public void removeRootNodeLeftRot() {
    map.insert("a","a");
    map.insert("b", "b");
    map.insert("c","c");
    map.insert("d","d");
    map.insert("e","e");
    map.insert("f","f");
    map.insert("g","g");
    map.remove("b");


    String[] expected = new String[] {
            "d:d:-1502686769",
            "a:a:-1170874532 g:g:-680157218",
            "null c:c:95830475 f:f:1702710456 null",
            "null null null null e:e:1929790192 null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeParentNodeRightRotAndMiddleNodeWoutRightChild() {
    map.insert("h","b");
    map.insert("e","a");
    map.insert("d","c");
    map.insert("c","d");
    map.insert("b","e");
    map.remove("e");


    String[] expected = new String[] {
            "c:d:-1502686769",
            "b:e:1929790192 h:b:-1170874532",
            "null null d:c:95830475 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeParentNodeLeftRotWithLeftChild() {
    map.insert("a","a");
    map.insert("b", "b");
    map.insert("c","c");
    map.insert("d","d");
    map.insert("e","e");
    map.insert("f","f");
    map.insert("g","g");
    map.remove("d");


    String[] expected = new String[] {
            "b:b:-1749212617",
            "a:a:-1170874532 g:g:-680157218",
            "null null c:c:95830475 null",
            "null null null null null f:f:1702710456 null null",
            "null null null null null null null null null null e:e:1929790192 null null null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeRootNodeRightRotAndMiddleNodeWithRightChild() {
    map.insert("l","a");
    map.insert("k","a");
    map.insert("m","a");
    map.insert("n","a");
    map.insert("j","a");
    map.insert("h","a");
    map.insert("f","a");
    map.insert("g","a");
    map.remove("k");
    map.remove("j");
    map.remove("f");
    map.remove("h");

    String[] expected = new String[] {
            "n:a:-1502686769",
            "l:a:-1170874532 null",
            "g:a:-895793374 m:a:95830475 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
}