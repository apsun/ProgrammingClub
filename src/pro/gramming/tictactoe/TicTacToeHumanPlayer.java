package pro.gramming.tictactoe;

import pro.gramming.boardgame.BoardPosition;
import pro.gramming.boardgame.BoardState;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToeHumanPlayer extends TicTacToePlayer {
    private static final Pattern LOCATION_PATTERN = Pattern.compile("^\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*$");

    private final Scanner _scanner;

    public TicTacToeHumanPlayer(char symbol) {
        super(symbol);

        _scanner = new Scanner(System.in);
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

    private BoardPosition promptMove() {
        while (true) {
            System.out.print("Enter " + getSymbol() + "'s location (row, column): ");
            BoardPosition pos = parsePosition(_scanner.nextLine());
            if (pos == null) {
                System.out.println("Invalid coordinate format!");
            } else {
                return pos;
            }
        }
    }

    @Override
    public TicTacToeMove getMove(BoardState<TicTacToePiece> board) {
        BoardPosition position = promptMove();
        return new TicTacToeMove(position, new TicTacToePiece(getSymbol()));
    }

    @Override
    public void onMoveFailed(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result) {
        switch (result) {
            case INVALID_POSITION:
                System.out.println("Invalid location! Enter a location that's on the board!");
                break;
            case POSITION_OCCUPIED:
                System.out.println("That location is already occupied!");
                break;
        }
    }

    @Override
    public void onMoveSucceeded(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result) {
        System.out.println("Move successful!");
    }
}
