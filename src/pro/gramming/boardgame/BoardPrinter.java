package pro.gramming.boardgame;

public class BoardPrinter {
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

    private static String getRowContentString(BoardState board, String delimiter, int row) {
        int size = board.getSize();
        String[] elements = new String[size];
        for (int i = 0; i < size; ++i) {
            elements[i] = getCellContentString(board, row, i);
        }
        return outerJoin(delimiter, elements);
    }

    private static String getCellContentString(BoardState board, int row, int col) {
        BoardPiece piece = board.getPieceAtPosition(row, col);
        if (piece == null) return "   ";
        return " " + piece.getSymbol() + " ";
    }

    private static String getHorizontalBorderString() {
        return "---";
    }

    private static String getVerticalBorderString() {
        return "|";
    }

    public static String toString(BoardState board) {
        StringBuilder sb = new StringBuilder();
        int size = board.getSize();
        String hBorder = getHorizontalBorderString();
        String vBorder = getVerticalBorderString();
        String boundRow = outerJoin(vBorder, repeatArray(hBorder, size));
        String newLine = System.getProperty("line.separator");

        for (int row = 0; row < size; ++row) {
            sb.append(boundRow);
            sb.append(newLine);
            sb.append(getRowContentString(board, vBorder, row));
            sb.append(newLine);
        }

        sb.append(boundRow);
        return sb.toString();
    }
}
