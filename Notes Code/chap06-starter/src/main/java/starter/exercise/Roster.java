package starter.exercise;

import java.util.Iterator;

@SuppressWarnings("all")
public interface Roster extends Iterable<Student> {

  void add(Student s);

  void remove(Student s);

  Student find(String email);

  /**
   * Returns an iterator over elements of this collection.
   *
   * @return an Iterator.
   */
  Iterator iterator();
}
