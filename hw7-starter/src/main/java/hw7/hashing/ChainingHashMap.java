package hw7.hashing;

//import com.sun.tools.javac.jvm.Code;
import hw7.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChainingHashMap<K, V> implements Map<K, V> {

  private static final int[] PRIMES = {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853,
      25717, 51437,102877, 205759, 411527, 823117, 1646237,3292489, 6584983, 13169977};

  private static final int DEFAULT_CAPACITY = 5;
  private static final float DEFAULT_LOAD_FACTOR = 0.5f;

  private int capacity;
  private float loadFactor;
  private int size;
  private int prIndex;
  private Entry<K,V>[] data;

  /**
   * Instantiate JdkHashMap.
   */
  public ChainingHashMap() {
    capacity = DEFAULT_CAPACITY;
    loadFactor = DEFAULT_LOAD_FACTOR;
    data = new Entry[capacity];
    size = 0;
    prIndex = 1;
  }

  // Entry to store a key, and a value pair.
  private static class Entry<K,V> {
    K key;
    V value;

    Entry<K,V> next;

    Entry(K k, V v) {
      this.key = k;
      this.value = v;
      next = null;
    }
  }

  public int getCapacity() {
    return capacity;
  }

  private void setCap() {
    if (prIndex < PRIMES.length) {
      capacity = PRIMES[prIndex];
      prIndex++;
      return;
    }
    capacity = capacity * 2 + 1;
  }

  private void rehash(Entry<K, V>[] temp) {
    Entry<K, V>[] newHash = new Entry[capacity];

    for (Entry<K, V> kvEntry : temp) {
      Entry<K, V> curr = kvEntry;
      while (curr != null) {
        int index = Math.abs(curr.key.hashCode()) % capacity;

        // Insert the entry into the new hash table
        if (newHash[index] == null) {
          newHash[index] = new Entry<>(curr.key, curr.value);
        } else {
          Entry<K, V> last = newHash[index];
          while (last.next != null) {
            last = last.next;
          }
          last.next = new Entry<>(curr.key, curr.value);
        }

        curr = curr.next;
      }
    }

    data = newHash;
  }

  private void grow() {
    // temp to hold old hashmap
    Entry<K,V>[] temp = data;
    setCap();
    // create new hashmap with next largest prime # capacity
    rehash(temp);
  }

  private void checkLoadFactor() {
    loadFactor = ((float) size) / capacity;
    if (loadFactor >= DEFAULT_LOAD_FACTOR) {
      grow();
      loadFactor = ((float) size) / capacity;
    }
  }

  private void insertEdgeCases(Entry<K,V> current, int index,K k, V v) {
    // Bucket is empty
    if (current == null) {
      data[index] = new Entry<>(k,v);
      size++;
      checkLoadFactor();
      return;
    }
    //Bucket not empty
    while (current.next != null) {
      current = current.next;
    }
    // add to end of chain
    current.next = new Entry<>(k,v);
    size++;
    // need to re-balance if lf > threshold
    checkLoadFactor();
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null || has(k)) {
      throw new IllegalArgumentException();
    }
    // make sure index is pos
    int index = Math.abs(k.hashCode()) % capacity;
    Entry<K,V> current = data[index];
    insertEdgeCases(current,index,k,v);
  }

  private V removeEntry(Entry<K,V> current,int index) {
    V temp = current.value;
    data[index] = null;
    size--;
    return temp;
  }

  private V removeEdgeCases(Entry<K,V> current, int index, K k) {
    // if only node in the list
    if (current.next == null) {
      return removeEntry(current,index);
    }
    //if first node in the list
    if (current.key.equals(k)) {
      return removeEntry(current,index);
    }
    while (current.next.key != k) {
      current = current.next;
    }
    V temp = current.next.value;
    current.next = current.next.next;
    size--;
    return temp;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = Math.abs(k.hashCode()) % capacity;
    Entry<K,V> current = data[index];
    return removeEdgeCases(current,index,k);
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = Math.abs(k.hashCode()) % capacity;
    Entry<K,V> current = data[index];
    // current cant be null since it has an entry
    while (!current.key.equals(k)) {
      current = current.next;
    }
    current.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    // get entry @ index
    int index = Math.abs(k.hashCode()) % capacity;
    Entry<K,V> current = data[index];

    while (!current.key.equals(k)) {
      current = current.next;
    }
    return current.value;
  }

  @Override
  public boolean has(K k) {
    if (k != null) {
      int index = Math.abs(k.hashCode()) % capacity;
      Entry<K, V> current = data[index];

      // Traverse the linked list at the specified index
      while (current != null) {
        if (current.key.equals(k)) {
          return true; // Key found in the linked list
        }
        current = current.next;
      }

      // Key not found in the linked list
      return false;
    }
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
    private Entry<K,V> currentEntry; // Current entry in the linked list

    HashIterator() {
      currentIndex = 0;
      currentEntry = null;
      findNext(); // Move to the first valid entry
    }

    @Override
    public boolean hasNext() {
      return currentEntry != null;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      K key = currentEntry.key;
      currentEntry = currentEntry.next; // Move to the next entry in the linked list

      // If the current linked list is exhausted, find the next valid entry in the array
      if (currentEntry == null) {
        findNext();
      }

      return key;
    }

    // Helper method to find the next valid entry in the array
    private void findNext() {
      while (currentIndex < data.length && data[currentIndex] == null) {
        currentIndex++;
      }

      // If currentIndex is within bounds, update currentEntry
      if (currentIndex < data.length) {
        currentEntry = data[currentIndex];
        currentIndex++;
      } else {
        currentEntry = null; // Reached the end of the array
      }
    }
  }
}