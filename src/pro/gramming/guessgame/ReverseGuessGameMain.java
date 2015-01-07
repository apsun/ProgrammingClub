package pro.gramming.guessgame;

import java.util.Scanner;

public class ReverseGuessGameMain {
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

    private static int promptInequality(Scanner scanner, int aiGuess) {
        System.out.println("The AI guesses: " + aiGuess);
        while (true) {
            System.out.print("Is this number [>], [<], or [=]?: ");
            String cmp = scanner.nextLine();
            if (">".equals(cmp)) {
                return 1;
            } else if ("<".equals(cmp)) {
                return -1;
            } else if ("=".equals(cmp)) {
                return 0;
            } else {
                System.out.println("Invalid input, enter either '<', '>', or '='!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            int lowerBound = promptLowerBound(scanner);
            int upperBound = promptUpperBound(scanner, lowerBound);
            int guessCount = 0;
            boolean done = false;

            while (lowerBound <= upperBound) {
                int aiGuess = (lowerBound + upperBound) / 2;
                ++guessCount;
                int cmp = promptInequality(scanner, aiGuess);
                if (cmp < 0) {
                    lowerBound = aiGuess + 1;
                } else if (cmp > 0) {
                    upperBound = aiGuess - 1;
                } else if (cmp == 0) {
                    if (guessCount == 1) {
                        System.out.println("Well, that was fast. Choose a harder number!");
                    } else {
                        System.out.println("Yay! The AI guessed your number in " + guessCount + " tries.");
                    }
                    done = true;
                    break;
                }
            }

            if (!done) {
                System.out.println("You cheater! Stop changing your number! >:-(");
            }
        } while (promptRetry(scanner));
        System.out.println("Bye~");
    }
}
