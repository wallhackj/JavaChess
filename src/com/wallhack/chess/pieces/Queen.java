package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends ChessPiece{
    private final Rook rook;
    private final Bishop bishop;

    public Queen(Player player, Rank rank, String pictureName, Point coordinates) {
        super(player, rank, pictureName, coordinates);
        this.rook = new Rook(player,Rank.Rook, "2",coordinates);
        this.bishop = new Bishop(player,Rank.Bishop, "2",coordinates);
    }

    @Override
    public boolean isValidMove(Point target) {
        return rook.isValidMove(target) || bishop.isValidMove(target);
    }

    @Override
    public Set<Point> squaresUnderAttack() {
        return Stream
                .concat(rook.squaresUnderAttack().stream()
                        ,bishop.squaresUnderAttack().stream())
                .collect(Collectors.toSet());
    }
}
