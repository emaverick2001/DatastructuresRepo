package starter;

public class DoublyLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int numElements;

  private static class Node<E> {
    E data;
    Node<E> next;
    Node<E> prev;

    Node(E data) {
      this.data = data;
    }
  }

  public DoublyLinkedList() {
    head = null;
    tail = null;
    numElements = 0;
  }

}