package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.*;
import static com.wallhack.chess.board.BoardUtils.isValidPosition;

public class Bishop extends ChessPiece{

    public Bishop(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        Set<Point> attackedSquares = new HashSet<>();

        int[][] diagonalDirections = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : diagonalDirections) {
            int dx = dir[0];
            int dy = dir[1];

            for (int i = 1; i <= 7; i++) {
                int newX = getCoordinates().x + i * dx;
                int newY = getCoordinates().y + i * dy;

                Point point = new Point(newX, newY);
                if (isValidPosition(point)) {
                    attackedSquares.add(point);

                    ChessPiece pieceAt = getPieceAt(point);
                    if (pieceAt != null && pieceAt.getPlayer() == getPlayer()) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return attackedSquares;
    }
}
