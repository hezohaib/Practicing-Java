
class Distance {
    private int feet;
    private int inches;

    public Distance() {
        feet = 0;
        inches = 0;
    }

    public Distance(int f, int i) {
        feet = f;
        inches = i;
    }

    public void setFeet(int f) {
        feet = f;
    }

    public void setInches(int i) {
        inches = i;
    }

    public int getFeet() {
        return feet;
    }

    public int getInches() {
        return inches;
    }

    public void display() {
        System.out.println("Feet: " + feet + " Inches: " + inches);
    }

    public Distance add(Distance d) {
        Distance temp = new Distance();
        temp.feet = this.feet + d.feet;
        temp.inches = this.inches + d.inches;

        if(temp.inches >= 12) {
            temp.feet = temp.feet + (temp.inches / 12);
            temp.inches = temp.inches % 12;
        }
        return temp;
    }
}
class Runner {
    public static void main(String[] args) {
        Distance d1 = new Distance(5, 8);
        Distance d2 = new Distance(3, 10);

        Distance d3 = d1.add(d2);

        System.out.print("Distance 1: ");
        d1.display();
        System.out.print("Distance 2: ");
        d2.display();
        System.out.print("Added Distance: ");
        d3.display();
    }
}
