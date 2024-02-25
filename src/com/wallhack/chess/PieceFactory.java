package com.wallhack.chess;

public class PieceFactory {
    public ChessPiece create (char name, Coordinates coordinates){
        switch (name){
            //Pawn
            case 'p' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Pawn, "Chess_pdt60.png", coordinates);
            }
            case 'P' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Pawn, "Chess_plt60.png", coordinates);
            }
            //Knight
            case 'h' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Knight, "Chess_ndt60.png", coordinates);
            }
            case 'H' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Knight, "Chess_nlt60.png", coordinates);

            }
            //Bishop
            case 'b' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Bishop, "Chess_bdt60.png", coordinates);
            }
            case 'B' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Bishop, "Chess_blt60.png", coordinates);

            }
            //Rook
            case 'r' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Rook, "Chess_rdt60.png", coordinates);
            }
            case 'R' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Rook, "Chess_rlt60.png", coordinates);
            }
            //Queen
            case 'q' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.Queen, "Chess_qdt60.png", coordinates);
            }
            case 'Q' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.Queen, "Chess_qlt60.png", coordinates);

            }
            //King
            case 'k' -> {
                return new ChessPiece(ChessPiece.Player.Black, ChessPiece.Rank.King, "Chess_kdt60.png", coordinates);
            }
            case 'K' -> {
                return new ChessPiece(ChessPiece.Player.White, ChessPiece.Rank.King, "Chess_klt60.png", coordinates);
            }
            default -> throw new RuntimeException("Unknown Piece name!");
        }
    }
}
