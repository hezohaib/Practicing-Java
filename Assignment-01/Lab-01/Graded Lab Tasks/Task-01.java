
class Student {
    String name;
    int rollNo;
    String department;
    double cgpa;

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Department: " + department);
        System.out.println("CGPA: " + cgpa);
    }

    void study() {
        System.out.println(name + " is studying.");
    }

    void attendClass() {
        System.out.println(name + " is attending class.");
    }
}

class Main {
    public static void main(String[] args) {
        Student s1 = new Student();

        s1.name = "Ali";
        s1.rollNo = 101;
        s1.department = "Computer Science";
        s1.cgpa = 3.5;

        s1.displayInfo();
        s1.study();
        s1.attendClass();
    }
}
