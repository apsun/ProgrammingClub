package pro.gramming.guessgame;

import java.util.Scanner;

public class GuessGameMain {
    private static int promptLowerBound(Scanner scanner) {
        return promptInteger("Enter the lower bound: ", scanner);
    }

    private static int promptUpperBound(Scanner scanner, int lowerBound) {
        while (true) {
            int input = promptInteger("Enter the upper bound: ", scanner);
            if (input > lowerBound) {
                return input;
            } else {
                System.out.println("The upper bound must be greater than the lower bound!");
            }
        }
    }

    private static int promptGuess(Scanner scanner, int lowerBound, int upperBound) {
        while (true) {
            int input = promptInteger("Guess a number (" + lowerBound + "~" + upperBound + "): ", scanner);
            if (input >= lowerBound && input <= upperBound) {
                return input;
            } else {
                System.out.println("Enter a number between the lower and upper bounds!");
            }
        }
    }

    private static int promptInteger(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer, try again!");
            }
        }
    }

    private static int generateNumber(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound;
        int delta = (int)(Math.random() * range);
        return lowerBound + delta;
    }

    private static boolean promptRetry(Scanner scanner) {
        while (true) {
            System.out.print("Play again? (y/n): ");
            String input = scanner.nextLine();
            if ("y".equalsIgnoreCase(input)) {
                return true;
            } else if ("n".equalsIgnoreCase(input)) {
                return false;
            } else {
                System.out.println("Invalid input, enter either 'y' or 'n'!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            int lowerBound = promptLowerBound(scanner);
            int upperBound = promptUpperBound(scanner, lowerBound);
            int randomNum = generateNumber(lowerBound, upperBound);
            int guessCount = 0;

            while (true) {
                int guess = promptGuess(scanner, lowerBound, upperBound);
                ++guessCount;
                if (guess < randomNum) {
                    System.out.println("Too low!");
                    lowerBound = guess + 1;
                } else if (guess > randomNum) {
                    System.out.println("Too high!");
                    upperBound = guess - 1;
                } else {
                    if (guessCount == 1) {
                        System.out.println("Yay! You got it right on your first try!");
                    } else {
                        System.out.println("Yay! You got it right in " + guessCount + " tries!");
                    }
                    break;
                }
            }
        } while (promptRetry(scanner));
        System.out.println("Bye~");
    }
}
