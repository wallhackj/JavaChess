package com.wallhack.chess;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private Component dragComponent;
    private final Board board;
    private final PieceMoves pieceMoves;
    private Point dragOffset;
    public static final boolean SNAP_TO_GRID = false;
    private Point dragOffsetToPoint;
    private final ChessCheck chessCheck;

    public MouseHandler(Board board, PieceMoves pieceMoves, ChessCheck chessCheck) {
        this.board = board;
        this.pieceMoves = pieceMoves;
        this.chessCheck = chessCheck;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component comp = getBoard().getComponentAt(e.getPoint());

        if (chessCheck.isCheckmate().equals(ChessCheck.GameState.ONGOING)) {
            if (comp != null) {
                dragComponent = comp;
                dragOffset = new Point();
                dragOffset.x = e.getPoint().x - comp.getX();
                dragOffset.y = e.getPoint().y - comp.getY();

                dragOffsetToPoint = getBoard().pointToGrid(comp.getLocation());
            }
        } else chessCheck.endMenu();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragComponent != null) {
            Point p = board.pointToGrid(e.getPoint());
            ChessPiece piece = board.getPieceAt(dragOffsetToPoint);
            ChessPiece pieceAt = board.getPieceAt(p);

            if (p != null) {
                if(piece.getRank() == ChessPiece.Rank.King && pieceMoves.isValidCasling(p, dragOffsetToPoint)){
                    piece.getCoordinates().setLocation(p);
                    board.setPieceGrid(dragComponent, p);
                    Point rookCoord = new Point(pieceMoves.getRook().getCoordinates().x + 1, pieceMoves.getRook().getCoordinates().y + 1);

                    Component rookMoved = getBoard().getComponentAt(getBoard().gridToPoint(rookCoord));

                    if (pieceMoves.getRook().getCoordinates().x == 7){
                        Point p1 = new Point(piece.getCoordinates().x - 1, piece.getCoordinates().y);
                        pieceMoves.getRook().getCoordinates().setLocation(p1);
                        board.setPieceGrid(rookMoved,p1);
                    }else if (pieceMoves.getRook().getCoordinates().x == 0){
                        Point p1 = new Point(piece.getCoordinates().x + 1, piece.getCoordinates().y);
                        pieceMoves.getRook().getCoordinates().setLocation(p1);
                        board.setPieceGrid(rookMoved,p1);
                    }
                }else if (pieceMoves.isAllowed(piece, p, dragOffsetToPoint)) {
                    board.deleteChessPiece(pieceAt);
                    piece.getCoordinates().setLocation(p);
                    board.setPieceGrid(dragComponent, p);

                } else {
                    board.setPieceGrid(dragComponent, dragOffsetToPoint);
                    piece.getCoordinates().setLocation(dragOffsetToPoint);
                }

                dragComponent = null;
                board.setHightlightCell(null);
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
}