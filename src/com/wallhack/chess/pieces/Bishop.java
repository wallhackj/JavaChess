package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;
import static com.wallhack.chess.board.Board.isValidPosition;

public class Bishop extends ChessPiece{

    public Bishop(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }
    @Override
    public boolean isValidMove(Point target) {
        ChessPiece piece = getPieceAt(target);
        ChessPiece initialPiece = getPieceAt(getCoordinates());
        boolean validation = false;

        if (piece == null || piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece)) {
            if (Math.abs(target.x - getCoordinates().x) == Math.abs(target.y - getCoordinates().y)) {
                if (isDiagonalClear(getCoordinates(), target)) {
                    validation = true;
                }
            }
        }
        return validation;
    }
    @Override
    public Set<Point> squaresUnderAttack() {
        Set<Point> attackedSquares = new HashSet<>();

        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                for (int i = 1; i < 8; i++) {
                    int x = getCoordinates().x + i * dx;
                    int y = getCoordinates().y + i * dy;
                    Point target = new Point(x, y);
                    if (isValidPosition(target)) {
                        attackedSquares.add(target);
                    }
                }
            }
        }

        return attackedSquares;
    }
}
