package learning_oop;

class Animalssounds {

    public void sound() {
        System.out.println("Animals make different sounds");
    }

    public void sound(String type) {
        System.out.println(type + "Sound");
    }
}

class Dog extends Animalssounds {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animalssounds {
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }
}

class Lion extends Animalssounds {
    @Override
    public void sound() {
        System.out.println("Lion roars");
    }
}

class Main {
    public static void main(String[] args) {

        Dog d = new Dog();
        d.sound();
        d.sound("Dog");

        Cat c = new Cat();
        c.sound();

        Lion l = new Lion();
        l.sound();
    }
}