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
    private final String img;
    private final Coordinates coordinates;

    public ChessPiece(Player player, Rank rank, String img, Coordinates coordinates) {
        this.player = player;
        this.rank = rank;
        this.img = img;
        this.coordinates = coordinates;
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getRank() {
        return rank;
    }

    public String getImg() {
        return img;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}