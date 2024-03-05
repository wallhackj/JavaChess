package com.wallhack.chess;

import java.awt.*;

public class ChessPiece {
    public enum Player {
        White, Black
    }
    public enum Rank {
        Pawn, Knight, Bishop, Rook, Queen, King
    }

    private final Player player;
    private final Rank rank;
    private final int index;
    private final Point coordinates;

    public ChessPiece(Player player, Rank rank, int index, Point coordinates) {
        this.player = player;
        this.rank = rank;
        this.index = index;
        this.coordinates = coordinates;
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getRank() {
        return rank;
    }

    public int getIndex() {
        return index;
    }

    public Point getCoordinates() {
        return coordinates;
    }
}