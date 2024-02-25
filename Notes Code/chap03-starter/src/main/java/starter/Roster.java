package starter;

/**
 * IndexedListADT .
 *
 */
public interface Roster {

  /**
   *
   * @param s
   */
  public  void add( Student s);

  /**
   *
   * @param s
   */

  public  void remove(Student s);

  /**
   *
   * @param email
   * @return student with associated email
   */
  public  Student find(String email);
}

// you can implment multiple interfaces

