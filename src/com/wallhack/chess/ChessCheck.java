package com.wallhack.chess;

import java.awt.*;

public class ChessCheck {
    public enum GameState{
        ONGOING,
        STALEMATE,
        CHECKMATE_TO_WHITE_KING,
        CHECKMATE_TO_BLACK_KING
    }

    private final Board board;
    private PieceMoves pieceMoves;

    public ChessCheck(Board board) {
        this.board = board;
    }
    private ChessPiece getKing(){
        ChessPiece piece = null;
        for (ChessPiece piece1 : board.pieceBox){
            if (piece1.getRank() == ChessPiece.Rank.King){
                piece = piece1;
            }
        }
        return piece;
    }

    private boolean isHorseAttacking(Point kingPosition) {
        int[][] knightMoves = {
                {1, 2}, {-1, 2}, {2, 1}, {2, -1},
                {-2, 1}, {-2, -1}, {1, -2}, {-1, -2}
        };

        for (int[] move : knightMoves) {
            int x = kingPosition.x + move[0];
            int y = kingPosition.y + move[1];

            if (board.getPieceAt(new Point(x,y)).getRank() == ChessPiece.Rank.Knight) {
                return true;
            }
        }
        return false;
    }
    private boolean isDiagonalAttacked(Point kingPozition){
        return false;
    }
    private boolean isPathAttacked(Point kingPozition){
        return false;
    }
    private boolean isStalemate(Point kingPozition){
        return false;
    }

    public GameState gameState() {
        Point kingPosition = getKing().getCoordinates();
            if ((getKing().getPlayer() == ChessPiece.Player.Black && isHorseAttacking(kingPosition))
                    || isDiagonalAttacked(kingPosition)
                    || isPathAttacked(kingPosition)) {
                if (isStalemate(kingPosition)) {
                    return GameState.STALEMATE;
                } else {
                    return getKing().getPlayer() == ChessPiece.Player.Black ? GameState.CHECKMATE_TO_BLACK_KING : GameState.CHECKMATE_TO_WHITE_KING;
                }
            }

        return GameState.ONGOING;
    }
    public void endMenu(){
        System.out.println("End");
    }

}
