package com.wallhack.chess;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Board extends JPanel implements ActionListener, MouseListener {


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int cellSize = 600 / 8;
        for (int i = 0; i < 4; i++) {
            g2.setColor(Color.WHITE);
            for (int j = 0; j < 4; j++) {
                g2.fillRect(2 * j * cellSize,(2 * i) * cellSize,cellSize,cellSize);
                g2.fillRect(( 1 + 2 * j ) * cellSize,(1 + 2 * i) * cellSize,cellSize,cellSize);
            }
            g2.setColor(Color.darkGray);
            for (int j = 0; j < 4; j++) {
                g2.fillRect((1 + 2 * j) * cellSize,(2 * i) * cellSize,cellSize,cellSize);
                g2.fillRect(2 * j * cellSize,(1 + 2 * i) * cellSize,cellSize,cellSize);
            }
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

    public static void main(String[] args) {

        Board board = new Board();
        JFrame frame = new JFrame("Exemplu JPanel");

        frame.add(board);
        frame.setSize(800, 600);
        frame.setVisible(true);


    }
}
