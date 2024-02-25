package hw3.list;

import exceptions.IndexException;

/**
 * An ArrayIndexedList that is able to report the number of
 * accesses and mutations, as well as reset those statistics.
 *
 * @param <T> The type of the array.
 */
public class MeasuredIndexedList<T> extends ArrayIndexedList<T>
    implements Measured<T> {
  private int accessCount;
  private int mutateCount;

  /**
   * Constructor for a MeasuredIndexedList.
   *
   * @param size         The size of the array.
   * @param defaultValue The initial value to set every object to in the array..
   */
  public MeasuredIndexedList(int size, T defaultValue) {
    super(size, defaultValue);
    accessCount = 0;
    mutateCount = 0;
  }

  /*
  * doesnt increase accessCount
  */
  @Override
  public int length() {
    return super.length();
  }

  private boolean isValid(int index) {
    return index < 0 || index >= length();
  }

  @Override
  public T get(int index) throws IndexException {
    if (isValid(index)) {
      throw new IndexException();
    }
    T val = super.get(index);
    accessCount++;
    return val;
  }

  /* put accesses data at index but doesnt increases accessCount*/
  @Override
  public void put(int index, T value) throws IndexException {
    if (isValid(index)) {
      throw new IndexException();
    }
    super.put(index, value);
    mutateCount++;
  }

  @Override
  public void reset() {
    accessCount = 0;
    mutateCount = 0;
  }

  @Override
  public int accesses() {
    return accessCount;
  }

  @Override
  public int mutations() {
    return mutateCount;
  }

  @Override
  public int count(T value) {
    int count = 0;
    for (int i = 0; i < super.length(); i++) {
      if (get(i).equals(value)) {
        count++;
      }
    }
    return count;
  }

}
