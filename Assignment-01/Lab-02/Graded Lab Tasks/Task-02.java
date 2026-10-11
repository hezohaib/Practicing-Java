
class Account {
    double balance;

    Account() {
        balance = 0;
    }

    Account(double b, int x) {
        balance = b;
    }

    void deposit(double amount) {
        balance = balance + amount;
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance = balance - amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }

    void display() {
        System.out.println("Balance: " + balance);
    }
}

class Main {
    public static void main(String[] args) {
        Account a1 = new Account();
        Account a2 = new Account(5000, 0);

        a1.deposit(1000);
        a1.withdraw(200);
        a1.display();

        a2.withdraw(1000);
        a2.display();
    }
}
