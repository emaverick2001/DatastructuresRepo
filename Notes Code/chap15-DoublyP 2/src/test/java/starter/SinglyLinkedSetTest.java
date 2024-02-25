package starter;

/**
 * Instantiate the LinkedSet to test.
 */
public class SinglyLinkedSetTest extends SetTest {
  @Override
  protected Set<String> createUnit() {
    return new SinglyLinkedSet<>();
  }
}