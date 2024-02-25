package starter.exercise;

import starter.ArrayIndexedList;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class MoocRoster implements Roster {
  private final Student[] students;
  private int numStudents;

  public MoocRoster(int size) {
    students = new Student[size];
    numStudents = 0;
  }

  @Override
  public void add(Student s) {
    if (numStudents < students.length) {
      students[numStudents++] = s;
    }
  }

  @Override
  public void remove(Student s) {
    // stub - leave it as is
  }

  @Override
  public Student find(String email) {
    return null; // stub - leave it as is
  }

  public Iterator iterator() {
    return new MoocRoster.MoocRosterIterator();
  }
  public class MoocRosterIterator implements Iterator<Student> {
    private int cursor = 0;


    // .length gives number of items, or 1 minus the length
    // is this relative to the current index? Doesnt this need to be specified for the next element?
    @Override
    public boolean hasNext() {
      return cursor < students.length;
    }

    @Override
    public Student next() {
      if (!hasNext()){
        throw new NoSuchElementException();
      }
      return students[cursor++];
    }
  }
}
