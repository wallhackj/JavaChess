package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;
import java.util.Objects;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;
import static com.wallhack.chess.board.BoardUtils.isNotKing;


public abstract class ChessPiece {
    private final Player player;
    private final Rank rank;
    private final String pictureName;
    private final Point coordinates;


    public ChessPiece(Player player, Rank rank, String pictureName, Point coordinates) {
        this.player = player;
        this.rank = rank;
        this.pictureName = pictureName;
        this.coordinates = coordinates;
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getRank() {
        return rank;
    }

    public String getIndex() {
        return pictureName;
    }

    public Point getCoordinates() {
        return coordinates;
    }


    public boolean isValidMove(Point target) {
        ChessPiece piece = getPieceAt(target);
        var validation = false;

        if (squaresUnderAttack().contains(target)){
            if (piece != null){
                if (getPlayer() != piece.getPlayer()
                        && isNotKing(piece)){
                    validation = true;
                }
            }else validation = true;
        }
        return validation;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece that)) return false;
        return player == that.player && rank == that.rank && Objects.equals(pictureName, that.pictureName) && Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, rank, pictureName, coordinates);
    }

    public abstract Set<Point> squaresUnderAttack();
}