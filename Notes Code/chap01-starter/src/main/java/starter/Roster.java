package starter;
//@SuppressWarnings('All')
public class Roster {
  private Student[] students;
  private int numStudents;

  public Roster(int size) {
    students = new Student[size];
    numStudents = 0;
  }

  public void add(Student s) {
    // stub
    students[numStudents] = s;
    numStudents++;
  }

  public void remove(Student s) {
    // stub
  }

  // Linear Search
  public Student Lfind(String email) {
    for (int i = 0; i < numStudents; i++){
      if (students[i].getEmail().equals(email)){
        return students[i];
      }
    }
    return null; // not found
  }

//  // Binary Search non-recursive
//  //assume list is sorted
//  public Student find(String email){
//    int start = 0;
//    int end = numStudents - 1;
//
//    while(start <= end) {
//      int mid = (start + end)/2;
//      int cmp = students[mid].getEmail().compareTo(email); // neg if current obj less than target
//      if  (cmp == 0) {
//        return students[mid];
//      } else if (cmp < 0) {
//        start = mid + 1; // email is in upper right half if current obj is less
//      } else {
//        end = mid - 1;
//      }
//    }
//    return null;
//  }

  // Binary Search non-recursive
  //assume list is sorted
  public Student find(String email) {
    int start = 0;
    int end = numStudents - 1;
    int mid;

    int cmp;

    //traverse through list
    while (start <= end){
      mid = (start + end)/2;
      cmp = students[mid].getEmail().compareTo(email); // compares left to right if l > r ret 1
      if (cmp == 0){
        return students[mid];
      } else if (cmp > 1) {
        end = mid - 1; // search bottom half of list
      } else {
        start = mid + 1; // search top half of list
      }
    }
    // didnt find target

    return null;
  }

  // Binary Search recursive
  public Student find(String email, int start, int end){
    if (start > end){
      return null;
    }

    int mid = (start + end)/2;
    int cmp = students[mid].getEmail().compareTo(email);

    if (cmp == 0){
      return students[mid];
    }
    else if (cmp < 0){
      return find(email,mid + 1, end);
    }else {
      return find(email,start, mid - 1);
    }
  }

  // Recursive Binary search
//  public Student Rfind(String email, int left , int right){
//    if (left > right) {
//      return null;
//    }
//
//    int mid = (left + right)/2;
//    int comp = students[mid].getEmail().compareTo(email); // use examples to see left right indexes
//
//
//    if (comp == 0) {
//      return students[mid];
//    } else if (comp > 0) {
//      Rfind(email, mid+1, right);
//    } else {
//      Rfind(email, left, mid-1);
//    }
//  }

  // Binary search has faster runtime than linear, emphasized with larger inputs n
  // Use binary search when list is sorted-> when the elements are comaprable
  // small data usage makes no difference using lin search vs bin search

  //scenario with multiple add + remove of students, need to ensure array is still sorted


}

