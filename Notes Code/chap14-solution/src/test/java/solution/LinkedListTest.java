package solution;

import starter.List;
import starter.ListTest;

public class LinkedListTest extends ListTest {
  @Override
  protected List<String> createList() {
    return new LinkedList<>();
  }
}
