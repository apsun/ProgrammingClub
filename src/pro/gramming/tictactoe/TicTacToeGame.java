package pro.gramming.tictactoe;

import pro.gramming.boardgame.*;

public class TicTacToeGame {
    private final Board<TicTacToePiece> _board;
    private final CircularQueue<TicTacToePlayer> _players;
    private boolean _isGameOver;
    private TicTacToePlayer _winningPlayer;

    public TicTacToeGame(int boardSize, TicTacToePlayer... players) {
        _board = new Board<TicTacToePiece>(boardSize);
        _players = new CircularQueue<TicTacToePlayer>(players);
        _isGameOver = false;
        _winningPlayer = null;
    }

    public boolean isGameOver() {
        return _isGameOver;
    }

    public TicTacToePlayer getWinningPlayer() {
        if (!isGameOver()) {
            throw new IllegalStateException("Game is currently in progress, no winning player");
        }

        return _winningPlayer;
    }

    public BoardState<TicTacToePiece> getBoard() {
        return _board;
    }

    private TicTacToeMoveResult validateMove(BoardState<TicTacToePiece> board, TicTacToeMove move) {
        BoardPosition position = move.getPosition();

        // Ensure position is valid
        if (!board.isValidPosition(position)) {
            return TicTacToeMoveResult.INVALID_POSITION;
        }

        // Ensure position isn't taken
        if (board.getPieceAtPosition(position) != null) {
            return TicTacToeMoveResult.POSITION_OCCUPIED;
        }

        return TicTacToeMoveResult.SUCCESS;
    }

    public TicTacToeMoveResult update() {
        if (isGameOver()) {
            throw new IllegalStateException("Game is already over, cannot submit new moves");
        }

        TicTacToePlayer player = _players.peekNext();
        Board<TicTacToePiece> board = _board;

        // Get (and validate) the move
        TicTacToeMove move;
        TicTacToeMoveResult result;
        do {
            move = player.getMove(board);
            result = validateMove(board, move);
            if (result != TicTacToeMoveResult.SUCCESS) {
                player.onMoveFailed(board, move, result);
            }
        } while (result != TicTacToeMoveResult.SUCCESS);

        // Perform the move
        board.setPieceAtPosition(move.getPiece(), move.getPosition());
        player.onMoveSucceeded(board, move, result);

        // Check results
        if (checkPlacementResults(move.getPiece(), move.getPosition())) {
            _isGameOver = true;
            _winningPlayer = player;
            result = TicTacToeMoveResult.GAME_ENDED;
        } else if (isBoardFull()) {
            _isGameOver = true;
            result = TicTacToeMoveResult.GAME_TIED;
        } else {
            _players.getNext();
        }
        return result;
    }

    private boolean checkRowForResult(TicTacToePiece piece, int col) {
        BoardState<TicTacToePiece> board = getBoard();
        int size = board.getSize();
        for (int checkX = 0; checkX < size; ++checkX) {
            if (!piece.equals(board.getPieceAtPosition(checkX, col))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumnForResult(TicTacToePiece piece, int row) {
        BoardState<TicTacToePiece> board = getBoard();
        int size = board.getSize();
        for (int checkY = 0; checkY < size; ++checkY) {
            if (!piece.equals(board.getPieceAtPosition(row, checkY))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDownDiagonalForResult(TicTacToePiece piece, int row, int col) {
        BoardState<TicTacToePiece> board = getBoard();
        if (row != col) return false;
        int size = board.getSize();
        for (int checkXY = 0; checkXY < size; ++checkXY) {
            if (!piece.equals(board.getPieceAtPosition(checkXY, checkXY))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkUpDiagonalForResult(TicTacToePiece piece, int row, int col) {
        BoardState<TicTacToePiece> board = getBoard();
        int size = board.getSize();
        if (row != size - 1 - col) return false;
        for (int checkXY = 0; checkXY < size; ++checkXY) {
            if (!piece.equals(board.getPieceAtPosition(checkXY, size - 1 - checkXY))) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoardFull() {
        BoardState<TicTacToePiece> board = getBoard();
        int size = board.getSize();
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                if (board.getPieceAtPosition(row, col) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkPlacementResults(TicTacToePiece piece, BoardPosition position) {
        int row = position.getRow();
        int col = position.getCol();
        if (checkRowForResult(piece, col)) return true;
        if (checkColumnForResult(piece, row)) return true;
        if (checkDownDiagonalForResult(piece, row, col)) return true;
        if (checkUpDiagonalForResult(piece, row, col)) return true;
        return false;
    }
}
