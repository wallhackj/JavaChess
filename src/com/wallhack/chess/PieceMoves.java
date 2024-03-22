package com.wallhack.chess;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PieceMoves {
    private final Board board;
    private final Set<ChessPiece> movedPieces = new HashSet<>();
    private ChessPiece rook;

    public PieceMoves(Board board) {
        this.board = board;
    }

    public boolean isPathClear(Point coord, Point initial) {
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
    public boolean isDiagonalClear(Point coord, Point initial) {
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
    private boolean isNotKing(ChessPiece piece){
        return !piece.getRank().equals(ChessPiece.Rank.King);
    }

    private boolean pieceIsMoved(ChessPiece piece){
        var isLegal = false;
            for (ChessPiece pawns : movedPieces){
                if (pawns.equals(piece)) {
                    isLegal = true;
                    break;
                }
            }

        return isLegal;
    }
    private boolean isValidPawnMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        ChessPiece initialPiece = board.getPieceAt(initial);

        var validation = false;
        int deltaX = initial.x - coord.x;
        int deltaY = initial.y - coord.y;


        if (piece == null) {
            if (initialPiece.getPlayer() == ChessPiece.Player.White ) {
                if ((deltaY == 2 || deltaY == 1) && coord.x == initial.x && !pieceIsMoved(initialPiece)) {
                    movedPieces.add(initialPiece);
                    validation = true;
                }else if (deltaY == 1 && pieceIsMoved(initialPiece)){
                    validation = true;
                }
            } else if (initialPiece.getPlayer() == ChessPiece.Player.Black) {
                if ((deltaY == -1 || deltaY == -2) && coord.x == initial.x && !pieceIsMoved(initialPiece)) {
                    movedPieces.add(initialPiece);
                    validation = true;
                } else if (deltaY == -1 && pieceIsMoved(initialPiece)) {
                    validation = true;
                }
            }

        } else if (initialPiece.getPlayer() == ChessPiece.Player.White && isNotKing(piece)) {
            if (deltaY == 1 && deltaX == 1 || deltaY == 1 && deltaX == -1) {
                validation = true;
            }
        }else if (initialPiece.getPlayer() == ChessPiece.Player.Black && isNotKing(piece)) {
            if (deltaY == -1 && deltaX == -1 || deltaY == -1 && deltaX == 1) {
                    validation = true;
            }
        }

        return validation;
    }


    private boolean isValidKnightMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        ChessPiece initialPiece = board.getPieceAt(initial);
        var validation = false;

        if (piece == null || piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece)){

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
        ChessPiece initialPiece = board.getPieceAt(initial);
        boolean validation = false;


        if (piece == null || piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece)){
            if (coord.x == initial.x || coord.y == initial.y) {
                if (isPathClear(initial, coord)) {
                    movedPieces.add(initialPiece);
                    validation = true;
                }
            }

        }
        return validation;
    }

    private boolean isValidBishopMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        ChessPiece initialPiece = board.getPieceAt(initial);
        boolean validation = false;

        if (piece == null || piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece)) {
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

    private boolean isValidCasling(Point coord, Point initial) {
        ChessPiece initialPiece = board.getPieceAt(initial);
        Point leftRook = new Point(0, initial.y);
        Point rightRook = new Point(7, initial.y);

        var valid = false;
        var deltaX = Math.abs(initial.x - coord.x);
        var deltaY = Math.abs(initial.y - coord.y);

        if (deltaY == 0 && deltaX == 2) {
            if (isPathClear(coord, initial) && !pieceIsMoved(initialPiece)) {
                ChessPiece leftRookPiece = board.getPieceAt(leftRook);
                ChessPiece rightRookPiece = board.getPieceAt(rightRook);
                if ((!pieceIsMoved(leftRookPiece))) {
                    valid = true;
                    rook = rightRookPiece;
                }else if (!pieceIsMoved(rightRookPiece)){
                    valid = true;
                    rook = leftRookPiece;
                }
            }
        }
        return valid;
    }


    private boolean isValidKingMove(Point coord, Point initial) {
        ChessPiece piece = board.getPieceAt(coord);
        ChessPiece initialPiece = board.getPieceAt(initial);
        var validation = false;

        if (isValidCasling(coord, initial)) {
            validation = true;
            if (coord.x == initial.x + 2) {
                board.deleteChessPiece(rook);
            }
            else if (coord.x == initial.x - 2) {
                board.deleteChessPiece(rook);
            }
        } else if (piece == null || (piece.getPlayer() != initialPiece.getPlayer() && isNotKing(piece))) {
            var deltaX = Math.abs(initial.x - coord.x);
            var deltaY = Math.abs(initial.y - coord.y);

            if (deltaX == 1 && deltaY == 0) {
                validation = true;
            } else if (deltaX == 0 && deltaY == 1) {
                validation = true;
            } else if (deltaX == 1 && deltaY == 1) {
                validation = true;
            }
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