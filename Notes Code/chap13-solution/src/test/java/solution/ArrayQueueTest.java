package solution;

import starter.Queue;
import starter.QueueTest;

public class ArrayQueueTest extends QueueTest {
  @Override
  protected Queue<Integer> createQueue() {
    return new ArrayQueue<>();
  }
}
