package com.wallhack.chess;

import com.wallhack.chess.board.Board;
import com.wallhack.chess.pieces.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.wallhack.chess.GameStateVerifier.*;
import static com.wallhack.chess.GameStates.*;
import static com.wallhack.chess.board.Board.getPieceAt;
import static com.wallhack.chess.board.BoardUtils.isValidPosition;

public class MouseHandler extends MouseAdapter {
    private int countMove = 0;
    private Component dragComponent;
    private final Board board;
    private Point dragOffset;
    public static final boolean SNAP_TO_GRID = false;
    private Point dragOffsetToPoint;

    public Board getBoard() {
        return board;
    }

    public MouseHandler(Board board) {
        this.board = board;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Component comp = getBoard().getComponentAt(e.getPoint());
        ChessPiece piece = getPieceAt(getBoard().pointToGrid(comp.getLocation()));

        System.out.println(gameState());
        System.out.println(getPiecesForSavingKing());
        if (gameState() == ONGOING){
            if ((countMove % 2 == 0 && piece.getPlayer() == Player.White)
                    || (countMove % 2 == 1 && piece.getPlayer() == Player.Black)) {
                setPieceMoves(comp, e);
            }
        }else if (gameState() == CHECK_TO_BLACK_KING
                || gameState() == CHECK_TO_WHITE_KING){
            if (getPiecesForSavingKing().contains(piece)){
                setPieceMoves(comp, e);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragComponent != null) {
            Board board = getBoard();
            Point grid = board.pointToGrid(e.getPoint());
            if (SNAP_TO_GRID) {
                Point p = board.gridToPoint(grid);
                dragComponent.setLocation(p);
            } else {
                Point dragPoint = new Point();
                dragPoint.x = e.getPoint().x - dragOffset.x;
                dragPoint.y = e.getPoint().y - dragOffset.y;

                dragComponent.setLocation(dragPoint);
            }
            board.setHightlightCell(grid);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragComponent != null) {
            Point p = board.pointToGrid(e.getPoint());

            if (isValidPosition(dragOffsetToPoint)) {
                ChessPiece piece = getPieceAt(dragOffsetToPoint);

                if (piece != null && p != null) {
                    if (piece.isValidMove(p) && isValidPosition(p)) {
                        // Elimina piesa de la destinatie daca exista
                        board.deleteChessPiece(getPieceAt(p));

                        // Mutare piesa la destinatie
                        piece.getCoordinates().setLocation(p);
                        board.setPieceGrid(dragComponent, p);
                        countMove++;
                    } else {
                        // Daca mutarea nu este valida, revenire la pozitia initiala
                        board.setPieceGrid(dragComponent, dragOffsetToPoint);
                    }

                    // Reseteaza componenta trasa si evidentierea celulei
                    dragComponent = null;
                    board.setHightlightCell(null);
                }
            }
        }
    }

    private void setPieceMoves(Component comp, MouseEvent e) {
        if (comp != null) {
            dragComponent = comp;
            dragOffset = new Point();
            dragOffset.x = e.getPoint().x - comp.getX();
            dragOffset.y = e.getPoint().y - comp.getY();

            dragOffsetToPoint = getBoard().pointToGrid(comp.getLocation());
        }
    }
}