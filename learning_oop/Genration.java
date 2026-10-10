package learning_oop;

public class Genration {
    private String name;
    private String color;
    private double hight;

    Genration(String name, String color, double hight) {
        this.name = name;
        this.color = color;
        this.hight = hight;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getHight() {
        return hight;
    }

    public void sound() {
        System.out.println(getName() + " is making sounds");
    }
}

class Dog extends Genration {

    private String breed;

    Dog(String name, String color, double hight, String breed) {
        super(name, color, hight);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void eat() {
        System.out.println(getName() + " is eating");
    }
}

class Puppy extends Dog {
    private int age;

    Puppy(String name, String color, double hight, String breed, int age) {
        super(name, color, hight, breed);
        this.age = age;

    }

    public int getAge() {
        return age;
    }

    public void run() {
        System.out.println(getName() + " is Barking");
    }
}

class genrationMain {

    public static void main(String[] args) {
        Puppy puppy = new Puppy("Bruno", "Black", 3.4, "Huskey", 4);
        System.out
                .println("Name : " + puppy.getName() + "\nColor : " + puppy.getColor() + "\nHight : " + puppy.getHight()
                        + "\nBreed : " + puppy.getBreed() + "\nAge : " + puppy.getAge());
        puppy.sound();
        puppy.run();
        puppy.run();

    }
}
