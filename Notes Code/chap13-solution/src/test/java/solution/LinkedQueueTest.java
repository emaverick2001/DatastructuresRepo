package solution;

import starter.Queue;
import starter.QueueTest;

public class LinkedQueueTest extends QueueTest {
  @Override
  protected Queue<Integer> createQueue() {
    return new LinkedQueue<>();
  }
}
