class Account {
    private double balance;
    public Account() {
        balance = 0;
    }
    public Account(double bal) {
        balance = bal;
    }
    public void deposit(double amount) {
        balance = balance + amount;
    }
    public void withdraw(double amount) {
        if(amount <= balance) {
            balance = balance - amount;
        }
        else {
            System.out.println("Not enough balance");
        }
    }
    public double getBalance() {
        return balance;
    }
}
 class Runner {
    public static void main(String[] args) {
        Account a1 = new Account(5000);
        System.out.println("Account 1 balance: " + a1.getBalance());
        Account a2 = new Account(a1.getBalance());
        System.out.println("Account 2 balance: " + a2.getBalance());
    }
}
