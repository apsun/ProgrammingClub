package pro.gramming.boardgame;

public class BoardPosition {
    private final int _row, _col;

    public BoardPosition(int row, int col) {
        _row = row;
        _col = col;
    }

    public int getRow() {
        return _row;
    }

    public int getCol() {
        return _col;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", getRow(), getCol());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BoardPosition)) return false;
        BoardPosition otherPos = (BoardPosition)o;
        return getRow() == otherPos.getRow() && getCol() == otherPos.getCol();
    }
}
