import java.util.Scanner;
import java.util.ArrayList;

// ==============================
// BankAccount CLASS (OOP - Object)
// ==============================
class BankAccount {

    // Attributes (encapsulation - private data)
    private String accountHolder;
    private String accountNumber;
    private int pin;
    private double balance;
    private ArrayList<String> transactions;

    // Constructor - called when object is created
    public BankAccount(String name, String accNo, int pin, double initialBalance) {
        this.accountHolder = name;
        this.accountNumber = accNo;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add("Account opened with Rs. " + initialBalance);
    }

    // Method - verify PIN
    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    // Method - deposit money
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        balance += amount;
        transactions.add("Deposited: Rs. " + amount + " | Balance: Rs. " + balance);
        System.out.println("Rs. " + amount + " deposited successfully!");
        System.out.println("New Balance: Rs. " + balance);
    }

    // Method - withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance! Your balance is Rs. " + balance);
        } else {
            balance -= amount;
            transactions.add("Withdrawn: Rs. " + amount + " | Balance: Rs. " + balance);
            System.out.println("Rs. " + amount + " withdrawn successfully!");
            System.out.println("Remaining Balance: Rs. " + balance);
        }
    }

    // Method - check balance
    public void checkBalance() {
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Current Balance: Rs. " + balance);
    }

    // Method - show transaction history
    public void showHistory() {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + ". " + transactions.get(i));
            }
        }
    }

    // Method - change PIN
    public void changePin(int oldPin, int newPin) {
        if (verifyPin(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Wrong PIN! Cannot change.");
        }
    }

    // Getter for account number (used in login)
    public String getAccountNumber() {
        return accountNumber;
    }
}


// ==============================
// MAIN CLASS
// ==============================
public class MiniBankSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pre-created accounts (objects)
        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new BankAccount("Rahul Sharma", "ACC001", 1234, 10000);
        accounts[1] = new BankAccount("Priya Singh",  "ACC002", 5678, 25000);
        accounts[2] = new BankAccount("Amit Verma",   "ACC003", 9999, 5000);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     MINI BANK SYSTEM  v1.0   ║");
        System.out.println("╚══════════════════════════════╝");

        // Login
        System.out.print("\nEnter Account Number: ");
        String accNo = sc.next();

        BankAccount loggedIn = null;
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                loggedIn = acc;
                break;
            }
        }

        if (loggedIn == null) {
            System.out.println("Account not found! Exiting.");
            return;
        }

        System.out.print("Enter PIN: ");
        int enteredPin = sc.nextInt();

        if (!loggedIn.verifyPin(enteredPin)) {
            System.out.println("Wrong PIN! Access Denied.");
            return;
        }

        System.out.println("\n✔ Login Successful! Welcome.");

        // Main menu
        int choice;
        do {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    loggedIn.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: Rs. ");
                    double dep = sc.nextDouble();
                    loggedIn.deposit(dep);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: Rs. ");
                    double with = sc.nextDouble();
                    loggedIn.withdraw(with);
                    break;
                case 4:
                    loggedIn.showHistory();
                    break;
                case 5:
                    System.out.print("Enter current PIN: ");
                    int old = sc.nextInt();
                    System.out.print("Enter new PIN: ");
                    int newP = sc.nextInt();
                    loggedIn.changePin(old, newP);
                    break;
                case 6:
                    System.out.println("Logged out. Thank you!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 6);

        sc.close();
    }
}