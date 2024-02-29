package com.wallhack.chess;

public enum PieceIndex {
    PAWN_LIGHT(0),
    KNIGHT_LIGHT(1),
    BISHOP_LIGHT(2),
    ROOK_LIGHT(3),
    QUEEN_LIGHT(4),
    KING_LIGHT(5),
    PAWN_DARK(6),
    KNIGHT_DARK(7),
    BISHOP_DARK(8),
    ROOK_DARK(9),
    QUEEN_DARK(10),
    KING_DARK(11);

        private final int index;

        PieceIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
}
