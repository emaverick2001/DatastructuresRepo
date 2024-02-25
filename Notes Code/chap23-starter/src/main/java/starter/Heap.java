package starter;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Heap {

  private static Integer[] sample1() {
    return new Integer[]{12, 14, 11};
  }

  private static Integer[] sample2() {
    return new Integer[]{10, 5, 8, 1, 6, 14, 12, 3, 7, 20};
  }

  /**
   * Demo starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    Integer[] data = sample2();

    System.out.println("Before:");
    System.out.println(Arrays.toString(data));

    sort2floyd(data);

    System.out.println("After:");
    System.out.println(Arrays.toString(data));
  }

  // Pre: data != null
  private static void sort(Integer[] data) {
    // rutime n lg n
    // input n
    // aux n
    // space n

    PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (int i = 0; i < data.length; i++) { // O(n)
        pq.add(data[i]); // o(lg n) -> floyd o 1
      }
    // aux space o n

    for (int i = 0; i < data.length; i++) { // o n
      data[i] = pq.remove();// o lg n -> need to fix tree after remove first node
    }

  }



  private static void sort2floyd(Integer[] data) {
    // heapify
    int numNodes = data.length;
    for (int i = numNodes / 2; i >= 0; i--) {
      sink(data, i, numNodes);
    }

    // sort
    for (int i = numNodes; i >= 1; i--) {
      swap(data, 0, --numNodes); // remove best
      sink(data, 0, numNodes); // restore order
    }
  }


  private static void sink(Integer[] data, int index, int numNodes) {
    int leftChildIndex = 2 * index + 1; // Index of the left child (0-based indexing)
    int rightChildIndex = 2 * index + 2; // Index of the right child (0-based indexing)
    int largest = index; // Initialize the index of the largest element to the current index

    // Check if the left child is within the heap and larger than the current element
    if (leftChildIndex < numNodes && data[leftChildIndex] > data[largest]) {
      largest = leftChildIndex;
    }

    // Check if the right child is within the heap and larger than the largest so far
    if (rightChildIndex < numNodes && data[rightChildIndex] > data[largest]) {
      largest = rightChildIndex;
    }

    // If the largest element is not the current element, swap them and continue sinking
    if (largest != index) {
      swap(data, index, largest);
      sink(data, largest, numNodes);
    }
  }

  private static void swap(Integer[] data, int i, int j) {
    // swap values between data[i] and data[j]
    int tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
  }


}
