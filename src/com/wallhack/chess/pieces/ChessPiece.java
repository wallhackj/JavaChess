package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;
import com.wallhack.chess.board.Board;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.wallhack.chess.board.Board.getPieceAt;

public abstract class ChessPiece {
    protected final Set<ChessPiece> movedPieces = new HashSet<>();
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

    protected boolean isNotKing(ChessPiece piece){
        return !piece.getRank().equals(Rank.King);
    }
    protected boolean isPathClear(Point coord, Point initial) {
        int deltaX = coord.x - initial.x;
        int deltaY = coord.y - initial.y;

        for (int i = 1; i < Math.max(Math.abs(deltaX), Math.abs(deltaY)); i++) {
            int x = initial.x + i * Integer.compare(deltaX, 0);
            int y = initial.y + i * Integer.compare(deltaY, 0);

            if (getPieceAt(new Point(x, y)) != null) {
                return false;
            }
        }
        return true;
    }
    protected boolean isDiagonalClear(Point coord, Point initial) {
        int deltaX = coord.x - initial.x;
        int deltaY = coord.y - initial.y;

        int dirX = Integer.compare(deltaX, 0);
        int dirY = Integer.compare(deltaY, 0);

        for (int i = 1; i < Math.max(Math.abs(deltaX), Math.abs(deltaY)); i++) {
            int x = initial.x + dirX * i;
            int y = initial.y + dirY * i;

            if (getPieceAt(new Point(x, y)) != null) {
                return false;
            }
        }
        return true;
    }

    protected boolean pieceIsMoved(ChessPiece piece){
        var isLegal = false;
        for (ChessPiece piece1 : movedPieces){
            if (piece1.equals(piece)) {
                isLegal = true;
                break;
            }
        }

        return isLegal;
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

    public abstract boolean isValidMove(Point target);

    public abstract Set<Point> squaresUnderAttack();
}