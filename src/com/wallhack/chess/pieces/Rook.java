package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.Set;

import static com.wallhack.chess.board.BoardUtils.getPointSet;

public class Rook extends ChessPiece{
    private boolean hasBeenMoved = false;

    public Rook(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    public boolean isHasBeenMoved() {

        a
        return hasBeenMoved;
    }

    @Override
    public boolean isValidMove(Point target) {
        if (super.isValidMove(target)){
            hasBeenMoved = true;
        }
        return super.isValidMove(target);
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        int[][] verticalDirections = {
                {1, 0}, {0, -1}, {-1, 0}, {0, 1}
        };

        return getPointSet(verticalDirections, this);
    }

}
