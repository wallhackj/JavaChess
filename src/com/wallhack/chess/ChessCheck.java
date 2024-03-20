package com.wallhack.chess;

public class ChessCheck {
    public enum GameState{
        ONGOING,
        STALEMATE,
        CHECKMATE_TO_WHITE_KING,
        CHECKMATE_TO_BLACK_KING
    }

    private final Board board;

    public ChessCheck(Board board) {
        this.board = board;
    }


}
