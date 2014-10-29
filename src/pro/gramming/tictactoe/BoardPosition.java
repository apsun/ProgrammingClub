package pro.gramming.tictactoe;

public class BoardPosition {
    private int _row, _col;

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
}
