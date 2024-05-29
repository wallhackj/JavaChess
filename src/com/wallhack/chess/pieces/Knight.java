package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.BoardUtils.isValidPosition;


public class Knight extends ChessPiece{

    public Knight(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        Set<Point> attackedSquares = new HashSet<>();
        int direction = getPlayer() == Player.White ? 1 : -1;

        int[] dx = {1, 2, 1, 2, -1, -2, -1, -2};
        int[] dy = {2, 1, -2, -1, 2, 1, -2, -1};

        for (int i = 0; i < dx.length; i++) {
            int x = getCoordinates().x + dx[i];
            int y = getCoordinates().y + dy[i] * direction;

            if (isValidPosition(new Point(x, y))) {
                attackedSquares.add(new Point(x, y));
            }
        }

        return attackedSquares;
    }

}
