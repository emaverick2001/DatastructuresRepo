package starter;

/*
  Abstratction

 */

public class Student {
  private String name;
  private String email;

  private int gpa;
  public Student(String name, String email) {
    this.name = name;
    this.email = email;
    this.gpa = 4;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
