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
    private final String name;
    private final Point coordinates;


    public ChessPiece(Player player, Rank rank, String name, Point coordinates) {
        this.player = player;
        this.rank = rank;
        this.name = name;
        this.coordinates = coordinates;
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getRank() {
        return rank;
    }

    public String getIndex() {
        return name;
    }

    public Point getCoordinates() {
        return coordinates;
    }

}