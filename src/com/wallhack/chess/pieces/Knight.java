package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;
import com.wallhack.chess.board.Board;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;

public class Knight extends ChessPiece{

    public Knight(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
    }

    @Override
    public boolean isValidMove(Point target) {
        ChessPiece piece = getPieceAt(target);
        ChessPiece initialPiece = getPieceAt(getCoordinates());
        var validation = false;

        if (piece == null || piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece)){

            var deltaX = getCoordinates().x - target.x;
            var deltaY = getCoordinates().y - target.y;

            if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1){
                validation = true;
            }else validation = Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2;

        }
        return validation;
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
            attackedSquares.add(new Point(x, y));
        }

        return attackedSquares;
    }


}
