package com.wallhack.chess;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private Component dragComponent;
    private final Board board;
    private Point dragOffset;
    public static final boolean SNAP_TO_GRID = false;

    public MouseHandler(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component comp = getBoard().getComponentAt(e.getPoint());
        if (comp != null) {
            dragComponent = comp;
            dragOffset = new Point();
            dragOffset.x = e.getPoint().x - comp.getX();
            dragOffset.y = e.getPoint().y - comp.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragComponent != null) {
            Board board = getBoard();
            Point p = board.pointToGrid(e.getPoint());
            System.out.println(p);
            board.setPieceGrid(dragComponent, p);

            dragComponent = null;
            board.setHightlightCell(null);
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
  //  https://stackoverflow.com/questions/13698217/how-to-make-draggable-components-with-imageicon/13700490#13700490
}