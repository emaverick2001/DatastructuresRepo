package starter;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class IndexedListTest {

  private IndexedList<Integer> numbers;

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
  IndexedList<Integer> numbers = new ArrayIndexedList<>(5,2);
  assertEquals(2,numbers.get(0));
    // TODO Implement me!
  }

  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstruction() {
    // TODO Implement me!
  }

  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    // TODO Implement me!
  }
}