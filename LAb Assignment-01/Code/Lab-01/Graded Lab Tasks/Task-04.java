
class Rectangle {
    double length;
    double width;

    double calculateArea() {
        return length * width;
    }

    double calculatePerimeter() {
        return 2 * (length + width);
    }

    void displayInfo() {
        System.out.println("Length: " + length);
        System.out.println("Width: " + width);
        System.out.println("Area: " + calculateArea());
        System.out.println("Perimeter: " + calculatePerimeter());
    }
}

class Main {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();

        r1.length = 5;
        r1.width = 3;

        r1.displayInfo();
    }
}
