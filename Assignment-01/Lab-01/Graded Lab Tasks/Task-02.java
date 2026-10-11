
class Time {
    int hours;
    int minutes;
    int seconds;

    void setTime(int h, int m, int s) {
        hours = h;
        minutes = m;
        seconds = s;
    }

    void displayTime() {
        System.out.println(hours + ":" + minutes + ":" + seconds);
    }

    void resetTime() {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }
}

class Main {
    public static void main(String[] args) {
        Time t1 = new Time();

        t1.setTime(10, 30, 45);
        t1.displayTime();

        t1.resetTime();
        t1.displayTime();
    }
}

