package starter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Map implemented using an unsorted ArrayList internally.
 *
 * <p>This implementation is deliberately inefficient so all
 * operations take O(n) time. The iterator() method makes a copy of
 * all keys, so it takes O(n) as well. The only positive thing here
 * is its simplicity.</p>
 *
 * <p>Note that we could do slightly better (in several ways!) by using
 * two arrays in parallel and managing our own insert/remove. You
 * should probably think about this because it makes for a great exam
 * question... :-)</p>
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class ArrayMap<K, V> implements Map<K, V> {

  private List<Entry<K,V>> data;
  private int size;

  /**
   * Create an empty map.
   */
  public ArrayMap() {
    this.data = new ArrayList<>();
    size = 0;
  }

  private static class Entry<K,V> {
    K k;
    V v;

    Entry() {
      k = null;
      v = null;
    }
  }


  @Override
  public void insert(K k, V v) {
    Entry<K,V> result = find(k);
    if (result != null) {
      throw new IllegalArgumentException("no duplicates");
    }
    Entry<K,V> n = new Entry<>();
    n.k = k;
    n.v = v;
    data.add(n);
    size++;
  }

  @Override
  public V remove(K k) {
    Entry<K,V> result = findForSure(k);
    V v = result.v;
    this.data.remove(result);
    size--;
    return v;
  }

  @Override
  public void put(K k, V v) {
    Entry<K,V> result = findForSure(k);
    result.v = v;
  }

  @Override
  public V get(K k) {
    Entry<K,V> result = findForSure(k);
    return result.v;
  }

  // Find entry for key k, throw exception if k is null.
  private Entry<K,V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }

    for (Entry<K,V> e : data) {
      if (e.k.equals(k)) {
        return e;
      }
    }
    return null;
  }

  // Find entry for key k, throw exception if k not mapped.
  private Entry<K,V> findForSure(K k) {
    Entry<K,V> result = find(k);
    if (result == null) {
      throw new IllegalArgumentException();
    }
    return result;
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }

  @Override
  public int size() {
    return size;
  }

  //we want to get he keys from each entry object
  @Override
  public Iterator<K> iterator() {
    List<K> keys = new ArrayList<>();
    for (Entry<K,V> e: data) {
      keys.add(e.k);
    }
    return keys.iterator();
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("{");
    for (int i = 0; i < this.data.size(); i++) {
      Entry<K,V> e = this.data.get(i);
      s.append("" + e.k + ": " + e.v);
      if (i < this.data.size() - 1) {
        s.append(", ");
      }
    }
    s.append("}");
    return s.toString();
  }

}
