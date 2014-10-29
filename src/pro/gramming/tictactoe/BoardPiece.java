package pro.gramming.tictactoe;

public enum BoardPiece {
    PLAYER_1,
    PLAYER_2;

    public BoardPiece getOther() {
        switch (this) {
            case PLAYER_1:
                return PLAYER_2;
            case PLAYER_2:
                return PLAYER_1;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case PLAYER_1:
                return "O";
            case PLAYER_2:
                return "X";
            default:
                return null;
        }
    }
}
