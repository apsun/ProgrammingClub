package pro.gramming.tictactoe;

import pro.gramming.boardgame.BoardPosition;

public class TicTacToeMove {
    private final BoardPosition _position;
    private final TicTacToePiece _piece;

    public TicTacToeMove(BoardPosition position, TicTacToePiece piece) {
        _position = position;
        _piece = piece;
    }

    public BoardPosition getPosition() {
        return _position;
    }

    public TicTacToePiece getPiece() {
        return _piece;
    }
}
