package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.Set;

import static com.wallhack.chess.board.BoardUtils.getPointSet;


public class Bishop extends ChessPiece{

    public Bishop(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        int[][] diagonalDirections = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

       return getPointSet(diagonalDirections, this);
    }
}
