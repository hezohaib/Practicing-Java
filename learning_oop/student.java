package learning_oop;

public class student {

  private String name;
  private int age;
  private int marks ;

  public void setName(String name ) {
   this.name = name;
  }
  public void setAge(int age ) {
   this.age = age;
  }
  public void setMarks(int marks ) {
   this.marks = marks ;
  }
  public String getName (){
   return name ;
  }
  public int getAge () {
   return age ;
  }
  public int getMarks  () {
   return marks ;
  }
  student(String name, int age, int marks) {
    this.name = name;
    this.age = age;
    this.marks = marks;
}
public void result ( ) {
  if ( marks >=50 ) {
    System.out.println("Your are pass with marks :" + marks );
  } else {
    System.out.println("Your are fail with marks :" + marks );
  }
}
   public static void main(String[] args) {
    student s = new student("Zohaib", 20 , 99);

   System.out.println("Name: " + s.getName ());
   System.out.println("Age: " + s.getAge ());
   System.out.println("Marks: " + s.getMarks ());
   s.result();
  }
}