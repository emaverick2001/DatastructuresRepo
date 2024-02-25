package hw7.hashing;

import hw7.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OpenAddressingHashMap<K, V> implements Map<K, V> {
  private static final int[] PRIMES = {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853,
    25717, 51437,102877, 205759, 411527, 823117, 1646237,3292489, 6584983, 13169977};
  private static final int DEFAULT_CAPACITY = 5;
  private static final float DEFAULT_LOAD_FACTOR = 0.5f;

  private final Entry<K, V> ts;
  private int capacity;
  private float loadFactor;
  private int size;
  private int tsSize;
  private int prIndex;
  private Entry<K, V>[] data;



  /**
   * Instantiate JdkHashMap.
   */
  public OpenAddressingHashMap() {
    capacity = DEFAULT_CAPACITY;
    loadFactor = DEFAULT_LOAD_FACTOR;
    data = new Entry[capacity];
    size = 0;
    tsSize = 0;
    prIndex = 1;
    ts = new Entry<>(null,null);
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

  public int getCapacity() {
    return capacity;
  }

  private void setCap() {
    if (prIndex < PRIMES.length) {
      capacity = PRIMES[prIndex++];
      return;
    }
    capacity = capacity * 2 + 1;
  }

  private void grow() {
    setCap();

    Entry<K, V>[] newHash = new Entry[capacity];
    int oldSize = size;

    for (int i = 0; i < data.length; i++) {
      Entry<K, V> entry = data[i];

      if (entry != null && entry != ts) {
        // Rehash the key and insert into the new hash map
        int newIndex = (entry.key.hashCode() & 0x7fffffff) % capacity;

        while (newHash[newIndex] != null) {
          newIndex = (newIndex + 1) % capacity;
        }

        newHash[newIndex] = new Entry<>(entry.key, entry.value);
      }
    }

    data = newHash;
    size = oldSize;
    tsSize = 0;
    checkLoadFactor(); // Recalculate load factor after growing
  }

  private void checkLoadFactor() {
    loadFactor = ((float) size + tsSize) / data.length;
    if (loadFactor >= DEFAULT_LOAD_FACTOR) {
      grow();
      loadFactor = ((float) size + tsSize) / data.length;
    }
  }

  private void directInsert(int index,K k,V v) {
    if (index == 51437) {
      System.out.println("workdirectInsert?");
    }
    data[index] = new Entry<>(k,v);
    size++;
    checkLoadFactor();
  }

  private void insertEdgeCases(Entry<K,V> current,int index, K k, V v) {
    // element at index is empty
    if (current == null) {
      directInsert(index,k,v);
      return;
    }
    // move current 1 element forward
    index = ++index % data.length;
    current = data[index];

    // find next available spot
    int quadProbeCount = 1;
    while (current != null) {
      // move to the next entry (first 5 searches are quad, then linear)
      if (quadProbeCount > 5) {
        index = (index + 1) % data.length;
        current = data[index];
      } else {
        index = (index + quadProbeCount * quadProbeCount) % data.length;
        current = data[index];
        quadProbeCount++;
      }
    }
    // found empty entry
    directInsert(index,k,v);
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null || has(k)) {
      throw new IllegalArgumentException();
    }
    // get index to entry
    int index = (k.hashCode() & 0x7fffffff) % data.length;
    Entry<K,V> current = data[index];

    //insert entry
    insertEdgeCases(current,index,k,v);
  }

  private V removeEdgeCases(int index,K k) {
    // key corresponds to k else shifted due to probing
    if (data[index].key.equals(k)) {
      V temp;
      temp = data[index].value;
      tsSize++;
      size--;
      data[index] = ts;
      return temp;
    }
    // move to next entry
    index = ++index % data.length;
    // traverse hashmap to find entry (shifted due to probing)
    while (data[index] != null && !data[index].key.equals(k)) {
      index = ++index % data.length;
    }
    V temp;
    temp = data[index].value;
    tsSize++;
    size--;
    data[index] = ts;
    return temp;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = (k.hashCode() & 0x7fffffff) % data.length;
    return removeEdgeCases(index,k);
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = (k.hashCode() & 0x7fffffff) % data.length;
    // key corresponds to k else shifted due to probing
    if (data[index].key.equals(k)) {
      data[index].value = v;
      return;
    }
    // move to next entry
    index = ++index % data.length;
    // traverse hashmap to find entry (shifted due to probing)
    while (data[index] != null && !data[index].key.equals(k)) {
      index = ++index % data.length;
    }
    // found entry corresponding with key corresponding to k
    data[index].value = v;

  }

  //TODO

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = (k.hashCode() & 0x7fffffff) % data.length;
    // key corresponds to k else shifted due to probing
    if (data[index] != null && data[index] != ts && data[index].key.equals(k)) {
      return data[index].value;
    }
    // move to next entry
    index = ++index % data.length;

    // need to wrap around hashmap to get correct key
    // traverse hashmap to find entry (shifted due to probing)
    while (data[index] == null || data[index] == ts || !data[index].key.equals(k)) {
      index = ++index % data.length;
    }
    return data[index].value;
  }

  @Override
  public boolean has(K k) {
    if (k != null) {
      int index = (k.hashCode() & 0x7fffffff) % data.length;

      // Check if the entry at the computed index matches the key
      if (data[index] != null && data[index] != ts && data[index].key.equals(k)) {
        return true;
      }

      // Check for probing (linear probing in this case)
      // start at 1 since we already know curr element isnt a match
      for (int i = 1; i < data.length; i++) {
        int newIndex = (index + i) % data.length;

        // Check if the entry at the probed index matches the key and is not a tombstone
        if (data[newIndex] != null && data[newIndex] != ts && data[newIndex].key.equals(k)) {
          return true;
        }
      }
    }
    // not in hashmap
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    return new HashIterator();
  }

  private class HashIterator implements Iterator<K> {

    private int currentIndex; // Index for array traversal

    HashIterator() {
      currentIndex = 0;
      findNext(); // Move to the first valid entry
    }

    @Override
    public boolean hasNext() {
      return currentIndex < data.length;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      K key = data[currentIndex].key;
      currentIndex++;

      // If currentIndex is within bounds, find the next non-null entry
      findNext();

      return key;
    }

    // Helper method to find the next valid entry in the array
    private void findNext() {
      while (currentIndex < data.length && (data[currentIndex] == null || data[currentIndex] == ts)) {
        currentIndex++;
      }
    }
  }

}
