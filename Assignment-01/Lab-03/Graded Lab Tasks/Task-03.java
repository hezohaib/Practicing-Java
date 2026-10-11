class Student {
    private String name;
    private int[] result_array = new int[5];
    public Student(String n, int[] marks) {
        name = n;
        for(int i = 0; i < 5; i++) {
            result_array[i] = marks[i];
        }
    }
    public double Average() {
        int sum = 0;
        for(int i = 0; i < 5; i++) {
            sum = sum + result_array[i];
        }
        return sum / 5.0;
    }
    public String getName() {
        return name;
    }
    public int[] getResultArray() {
        return result_array;
    }
}
class Runner {
    public static void main(String[] args) {
        int[] marks1 = {70, 80, 65, 90, 75};
        int[] marks2 = {85, 78, 92, 60, 88};

        Student s1 = new Student("Ali", marks1);
        Student s2 = new Student("Ahmed", marks2);

        double avg1 = s1.Average();
        double avg2 = s2.Average();
        System.out.println(s1.getName() + " average: " + avg1);
        System.out.println(s2.getName() + " average: " + avg2);
        if(avg1 > avg2) {
            System.out.println(s1.getName() + " has higher average");
        }
        else {
            System.out.println(s2.getName() + " has higher average");
        }
        Student s3 = new Student(s1.getName(), s2.getResultArray());
        System.out.println("Third student name: " + s3.getName());
        System.out.println("Third student average: " + s3.Average());
    }
}
