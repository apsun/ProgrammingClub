package pro.gramming.tictactoe;

import pro.gramming.boardgame.BoardPiece;

public class TicTacToePiece implements BoardPiece {
    private final char _symbol;

    public TicTacToePiece(char symbol) {
        _symbol = symbol;
    }
    
    @Override
    public char getSymbol() {
        return _symbol;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TicTacToePiece) && getSymbol() == ((TicTacToePiece)o).getSymbol();
    }
}
