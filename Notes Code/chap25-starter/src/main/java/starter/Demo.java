package starter;

import java.util.Arrays;

public class Demo {

  /**
   * Sorts the input arr.
   *
   * @param arr an array of integers.
   */
  public static void quicksort(int[] arr) {
    quicksort(arr, 0, arr.length);
  }

  // Pre: 0 <= left <= right <= arr.length.
  // Post: arr[left] ... arr[right-1] is sorted.
  private static void quicksort(int[] arr, int begin, int end) {
    if (end - begin <= 1) {
      return;
    }

    int pivotIndex = partition(arr,begin,end);
    quicksort(arr,begin, pivotIndex);
    quicksort(arr,pivotIndex + 1,end);
  }

  // Partition by taking the right-most element as pivot.
  // Pre: 0 <= left <= right <= arr.length;
  // Post: a[left] <= .. <= pivot <= .. <= a[right - 1]
  // Returns: index of pivot element in array.
  private static int partition(int[] arr, int begin, int end) {
    int p = end - 1;
    int l = begin;
    int r = p - 1;

    int pivot = arr[p];

    while (l < r) {
      while (l < r && arr[l] <= pivot) {
        l++;
      }
      while (r > l && arr[r] >= pivot) {
        r--;
      }

      swap(arr, l, r);
    }
    swap(arr,l,p);
    // need to start at new pivot
    return l;
  }

  // Swap arr[i] with arr[j]
  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * Demo starts here.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    int[] arr = {20, 13, 7, 71, 31, 10, 5, 50, 100};
    System.out.println("Input: " + Arrays.toString(arr));
    quicksort(arr);
    System.out.println("Output: " + Arrays.toString(arr));
  }
}
