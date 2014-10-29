package pro.gramming.tictactoe;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern LOCATION_PATTERN = Pattern.compile("^\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*$");

    private static String getMoveResultString(MoveResult res) {
        switch (res) {
            case PLAYER_1_WON:
                return "Player " + BoardPiece.PLAYER_1 + " wins!";
            case PLAYER_2_WON:
                return "Player " + BoardPiece.PLAYER_2 + " wins!";
            case GAME_TIED:
            	return "Game tied!";
            case INVALID_LOCATION:
                return "Invalid location!";
            case LOCATION_OCCUPIED:
                return "That spot is already occupied!";
            case SUCCESS:
                return "Success!";
            default:
                return null;
        }
    }

    private static BoardPosition parsePosition(String input) {
        Matcher matcher = LOCATION_PATTERN.matcher(input);
        if (matcher.matches()) {
            int row = Integer.parseInt(matcher.group(1));
            int col = Integer.parseInt(matcher.group(2));
            return new BoardPosition(row, col);
        } else {
            return null;
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

    public static void main(String[] args) {
        Board board;
        Scanner scanner = new Scanner(System.in);
        boolean again;
        do {
        	board = new Board(3);
	        while (!board.isGameOver()) {
	            System.out.print("Enter " + board.getCurrentPlayer() + "'s location (row, column): ");
	            BoardPosition pos = parsePosition(scanner.nextLine());
	            if (pos != null) {
	                MoveResult result = board.putPieceAtPosition(pos);
	                String resultStr = getMoveResultString(result);
	                System.out.println(board);
	                System.out.println(resultStr);
	            } else {
	                System.out.println("Invalid coordinate format!");
	            }
	        }
	        again = promptRetry(scanner);
        } while (again);
        System.out.println("Bye!");
        scanner.close();
    }
}
