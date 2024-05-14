package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.*;
import static com.wallhack.chess.board.BoardUtils.isValidPosition;
import static com.wallhack.chess.board.BoardUtils.pieceIsMoved;

public class King extends ChessPiece {
    private ChessPiece rook;
    private final Set<ChessPiece> movedPieces = new HashSet<>();

    public King(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    private boolean isPathClear(Point coord) {
        int deltaX = coord.x - getCoordinates().x;
        int deltaY = coord.y - getCoordinates().y;

        for (int i = 1; i < Math.max(Math.abs(deltaX), Math.abs(deltaY)); i++) {
            int x = getCoordinates().x + i * Integer.compare(deltaX, 0);
            int y = getCoordinates().y + i * Integer.compare(deltaY, 0);

            if (getPieceAt(new Point(x, y)) != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValidMove(Point target) {
        var validation = true;
        ChessPiece pieceAt = getPieceAt(target);

        if (!isValidCastling(target)){
            for (ChessPiece piece : getPieceBox()) {
                if (piece.getPlayer() != getPlayer()) {
                    if (!piece.squaresUnderAttack().contains(target)) {
                        if (squaresUnderAttack().contains(target)){
                            if (pieceAt != null){
                                validation = getPlayer() != piece.getPlayer();
                            }else validation = true;
                        }else validation = false;
                    }
                }
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

    private boolean isValidCastling(Point coord) {

        Point leftRook = new Point(0, getCoordinates().y);
        Point rightRook = new Point(7, getCoordinates().y);

        var valid = false;
        var deltaX = coord.x - getCoordinates().x;
        var deltaY = coord.y - getCoordinates().y;

        if (deltaY == 0 && Math.abs(deltaX) == 2) {
            if (isPathClear(coord) && pieceIsMoved(getPieceAt(getCoordinates()), movedPieces)) {
                ChessPiece leftRookPiece = getPieceAt(leftRook);
                ChessPiece rightRookPiece = getPieceAt(rightRook);
                if (deltaX == -2 && pieceIsMoved(leftRookPiece, movedPieces)) {
                    valid = true;
                    rook = leftRookPiece;
                } else if (deltaX == 2 && pieceIsMoved(rightRookPiece, movedPieces)) {
                    valid = true;
                    rook = rightRookPiece;
                }
            }
        }
        return valid;
    }

}
