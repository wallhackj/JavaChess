package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;
import static com.wallhack.chess.board.BoardUtils.*;

public class Pawn extends ChessPiece {
    private final Set<ChessPiece> movedPieces = new HashSet<>();

    public Pawn(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    private boolean isFirstMove() {
        return pieceIsMoved(this, movedPieces);
    }


    @Override
    public boolean isValidMove(Point target) {
        ChessPiece targetPiece = getPieceAt(target);

        int deltaX = Math.abs(getCoordinates().x - target.x);
        int deltaY = Math.abs(getCoordinates().y - target.y);
        int direction = getPlayer() == Player.White ? -1 : 1;

        if (targetPiece != null) {
            if (squaresUnderAttack().contains(target) && isNotKing(targetPiece) && getPlayer() != targetPiece.getPlayer()) {
                movedPieces.add(this);
                return true;
            }
        } else {
            if (!isFirstMove() && deltaY == 2 && deltaX == 0
                    && direction == (target.y - getCoordinates().y) / deltaY) {
                movedPieces.add(this);
                return true;
            }

            if (deltaX == 0 && deltaY == 1
                    && direction == (target.y - getCoordinates().y) / deltaY) {
                movedPieces.add(this);
                return true;
            }
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
