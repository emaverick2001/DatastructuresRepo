package starter;

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
    // base case
    int size = right - left;
    if (size <= 1) {
      return;
    }
    // find midpoint
    int mid = (left + right) / 2;

    // divide input arr into left section
    mergesort(arr,left,mid);
    // divide input arr into right section
    mergesort(arr,mid,right);
    // merge left and right sections
    merge(arr,left,mid,right);
  }

  // Pre:
  //  1) left, mid and right are valid indices such that
  //        0 <= left <= mid < right <= arr.length;
  //  2) arr[left] ... arr[mid-1] is sorted
  //  3) arr[mid] ... arr[right-1] is sorted
  // Post: arr[left] ... arr[right-1] is sorted.
  private static void merge(int[] arr, int left, int mid, int right) {
    int l = left;
    // references will change thats why we dont use original param since we dont want it to change
    int m = mid;
    int k = 0;
    int length = right - left;
    int[] temp = new int[length];
    for (; l < mid && m < right; k++) {
      if (arr[l] < arr[m]) {
        temp[k] = arr[l++];
      } else {
        temp[k] = arr[m++];
      }
    }
    // if temp isnt fully filled and left section still has elem
    while(l < mid) {
      temp[k++] = arr[l++];
    }
    // if temp isnt fully filled and right section still has elem
    while(m < right) {
      temp[k++] = arr[m++];
    }
    // transfer sorted contents of temp to actual array
    for (int i = 0; i < length; i++) {
      arr[left + i] = temp[i];
    }
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