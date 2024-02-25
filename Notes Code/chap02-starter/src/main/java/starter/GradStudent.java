package starter;

public class GradStudent extends Student {
  private String advisor;
  //private string name -> this shadows the field in base class


  public GradStudent(String name, String email, String advisor) {
    super(name,email); // calls parent class
    this.advisor = advisor;
  }

  public String getAdvisor() {
    return advisor + getName();
  }

//  @Override
//  public String getName() {
//    return this.name + ", the grad student";
////    return name + ", the grad student";
////    return super.name + ", the grad student";

  // return this.getname() -> doesnt work if get name already declared
//
//  }



  public void setAdvisor(String advisor) {
    this.advisor = advisor;
  }

  //information hiding principle

}