package com.wallhack.chess;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private int countMove = 0;
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

    private void setPieceMoves(Component comp, MouseEvent e) {
        if (comp != null) {
            dragComponent = comp;
            dragOffset = new Point();
            dragOffset.x = e.getPoint().x - comp.getX();
            dragOffset.y = e.getPoint().y - comp.getY();

            dragOffsetToPoint = getBoard().pointToGrid(comp.getLocation());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component comp = getBoard().getComponentAt(e.getPoint());
        ChessPiece piece = getBoard().getPieceAt(getBoard().pointToGrid(comp.getLocation()));

        if ((countMove % 2 == 0 && piece.getPlayer() == ChessPiece.Player.White)
                || (countMove % 2 == 1 && piece.getPlayer() == ChessPiece.Player.Black)) {
            if (chessCheck.gameState().equals(ChessCheck.GameState.ONGOING)) {
                setPieceMoves(comp, e);
            } else if (chessCheck.gameState().equals(ChessCheck.GameState.CHECK_TO_BLACK_KING)
                    || chessCheck.gameState().equals(ChessCheck.GameState.CHECK_TO_WHITE_KING)) {
                if (piece.getRank() == ChessPiece.Rank.King) {
                   setPieceMoves(comp, e);
                }
                System.out.println("e");
            } else System.out.println("p");
        }
    }

    private void setCountMove(Point p, Component rookMoved){
        pieceMoves.getRook().getCoordinates().setLocation(p);
        board.setPieceGrid(rookMoved,p);
        countMove++;
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
                    Point rookCoord = new Point(pieceMoves.getRook().getCoordinates().x + 2,
                            pieceMoves.getRook().getCoordinates().y);

                    Component rookMoved = getBoard().getComponentAt(getBoard().gridToPoint(rookCoord));

                    if (pieceMoves.getRook().getCoordinates().x == 7){
                        Point p1 = new Point(piece.getCoordinates().x - 1, piece.getCoordinates().y);
                        setCountMove(p1, rookMoved);
                    }else if (pieceMoves.getRook().getCoordinates().x == 0){
                        Point p1 = new Point(piece.getCoordinates().x + 1, piece.getCoordinates().y);
                        setCountMove(p1, rookMoved);
                    }
                }else if (pieceMoves.isAllowed(p, dragOffsetToPoint)) {
                    board.deleteChessPiece(pieceAt);
                    piece.getCoordinates().setLocation(p);
                    board.setPieceGrid(dragComponent, p);
                    countMove++;

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