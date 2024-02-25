package hw5;


/**
 * Set implemented using a doubly linked list and move-to-front heuristic.
 *
 * @param <T> Element type.
 */
public class MoveToFrontLinkedSet<T> extends LinkedSet<T> {

  // Performs linear search.
  // Returns the target node containing t or null if t was not found.
  // Pre: t != null

  @Override
  protected Node<T> find(T t) {
    for (Node<T> n = head; n != null; n = n.next) {
      if (n.data.equals(t)) {
        // remove target element
        remove(n);
        // add to front of list to make element easier to find
        n.prev = null;
        if (head == null) {
          head = n;
          tail = n;
          return n;
        }
        n.next = head;
        head.prev = n;
        head = n;
        return n;
      }
    }
    return null;
  }



  // TODO: incorporate move-to-front heuristic each time a value is accessed.
  //  Override the relevant method(s) from LinkedSet.

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    MoveToFrontLinkedSet<String> test = new MoveToFrontLinkedSet<>();
    String[] values = {"5","6","7","8","10","10"};
    for (String val : values) {
      test.insert(val);
    }
    test.insert("10");
    System.out.println(test.has("5"));
    test.remove("9");
    System.out.println(test.size());

    for (String val : values) {
      test.remove(val);
    }
    System.out.println(test.size());
    System.out.println(test.has("5"));
  }
}

