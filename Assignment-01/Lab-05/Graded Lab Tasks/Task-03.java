class Point {
    private double x;
    private double y;
    public Point(double xCord, double yCord) {
        x = xCord;
        y = yCord;
    }
    public void setX(double xCord) {
        x = xCord;
    }
    public void setY(double yCord) {
        y = yCord;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void display() {
        System.out.println("(" + x + ", " + y + ")");
    }
}
class Line {
    private Point startPoint;
    private Point endPoint;
public Line(Point s, Point e) {
        startPoint = s;
        endPoint = e;
    }
    public double findLength() {
        double x1 = startPoint.getX();
        double y1 = startPoint.getY();
        double x2 = endPoint.getX();
        double y2 = endPoint.getY();
        double length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return length;
    }
}
class Runner {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Line line1 = new Line(p1, p2);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(5, 5);
        Line line2 = new Line(p3, p4);
        System.out.println("Length of line 1: " + line1.findLength());
        System.out.println("Length of line 2: " + line2.findLength());
    }
}

