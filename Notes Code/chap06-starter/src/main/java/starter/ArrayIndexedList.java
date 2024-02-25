package starter;

import exception.IndexException;
import exception.LengthException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An implementation of IndexedList that takes a default value
 * to plaster over the entire data structure.
 *
 * @param <T> the base type of the items in the IndexedList.
 */
public class ArrayIndexedList<T> implements IndexedList<T> {
  private final T[] data;

  /**
   * Construct an ArrayIndexedList with given size and default value.
   *
   * @param size         the capacity of this list.
   * @param defaultValue a default value to plaster over the entire list.
   * @throws LengthException when size <= 0
   */
  public ArrayIndexedList(int size, T defaultValue) throws LengthException {
    if (size <= 0) {
      throw new LengthException();
    }

    data = (T[]) new Object[size];
    for (int i = 0; i < size; i++) {
      data[i] = defaultValue;
    }
  }

  // returns an iterator object for the class
  @Override
  public Iterator<T> iterator() {
    // iterator object used to iterate through lists

    return new ArrayIndexedListIterator();
  }

  //want to override methods in Iterator interface
  private class ArrayIndexedListIterator implements Iterator<T> {
    private int index;

    ArrayIndexedListIterator(){
      index = 0;
    }

    //    while(index < data.length){
    //      Integer val = it.next();
    //      print(val)
    //    }

    // .length gives number of items, or 1 minus the length
    // is this relative to the current index? Doesnt this need to be specified for the next element?
    // tells you if you have another element available to iterate over
    //like saying has current
    @Override
    public boolean hasNext() {
      return index < data.length;
    }


    // returns current and then inc index
    @Override
    public T next() {
      if (!hasNext()){
        throw new NoSuchElementException();
      }
      return (T) data[index++];
    }
  }

  @Override
  public void put(int index, T value) throws IndexException {
    if (index < 0 || index > length()) {
      throw new IndexException();
    }

    data[index] = value;
  }

  @Override
  public T get(int index) throws IndexException {
    if (index >= 0 && index < length()) {
      return data[index];
    } else {
      throw new IndexException();
    }
  }



  @Override
  public int length() {
    return data.length;
  }
}

