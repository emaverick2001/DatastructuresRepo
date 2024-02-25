package solution;

import starter.OrderedSetTest;
import starter.Set;

public class BstOrderedSetTest extends OrderedSetTest {
  @Override
  protected Set<String> createSet() {
    return new BstOrderedSet<>();
  }
}
