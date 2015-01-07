package pro.gramming.tictactoe;

import pro.gramming.boardgame.BoardPrinter;
import pro.gramming.tictactoe.ai.TicTacToeRandomAI;

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
        _game = new TicTacToeGame(3,        // Board size
            new TicTacToeHumanPlayer('O'),  // Player 1
            new TicTacToeRandomAI('X')      // Player 2
        );
        _pauseOnUpdate = true;
    }

    private void printBoardState() {
        System.out.println("Current board state:");
        System.out.println(BoardPrinter.toString(_game.getBoard()));
    }

    private void pause() {
        if (_pauseOnUpdate) {
            System.out.print("Press ENTER to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    public void update() {
        printBoardState();
        switch (_game.update()) {
            case GAME_ENDED:
                printBoardState();
                System.out.println("Game over! Winning player: " + _game.getWinningPlayer().getSymbol());
                break;
            case GAME_TIED:
                printBoardState();
                System.out.println("Game tied!");
                break;
            default:
                pause();
                break;
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

    public static void main(String[] args) {
        TicTacToeMain main = new TicTacToeMain();
        do {
            main.initializeGame();
            while (!main.isGameOver()) {
                main.update();
            }
        } while (main.promptRetry());
    }
}
