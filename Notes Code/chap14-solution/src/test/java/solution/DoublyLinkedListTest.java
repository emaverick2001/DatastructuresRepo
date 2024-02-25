package solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoublyLinkedListTest {
  private DoublyLinkedList<Integer> linkedList;

  @BeforeEach
  void setUp() {
    linkedList = new DoublyLinkedList<>();
  }

  @Test
  void addFirstEmptyList() {
    linkedList.addFirst(10);
    assertEquals(1, linkedList.length());
    assertEquals(10, linkedList.get(0));
  }

  @Test
  void addFirstNonEmptyList() {
    linkedList.addFirst(10);
    linkedList.addFirst(7);
    linkedList.addFirst(9);
    linkedList.addFirst(5);
    assertEquals(4, linkedList.length());
    assertEquals(5, linkedList.get(0));
    assertEquals(9, linkedList.get(1));
    assertEquals(7, linkedList.get(2));
    assertEquals(10, linkedList.get(3));
  }

  @Test
  void addLastEmptyList() {
    linkedList.addLast(10);
    assertEquals(1, linkedList.length());
    assertEquals(10, linkedList.get(0));
  }

  @Test
  void addLastNonEmptyList() {
    linkedList.addLast(9);
    linkedList.addLast(7);
    linkedList.addLast(10);
    linkedList.addLast(5);
    assertEquals(4, linkedList.length());
    assertEquals(9, linkedList.get(0));
    assertEquals(7, linkedList.get(1));
    assertEquals(10, linkedList.get(2));
    assertEquals(5, linkedList.get(3));
  }

  @Test
  void deleteTheOnlyElement() {
    DoublyLinkedList.Node<Integer> target = linkedList.addFirst(10);
    linkedList.delete(target);
    assertEquals(0, linkedList.length());
  }

  @Test
  void deleteFirstElement() {
    DoublyLinkedList.Node<Integer> target = linkedList.addFirst(1);
    linkedList.addLast(2);
    linkedList.addLast(3);
    linkedList.delete(target);
    assertEquals(2, linkedList.length());
    assertEquals(2, linkedList.get(0));
    assertEquals(3, linkedList.get(1));
  }

  @Test
  void deleteLastElement() {
    linkedList.addFirst(1);
    linkedList.addLast(2);
    DoublyLinkedList.Node<Integer> target = linkedList.addLast(3);
    linkedList.delete(target);
    assertEquals(2, linkedList.length());
    assertEquals(1, linkedList.get(0));
    assertEquals(2, linkedList.get(1));
  }

  @Test
  void deleteMiddleElement() {
    linkedList.addFirst(1);
    DoublyLinkedList.Node<Integer> target = linkedList.addLast(2);
    linkedList.addLast(3);
    linkedList.delete(target);
    assertEquals(2, linkedList.length());
    assertEquals(1, linkedList.get(0));
    assertEquals(3, linkedList.get(1));
  }

  @Test
  void deleteAllElements() {
    DoublyLinkedList.Node<Integer> one = linkedList.addFirst(1);
    DoublyLinkedList.Node<Integer> two = linkedList.addLast(2);
    DoublyLinkedList.Node<Integer> three = linkedList.addLast(3);
    linkedList.delete(one);
    linkedList.delete(two);
    linkedList.delete(three);
    assertEquals(0, linkedList.length());

    one = linkedList.addFirst(1);
    two = linkedList.addLast(2);
    three = linkedList.addLast(3);
    linkedList.delete(three);
    linkedList.delete(two);
    linkedList.delete(one);
    assertEquals(0, linkedList.length());
  }

  @Test
  void insertAfterFirstElement() {
    DoublyLinkedList.Node<Integer> target;
    target = linkedList.addFirst(10);
    linkedList.addLast(9);
    linkedList.addLast(5);

    linkedList.insertAfter(target, 7);

    assertEquals(4, linkedList.length());
    assertEquals(10, linkedList.get(0));
    assertEquals(7, linkedList.get(1));
    assertEquals(9, linkedList.get(2));
    assertEquals(5, linkedList.get(3));
  }

  @Test
  void insertAfterLastElement() {
    DoublyLinkedList.Node<Integer> target;
    linkedList.addFirst(10);
    linkedList.addLast(9);
    target = linkedList.addLast(5);

    linkedList.insertAfter(target, 7);

    assertEquals(4, linkedList.length());
    assertEquals(10, linkedList.get(0));
    assertEquals(9, linkedList.get(1));
    assertEquals(5, linkedList.get(2));
    assertEquals(7, linkedList.get(3));
  }

  @Test
  void insertAfterMiddleElement() {
    DoublyLinkedList.Node<Integer> target;
    linkedList.addFirst(10);
    target = linkedList.addLast(9);
    linkedList.addLast(5);

    linkedList.insertAfter(target, 7);

    assertEquals(4, linkedList.length());
    assertEquals(10, linkedList.get(0));
    assertEquals(9, linkedList.get(1));
    assertEquals(7, linkedList.get(2));
    assertEquals(5, linkedList.get(3));
  }

  @Test
  void insertBeforeFirstElement() {
    DoublyLinkedList.Node<Integer> target;
    target = linkedList.addFirst(10);
    linkedList.addLast(9);
    linkedList.addLast(5);

    linkedList.insertBefore(target, 7);

    assertEquals(4, linkedList.length());
    assertEquals(7, linkedList.get(0));
    assertEquals(10, linkedList.get(1));
    assertEquals(9, linkedList.get(2));
    assertEquals(5, linkedList.get(3));
  }

  @Test
  void insertBeforeLastElement() {
    DoublyLinkedList.Node<Integer> target;
    linkedList.addFirst(10);
    linkedList.addLast(9);
    target = linkedList.addLast(5);

    linkedList.insertBefore(target, 7);

    assertEquals(4, linkedList.length());
    assertEquals(10, linkedList.get(0));
    assertEquals(9, linkedList.get(1));
    assertEquals(7, linkedList.get(2));
    assertEquals(5, linkedList.get(3));
  }

  @Test
  void insertBeforeMiddleElement() {
    DoublyLinkedList.Node<Integer> target;
    linkedList.addFirst(10);
    target = linkedList.addLast(9);
    linkedList.addLast(5);

    linkedList.insertBefore(target, 7);

    assertEquals(4, linkedList.length());
    assertEquals(10, linkedList.get(0));
    assertEquals(7, linkedList.get(1));
    assertEquals(9, linkedList.get(2));
    assertEquals(5, linkedList.get(3));
  }
}