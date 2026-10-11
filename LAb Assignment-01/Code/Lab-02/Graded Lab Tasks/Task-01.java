
class Circle {
    double radius;

    Circle() {
        radius = 1;
    }

    Circle(double r, int x) {
        radius = r;
    }

    double calculateCircumference() {
        return 2 * 3.14 * radius;
    }

    void display() {
        System.out.println("Radius: " + radius);
        System.out.println("Circumference: " + calculateCircumference());
    }
}

class Main {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        Circle c2 = new Circle(5, 0);

        c1.display();
        c2.display();
    }
}
