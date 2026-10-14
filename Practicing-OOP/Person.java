package learning_oop;

class Person {
 String name ;
 int age ;
 
 Person ( String name , int age ) {
    this.name = name ;
    this.age = age ;
 }
 String getname () { return name;}
 int getage () { return age ;} 
}
class Student extends Person { 
private int marks ;

Student ( String name , int age , int marks ) {
    super ( name , age ) ;
    this.marks = marks ;
}
int getmarks () { return marks ;} 
void Result ( ) {
    if ( marks >= 50 ) {
        System.out.println("You are pass with marks :" + marks );
    } else {
        System.out.println("You are fail with marks :" + marks );
        }
    
    }
}
  class PersonMain  {
    public static void main ( String [] args ) {
        Student s = new Student ( "Zohaib" , 20 , 99 ) ;
        System.out.println("Name: " + s.getname ());
        System.out.println("Age: " + s.getage ());
        System.out.println("Marks: " + s.getmarks ());
            s.Result();
    }   
 
 }


    
