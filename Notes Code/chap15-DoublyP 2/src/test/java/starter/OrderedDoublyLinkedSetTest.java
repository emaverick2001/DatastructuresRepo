package starter;

/**
 * Instantiate the LinkedSet to test.
 */
public class OrderedDoublyLinkedSetTest extends SetTest {
  @Override
  protected Set<String> createUnit() {
    return new OrderedDoublyLinkedSet<>();
  }
}
