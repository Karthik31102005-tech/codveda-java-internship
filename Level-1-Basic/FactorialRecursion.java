
import java.util.Scanner;

public class FactorialRecursion {

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                "Factorial is not defined for negative numbers."
            );
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Factorial Calculator ===");
        System.out.print("Enter a non-negative integer (0-20): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.close();
            return;
        }

        int n = scanner.nextInt();

        try {
            if (n > 20) {
                System.out.println("Please enter a number from 0 to 20.");
            } else {
                System.out.println(
                    "Factorial of " + n + " = " + factorial(n)
                );
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
