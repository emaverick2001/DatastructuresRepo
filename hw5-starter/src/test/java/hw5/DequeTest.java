package hw5;

import exceptions.EmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class DequeTest {

  private Deque<String> deque;

  @BeforeEach
  public void setUp() {
    this.deque = createDeque();
  }

  protected abstract Deque<String> createDeque();

  //test constructor
  @Test
  @DisplayName("Deque is empty after construction.")
  public void testConstructor() {
    assertTrue(deque.empty());
    assertEquals(0, deque.length());
  }

  //test front
  @Test
  @DisplayName("Deque throws empty exception when front is called")
  public void testFrontEmpty() {
    try {
      deque.front();
      fail("deque doesnt throw empty exception");
    } catch (EmptyException e){
      return;
    }
  }

  // test back
  @Test
  @DisplayName("Deque throws empty exception when back is called")
  public void testBackEmpty() {
    try {
      deque.back();
      fail("deque doesnt throw empty exception");
    } catch (EmptyException e){
      return;
    }
  }

  @Test
  @DisplayName("Deque prints correct value when back is called")
  public void testBackNotEmptyAfterInsertBack() {
    try {
      deque.insertBack("1");
      deque.back();
      return;
    } catch (EmptyException e){
      fail();
    }
  }

  @Test
  @DisplayName("Deque prints correct value when back is called")
  public void testBackNotEmptyAfterInsertFront() {
    try {
      deque.insertFront("1");
      deque.back();
      return;
    } catch (EmptyException e){
      fail();
    }
  }


  // test remove back
  @Test
  @DisplayName("Deque throws empty exception when removeBack is called")
  public void testRemoveBackEmpty() {
    try {
      deque.removeBack();
      fail("deque doesnt throw empty exception");
    } catch (EmptyException e){
      return;
    }
  }

  // test remove front
  @Test
  @DisplayName("Deque throws empty exception when removeFront is called")
  public void testRemoveFrontEmpty() {
    try {
      deque.removeFront();
      fail("deque doesnt throw empty exception");
    } catch (EmptyException e){
      return;
    }
  }

  // test insertFront
  @Test
  @DisplayName("Deque length 1 when insertFront called.")
  public void testInsertFrontLength() {
    deque.insertFront("1");
    assertFalse(deque.empty());
    assertEquals(1,deque.length());
  }

  @Test
  @DisplayName("Deque prints correct value when front called")
  public void testFrontOneNode() {
    deque.insertFront("1");
    assertFalse(deque.empty());
    assertEquals("1",deque.front());
  }

  @Test
  @DisplayName("Deque prints correct value when front called after adding multiple nodes")
  public void testFrontMultipleNodes() {
    deque.insertFront("1");
    deque.insertFront("2");
    deque.insertFront("3");
    assertFalse(deque.empty());
    assertEquals("3",deque.front());
  }

  @Test
  @DisplayName("Deque list has correct order after using insertFront")
  public void testInsertFrontMultipleNodes() {
    // 3 2 1
    deque.insertFront("1");
    deque.insertFront("2");
    deque.insertFront("3");
    assertFalse(deque.empty());
    for (int i = 3; i > 0; i--){
      assertEquals(i + "" ,deque.front());
      assertEquals(i,deque.length());
      deque.removeFront();
    }
    assertEquals(0,deque.length());
    assertTrue(deque.empty());
  }

  // test insertBack
  @Test
  @DisplayName("Deque length 1 after insertingBack 1 value")
  public void testInsertBackLength() {
    deque.insertBack("1");
    assertFalse(deque.empty());
    assertEquals(1,deque.length());
  }

  @Test
  @DisplayName("Deque prints correct value when back called")
  public void testBackOneNode() {
    deque.insertBack("1");
    assertFalse(deque.empty());
    assertEquals("1",deque.back());
  }

  @Test
  @DisplayName("Deque prints correct value when back called with multiple nodes")
  public void testBackMultipleNodes() {
    deque.insertBack("1");
    deque.insertBack("2");
    deque.insertBack("3");
    assertFalse(deque.empty());
    // verifying front of list isn't reordered
    assertEquals("1",deque.front());
    assertEquals("3",deque.back());
  }

  @Test
  @DisplayName("Deque has correct configuration after inserting Back multiple nodes")
  public void testInsertBackMultipleNodes() {
    // 1 2 3
    deque.insertBack("1");
    assertEquals(1,deque.length());
    deque.insertBack("2");
    assertEquals(2,deque.length());
    deque.insertBack("3");
    assertEquals(3,deque.length());
    assertFalse(deque.empty());

    // checking each element is consistent with ones added
    for (int i = 1; i <= 3; i++){
      assertEquals(i + "" ,deque.front());
      deque.removeFront();
    }
    assertEquals(0,deque.length());
    assertTrue(deque.empty());
  }

  // testing flaw is consistent with multiple nodes
  @Test
  @DisplayName("Deque insertBack skips every other call with multiple nodes")
  public void testInsertBackManyNodes() {
    deque.insertBack("1");
    assertEquals(1,deque.length());
    assertEquals("1",deque.back());
    deque.insertBack("2");
    assertEquals(1,deque.length());
    assertEquals("1",deque.back());
    deque.insertBack("2");
    assertEquals(2,deque.length());
    assertEquals("2",deque.back());
    deque.insertBack("3");
    assertEquals(2,deque.length());
    assertEquals("2",deque.back());
    deque.insertBack("3");
    assertEquals(3,deque.length());
    assertEquals("3",deque.back());
  }

  //checking if flaw apparent when insertFront is used
  @Test
  @DisplayName("Deque insertFront adds node each call")
  public void testInsertFrontManyNodes() {
    deque.insertFront("1");
    assertEquals(1,deque.length());
    assertEquals("1",deque.front());
    deque.insertFront("2");
    assertEquals(2,deque.length());
    assertEquals("2",deque.front());
    deque.insertFront("3");
    assertEquals(3,deque.length());
    assertEquals("3",deque.front());
    deque.insertFront("4");
    assertEquals(4,deque.length());
    assertEquals("4",deque.front());
  }

  // testing removeBack and removeFront
  @Test
  @DisplayName("Deque removes entire list after inserting with insertFront and removing with removeBack")
  public void testInsertFrontAndRemoveBackMultipleNodes() {
    //1 2 3
    deque.insertFront("3");
    deque.insertFront("2");
    deque.insertFront("1");
    assertFalse(deque.empty());
    for (int i = 3; i > 0; i--){
      assertEquals(i,deque.length());
      deque.removeBack();
    }
    assertEquals(0,deque.length());
    assertTrue(deque.empty());

  }

  @Test
  @DisplayName("Deque removes entire list after inserting with insertFront and removing with removeFront")
  public void testInsertFrontAndRemoveFrontMultipleNodes() {
    // 1 2 3
    deque.insertFront("3");
    deque.insertFront("2");
    deque.insertFront("1");
    assertFalse(deque.empty());
    for (int i = 3; i > 0; i--){
      assertEquals(i,deque.length());
      deque.removeFront();
    }
    assertEquals(0,deque.length());
    assertTrue(deque.empty());

  }

  @Test
  @DisplayName("Deque removes entire list after inserting with insertBack and removing with removeFront")
  public void testInsertBackAndRemoveFrontMultipleNodes() {
    // 1 2 3
    deque.insertBack("1");
    deque.insertBack("2");
    deque.insertBack("3");
    assertFalse(deque.empty());
    for (int i = 3; i > 0; i--){
      assertEquals(i,deque.length());
      deque.removeFront();
    }
    assertEquals(0,deque.length());
    assertTrue(deque.empty());

  }

  @Test
  @DisplayName("Deque removes entire list after inserting with insertBack and removing with removeFront")
  public void testInsertBackAndRemoveBackMultipleNodes() {
    // 1 2 3
    deque.insertBack("1");
    deque.insertBack("2");
    deque.insertBack("3");
    assertFalse(deque.empty());
    for (int i = 3; i > 0; i--){
      assertEquals(i,deque.length());
      deque.removeBack();
    }
    assertEquals(0,deque.length());
    assertTrue(deque.empty());

  }

}
