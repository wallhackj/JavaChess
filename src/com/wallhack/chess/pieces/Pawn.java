package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;
import static com.wallhack.chess.board.BoardUtils.isValidPosition;

public class Pawn extends ChessPiece {

    public Pawn(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    private boolean isFirstMove() {
        return pieceIsMoved(this);
    }

    @Override
    public boolean isValidMove(Point target) {
        ChessPiece targetPiece = getPieceAt(target);

        int deltaX = getCoordinates().x - target.x;
        int deltaY = getCoordinates().y - target.y;

        if (targetPiece != null) {
            return false;
        }

        int direction = getPlayer() == Player.White ? 1 : -1;

        if (!isFirstMove() && Math.abs(deltaY) == 2) {
            return false;
        }

        if (Math.abs(deltaX) == 1) {
            return deltaY == direction;
        } else if (deltaX == 0) {
            return deltaY == direction || (isFirstMove() && deltaY == 2 * direction);
        }

        return false;
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        int direction = getPlayer() == Player.White ? -1 : 1;
        Point target1 = new Point(getCoordinates().x + 1, getCoordinates().y + direction);
        Point target2 = new Point(getCoordinates().x - 1, getCoordinates().y + direction);

        Set<Point> attackedSquares = new HashSet<>();
        if (isValidPosition(target1) && isValidPosition(target2)) {
            attackedSquares.add(target1);
            attackedSquares.add(target2);
        }
        return attackedSquares;
    }
}
