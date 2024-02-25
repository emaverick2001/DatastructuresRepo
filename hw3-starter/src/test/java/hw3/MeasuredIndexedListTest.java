package hw3;

import hw3.list.MeasuredIndexedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeasuredIndexedListTest {

  private static final int LENGTH = 15;
  private static final int DEFAULT_VALUE = 3;

  private MeasuredIndexedList<Integer> measuredIndexedList;

  @BeforeEach
  void setup() {
    measuredIndexedList = new MeasuredIndexedList<>(LENGTH, DEFAULT_VALUE);
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero reads")
  void zeroReadsStart() {
    assertEquals(0, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero writes")
  void zeroWritesStart() {
    assertEquals(0, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("check if each value in array is 3")
  void testArrayIsCreated() {

    for (int i = 0; i < LENGTH; i++) {
      assertEquals(DEFAULT_VALUE, measuredIndexedList.get(i));
    }
  }

  // GET

  @Test
  @DisplayName("get increases accessCount")
  void getIncAccessCount() {
    measuredIndexedList.get(0);
    assertEquals(1, measuredIndexedList.accesses());
  }

  //PUT

  @Test
  @DisplayName("put increases mutateCount and accessCount")
  void putIncMutateCount() {
    measuredIndexedList.put(0,5);
    assertEquals(1, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("Check if mutation() works with more than one put()")
  void MutationUpdateWhenPutCalledMultipleTimesTest() {
    measuredIndexedList.put(2, 4);
    assertEquals(1, measuredIndexedList.mutations());
    measuredIndexedList.put(5, 4);
    assertEquals(2, measuredIndexedList.mutations());
  }

  // COUNT

  @Test
  @DisplayName("count increases accessCount")
  void countIncAccessCount() {
    measuredIndexedList.count(3);
    assertEquals(15, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("count returns correct countValue for default value")
  void countGivesValidCountForDefaultValue() {
    assertEquals(15, measuredIndexedList.count(3));
  }

  @Test
  @DisplayName("count returns correct countValue for nonexistent value")
  void countGivesValidCountForNonexistentValue() {
    assertEquals(0, measuredIndexedList.count(5));
  }

  @Test
  @DisplayName("count returns correct countValue for specific value beginning of list")
  void countGivesValidCountForSpecificValueBeginningList() {
    measuredIndexedList.put(0,1);
    assertEquals(1, measuredIndexedList.count(1));
  }

  @Test
  @DisplayName("count returns correct countValue for specific value end of list")
  void countGivesValidCountForSpecificValueEndList() {
    measuredIndexedList.put(14,1);
    assertEquals(1, measuredIndexedList.count(1));
  }

  @Test
  @DisplayName("count returns correct countValue for specific value with multiple in list")
  void countGivesValidCountForMultipleInList() {
    measuredIndexedList.put(14,1);
    measuredIndexedList.put(0,1);
    measuredIndexedList.put(6,1);
    assertEquals(3, measuredIndexedList.count(1));
  }

  @Test
  @DisplayName("count returns correct accessValue for specific value with multiple in list")
  void countGivesValidAccessCountForMultipleInList() {
    measuredIndexedList.put(14,1);
    measuredIndexedList.put(0,1);
    measuredIndexedList.put(6,1);
    measuredIndexedList.count(1);
    assertEquals(15, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("count updates correct accessCount for specific value of list")
  void countGivesValidAccessCountForSpecificValue() {
    measuredIndexedList.put(0,1);
    measuredIndexedList.count(1);
    assertEquals(15, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("check if put() functions")
  void testPut() {
    measuredIndexedList.put(3,5);
    assertEquals(5, measuredIndexedList.get(3));
  }

  @Test
  @DisplayName("check if acesssess() works with get()")
  void testGetAcessesUpdateWhenGetCalled() {
    measuredIndexedList.get(3);
    assertEquals(1, measuredIndexedList.accesses());
  }
  @Test
  @DisplayName("check if mutation() works with put()")
  void testMutationUpdateWhenPutCalled() {
    measuredIndexedList.put(2,4);
    assertEquals(1, measuredIndexedList.mutations());
    measuredIndexedList.put(5,4);
    assertEquals(2, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("check if mutation() + accessor() works with put() and get()")
  void testMutationAndAccessesUpdateWhenPutAndGetCalled() {
    measuredIndexedList.put(2,4);
    assertEquals(1, measuredIndexedList.mutations());
    measuredIndexedList.get(2);
    assertEquals(1, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("check if count(T value) functions properly and if accesses updates properly")
  void testCountUpdateWhenCalled() {
    assertEquals(15, measuredIndexedList.count(3));
    assertEquals(15,measuredIndexedList.accesses());
  }
  @Test
  @DisplayName("check if count(T value) functions properly and if accesses updates properly when not all values in array are the same")
  void testCountUpdateWhenCalledCase1() {
    measuredIndexedList.put(5,5);
    assertEquals(1, measuredIndexedList.mutations());
    assertEquals(14,measuredIndexedList.count(3));
    assertEquals(15,measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Check if count() call increments accesses()")
  void CountShouldIncrementAccessesTest() {
    Integer value = measuredIndexedList.count(3);
    assertEquals(15, value);
    assertEquals(value, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("check if reset() works")
  void testReset() {
    measuredIndexedList.put(5,5);
    measuredIndexedList.reset();
    assertEquals(0, measuredIndexedList.mutations());
    measuredIndexedList.get(5);
    measuredIndexedList.reset();
    assertEquals(0,measuredIndexedList.accesses());
    measuredIndexedList.count(3);
    measuredIndexedList.reset();
    assertEquals(0,measuredIndexedList.accesses());
  }
  @Test
  @DisplayName("New array with call get() returns default value and accesses() is incremented")
  void NewArrayIsCreatedTest() {
    for (int i = 0; i < LENGTH; i++) {
      assertEquals(DEFAULT_VALUE, measuredIndexedList.get(i));
    }
    assertEquals(LENGTH, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Check if mutation() and accessor() works with put() and get() combined")
  void MutationAndAccessesUpdateWhenPutAndGetCalledTest() {
    measuredIndexedList.put(2,4);
    assertEquals(1, measuredIndexedList.mutations());
    measuredIndexedList.get(2);
    assertEquals(1, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("check if count(T value) functions properly and if accesses updates properly when not all values in array are the same")
  void CountUpdateWhenPutCalledTest() {
    measuredIndexedList.put(5,5);
    assertEquals(1, measuredIndexedList.mutations());
    assertEquals(14,measuredIndexedList.count(3));
    assertEquals(15,measuredIndexedList.accesses());
  }

}
