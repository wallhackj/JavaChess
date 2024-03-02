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

        Graphics2D g2 = (Graphics2D) g;
        drawBoard(g2);
        drawLabel(g2);

        pieceRender.pieceByDefault(g2, cellSize);

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
        g.fillRect(initialX + x * cellSize, initialY + y * cellSize, cellSize, cellSize);
    }

    private void drawLabel(Graphics2D g2){
        char[] litere = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        char[] cifre = {'1', '2', '3', '4', '5', '6', '7', '8'};

        Font font = new Font("default", Font.BOLD, 20);
        g2.setFont(font);

        var pozitionX = initialX;
        var pozitionY = initialY;

        for(int i = 0; i < litere.length; i++){
            g2.drawChars(litere, i, 1, pozitionX + 30, pozitionY - 10);
            pozitionX += 75;
        }
        for(int i = 0; i < cifre.length; i++){
            g2.drawChars(cifre, i, 1, pozitionX - 625, pozitionY + 45);
            pozitionY += 75;
        }
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