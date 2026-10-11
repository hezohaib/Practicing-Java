
class Marks {
    private int mark1;
    private int mark2;
    private int mark3;
    public void setMark1(int m) {
        mark1 = m;
    }
    public void setMark2(int m) {
        mark2 = m;
    }
    public void setMark3(int m) {
        mark3 = m;
    }
    public int getMark1() {
        return mark1;
    }
    public int getMark2() {
        return mark2;
    }
    public int getMark3() {
        return mark3;
    }
}
class Runner {
    public static void main(String[] args) {
        Marks m = new Marks();
        m.setMark1(80);
        m.setMark2(75);
        m.setMark3(90);

        System.out.println("Mark1: " + m.getMark1());
        System.out.println("Mark2: " + m.getMark2());
        System.out.println("Mark3: " + m.getMark3());
    }
}
