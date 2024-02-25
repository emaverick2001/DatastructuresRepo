package hw5;

/**
 * Set implemented using plain Java arrays and transpose-sequential-search heuristic.
 *
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {

  // Returns the index of t in data or -1 if it is not found.
  // Pre: t != null
  @Override
  protected int find(T t) {
    for (int i = 0; i < numElements; i++) {
      if (t.equals(data[i])) {
        if (i == 0) {
          return i;
        }
        T curr = data[i];
        data[i] = data[i - 1];
        data[i - 1] = curr;
        return i - 1;
      }
    }
    return -1;
  }


  // TODO: incorporate the transpose-sequential-search heuristic
  //  each time a value is accessed. Override the relevant method(s) from ArraySet.

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    TransposeArraySet<String> test = new TransposeArraySet<>();
    String[] vals = {"5","6","7","8","10","8","8","8"};
    for (String val : vals) {
      test.insert(val);
    }
    test.insert("10");
    System.out.println(test.has("5"));
    test.remove("9");
    System.out.println(test.size());

    for (String val : vals) {
      test.remove(val);
    }
    System.out.println(test.size());
    System.out.println(test.has("5"));
  }
}
