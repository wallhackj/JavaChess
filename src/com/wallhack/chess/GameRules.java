package com.wallhack.chess;

import java.awt.*;

public class GameRules {
    private final Board board;

    public GameRules(Board board) {
        this.board = board;
    }

    private boolean isValidPawnMove(ChessPiece pawn, Point coord, Point initial) {
        // Implementează logica pentru mișcarea pionului
        return true; // Placeholder, înlocuiește cu logica reală
    }

    private boolean isValidKnightMove(ChessPiece knight, Point coord, Point initial) {
        // Implementează logica pentru mișcarea călărețului
        return true; // Placeholder, înlocuiește cu logica reală
    }

    private boolean isValidRookMove(ChessPiece rook, Point coord, Point initial) {
        // Implementează logica pentru mișcarea turnului
        return true; // Placeholder, înlocuiește cu logica reală
    }

// Implementează metode similare pentru Bishop, Queen și King

    public boolean isAllowed(Component dragComponent, Point coord, Point initial) {
        boolean allowed = true;
        try {
            ChessPiece piece = board.getPieceAt(dragComponent.getLocation());

            System.out.println(board.getPieceAt(dragComponent.getLocation()).getRank());

            if (piece != null) {
                switch (piece.getRank()) {
                    case Rook:
                        return allowed = isValidPawnMove(piece, coord, initial);
                    default:
                        return allowed = false; // Poate fi deplasată în mod implicit
                }
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return allowed; // Dacă nu este găsită nicio piesă
    }

}
