package solution;

import java.util.Arrays;

public class Demo {

  /**
   * Sorts the input arr.
   * @param arr an array of integers.
   */
  public static void mergesort(int[] arr) {
    mergesort(arr, 0, arr.length);
  }

  // Pre: left and right are valid indices such that
  //        0 <= left <= right <= arr.length;
  // Post: arr[left] ... arr[right-1] is sorted.
  private static void mergesort(int[] arr, int left, int right) {
    if (right - left < 2) { // make sure we have singletons
      return;
    }

    int mid = (left + right) / 2;

    mergesort(arr, left, mid);
    mergesort(arr, mid, right);
    merge(arr, left, mid, right);
  }

  // Pre:
  //  1) left, mid and right are valid indices such that
  //        0 <= left <= mid < right <= arr.length;
  //  2) arr[left] ... arr[mid-1] is sorted
  //  3) arr[mid] ... arr[right-1] is sorted
  // Post: arr[left] ... arr[right-1] is sorted.
  private static void merge(int[] arr, int left, int mid, int right) {
    int i = left;
    int j = mid;
    int k = 0;

    int[] tmp = new int[right - left];
    while (i < mid && j < right) {
      // INVARIANT: tmp[0...k) is sorted
      tmp[k++] = (arr[i] <= arr[j] ? arr[i++] : arr[j++]);
    }
    // if (i < mid )
    System.arraycopy(arr, i, arr, left + k, mid - i);
    // else if (j < right)
    // System.arraycopy(arr, j, arr, left+k, right-j);
    // no need for the above copy since those elements are already in place.

    // copy stuff over from tmp to arr
    System.arraycopy(tmp, 0, arr, left, k);
  }

  /**
   * Demo starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    int[] arr = {5, 1, 3, 0, 8, 2, 4, 9};
    System.out.println("Input: " + Arrays.toString(arr));
    mergesort(arr);
    System.out.println("Output: " + Arrays.toString(arr));
  }
}