package starter;

/**
 * An implementation of IndexedList that takes a default value
 * to plaster over the entire data structure.
 */
public class ArrayIndexedList<T> implements IndexedList {
  private int[] data;

  /**
   * Construct an ArrayIndexedList with given size and default value.
   *
   * @param size         the capacity of this list.
   * @param defaultValue an integer to plaster over the entire list.
   */
  public ArrayIndexedList(int size, int defaultValue) {
    data = new int[size];
    for (int i = 0; i < size; i++){
      data[i] = defaultValue;
    }
  }

  @Override
  public void put(int index, int value) {
    data[index] = value;
  }

  @Override
  public int get(int index) {
    return data[index];
  }

  @Override
  public int length() {
    return data.length;
  }
}