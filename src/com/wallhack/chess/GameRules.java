package com.wallhack.chess;

import java.awt.*;

public class GameRules {
    private final Board board;

    public GameRules(Board board) {
        this.board = board;
    }



    private boolean isValidPawnMove(Point coord, Point initial) {
        int deltaY = initial.y - coord.y;

        return (deltaY == 1 || deltaY == 2) && coord.x == initial.x;
    }



    private boolean isValidKnightMove(Point coord, Point initial) {

        return true;
    }

    private boolean isValidRookMove(Point coord, Point initial) {

        return true;
    }



    public boolean isAllowed(ChessPiece piece,Point coord, Point initial) {

        System.out.println(piece.getCoordinates());

        return switch (piece.getRank()) {
            case Pawn -> isValidPawnMove(coord, initial);
            case Rook -> isValidRookMove(coord, initial);
            case King -> isValidKnightMove(coord, initial);
            default -> false; // Poate fi deplasată în mod implicit
        };
    }
}
