package starter;

public class Student {
  private final String name;
  private final String email;


  public Student(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString(){
    String name_email = "";
    name_email = this.getName() + this.getEmail();
    return name_email;
  }
}
