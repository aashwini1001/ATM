// Account.java
public class Account {
    private String accountNumber;
    private int pin;
    private double balance;

    public Account(String accountNumber, int pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePIN(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Please collect your cash.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful.");
    }

    public void changePIN(int newPIN) {
        this.pin = newPIN;
        System.out.println("PIN successfully changed.");
    }
}
// ATM.java
import java.util.Scanner;

public class ATM {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        // Authentication Step
        System.out.print("Please enter your PIN: ");
        int inputPIN = scanner.nextInt();
        if (account.validatePIN(inputPIN)) {
            showMenu(scanner);
        } else {
            System.out.println("Incorrect PIN. Exiting.");
        }
    }

    private void showMenu(Scanner scanner) {
        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawCash(scanner);
                    break;
                case 3:
                    depositCash(scanner);
                    break;
                case 4:
                    changePIN(scanner);
                    break;
                case 5:
                    sessionActive = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your balance is: $" + account.getBalance());
    }

    private void withdrawCash(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
        System.out.println("Your new balance is: $" + account.getBalance());
    }

    private void depositCash(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Your new balance is: $" + account.getBalance());
    }

    private void changePIN(Scanner scanner) {
        System.out.print("Enter your new PIN: ");
        int newPIN = scanner.nextInt();
        account.changePIN(newPIN);
    }
}
// Main.java
public class Main {
    public static void main(String[] args) {
        // Creating a sample account with initial data
        Account account = new Account("123456789", 1234, 500.00);

        // Creating an ATM object with the account
        ATM atm = new ATM(account);

        // Starting the ATM operations
        atm.start();
    }
}
