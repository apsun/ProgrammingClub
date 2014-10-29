package pro.gramming.tictactoe;

public enum MoveResult {
    // Move failed
    INVALID_LOCATION,
    LOCATION_OCCUPIED,

    // Move succeeded
    SUCCESS,
    PLAYER_1_WON,
    PLAYER_2_WON,
    GAME_TIED
}
