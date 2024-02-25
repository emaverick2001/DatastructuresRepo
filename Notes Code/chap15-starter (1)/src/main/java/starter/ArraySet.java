package starter;

import java.util.Iterator;

public class ArraySet<T> implements Set<T> {
  private T[] list;
  private int numElements;

  ArraySet() {
    numElements = 0;
    list = (T[]) new Object[1];
  }

  private void grow(){
    T[] bigger = (T[]) new Object[2 * numElements];

  }
  @Override
  public void insert(T t) {
    if (has(t)){
      return;
    }
    if (numElements >= list.length){
      grow();
    }
    list[numElements++] = t;
  }

  @Override
  public void remove(T t) {
    // TODO Implement Me!
  }

  private int find(T t){
    for(int i = 0; i < numElements; i++){
      if(list[i].equals(t)){
        return i;
      }
    }
    return -1;
  }
  @Override
  public boolean has(T t) {

    return find(t) != -1;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Implement Me!
    return null;
  }
}
