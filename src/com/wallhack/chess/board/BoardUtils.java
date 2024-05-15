package com.wallhack.chess.board;

import com.wallhack.chess.Rank;
import com.wallhack.chess.pieces.ChessPiece;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;

public class BoardUtils {
    public static boolean isNotKing(ChessPiece piece){
        return !piece.getRank().equals(Rank.King);
    }

    public static boolean isValidPosition(Point p) {
        return p.x >= 0 && p.x < 8 && p.y >= 0 && p.y < 8;
    }

    public static Set<Point> getPointSet(int[][] verticalDirections , ChessPiece piece) {
        Set<Point> attackedSquares = new HashSet<>();

        for (int[] dir : verticalDirections) {
            int dx = dir[0];
            int dy = dir[1];

            for (int i = 1; i <= 7; i++) {
                int newX = piece.getCoordinates().x + i * dx;
                int newY = piece.getCoordinates().y + i * dy;

                Point point = new Point(newX, newY);
                if (isValidPosition(point)) {
                    attackedSquares.add(point);

                    ChessPiece pieceAt = getPieceAt(point);
                    if (pieceAt != null && pieceAt.getPlayer() == piece.getPlayer()) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        a

        return attackedSquares;
    }

}
