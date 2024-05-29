package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.BoardUtils.*;

public class King extends ChessPiece {
    private boolean hasBeenMoved = false;

    public King(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    @Override
    public boolean isValidMove(Point target) {
        var validation = false;
        ChessPiece pieceAt = getPieceAt(target);

        if (!isValidCastling(target)){
            for (ChessPiece piece : getPieceBox()) {
                if (!piece.squaresUnderAttack().contains(target)
                        && piece.getPlayer() != getPlayer()) {

                    if (squaresUnderAttack().contains(target)){
                        if (pieceAt != null){
                            if (pieceAt.getPlayer() != getPlayer() && !attackerIsUnderProtection(pieceAt)){
                                validation = true;
                                hasBeenMoved = true;
                            }
                        }else{
                            validation = true;
                            hasBeenMoved = true;
                        }
                    }
                }
            }
        } else validation = true;
        return validation;
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        Set<Point> attackedSquares = new HashSet<>();

        int[][] directions = {
                {0, 1}, {0, -1},  // sus, jos
                {1, 0}, {-1, 0},  // dreapta, stânga
                {1, 1}, {1, -1},  // diagonale
                {-1, 1}, {-1, -1}
        };


        int kingX = getCoordinates().x;
        int kingY = getCoordinates().y;

        for (int[] dir : directions) {
            int newX = kingX + dir[0];
            int newY = kingY + dir[1];

            Point coordinates = new Point(newX, newY);
            if (isValidPosition(coordinates)) {
                attackedSquares.add(coordinates);
            }
        }
        return attackedSquares;
    }

    private boolean attackerIsUnderProtection(ChessPiece target) {
        var valid = false;

        for (ChessPiece piece : getPieceBox()) {
            if (piece.squaresUnderAttack().contains(target.getCoordinates()) && piece.getPlayer() == target.getPlayer()){
                valid = true;
            }
        }
        return valid;
    }

    private boolean isValidCastling(Point coord) {

        Point leftRook = new Point(0, getCoordinates().y);
        Point rightRook = new Point(7, getCoordinates().y);

        var valid = false;
        var deltaX = coord.x - getCoordinates().x;
        var deltaY = coord.y - getCoordinates().y;

        if (deltaY == 0 && Math.abs(deltaX) == 2 && !hasBeenMoved) {
            Rook leftRookPiece = (Rook) getPieceAt(leftRook);
            Rook rightRookPiece = (Rook) getPieceAt(rightRook);

            if (deltaX == -2 && isPathClear(getCoordinates(),leftRook)
                    && leftRookPiece.isHasBeenMoved()) {
                leftRookPiece.getCoordinates().setLocation(getCoordinates().x - 1, getCoordinates().y);
                hasBeenMoved = true;
                valid = true;
            }
            if (deltaX == 2 && isPathClear(getCoordinates(),rightRook)
                    && rightRookPiece.isHasBeenMoved()) {
                rightRookPiece.getCoordinates().setLocation(getCoordinates().x + 1, getCoordinates().y);
                hasBeenMoved = true;
                valid = true;
            }

        }
        return valid;
    }

}
