
class Time {
    int hr;
    int min;
    int sec;

    Time() {
        hr = 0;
        min = 0;
        sec = 0;
    }

    Time(int h, int m, int s) {
        setTime(h, m, s);
    }

    void setTime(int h, int m, int s) {
        if (h >= 0 && h < 24 && m >= 0 && m < 60 && s >= 0 && s < 60) {
            hr = h;
            min = m;
            sec = s;
        } else {
            hr = 0;
            min = 0;
            sec = 0;
        }
    }

    void display() {
        System.out.println("Time: " + hr + ":" + min + ":" + sec);
    }
}

class Main {
    public static void main(String[] args) {
        Time t1 = new Time();
        Time t2 = new Time(10, 30, 45);
        Time t3 = new Time(25, 70, 80);

        t1.display();
        t2.display();
        t3.display();
    }
}
