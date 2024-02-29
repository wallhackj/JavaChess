package com.wallhack.chess;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Board extends JPanel implements ActionListener, MouseListener {

    private final PieceRender pieceRender = new PieceRender();
    private final int cellSize = 600 / 8;
    private final int initialX = 45;
    private final int initialY = 55;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        PieceFactory pieceFactory = new PieceFactory();
        ChessPiece chessPiece = pieceFactory.create('p',new Coordinates(1,1));

        Graphics2D g2 = (Graphics2D) g;
        String pieceName = chessPiece.getImg();

        drawBoard(g2);
        pieceRender.drawPiece(g2,pieceName, initialX, initialY, cellSize);

    }

    private void drawBoard(Graphics2D g2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                drawSquare(g2,2 * j, 2 * i, true);
                drawSquare(g2,1 + 2 * j, 1 + 2 * i, true);

                drawSquare(g2,1 + 2 * j, 2 * i, false);
                drawSquare(g2,2 * j, 1+ 2 * i, false);
            }
        }
    }

    private void drawSquare(Graphics2D g, int x, int y, boolean color){
        g.setColor(color ? Color.WHITE : Color.darkGray);
        g.fillRect(initialX + x * cellSize,initialY + y * cellSize, cellSize, cellSize);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    //De utilizat mai mult multithreading
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            Board board = new Board();
            JFrame frame = new JFrame("Chess Game");

            frame.add(board);
            frame.setSize(800, 800);
            frame.setVisible(true);
        });

        t1.start();



    }
}
