package com.wallhack.chess;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Board extends JPanel implements ActionListener, MouseListener {

    // de adugat PieceRender

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        PieceRender pieceRender = new PieceRender();
        Graphics2D g2 = (Graphics2D) g;

        drawBoard(g2);
        pieceRender.drawPiece();

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
        var cellSize = 600 / 8;

        g.setColor(color ? Color.WHITE : Color.darkGray);
        g.fillRect(45 + x * cellSize,55 + y * cellSize, cellSize, cellSize);
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
