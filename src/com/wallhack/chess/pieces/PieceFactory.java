package com.wallhack.chess.pieces;

import com.wallhack.chess.Player;
import com.wallhack.chess.Rank;

import java.awt.*;

public class PieceFactory {
    public ChessPiece create (char name, Point coordinates){
        switch (name){
            //Pawn
            case 'p' -> {
                return new Pawn(Player.Black, Rank.Pawn, "pawn_dark.png", coordinates);
            }
            case 'P' -> {
                return new Pawn(Player.White, Rank.Pawn, "pawn_light.png", coordinates);
            }
            //Knight
            case 'n' -> {
                return new Knight(Player.Black, Rank.Knight, "knight_dark.png", coordinates);
            }
            case 'N' -> {
                return new Knight(Player.White, Rank.Knight, "knight_light.png", coordinates);

            }
            //Bishop
            case 'b' -> {
                return new Bishop(Player.Black, Rank.Bishop, "bishop_dark.png", coordinates);
            }
            case 'B' -> {
                return new Bishop(Player.White, Rank.Bishop, "bishop_light.png", coordinates);

            }
            //Rook
            case 'r' -> {
                return new Rook(Player.Black, Rank.Rook, "rook_dark.png", coordinates);
            }
            case 'R' -> {
                return new Rook(Player.White, Rank.Rook, "rook_light.png", coordinates);
            }
            //Queen
            case 'q' -> {
                return new Queen(Player.Black, Rank.Queen, "queen_dark.png", coordinates);
            }
            case 'Q' -> {
                return new Queen(Player.White, Rank.Queen, "queen_light.png", coordinates);

            }
            //King
            case 'k' -> {
                return new King(Player.Black, Rank.King, "king_dark.png", coordinates);
            }
            case 'K' -> {
                return new King(Player.White, Rank.King, "king_light.png", coordinates);
            }
            default -> throw new RuntimeException("Unknown Piece name!");
        }
    }
}
