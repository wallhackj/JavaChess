package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;

public class Rook extends ChessPiece{

    public Rook(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    @Override
    public boolean isValidMove(Point target) {
        ChessPiece piece = getPieceAt(target);
        ChessPiece initialPiece = getPieceAt(getCoordinates());
        boolean validation = false;

        if (piece == null || piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece)){
            if (target.x == getCoordinates().x || target.y == getCoordinates().y) {
                if (isPathClear(getCoordinates(), target)) {
                    movedPieces.add(initialPiece);
                    validation = true;
                }
            }
        }
        return validation;
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        Set<Point> attackedSquares = new HashSet<>();

        for (int x = 0; x < 8; x++) {
            if (x != getCoordinates().x) {
                attackedSquares.add(new Point(x, getCoordinates().y));
            }
        }
        for (int y = 0; y < 8; y++) {
            if (y != getCoordinates().y) {
                attackedSquares.add(new Point(getCoordinates().x, y));
            }
        }
        return attackedSquares;
    }


}
