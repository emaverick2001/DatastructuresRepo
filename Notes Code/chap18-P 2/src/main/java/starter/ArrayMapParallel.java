package starter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class ArrayMapParallel<K, V> implements Map<K, V> {
   private Entry<K, V>[] data;

  private K[] keys;
  private V[] values;

  int size;

  /**
   * Create an empty map.
   */
  public ArrayMapParallel() {
    this.data = (Entry<K,V>[]) new Object[5];
    this.keys = (K[]) new Object[5];
    this.values = (V[]) new Object[5];
    this.size = 0;
  }

  // Entry to store a key, and a value pair.
  private static class Entry<K,V> {
    K key;
    V value;

    Entry(K k, V v) {
      this.key = k;
      this.value = v;
    }
  }
  // Find key k, throw exception if k is null.
  private int find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }

    for (int i = 0; i < keys.length; i++) {
      if (keys[i].equals(k)) {
        return i;
      }
    }
    return -1;
  }

  // Find entry for key k, throw exception if k not mapped.
  private int findForSure(K k) {
    int index = find(k);
    if (index == -1) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return index;
  }

  @Override
  public void insert(K k, V v) {
    int index = find(k);
    if (index != -1) {
      throw new IllegalArgumentException("duplicate key " + k);
    }
   keys[index] = k;
    this.data.add(e);
  }

  @Override
  public V remove(K k) {
    int index = findForSure(k);
    V v = values[index];
    keys[index] = null;
    values[index] = null;
    return v;
  }

  @Override
  public void put(K k, V v) {
    int index = findForSure(k);
    values[index] = v;
  }

  @Override
  public V get(K k) {
    return values[findForSure(k)];
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    return this.find(k) != -1;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterator<K> iterator() {
    List<K> keys = new ArrayList<K>();
    for (Entry<K,V> e : this.data) {
      keys.add(e.key);
    }
    return keys.iterator();
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("{");
    for (int i = 0; i < this.data.size(); i++) {
      Entry<K,V> e = this.data.get(i);
      s.append("" + e.key + ": " + e.value);
      if (i < this.data.size() - 1) {
        s.append(", ");
      }
    }
    s.append("}");
    return s.toString();
  }

}
