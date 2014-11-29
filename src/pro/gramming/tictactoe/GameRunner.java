package pro.gramming.tictactoe;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameRunner {
    private static final Pattern LOCATION_PATTERN = Pattern.compile("^\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*$");

    private Board _board;
    private int _boardSize;
    private Scanner _scanner;

    public GameRunner(int boardSize) {
        _boardSize = boardSize;
        _scanner = new Scanner(System.in);
    }

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

    public void initializeBoard() {
        _board = new Board(_boardSize);
    }

    public boolean promptRetry() {
        while (true) {
            System.out.print("Play again? (y/n): ");
            String input = _scanner.nextLine();
            if ("y".equalsIgnoreCase(input)) {
                return true;
            } else if ("n".equalsIgnoreCase(input)) {
                return false;
            } else {
                System.out.println("Invalid input, enter either 'y' or 'n'!");
            }
        }
    }

    public BoardPosition promptMove() {
        while (true) {
            System.out.print("Enter " + _board.getCurrentPlayer() + "'s location (row, column): ");
            BoardPosition pos = parsePosition(_scanner.nextLine());
            if (pos == null) {
                System.out.println("Invalid coordinate format!");
            } else {
                return pos;
            }
        }
    }

    public boolean isGameOver() {
        return _board.isGameOver();
    }

    public void performMove(BoardPosition pos) {
        MoveResult result = _board.putPieceAtPosition(pos);
        String resultStr = getMoveResultString(result);
        System.out.println(_board);
        System.out.println(resultStr);
    }

    public void endGame() {
        System.out.println("Bye~");
    }
}
