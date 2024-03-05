package com.wallhack.chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JLabel;


public class Board extends JPanel{

    private final int cellSize = 80;
    private final int initialX = 45;
    private final int initialY = 55;

    ChessPiece pawn_black;
    Graphics2D g2;
    BoardRender boardRender;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Board());
        frame.setSize(800,800);
        frame.setVisible(true);

    }

    public Board() {
        setLayout(null);
        PieceFactory pieceFactory = new PieceFactory();
        boardRender = new BoardRender(initialX, initialY, cellSize);

        pawn_black = pieceFactory.create('p', new Point(0, 2));

        JLabel piece_pawn_black = new JLabel();
        try {
            piece_pawn_black.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("pawn_dark.png"))));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Randul 80!");
        }

        piece_pawn_black.setBounds(pawn_black.getCoordinates().x * cellSize + initialX,
                pawn_black.getCoordinates().y * cellSize + initialY,
                cellSize, cellSize);

        add(piece_pawn_black);

        MouseHandler mouseHandler = new MouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g2 = (Graphics2D) g;
        boardRender.drawBoard(g2);
        boardRender.drawLabel(g2);


    }

    public Point pointToGrid(Point p) {
        Point point = null;
        if (p != null) {
            var pointX = (p.x - initialX) / cellSize;
            var pointY = (p.y - initialY) / cellSize;
            if (0 <= pointX && pointX <= 7 && 0 <= pointY && pointY <= 7){
                point = new Point(pointX, pointY);
            }
        }
        return point;
    }

    public void setPieceGrid(Component comp, Point grid) {
 //       ((BoardLayoutManager) getLayout()).setPieceGrid(comp, grid);
        invalidate();
        revalidate();
        repaint();
    }

}