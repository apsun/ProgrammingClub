package pro.gramming.boardgame;

public class Board<TPiece extends BoardPiece> implements BoardState<TPiece> {
    private final int _size;
    private final Object[][] _pieces;

    public Board(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Board size must be greater than 1");
        }
        _size = size;
        _pieces = new Object[size][size];
    }

    @Override
    public int getSize() {
        return _size;
    }

    @Override
    public boolean isValidPosition(int row, int col) {
        int size = getSize();
        if (row < 0 || row >= size) return false;
        if (col < 0 || col >= size) return false;
        return true;
    }

    @Override
    public boolean isValidPosition(BoardPosition position) {
        return isValidPosition(position.getRow(), position.getCol());
    }

    @Override
    @SuppressWarnings("unchecked")
    public TPiece getPieceAtPosition(int row, int col) {
        ensureValidPosition(row, col);
        return (TPiece)_pieces[row][col];
    }

    @Override
    public TPiece getPieceAtPosition(BoardPosition position) {
        return getPieceAtPosition(position.getRow(), position.getCol());
    }

    public void setPieceAtPosition(TPiece piece, int row, int col) {
        ensureValidPosition(row, col);
        _pieces[row][col] = piece;
    }

    public void setPieceAtPosition(TPiece piece, BoardPosition position) {
        setPieceAtPosition(piece, position.getRow(), position.getCol());
    }

    private void ensureValidPosition(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IndexOutOfBoundsException(String.format("Invalid position: (%d, %d)", row, col));
        }
    }

    @Override
    public String toString() {
        return BoardPrinter.toString(this);
    }
}
