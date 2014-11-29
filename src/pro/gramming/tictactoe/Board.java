package pro.gramming.tictactoe;

public class Board {
    private int _size;
    private BoardPiece[][] _pieces;
    private BoardPiece _currPlayer;
    private boolean _gameOver;

    public Board(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Board size must be greater than 1");
        }
        _size = size;
        _pieces = new BoardPiece[size][size];
        _currPlayer = BoardPiece.PLAYER_1;
        _gameOver = false;
    }

    public int getBoardSize() {
        return _size;
    }

    public boolean isValidPosition(BoardPosition position) {
        int size = getBoardSize();
        int row = position.getRow();
        int col = position.getCol();
        if (row < 0 || row >= size) return false;
        if (col < 0 || col >= size) return false;
        return true;
    }

    public BoardPiece getPieceAtPosition(BoardPosition position) {
        if (!isValidPosition(position)) {
            throw new IndexOutOfBoundsException(position.toString());
        } else {
            return getPieceAtPosition(position.getRow(), position.getCol());
        }
    }

    private BoardPiece getPieceAtPosition(int row, int col) {
        return _pieces[row][col];
    }

    public boolean isGameOver() {
        return _gameOver;
    }

    public BoardPiece getCurrentPlayer() {
        return _currPlayer;
    }

    public MoveResult putPieceAtPosition(int row, int col) {
        BoardPosition pos = new BoardPosition(row, col);
        return putPieceAtPosition(pos);
    }

    public MoveResult putPieceAtPosition(BoardPosition position) {
        if (isGameOver()) {
            throw new IllegalStateException("Game is already over, cannot submit new moves");
        }

        if (!isValidPosition(position)) {
            return MoveResult.INVALID_LOCATION;
        }

        if (getPieceAtPosition(position) != null) {
            return MoveResult.LOCATION_OCCUPIED;
        }

        BoardPiece piece = getCurrentPlayer();
        _pieces[position.getRow()][position.getCol()] = piece;
        _currPlayer = piece.getOther();

        if (checkPlacementResults(piece, position.getRow(), position.getCol())) {
            _gameOver = true;
            if (piece == BoardPiece.PLAYER_1) {
                return MoveResult.PLAYER_1_WON;
            } else {
                return MoveResult.PLAYER_2_WON;
            }
        } else if (isBoardFull()) {
            _gameOver = true;
        	return MoveResult.GAME_TIED;
        } else {
            return MoveResult.SUCCESS;
        }
    }

    private boolean checkRowForResult(BoardPiece piece, int col) {
        int size = getBoardSize();
        for (int checkX = 0; checkX < size; ++checkX) {
            if (getPieceAtPosition(checkX, col) != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumnForResult(BoardPiece piece, int row) {
        int size = getBoardSize();
        for (int checkY = 0; checkY < size; ++checkY) {
            if (getPieceAtPosition(row, checkY) != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDownDiagonalForResult(BoardPiece piece, int row, int col) {
        if (row != col) return false;
        int size = getBoardSize();
        for (int checkXY = 0; checkXY < size; ++checkXY) {
            if (getPieceAtPosition(checkXY, checkXY) != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkUpDiagonalForResult(BoardPiece piece, int row, int col) {
        int size = getBoardSize();
        if (row != size - 1 - col) return false;
        for (int checkXY = 0; checkXY < size; ++checkXY) {
            if (getPieceAtPosition(checkXY, size - 1 - checkXY) != piece) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isBoardFull() {
    	int size = getBoardSize();
    	for (int row = 0; row < size; ++row) {
    		for (int col = 0; col < size; ++col) {
    			if (getPieceAtPosition(row, col) == null) {
    				return false;
    			}
    		}
    	}
    	return true;
    }

    private boolean checkPlacementResults(BoardPiece piece, int row, int col) {
        if (checkRowForResult(piece, col)) return true;
        if (checkColumnForResult(piece, row)) return true;
        if (checkDownDiagonalForResult(piece, row, col)) return true;
        if (checkUpDiagonalForResult(piece, row, col)) return true;
        return false;
    }

    private static String outerJoin(String delimiter, String[] elements) {
        StringBuilder sb = new StringBuilder(delimiter);
        for (String element : elements) {
            sb.append(element);
            sb.append(delimiter);
        }
        return sb.toString();
    }

    private static String[] repeatArray(String element, int count) {
        String[] arr = new String[count];
        for (int i = 0; i < count; ++i) {
            arr[i] = element;
        }
        return arr;
    }

    private String getRowContentString(String delimiter, int row) {
        int size = getBoardSize();
        String[] elements = new String[size];
        for (int i = 0; i < size; ++i) {
            elements[i] = getCellContentString(row, i);
        }
        return outerJoin(delimiter, elements);
    }

    private String getCellContentString(int row, int col) {
        BoardPiece piece = getPieceAtPosition(row, col);
        if (piece == null) return "   ";
        return " " + piece.toString() + " ";
    }

    private static String getHorizontalBorderString() {
        return "---";
    }

    private static String getVerticalBorderString() {
        return "|";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = getBoardSize();
        String hBorder = getHorizontalBorderString();
        String vBorder = getVerticalBorderString();
        String boundRow = outerJoin(vBorder, repeatArray(hBorder, size));
        String newLine = System.getProperty("line.separator");

        for (int row = 0; row < size; ++row) {
            sb.append(boundRow);
            sb.append(newLine);
            sb.append(getRowContentString(vBorder, row));
            sb.append(newLine);
        }

        sb.append(boundRow);
        return sb.toString();
    }
}
