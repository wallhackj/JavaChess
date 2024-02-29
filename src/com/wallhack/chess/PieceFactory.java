package com.wallhack.chess;

public class PieceFactory {
    public ChessPiece create (char name, Coordinates coordinates){
        switch (name){
            //Pawn
            case 'p' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Pawn, "pawn_dark", coordinates);
            }
            case 'P' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Pawn, "pawn_light.png", coordinates);
            }
            //Knight
            case 'n' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Knight, "knight_dark.png", coordinates);
            }
            case 'N' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Knight, "knight_light.png", coordinates);

            }
            //Bishop
            case 'b' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Bishop, "bishop_dark.png", coordinates);
            }
            case 'B' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Bishop, "bishop_light.png", coordinates);

            }
            //Rook
            case 'r' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Rook, "rook_dark.png", coordinates);
            }
            case 'R' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Rook, "rook_light.png", coordinates);
            }
            //Queen
            case 'q' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Queen, "queen_dark.png", coordinates);
            }
            case 'Q' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Queen, "queen_light.png", coordinates);

            }
            //King
            case 'k' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.King, "king_dark.png", coordinates);
            }
            case 'K' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.King, "king_light.png", coordinates);
            }
            default -> throw new RuntimeException("Unknown Piece name!");
        }
    }
}
