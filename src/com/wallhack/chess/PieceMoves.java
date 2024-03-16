package com.wallhack.chess;

import java.awt.*;

public class PieceMoves {
    private final Board board;

    public PieceMoves(Board board) {
        this.board = board;
    }

    private boolean isPathClear(Point coord, Point initial) {
        int deltaX = coord.x - initial.x;
        int deltaY = coord.y - initial.y;

        for (int i = 1; i < Math.max(Math.abs(deltaX), Math.abs(deltaY)); i++) {
            int x = initial.x + i * Integer.compare(deltaX, 0);
            int y = initial.y + i * Integer.compare(deltaY, 0);

            if (board.getPieceAt(new Point(x, y)) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalClear(Point coord, Point initial) {
        int deltaX = coord.x - initial.x;
        int deltaY = coord.y - initial.y;

        int dirX = Integer.compare(deltaX, 0);
        int dirY = Integer.compare(deltaY, 0);

        for (int i = 1; i < Math.max(Math.abs(deltaX), Math.abs(deltaY)); i++) {
            int x = initial.x + dirX * i;
            int y = initial.y + dirY * i;

            if (board.getPieceAt(new Point(x, y)) != null) {
                return false;
            }
        }
        return true;
    }


    private boolean isValidPawnMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        var validation = false;

        if (piece == null) {
            int deltaX = Math.abs(coord.x - initial.x);
            int deltaY = Math.abs(initial.y - coord.y);

            if ((deltaY == 1 || deltaY == 2) && coord.x == initial.x) {
                validation = true;
            }
            else if (deltaY == 1 && deltaX == 1 && board.getPieceAt(coord) != null) {
                validation = true;
            }
        }
        return validation;
    }


    private boolean isValidKnightMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        var validation = false;

        if (piece == null){
            var deltaX = initial.x - coord.x;
            var deltaY = initial.y - coord.y;

            if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1){
                validation = true;
            }else validation = Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2;
        }
        return validation;
    }

    private boolean isValidRookMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        boolean validation = false;

        if (piece == null) {
            if (coord.x == initial.x || coord.y == initial.y) {
                if (isPathClear(initial, coord)) {
                    validation = true;
                }
            }
        }
        return validation;
    }


    private boolean isValidBishopMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        boolean validation = false;

        if (piece == null) {
            if (Math.abs(coord.x - initial.x) == Math.abs(coord.y - initial.y)) {
                if (isDiagonalClear(initial, coord)) {
                    validation = true;
                }
            }
        }
        return validation;
    }


    private boolean isValidQueenMove(Point coord, Point initial) {
        return isValidBishopMove(coord , initial) || isValidRookMove(coord , initial);
    }

    private boolean isValidKingMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        var validation = false;

        if (piece == null){
            var deltaX = Math.abs(initial.x - coord.x);
            var deltaY = Math.abs(initial.y - coord.y);

            if (deltaX == 1 && deltaY == 0){
                validation = true;
            }else if (deltaX == 0 && deltaY == 1) validation = true;
            else if (deltaX == 1 && deltaY == 1) validation = true;
        }
        return validation;
    }


    public boolean isAllowed(ChessPiece piece,Point coord, Point initial) {

        System.out.println(piece.getCoordinates());

        return switch (piece.getRank()) {
            case Pawn -> isValidPawnMove(coord, initial);
            case Knight -> isValidKnightMove(coord,initial);
            case Bishop -> isValidBishopMove(coord , initial);
            case Rook -> isValidRookMove(coord, initial);
            case Queen -> isValidQueenMove(coord, initial);
            case King -> isValidKingMove(coord, initial);
        };
    }
}
