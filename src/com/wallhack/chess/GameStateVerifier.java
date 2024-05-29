package com.wallhack.chess;

import com.wallhack.chess.pieces.ChessPiece;
import com.wallhack.chess.pieces.King;
import com.wallhack.chess.pieces.Knight;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.wallhack.chess.board.BoardUtils.*;

public class GameStateVerifier {
    private static King king;
    private static final Set<ChessPiece> piecesForSavingKing = new HashSet<>();

    public static Set<ChessPiece> getPiecesForSavingKing() {
        return piecesForSavingKing;
    }

    public GameStates gameState() {
        for (ChessPiece piece : getPieceBox()) {
            for (Point point : piece.squaresUnderAttack()) {
                ChessPiece attackedPiece = getPieceAt(point);
                if (attackedPiece != null) {
                    if (attackedPiece.getRank() == Rank.King
                            && attackedPiece.getPlayer() != piece.getPlayer()
                            && isPathClear(piece.getCoordinates(), point)) {
                        king = (King) attackedPiece;

                        if (canBeSaved(piece.getCoordinates())) {
                            return king.getPlayer() == Player.White ? GameStates.CHECK_TO_WHITE_KING
                                    : GameStates.CHECK_TO_BLACK_KING;
                        } else {
                            return king.getPlayer() == Player.White ? GameStates.CHECKMATE_TO_WHITE_KING
                                    : GameStates.CHECKMATE_TO_BLACK_KING;
                        }
                    }
                }
            }
        }

        return GameStates.ONGOING;
    }

    private boolean canBeSaved(Point checkPosition) {
        if (!piecesWhichCanSave(checkPosition).isEmpty()){
            piecesForSavingKing.addAll(piecesWhichCanSave(checkPosition));
            return true;
        }
        return false;
    }

    private Set<ChessPiece> piecesWhichCanSave(Point checkPosition) {
        Set<ChessPiece> saviors = new HashSet<>();

        ChessPiece attackingPiece = getPieceAt(checkPosition);
        if (attackingPiece == null) {
            return saviors;
        }

        for (ChessPiece piece : getPieceBox()) {
            if (piece.getPlayer() == king.getPlayer()) {
                if (piece.isValidMove(checkPosition)) {
                    saviors.add(piece);
                }

                if (!(attackingPiece instanceof Knight)) {
                    Set<Point> pathPoints = new HashSet<>();
                    pathPoints.addAll(getPathPoints(king.getCoordinates(), checkPosition));
                    pathPoints.addAll(getDiagonalPathPoints(king.getCoordinates(), checkPosition));

                    for (Point blockPosition : pathPoints) {
                        if (piece.isValidMove(blockPosition) && isNotKing(piece)) {
                            saviors.add(piece);
                        }
                    }
                }
            }
        }

        for (Point move : king.squaresUnderAttack()) {
            if (king.isValidMove(move) && !isSquareUnderAttack(move, king.getPlayer())) {
                saviors.add(king);
                break;
            }
        }

        return saviors;
    }

    private boolean isSquareUnderAttack(Point square, Player player) {
        for (ChessPiece piece : getPieceBox()) {
            if (piece.getPlayer() != player && piece.squaresUnderAttack().contains(square)) {
                return true;
            }
        }
        return false;
    }
}
