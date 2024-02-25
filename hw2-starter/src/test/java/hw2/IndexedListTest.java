package hw2;

import exceptions.IndexException;
//import exceptions.LengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Tests for any class implementing the IndexedList interface.
 */
public abstract class IndexedListTest {
  protected static final int LENGTH = 3;
  protected static final int INITIAL = 7;
  private IndexedList<Integer> indexedList;

  public abstract IndexedList<Integer> createArray();

  @BeforeEach
  public void setup() {
    indexedList = createArray();
  }

  //test indexes

  @Test
  @DisplayName("Check to see if construction of sparselist produces correct length")
  void testEmptySparseListLength() {
    assertEquals(LENGTH, indexedList.length());
  }

  // Constructor
  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    for (int index = 0; index < indexedList.length(); index++) {
      assertEquals(INITIAL, indexedList.get(index));
    }
  }

  @Test
  @DisplayName("constructor throws exception if size is <= 0.")
  void testConstructorThrowsExceptionForInvalidSize() {
    try {
      indexedList.put(-3,5);
      fail("IndexException was not thrown where it was expected!");
    } catch (IndexException ex) {
      return;
    }
  }

  // Get
  @Test
  @DisplayName("get() accesses first index val with node")
  void testGetWithFirstValidIndex() {

    indexedList.put(0, 5);
    indexedList.put(1, 6);
    indexedList.put(2, 8);
    assertEquals(5, indexedList.get(0));
  }

  @Test
  @DisplayName("get() accesses first index val with node")
  void testGetWithSecondValidIndex() {
    indexedList.put(0, 5);
    indexedList.put(1, 6);
    indexedList.put(2, 8);
    assertEquals(6, indexedList.get(1));
  }

  @Test
  @DisplayName("get() accesses last index val with node")
  void testGetWithLastValidIndex() {
    indexedList.put(0, 5);
    indexedList.put(1, 6);
    indexedList.put(2, 8);
    assertEquals(8, indexedList.get(2));
  }

  @Test
  @DisplayName("get() accesses val at index without a node but still valid")
  void testGetWithDefaultValue() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(3, 7);
    test.put(0, 5);
    test.put(1, 6);
    assertEquals(7, test.get(2));
  }

  @Test
  @DisplayName("get() accesses val at not a valid index")
  void testGetWithIndexGreaterThanLength() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(3, 7);
    test.put(0, 5);
    test.put(1, 6);
    try {
      test.get(3);
      fail("did not catch exception for index > length");
    } catch (IndexException e) {
      return;
    }
  }

  @Test
  @DisplayName("get() throws exception if index is below the valid range.")
  void testGetWithIndexBelowRangeThrowsException() {
    try {
      indexedList.get(-1);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }


  // UNIT TESTS for put()

  @Test
  @DisplayName("put() changes val at index with a Node")
  void testPutWithNode() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(3, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);

    test.put(1, 4);
    assertEquals(4, test.get(1));
  }

  @Test
  @DisplayName("put() throws exception if index is below the valid range.")
  void testPutWithIndexBelowRangeThrowsException() {
    try {
      indexedList.put(-1, 10);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("put() throws exception if index is above the valid range.")
  void testPutWithIndexAboveRangeThrowsException() {
    try {
      indexedList.put(LENGTH + 1, 10);
      fail("IndexException was not thrown for index > length");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("put() adds node when sparselist is empty.")
  void testPutWithEmptyList() {
    indexedList.put(0, 3);
    assertEquals(3, indexedList.get(0));
  }

// can we have 2 of the same values in sparse list?

  @Test
  @DisplayName("put() adds node when node does't already exist in list with default value.")
  void testPutAddsNewNodeWithDefaultValueAtIndexWithNoNode() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(3, 7);
    test.put(0, 5);
    test.put(1, 6);

    test.put(2, 7);

    assertEquals(7, test.get(2));
  }

  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    indexedList.put(1, 3);
    indexedList.put(1, 8);
    assertEquals(8, indexedList.get(1));
    indexedList.put(1, -5);
    assertEquals(-5, indexedList.get(1));
  }

  //DEFAULT VALUE UNIT TESTS
  @Test
  @DisplayName("put() changes last node val to default val and deletes node.")
  void testPutDeletesNodeAtLastIndexSinceItIsDefaultValue() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(4, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);

    test.put(2, 7);

    assertEquals(7, test.get(2));
  }

  @Test
  @DisplayName("put() changes beginning node val to default val and deletes node.")
  void testPutDeletesNodeAtFirstIndexSinceItIsDefaultValue() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(3, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);

    test.put(0, 7);

    // edge case when index dne
    assertEquals(7, test.get(0));
  }

  @Test
  @DisplayName("put() changes middle node val to default val and deletes node.")
  void testPutDeletesNodeAtIndexInMiddleSinceItIsDefaultValue() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(3, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);

    test.put(1, 7);

    assertEquals(7, test.get(1));
  }

  @Test
  @DisplayName("put() changes middle nodes val to default val and deletes node with 4 nodes.")
  void testPutDeletesNodeAtIndexInMiddleSinceItIsDefaultValueWithFiveNodesFirst() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(4, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);
    test.put(3, 9);

    test.put(1, 7);
    test.put(2, 7);

    assertEquals(7, test.get(2));
  }

  @Test
  @DisplayName("put() changes middle nodes val to default val and deletes node with 4 nodes.")
  void testPutDeletesNodeAtFirstIndexSinceItIsDefaultValueWithFiveNodesSecond() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(4, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);
    test.put(3, 9);

    test.put(1, 7);

    assertEquals(7, test.get(1));

  }

  @Test
  @DisplayName("put() changes middle nodes val to default val and deletes node with 4 nodes.")
  void testPutAddsNodeToNodeThatWasDeletedSinceItsValueChangedToDefaultValue() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(4, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);
    test.put(3, 9);

    test.put(1, 7);
    assertEquals(7, test.get(1));

    test.put(1, 3);
    assertEquals(3, test.get(1));
  }

  @Test
  @DisplayName("put() changes middle nodes val to default val and deletes node with 4 nodes.")
  void testPutAddsNodeToNodeThatWasDeletedAtFirstIndexSinceItsValueChangedToDefaultValue() {
    SparseIndexedList<Integer> test = new SparseIndexedList<>(4, 7);
    test.put(0, 5);
    test.put(1, 6);
    test.put(2, 8);
    test.put(3, 9);

    test.put(0, 7);
    assertEquals(7, test.get(0));

    test.put(0, 3);
    assertEquals(3, test.get(0));
  }


// UNIT TESTS FOR ITERATOR

@Test
@DisplayName("test iterator when all indexes filled with non default")
void testEnhancedLoopCompletelyFilled() {
  int counter = 0;
  indexedList.put(0, 5);
  indexedList.put(1, 6);
  indexedList.put(2, 8);

  for (int element : indexedList) {
    assertEquals(element, indexedList.get(counter));
    counter++;
  }
  assertEquals(LENGTH, counter);
}

@Test
@DisplayName("test iterator after IndexedList is instantiated (all indexes have default value).")
void testEnhancedLoopAfterConstruction() {
  int counter = 0;

  for (int element : indexedList) {
    assertEquals(element, indexedList.get(counter));
    counter++;
  }
  assertEquals(LENGTH, counter);
}

@Test
@DisplayName("test iterator when last element is default")
void testEnhancedLoopLastElementDefault() {
  int counter = 0;
  indexedList.put(0, 5);
  indexedList.put(1, 6);

  for (int element : indexedList) {
    assertEquals(element, indexedList.get(counter));
    counter++;
  }
  assertEquals(LENGTH, counter);
}

  @Test
  @DisplayName("test iterator when first index has default")
  void testEnhancedLoopFirstElementDefault() {
    int counter = 0;

    indexedList.put(1, 6);
    indexedList.put(2, 8);

    for (int element : indexedList) {
      assertEquals(element, indexedList.get(counter));
      counter++;
    }
    assertEquals(LENGTH, counter);
  }

  @Test
  @DisplayName("test iterator when middle index has default")
  void testEnhancedLoopMiddleElementDefault() {
    int counter = 0;

    indexedList.put(0, 5);
    indexedList.put(2, 8);

    for (int element : indexedList) {
      assertEquals(element, indexedList.get(counter));
      counter++;
    }
    assertEquals(LENGTH, counter);
  }


}


