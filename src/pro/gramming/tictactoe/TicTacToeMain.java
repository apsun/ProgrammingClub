package pro.gramming.tictactoe;

import pro.gramming.boardgame.BoardPrinter;
import pro.gramming.tictactoe.ai.TicTacToeRandomAI;
import pro.gramming.tictactoe.ai.TicTacToeSequentialAI;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeMain {
    private TicTacToeGame _game;
    private boolean _pauseOnUpdate;
    private final Scanner _scanner;

    public TicTacToeMain() {
        _scanner = new Scanner(System.in);
    }

    public void initializeGame() {
        _game = new TicTacToeGame(
            // new TicTacToeHumanPlayer('O'),
            // new TicTacToeHumanPlayer('X')
            new TicTacToeRandomAI('X'),
            new TicTacToeSequentialAI('O')
        );
        _pauseOnUpdate = true;
    }

    public void update() {
        System.out.println("Current board state:");
        System.out.println(BoardPrinter.toString(_game.getBoard()));
        switch (_game.update()) {
            case GAME_ENDED:
                System.out.println("Current board state:");
                System.out.println(BoardPrinter.toString(_game.getBoard()));
                System.out.println("Game over! Winning player: " + _game.getWinningPlayer().getSymbol());
                break;
            case GAME_TIED:
                System.out.println("Current board state:");
                System.out.println(BoardPrinter.toString(_game.getBoard()));
                System.out.println("Game tied!");
                break;
        }
        if (_pauseOnUpdate) {
            System.out.println("Press ENTER to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isGameOver() {
        return _game.isGameOver();
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

    public void onEndGame() {
        System.out.println("Bye~");
    }

    public static void main(String[] args) {
        TicTacToeMain main = new TicTacToeMain();
        do {
            main.initializeGame();
            while (!main.isGameOver()) {
                main.update();
            }
        } while (main.promptRetry());
        main.onEndGame();
    }
}
