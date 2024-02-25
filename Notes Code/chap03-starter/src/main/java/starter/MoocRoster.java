package starter;



public class MoocRoster implements Roster {
  protected Student[] students;
  protected int numStudents;
  public MoocRoster (int size, Student [] students){
  numStudents = size;
  this.students = students;
  }


  public void add(Student s){}

  public void remove(Student s){}

  public Student find (String email){return null;}
}

