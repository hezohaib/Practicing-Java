package learning_oop;

public class Vehical {
    String name;

    public void printVehicalName () {
        System.out.println("Your are the proude owner of " + name);
    }

    public void Drive() {
        System.out.println(name + " is driving ");
    }
}

class Porshe extends Vehical {

    public void playboicarti() {
        System.out.println("Playlist of playboicarti is playing in " + name);
    }
}

class vehicalMain {
    public static void main(String[] args) {
        Porshe p = new Porshe();
        p.name = "Porshe 911";
        p.printVehicalName();
        p.Drive();
        p.playboicarti();

    }

}