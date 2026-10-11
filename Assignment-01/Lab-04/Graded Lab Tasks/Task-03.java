class Fraction {
    private int numerator;
    private int denominator;
    public Fraction() {
        numerator = 0;
        denominator = 1;
    }
    public Fraction(int num, int den) {
        numerator = num;
        denominator = den;
    }
    public void setNumerator(int num) {
        numerator = num;
    }
    public void setDenominator(int den) {
        denominator = den;
    }
    public int getNumerator() {
        return numerator;
    }
    public int getDenominator() {
        return denominator;
    }
    public void display() {
        System.out.println(numerator + "/" + denominator);
    }
    public boolean equals(Fraction f) {
        if(this.numerator == f.numerator && this.denominator == f.denominator) {
            return true;
        }
        else {
            return false;
        }
    }
}
class Runner {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(3, 4);
        Fraction f2 = new Fraction(3, 4);
        Fraction f3 = new Fraction(2, 5);

        System.out.print("Fraction 1: ");
        f1.display();
        System.out.print("Fraction 2: ");
        f2.display();
        System.out.print("Fraction 3: ");
        f3.display();

        System.out.println("f1 equals f2? " + f1.equals(f2));
        System.out.println("f1 equals f3? " + f1.equals(f3));
    }
}
