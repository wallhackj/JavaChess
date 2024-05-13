package com.wallhack.chess.board;

import com.wallhack.chess.Rank;
import com.wallhack.chess.pieces.ChessPiece;

import java.awt.*;

public class BoardUtils {
    public static boolean isNotKing(ChessPiece piece){
        return !piece.getRank().equals(Rank.King);
    }

    public static boolean isValidPosition(Point p) {
        return p.x >= 0 && p.x < 8 && p.y >= 0 && p.y < 8;
    }

}
