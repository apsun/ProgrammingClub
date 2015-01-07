package pro.gramming.boardgame;

public interface BoardState<TPiece extends BoardPiece> {
    int getSize();
    boolean isValidPosition(BoardPosition position);
    boolean isValidPosition(int row, int col);
    TPiece getPieceAtPosition(BoardPosition position);
    TPiece getPieceAtPosition(int row, int col);
}
