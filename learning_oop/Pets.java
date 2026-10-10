package learning_oop;

public class Pets {
    String name;

    public void eat() {
        System.out.println(name + " is eating ");
    }

    public void sleep() {
        System.out.println(name + " is sleeping ");
    }

}

class Cat extends Pets {
    public void meow() {
        System.out.println(name + " is meowing ");
    }

    public static void main(String[] args) {
        Cat c = new Cat();
        c.name = "Luna";
        c.eat();
        c.sleep();
        c.meow();

    }
}
