package com.wallhack.chess;

import java.awt.*;
import java.util.Objects;

public class ChessCheck {

    public enum GameState{
        ONGOING,
        STALEMATE,
        CHECK_TO_WHITE_KING,
        CHECK_TO_BLACK_KING,
        CHECKMATE_TO_WHITE_KING,
        CHECKMATE_TO_BLACK_KING
    }

    private final Board board;
    private final PieceMoves pieceMoves;

    public ChessCheck(Board board, PieceMoves pieceMoves) {
        this.board = board;
        this.pieceMoves = pieceMoves;
    }

    private ChessPiece[] getKing(){
        ChessPiece[] kings = new ChessPiece[2];
        for (ChessPiece piece : board.getPieceBox()){
            if (piece.getRank() == ChessPiece.Rank.King){
                if (piece.getPlayer() == ChessPiece.Player.White){
                   kings[0] = piece;
                }else kings[1] = piece;
            }
        }
      return kings;
    }

    private GameState isCheck(){
        for (ChessPiece piece : board.getPieceBox()){
            for (int i = 0; i < getKing().length; i++){
                // Verificăm dacă mutarea piesei amenință regele
                Point kingCoordinates = getKing()[i].getCoordinates();
                Point pieceCoordinates = piece.getCoordinates();
                if (pieceMoves.isAllowed(kingCoordinates, pieceCoordinates)) {
                    // Dacă da, returnăm starea corespunzătoare șahului
                    return i == 0 ? GameState.CHECK_TO_WHITE_KING : GameState.CHECK_TO_BLACK_KING;
                }
            }
        }
        // Dacă nicio piesă nu amenință regele, returnăm null (nu este șah)
        return null;
    }

    private GameState isCheckMate(){
        // Verificăm dacă regele este în șah
        GameState checkState = isCheck();
        if (checkState != null){
            // Dacă regele este în șah, verificăm dacă acesta poate fi salvat
            if (canSaveKing(checkState)){
                // Dacă regele poate fi salvat, jocul continuă
                return GameState.ONGOING;
            } else {
                // Dacă regele nu poate fi salvat, este șah mat
                return checkState == GameState.CHECK_TO_BLACK_KING ? GameState.CHECKMATE_TO_BLACK_KING : GameState.CHECKMATE_TO_WHITE_KING;
            }
        }
        // Dacă regele nu este în șah, nu este șah mat
        return null;
    }

    private boolean canSaveKing(GameState checkState) {
        ChessPiece[] kings = getKing();
        ChessPiece kingInCheck = kings[checkState == GameState.CHECK_TO_WHITE_KING ? 0 : 1];

        // Verificăm dacă regele în șah poate fi mutat într-o poziție sigură
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue; // Nu ne interesează poziția actuală a regelui
                }
                Point newKingPos = new Point(kingInCheck.getCoordinates().x + dx, kingInCheck.getCoordinates().y + dy);
                if (pieceMoves.isAllowed(newKingPos, kingInCheck.getCoordinates())) {
                    // Dacă regele poate fi mutat în poziția nouă fără a fi în șah, returnăm true
                    if (!Objects.equals(isCheck(), GameState.CHECK_TO_WHITE_KING) || !Objects.equals(isCheck(), GameState.CHECK_TO_BLACK_KING)) {
                        return true;
                    }
                }
            }
        }

        // Verificăm dacă o altă piesă poate captura piesa care amenință regele
        for (ChessPiece piece : board.getPieceBox()) {
            if (piece.getPlayer() == kingInCheck.getPlayer()) {
                continue; // Ignorăm propriile piese
            }
            Point piecePos = piece.getCoordinates();
            if (pieceMoves.isAllowed(piecePos, kingInCheck.getCoordinates())) {
                // Dacă o piesă poate captura piesa care amenință regele, returnăm true
                return true;
            }
        }

        // Dacă nicio piesă nu poate salva regele, returnăm false
        return false;
    }


    public GameState gameState() {
        if (isCheckMate() != null){
            return isCheckMate();
        } else if (isCheck() != null) {
            return isCheck();
        }else
        return GameState.ONGOING;
    }

}
