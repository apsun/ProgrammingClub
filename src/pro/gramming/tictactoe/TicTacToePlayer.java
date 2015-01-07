package pro.gramming.tictactoe;

import pro.gramming.boardgame.BoardState;

public abstract class TicTacToePlayer {
    private char _symbol;

    protected TicTacToePlayer(char symbol) {
        _symbol = symbol;
    }

    protected char getSymbol() {
        return _symbol;
    }

    public abstract TicTacToeMove getMove(BoardState<TicTacToePiece> board);
    public abstract void onMoveFailed(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result);
    public abstract void onMoveSucceeded(BoardState<TicTacToePiece> board, TicTacToeMove move, TicTacToeMoveResult result);
}
