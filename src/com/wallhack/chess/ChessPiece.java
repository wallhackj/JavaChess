package com.wallhack.chess;

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
    private final Coordinates coordinates;

    public ChessPiece(Player player, Rank rank, int index, Coordinates coordinates) {
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

    public Coordinates getCoordinates() {
        return coordinates;
    }
}