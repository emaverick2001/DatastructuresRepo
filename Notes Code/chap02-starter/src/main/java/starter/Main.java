package starter;

import java.sql.SQLOutput;

public class Main {
  public static void main(String[] args) {

  }
  public static void greetAdvisor(Student student){
    if (student instanceof  GradStudent) { // student actual type is GradStudent
      GradStudent gs = (GradStudent) student; // if tried to downcast actual is Student and there is no actually advisor
      //throws runtime error
      System.out.println("Students advisor: "+ gs.getAdvisor());
    } else {
      System.out.println("This stud has no advisor!");
    }
  }
}
