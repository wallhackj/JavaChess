package com.wallhack.chess;

import javax.swing.*;
import java.awt.*;

public class ChessGame {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new Board());
            frame.setSize(800,800);
            frame.setResizable(false);
            frame.setVisible(true);

        });
    }
}
