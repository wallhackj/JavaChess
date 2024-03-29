package com.wallhack.chess;

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
    private PieceMoves pieceMoves;

    public ChessCheck(Board board) {
        this.board = board;
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
        GameState check = null;

        for (ChessPiece piece : board.getPieceBox()){
            for (int i = 0; i< getKing().length; i++){
                if (pieceMoves.isAllowed(getKing()[i].getCoordinates(), piece.getCoordinates())) {
                    if (i == 0){
                        check = GameState.CHECK_TO_WHITE_KING;
                    }else check = GameState.CHECK_TO_BLACK_KING;
                }
            }
        }
        return check;
    }
    private GameState isCheckMate(){
        if (isCheck() != null ){
            if (isCheck() == GameState.CHECK_TO_BLACK_KING){
                return GameState.CHECKMATE_TO_BLACK_KING;
            }else return GameState.CHECKMATE_TO_WHITE_KING;
        }
        return null;
    }

    public GameState gameState() {
        if (isCheckMate() != null){
            return isCheckMate();
        } else if (isCheck() != null) {
            return isCheck();
        } else return GameState.ONGOING;
    }
    public void endMenu(){
        System.out.println("End");
    }

}
