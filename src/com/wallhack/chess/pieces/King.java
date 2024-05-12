package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;
import static com.wallhack.chess.board.Board.isValidPosition;

public class King extends ChessPiece{
    private ChessPiece rook;

    public King(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    @Override
    public boolean isValidMove(Point target) {
        ChessPiece piece = getPieceAt(target);
        ChessPiece initialPiece = getPieceAt(getCoordinates());
        var validation = false;

        if (piece == null || (piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece))) {
            var deltaX = Math.abs(getCoordinates().x - target.x);
            var deltaY = Math.abs(getCoordinates().y - target.y);

            if ((deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 1)) {
                movedPieces.add(initialPiece);
                validation = true;
            }
        }
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

    public void setRook(ChessPiece rook) {
        this.rook = rook;
    }

    private boolean isValidCasling(Point coord) {
        ChessPiece initialPiece = getPieceAt(getCoordinates());
        Point leftRook = new Point(0, getCoordinates().y);
        Point rightRook = new Point(7, getCoordinates().y);

        var valid = false;
        var deltaX = coord.x - getCoordinates().x;
        var deltaY = coord.y - getCoordinates().y;

        if (deltaY == 0 && Math.abs(deltaX) == 2) {
            if (isPathClear(coord, getCoordinates()) && pieceIsMoved(initialPiece)) {
                ChessPiece leftRookPiece = getPieceAt(leftRook);
                ChessPiece rightRookPiece = getPieceAt(rightRook);
                if (deltaX == -2 && pieceIsMoved(leftRookPiece)) {
                    valid = true;
                    rook = leftRookPiece;
                }else if (deltaX == 2 && pieceIsMoved(rightRookPiece)){
                    valid = true;
                    rook = rightRookPiece;
                }
            }
        }
        return valid;
    }
}
