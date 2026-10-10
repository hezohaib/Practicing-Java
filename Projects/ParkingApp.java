import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface: any class that implements it must provide a fee calculation
interface FeeCalculatable {

    double calculateFee();
}

// Interface: any class that implements it must provide a report
interface Reportable {

    void generateReport();
}

// Abstract parent class: holds the data and behavior common to all vehicles.
// It cannot be instantiated directly; only Car and Bus objects are created.
abstract class Vehicle implements FeeCalculatable, Reportable {

    // Private fields (Encapsulation): accessible only through getters/setters
    private int vehicleId;
    private String ownerName;
    private String vehicleNumber;
    private int hours;

    // Constructor: validates hours and initializes the fields
    public Vehicle(int vehicleId, String ownerName, String vehicleNumber, int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative");
        }
        this.vehicleId = vehicleId;
        this.ownerName = ownerName;
        this.vehicleNumber = vehicleNumber;
        this.hours = hours;
    }

    // Getters and Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative");
        }
        this.hours = hours;
    }

    // Fee is calculated fresh every time, so there is no dependency
    // on calling calculateFee() first
    public double getFee() {
        return calculateFee();
    }

    // Each child class returns its own type name ("Car" or "Bus")
    public abstract String getType();

    // Prints the common details; child classes add their own fee lines
    @Override
    public void generateReport() {
        System.out.println("\n-------- " + getType() + " Report --------");
        System.out.println("Vehicle ID     : " + vehicleId);
        System.out.println("Owner Name     : " + ownerName);
        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Parking Hours  : " + hours);
    }

    // Builds the text that is written to the file for this vehicle
    public String toRecord() {
        return "Type: " + getType()
                + "\nVehicle ID: " + vehicleId
                + "\nOwner Name: " + ownerName
                + "\nVehicle Number: " + vehicleNumber
                + "\nHours: " + hours
                + "\nFee: " + getFee();
    }
}

// Car inherits from Vehicle
class Car extends Vehicle {

    // Car parking rate per hour (constant instead of a hardcoded number)
    private static final double HOURLY_RATE = 100;

    public Car(int vehicleId, String ownerName, String vehicleNumber, int hours) {
        super(vehicleId, ownerName, vehicleNumber, hours);
    }

    // Car fee = hours * rate
    @Override
    public double calculateFee() {
        return getHours() * HOURLY_RATE;
    }

    @Override
    public String getType() {
        return "Car";
    }

    // Common report details + the car's total fee
    @Override
    public void generateReport() {
        super.generateReport();
        System.out.println("Total Fee      : Rs. " + getFee());
    }
}

// Bus also inherits directly from Vehicle (a Bus is not a Car)
class Bus extends Vehicle {

    // Bus parking rate per hour
    private static final double HOURLY_RATE = 200;

    // Extra field that only a Bus has
    private double extraCharges;

    public Bus(int vehicleId, String ownerName, String vehicleNumber,
            int hours, double extraCharges) {
        super(vehicleId, ownerName, vehicleNumber, hours);
        this.extraCharges = extraCharges;
    }

    public double getExtraCharges() {
        return extraCharges;
    }

    public void setExtraCharges(double extraCharges) {
        this.extraCharges = extraCharges;
    }

    // Bus fee = (hours * rate) + extra charges
    @Override
    public double calculateFee() {
        return (getHours() * HOURLY_RATE) + extraCharges;
    }

    @Override
    public String getType() {
        return "Bus";
    }

    // Common report details + extra charges + total fee
    @Override
    public void generateReport() {
        super.generateReport();
        System.out.println("Extra Charges  : Rs. " + extraCharges);
        System.out.println("Total Fee      : Rs. " + getFee());
    }

    // File record also includes the extra charges for a bus
    @Override
    public String toRecord() {
        return super.toRecord() + "\nExtra Charges: " + extraCharges;
    }
}

// Stores the manager's name and shows which vehicle the manager is handling
class ParkingManager {

    private String managerName;

    public ParkingManager(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    // Accepts any Vehicle (Car or Bus) thanks to polymorphism
    public void showManager(Vehicle v) {
        System.out.println("Manager " + managerName + " is managing vehicle: "
                + v.getVehicleNumber());
    }
}

// Handles reading from and writing to the text file
class FileHandler {

    private static final String FILE_NAME = "CarRecords.txt";

    // Writes all vehicles to the file.
    // append = false means the file is rewritten each time, so no duplicates.
    // try-with-resources closes the file automatically, even on error.
    public void writeData(List<Vehicle> vehicles) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Vehicle v : vehicles) {
                bw.write(v.toRecord());
                bw.newLine();
                bw.newLine();
            }
            System.out.println("Data saved to file (" + FILE_NAME + ")");
        } catch (IOException e) {
            System.out.println("File Writing Error: " + e.getMessage());
        }
    }

    // Reads the file line by line and prints it on the screen
    public void readData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("\n======== File Data ========");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File does not exist yet or could not be read: " + e.getMessage());
        }
    }
}

// Helper class for safe user input.
// It keeps asking until the user enters a valid value, so the program never crashes.
class InputHelper {

    private final Scanner sc;

    public InputHelper(Scanner sc) {
        this.sc = sc;
    }

    // Reads a non-empty text value
    public String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Empty input is not allowed, try again.");
        }
    }

    // Reads an integer that is at least 'min'
    public int readInt(String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n >= min) {
                    return n;
                }
                System.out.println("Number must be at least " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }

    // Reads a decimal number that is at least 'min'
    public double readDouble(String prompt, double min) {
        while (true) {
            System.out.print(prompt);
            try {
                double n = Double.parseDouble(sc.nextLine().trim());
                if (n >= min) {
                    return n;
                }
                System.out.println("Number must be at least " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }
}

// Main class: program starts here
public class ParkingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InputHelper input = new InputHelper(sc);

        // One list holds both Cars and Buses (polymorphism)
        List<Vehicle> vehicles = new ArrayList<>();
        FileHandler fileHandler = new FileHandler();

        System.out.println("=====================================");
        System.out.println("     CAR PARKING FEE SYSTEM");
        System.out.println("=====================================");

        // Ask for the manager's name at startup
        ParkingManager manager = new ParkingManager(input.readText("Enter manager name: "));
        System.out.println("Welcome, Manager " + manager.getManagerName() + "!");

        boolean running = true;

        // Menu loop: runs until the user chooses 0 (Exit)
        while (running) {
            showMenu();
            int choice = input.readInt("Enter your choice (0-7): ", 0);

            switch (choice) {
                case 1:
                    addCar(input, vehicles, manager);
                    break;
                case 2:
                    addBus(input, vehicles, manager);
                    break;
                case 3:
                    showAll(vehicles);
                    break;
                case 4:
                    searchVehicle(input, vehicles);
                    break;
                case 5:
                    showTotal(vehicles);
                    break;
                case 6:
                    fileHandler.writeData(vehicles);
                    break;
                case 7:
                    fileHandler.readData();
                    break;
                case 0:
                    // Auto-save before exiting
                    fileHandler.writeData(vehicles);
                    System.out.println("Thank you! Program is closing.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, enter a number from 0 to 7.");
            }
        }

        sc.close();
    }

    // Prints the main menu
    private static void showMenu() {
        System.out.println("\n============== MENU ==============");
        System.out.println("1. Add Car");
        System.out.println("2. Add Bus");
        System.out.println("3. Show all vehicle reports");
        System.out.println("4. Search vehicle by number");
        System.out.println("5. Show total earnings");
        System.out.println("6. Save data to file");
        System.out.println("7. Read data from file");
        System.out.println("0. Exit (data is auto-saved)");
        System.out.println("==================================");
    }

    // Checks whether a vehicle with the given ID already exists
    private static boolean idExists(List<Vehicle> vehicles, int id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId() == id) {
                return true;
            }
        }
        return false;
    }

    // Takes car details from the user, creates a Car and adds it to the list
    private static void addCar(InputHelper input, List<Vehicle> vehicles, ParkingManager manager) {
        System.out.println("\n--- New Car ---");
        int id = input.readInt("Vehicle ID: ", 1);

        // Prevent duplicate IDs
        if (idExists(vehicles, id)) {
            System.out.println("This ID already exists!");
            return;
        }

        String owner = input.readText("Owner name: ");
        String number = input.readText("Vehicle number (e.g. LEA-123): ");
        int hours = input.readInt("Parking hours: ", 0);

        Car car = new Car(id, owner, number, hours);
        vehicles.add(car);

        System.out.println("Car added! Parking Fee: Rs. " + car.getFee());
        manager.showManager(car);
    }

    // Takes bus details from the user, creates a Bus and adds it to the list
    private static void addBus(InputHelper input, List<Vehicle> vehicles, ParkingManager manager) {
        System.out.println("\n--- New Bus ---");
        int id = input.readInt("Vehicle ID: ", 1);

        // Prevent duplicate IDs
        if (idExists(vehicles, id)) {
            System.out.println("This ID already exists!");
            return;
        }

        String owner = input.readText("Owner name: ");
        String number = input.readText("Vehicle number (e.g. ISB-786): ");
        int hours = input.readInt("Parking hours: ", 0);
        double extra = input.readDouble("Extra charges: ", 0);

        Bus bus = new Bus(id, owner, number, hours, extra);
        vehicles.add(bus);

        System.out.println("Bus added! Parking Fee: Rs. " + bus.getFee());
        manager.showManager(bus);
    }

    // Prints the report of every vehicle.
    // Polymorphism: the correct generateReport() runs for Car or Bus automatically.
    private static void showAll(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles added yet.");
            return;
        }
        for (Vehicle v : vehicles) {
            v.generateReport();
        }
    }

    // Searches for a vehicle by its number (case-insensitive)
    private static void searchVehicle(InputHelper input, List<Vehicle> vehicles) {
        String number = input.readText("Enter vehicle number: ");
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumber().equalsIgnoreCase(number)) {
                v.generateReport();
                return;
            }
        }
        System.out.println("No vehicle found with this number.");
    }

    // Adds up the fees of all vehicles and shows the total
    private static void showTotal(List<Vehicle> vehicles) {
        double total = 0;
        for (Vehicle v : vehicles) {
            total += v.getFee();
        }
        System.out.println("\nTotal vehicles : " + vehicles.size());
        System.out.println("Total earnings : Rs. " + total);
    }
}