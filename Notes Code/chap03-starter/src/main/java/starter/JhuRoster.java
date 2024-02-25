package starter;

public class JhuRoster implements Roster{
  protected Student[] students;
  protected int numStudents;
  public JhuRoster (int size, Student[] stud){
    size = size;
   students = stud;
  }




  public void add(Student s){}

  public void remove(Student s){}

  public Student find (String email){return null;}
}
