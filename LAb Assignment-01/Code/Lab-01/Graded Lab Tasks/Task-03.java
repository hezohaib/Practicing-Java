
class Car {
    String brand;
    String model;
    int speed;

    void start() {
        System.out.println(brand + " car started.");
    }

    void accelerate() {
        speed = speed + 10;
        System.out.println("Speed: " + speed);
    }

    void stop() {
        speed = 0;
        System.out.println("Car stopped.");
    }

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Speed: " + speed);
    }
}

class Main {
    public static void main(String[] args) {
        Car c1 = new Car();

        c1.brand = "Toyota";
        c1.model = "Corolla";
        c1.speed = 0;

        c1.displayInfo();
        c1.start();
        c1.accelerate();
        c1.stop();
    }
}

