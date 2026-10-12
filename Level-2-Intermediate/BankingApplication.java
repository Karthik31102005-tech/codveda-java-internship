
import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        balance += amount;
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful.");
        }
    }

    public void checkBalance() {
        System.out.printf("Current balance: %.2f%n", balance);
    }

    public void displayAccount() {
        System.out.println("Account holder: " + accountHolder);
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        BankAccount account = new BankAccount(name, 0.0);

        while (true) {
            System.out.println("\n=== Simple Banking Application ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    if (scanner.hasNextDouble()) {
                        account.deposit(scanner.nextDouble());
                    } else {
                        System.out.println("Invalid amount.");
                        scanner.next();
                    }
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    if (scanner.hasNextDouble()) {
                        account.withdraw(scanner.nextDouble());
                    } else {
                        System.out.println("Invalid amount.");
                        scanner.next();
                    }
                    break;

                case 3:
                    account.checkBalance();
                    break;

                case 4:
                    account.displayAccount();
                    account.checkBalance();
                    break;

                case 5:
                    System.out.println("Thank you for using our bank.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
