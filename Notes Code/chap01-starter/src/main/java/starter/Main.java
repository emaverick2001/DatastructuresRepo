package starter;

import java.sql.SQLOutput;
import java.util.function.DoubleToIntFunction;

public class Main {
  /**
   * Execution starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    Student Mav;


    Student student2 = new Student("Alice", "alice@example.com");
    Student student3 = new Student("Bob", "bob@example.com");
    Student student4 = new Student("Eva", "eva@example.com");
    Student student1 = new Student("John", "john@example.com");
    Mav = new Student("Maverick","mespin11@jh.edu");
    Student student5 = new Student("Mike", "mike@example.com");
    Student student6 = new Student("Sarah", "sarah@example.com");

    Roster datastruct = new Roster(7);
    datastruct.add(student2);
    datastruct.add(student3);
    datastruct.add(student4);
    datastruct.add(student1);
    datastruct.add(Mav);
    datastruct.add(student5);
    datastruct.add(student6);

    Student Bfound = datastruct.find("mespin11@jh.edu"); // return MAv email
    Student BRfound = datastruct.find("alice@example.com",0,7);

    System.out.println(Bfound.getName());
    System.out.println(BRfound.getName());


//    System.out.println(Mav.getEmail());
//    System.out.println(Mav.getName());

    System.out.println("Hello Data Structures!");
  }
}
