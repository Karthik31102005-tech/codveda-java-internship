
import java.util.Scanner;

public class BasicCalculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Basic Calculator ===");
        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();

        System.out.println("Choose operation: +, -, *, /");
        System.out.print("Operation: ");
        String operation = scanner.next();

        try {
            double result;

            switch (operation) {
                case "+":
                    result = add(a, b);
                    break;
                case "-":
                    result = subtract(a, b);
                    break;
                case "*":
                    result = multiply(a, b);
                    break;
                case "/":
                    result = divide(a, b);
                    break;
                default:
                    System.out.println("Invalid operation.");
                    scanner.close();
                    return;
            }

            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
