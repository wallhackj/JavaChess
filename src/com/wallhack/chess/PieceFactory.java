package com.wallhack.chess;

import java.awt.*;

public class PieceFactory {
    public ChessPiece create (char name, Point coordinates){
        switch (name){
            //Pawn
            case 'p' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Pawn, 6, coordinates);
            }
            case 'P' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Pawn, 7, coordinates);
            }
            //Knight
            case 'n' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Knight, 4, coordinates);
            }
            case 'N' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Knight, 5, coordinates);

            }
            //Bishop
            case 'b' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Bishop, 0, coordinates);
            }
            case 'B' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Bishop, 1, coordinates);

            }
            //Rook
            case 'r' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Rook, 11, coordinates);
            }
            case 'R' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Rook, 10, coordinates);
            }
            //Queen
            case 'q' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Queen, 8, coordinates);
            }
            case 'Q' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Queen, 9, coordinates);

            }
            //King
            case 'k' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.King, 2, coordinates);
            }
            case 'K' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.King, 3, coordinates);
            }
            default -> throw new RuntimeException("Unknown Piece name!");
        }
    }
}
