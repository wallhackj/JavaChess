package com.wallhack.chess;

import com.wallhack.chess.pieces.ChessPiece;
import com.wallhack.chess.pieces.King;

import java.awt.*;

import static com.wallhack.chess.board.Board.getPieceAt;
import static com.wallhack.chess.board.Board.getPieceBox;

public class GameStateVerifier {
    public static GameStates gameState(){
        Point attackedCoordinates = null;

        for (ChessPiece piece : getPieceBox()){
            for (Point point : piece.squaresUnderAttack()) {
                attackedCoordinates = point;
            }

            King king = (King) getPieceAt(attackedCoordinates);
            if (king.getRank() == Rank.King && king.getPlayer() != piece.getPlayer()){
                if (canBeSaved()){
                    return king.getPlayer() == Player.White
                            ? GameStates.CHECK_TO_WHITE_KING : GameStates.CHECK_TO_BLACK_KING;
                }else {
                    return king.getPlayer() == Player.White
                            ? GameStates.CHECKMATE_TO_WHITE_KING : GameStates.CHECKMATE_TO_BLACK_KING;
                }
            }
        }
        return GameStates.ONGOING;
    }

    private static boolean canBeSaved(){
        return false;
    }
}
