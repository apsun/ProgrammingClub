package pro.gramming.calculator;

import java.util.Scanner;

public class CalculatorMain {
    private static double promptNumber(Scanner scanner) {
        while (true) {
            System.out.print("Enter a number: ");
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again!");
            }
        }
    }

    private static String promptOperator(Scanner scanner) {
        while (true) {
            System.out.print("Enter an operator: ");
            String input = scanner.nextLine();
            if ("+".equals(input) ||
                "-".equals(input) ||
                "*".equals(input) ||
                "/".equals(input) ||
                "^".equals(input)) {
                return input;
            }
            System.out.println("Invalid operator, try again!");
        }
    }

    private static boolean promptRetry(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to perform another calculation? (y/n): ");
            String input = scanner.nextLine();
            if ("y".equalsIgnoreCase(input)) {
                return true;
            } else if ("n".equalsIgnoreCase(input)) {
                return false;
            } else {
                System.out.println("Invalid input, try again!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean retry;
        do {
            // Ask for input
            double a = promptNumber(scanner);
            String op = promptOperator(scanner);
            double b = promptNumber(scanner);
            double result;

            // Perform calculations
            if ("+".equals(op)) {
                result = a + b;
            } else if ("-".equals(op)) {
                result = a - b;
            } else if ("*".equals(op)) {
                result = a * b;
            } else if ("/".equals(op)) {
                result = a / b;
            } else if ("^".equals(op)) {
                result = Math.pow(a, b);
            } else {
                // Error: if this line is run, there is a bug!
                result = 0;
            }

            // Display result
            System.out.println(a + " " + op + " " + b + " = " + result);

            // Ask if user wants to continue
            retry = promptRetry(scanner);
        } while (retry);
    }
}
