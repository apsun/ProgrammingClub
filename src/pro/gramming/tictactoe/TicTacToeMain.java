package pro.gramming.tictactoe;

public class TicTacToeMain {
    public static void main(String[] args) {
        GameRunner theGame = new GameRunner(3);
        do {
            theGame.initializeBoard();
            while (!theGame.isGameOver()) {
                BoardPosition pos = theGame.promptMove();
                theGame.performMove(pos);
            }
        } while (theGame.promptRetry());
        theGame.endGame();
    }
}
