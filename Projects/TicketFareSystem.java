import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Interface: every ticket must calculate its fare and print itself
interface Ticket {

    double calculateFare();

    void displayInfo();
}

// Abstract parent class: holds the data common to all tickets
abstract class TravelTicket implements Ticket {

    private static int idCounter = 1000;                  // gives every ticket a unique ID
    private static final double STUDENT_DISCOUNT = 0.10;  // 10% student discount
    private static final Random RANDOM = new Random();

    private final int ticketId;
    private final String passengerName;
    private final boolean student;
    private final String from;
    private final String to;
    private final int seatNumber;

    public TravelTicket(String passengerName, boolean student, String from, String to) {
        this.ticketId = ++idCounter;
        this.passengerName = passengerName;
        this.student = student;
        this.from = from;
        this.to = to;
        this.seatNumber = RANDOM.nextInt(40) + 1; // random seat from 1 to 40
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getRoute() {
        return from + " -> " + to;
    }

    // Only students get a discount
    public double getDiscount() {
        return student ? calculateFare() * STUDENT_DISCOUNT : 0;
    }

    public double getFinalFare() {
        return calculateFare() - getDiscount();
    }

    // Each child class returns its own type name ("Bus" or "Train")
    public abstract String getTypeName();

    // Each child class prints its own extra details (distance, stations, etc.)
    protected abstract void printExtraDetails();

    // Prints the ticket in simple text format
    @Override
    public void displayInfo() {
        System.out.println("\n--- " + getTypeName() + " Ticket #" + ticketId + " ---");
        System.out.println("Passenger  : " + passengerName);
        System.out.println("Route      : " + getRoute());
        System.out.println("Seat No    : " + seatNumber);
        System.out.println("Student    : " + (student ? "Yes (10% discount)" : "No"));
        printExtraDetails();
        System.out.println("Base Fare  : Rs. " + calculateFare());
        System.out.println("Discount   : Rs. " + getDiscount());
        System.out.println("Final Fare : Rs. " + getFinalFare());
    }
}

// Bus ticket: fare depends on distance
class BusTicket extends TravelTicket {

    private static final double FARE_PER_KM = 5;

    private final double distance;

    public BusTicket(String name, boolean student, String from, String to, double distance) {
        super(name, student, from, to);
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        this.distance = distance;
    }

    // Fare = distance * rate per km
    @Override
    public double calculateFare() {
        return distance * FARE_PER_KM;
    }

    @Override
    public String getTypeName() {
        return "Bus";
    }

    @Override
    protected void printExtraDetails() {
        System.out.println("Distance   : " + distance + " km");
        System.out.println("Rate/km    : Rs. " + FARE_PER_KM);
    }
}

// Train ticket: fare depends on stations and the class (Economy or Business)
class TrainTicket extends TravelTicket {

    private static final double FARE_PER_STATION = 50;
    private static final double BUSINESS_MULTIPLIER = 1.5; // business costs 50% more

    private final int stations;
    private final boolean business;

    public TrainTicket(String name, boolean student, String from, String to,
            int stations, boolean business) {
        super(name, student, from, to);
        if (stations <= 0) {
            throw new IllegalArgumentException("Stations must be greater than 0");
        }
        this.stations = stations;
        this.business = business;
    }

    // Fare = stations * rate per station (business class costs more)
    @Override
    public double calculateFare() {
        double fare = stations * FARE_PER_STATION;
        return business ? fare * BUSINESS_MULTIPLIER : fare;
    }

    @Override
    public String getTypeName() {
        return "Train";
    }

    @Override
    protected void printExtraDetails() {
        System.out.println("Class      : " + (business ? "Business" : "Economy"));
        System.out.println("Stations   : " + stations);
        System.out.println("Rate/Stn   : Rs. " + FARE_PER_STATION);
    }
}

// Helper class for safe user input: keeps asking until the value is valid
class TicketInput {

    private final Scanner sc;

    public TicketInput(Scanner sc) {
        this.sc = sc;
    }

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

    public double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double n = Double.parseDouble(sc.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("Number must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }

    // Reads y/n and returns true for yes
    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.equals("y") || s.equals("yes")) {
                return true;
            }
            if (s.equals("n") || s.equals("no")) {
                return false;
            }
            System.out.println("Please enter y or n.");
        }
    }
}

// Main class: program starts here
public class TicketFareSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TicketInput input = new TicketInput(sc);

        // One list holds both Bus and Train tickets (polymorphism)
        List<TravelTicket> tickets = new ArrayList<>();

        System.out.println("===== TICKET FARE SYSTEM =====");

        boolean running = true;

        // Menu loop: runs until the user chooses 0 (Exit)
        while (running) {
            showMenu();
            int choice = input.readInt("Enter your choice (0-5): ", 0);

            switch (choice) {
                case 1:
                    bookBusTicket(input, tickets);
                    break;
                case 2:
                    bookTrainTicket(input, tickets);
                    break;
                case 3:
                    showTicketTable(tickets);
                    break;
                case 4:
                    findTicket(input, tickets);
                    break;
                case 5:
                    showSalesReport(tickets);
                    break;
                case 0:
                    System.out.println("Thank you! Program is closing.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, enter a number from 0 to 5.");
            }
        }

        sc.close();
    }

    // Prints the main menu
    private static void showMenu() {
        System.out.println("\n----- MENU -----");
        System.out.println("1. Book Bus ticket");
        System.out.println("2. Book Train ticket");
        System.out.println("3. Show all tickets");
        System.out.println("4. Find a ticket by ID");
        System.out.println("5. Sales report");
        System.out.println("0. Exit");
    }

    // Takes bus ticket details from the user and adds the ticket to the list
    private static void bookBusTicket(TicketInput input, List<TravelTicket> tickets) {
        System.out.println("\n--- Book Bus Ticket ---");
        String name = input.readText("Passenger name: ");
        boolean student = input.readYesNo("Student? (y/n): ");
        String from = input.readText("From city: ");
        String to = input.readText("To city: ");
        double distance = input.readPositiveDouble("Distance in km: ");

        TravelTicket ticket = new BusTicket(name, student, from, to, distance);
        tickets.add(ticket);

        System.out.println("Ticket booked successfully!");
        ticket.displayInfo();
    }

    // Takes train ticket details from the user and adds the ticket to the list
    private static void bookTrainTicket(TicketInput input, List<TravelTicket> tickets) {
        System.out.println("\n--- Book Train Ticket ---");
        String name = input.readText("Passenger name: ");
        boolean student = input.readYesNo("Student? (y/n): ");
        String from = input.readText("From city: ");
        String to = input.readText("To city: ");
        int stations = input.readInt("Number of stations: ", 1);
        boolean business = input.readYesNo("Business class? (y/n): ");

        TravelTicket ticket = new TrainTicket(name, student, from, to, stations, business);
        tickets.add(ticket);

        System.out.println("Ticket booked successfully!");
        ticket.displayInfo();
    }

    // Prints all tickets as a simple table (no border lines)
    private static void showTicketTable(List<TravelTicket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("\nNo tickets booked yet.");
            return;
        }

        System.out.println();
        System.out.printf("%-6s %-14s %-8s %-24s %s%n",
                "ID", "Passenger", "Type", "Route", "Final Fare");

        for (TravelTicket t : tickets) {
            System.out.printf("%-6d %-14s %-8s %-24s %.2f%n",
                    t.getTicketId(),
                    t.getPassengerName(),
                    t.getTypeName(),
                    t.getRoute(),
                    t.getFinalFare());
        }
        System.out.println("\nTotal tickets: " + tickets.size());
    }

    // Searches a ticket by its ID and shows its details
    private static void findTicket(TicketInput input, List<TravelTicket> tickets) {
        int id = input.readInt("Enter ticket ID: ", 1);
        for (TravelTicket t : tickets) {
            if (t.getTicketId() == id) {
                t.displayInfo();
                return;
            }
        }
        System.out.println("No ticket found with ID " + id + ".");
    }

    // Shows how much money Bus and Train tickets earned, and the total
    private static void showSalesReport(List<TravelTicket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("\nNo tickets booked yet. Please book a ticket first.");
            return;
        }

        double busTotal = 0;
        double trainTotal = 0;
        int busCount = 0;
        int trainCount = 0;

        for (TravelTicket t : tickets) {
            if (t.getTypeName().equals("Bus")) {
                busTotal += t.getFinalFare();
                busCount++;
            } else {
                trainTotal += t.getFinalFare();
                trainCount++;
            }
        }

        System.out.println("\n--- Sales Report ---");
        System.out.println("Bus tickets   : " + busCount + "  (Rs. " + busTotal + ")");
        System.out.println("Train tickets : " + trainCount + "  (Rs. " + trainTotal + ")");
        System.out.println("Total sales   : Rs. " + (busTotal + trainTotal));
    }
}