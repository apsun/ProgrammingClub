package pro.gramming.tictactoe.ai;

import pro.gramming.boardgame.BoardPosition;
import pro.gramming.boardgame.BoardState;
import pro.gramming.tictactoe.TicTacToeMove;
import pro.gramming.tictactoe.TicTacToeMoveResult;
import pro.gramming.tictactoe.TicTacToePiece;
import pro.gramming.tictactoe.TicTacToePlayer;

public class TicTacToeSequentialAI extends TicTacToePlayer {
    public TicTacToeSequentialAI(char symbol) {
        super(symbol);
    }

    @Override
    public TicTacToeMove getMove(BoardState<TicTacToePiece> board) {
        for (int i = 0; i < board.getSize(); ++i) {
            for (int j = 0; j < board.getSize(); ++j) {
                if (board.getPieceAtPosition(i, j) == null) {
                    return new TicTacToeMove(new BoardPosition(i, j), new TicTacToePiece(getSymbol()));
                }
            }
        }
        return null;
    }

    @Override
    public void onMoveFailed(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result) {
        // This should never happen
    }

    @Override
    public void onMoveSucceeded(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result) {
        System.out.println("Sequential AI put piece at: " + move.getPosition());
    }
}
