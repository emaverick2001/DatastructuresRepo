package starter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class IndexedListTest {
  private IndexedList<Integer> numbers;
  private final static int size = 5;
  private final static int defaultValue = 10;

  @BeforeEach
  void setUp() {
    numbers = new ArrayIndexedList<>(size, defaultValue);
  }

  @Test
  @DisplayName("Constructor should throw custom exception when size < 0 ")
  void testConstructorWithInvalidSizeNegative(){
    try {
      numbers = new ArrayIndexedList<>(-1, 2);
      fail("LengthException was not thrown for index < 0");
    } catch (LengthException e) {
      return;
    }
  }

  @Test
  @DisplayName("Constructor should throw custom exception when size <= 0 ")
  void testConstructorWithInvalidSizeZero(){
    try {
      numbers = new ArrayIndexedList<>(0, 2);
      fail("LengthException was not thrown for index <= 0");
    } catch (LengthException e) {
      return;
    }
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    for (int index = 0; index < numbers.length(); index++) {
      assertEquals(defaultValue, numbers.get(index));
    }
  }

  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstruction() {
    numbers.put(2, 7);
    assertEquals(7, numbers.get(2));
  }

  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    numbers.put(1, 8);
    assertEquals(8, numbers.get(1));
    numbers.put(1, -5);
    assertEquals(-5, numbers.get(1));
  }

  @Test
  @DisplayName("length() returns the correct size after IndexedList is instantiated.")
  void testLengthAfterConstruction() {
    assertEquals(size, numbers.length());
  }

  @Test
  @DisplayName("get() throws exception if index is below the valid range.")
  void testGetWithIndexBelowRangeThrowsException() {
    try {
      numbers.get(-1);
      fail("IndexOutOfBoundsException was not thrown for index < 0");
    } catch (IndexOutOfBoundsException ex) {
      return;
    }
  }

  @Test
  @DisplayName("get() throws exception if index is above the valid range.")
  void testGetWithIndexAboveRangeThrowsException() {
    try {
      numbers.get(size + 1);
      fail("IndexOutOfBoundsException was not thrown for index > length");
    } catch (IndexOutOfBoundsException ex) {
      return;
    }
  }


}
