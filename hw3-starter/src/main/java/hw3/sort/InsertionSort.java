package hw3.sort;


import hw3.list.IndexedList;

/**
 * The Insertion Sort algorithm, with minimizing swaps optimization.
 *
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  private boolean greater(T a, T b) {
    return a.compareTo(b) > 0;
  }

  @Override
  public void sort(IndexedList<T> indexedList) {
    //assume first element is sorted
    int i = 1;
    int j;
    T x;
    // traverse list
    while (i < indexedList.length()) {
      // x holds first unsorted element
      x = indexedList.get(i);
      //j holds last sorted element
      j = i - 1;
      // traverse sorted list if sorted element is greater than unsorted and j doesn't go past 0
      // used to sort sorted list when a smaller unsorted value is found
      while (j >= 0 && greater(indexedList.get(j),x)) { // element at j greater than i
        // replace sorted element with unsorted
        indexedList.put(j + 1,indexedList.get(j));
        //move to next sorted element
        j = j - 1;
      }
      // add unsorted element to ordered position in sorted section
      indexedList.put(j + 1, x);
      //sorted list grows by 1 (after sorting unsorted value)
      i = i + 1;
    }

  }

  @Override
  public String name() {
    return "Insertion Sort";
  }
}
