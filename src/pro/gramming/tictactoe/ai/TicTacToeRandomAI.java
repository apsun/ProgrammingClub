package pro.gramming.tictactoe.ai;

import pro.gramming.boardgame.BoardPosition;
import pro.gramming.boardgame.BoardState;
import pro.gramming.tictactoe.TicTacToeMove;
import pro.gramming.tictactoe.TicTacToeMoveResult;
import pro.gramming.tictactoe.TicTacToePiece;
import pro.gramming.tictactoe.TicTacToePlayer;

public class TicTacToeRandomAI extends TicTacToePlayer {
    public TicTacToeRandomAI(char symbol) {
        super(symbol);
    }

    @Override
    public TicTacToeMove getMove(BoardState<TicTacToePiece> board) {
        int row = (int)(Math.random() * board.getSize());
        int col = (int)(Math.random() * board.getSize());
        BoardPosition pos = new BoardPosition(row, col);
        System.out.println("AI guessing: " + pos);
        return new TicTacToeMove(pos, new TicTacToePiece(getSymbol()));
    }

    @Override
    public void onMoveFailed(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result) {
        System.out.println("AI guess failed, trying again...");
    }

    @Override
    public void onMoveSucceeded(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result) {
        System.out.println("Random AI put piece at: " + move.getPosition());
    }
}
