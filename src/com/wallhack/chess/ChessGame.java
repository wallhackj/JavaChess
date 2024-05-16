package com.wallhack.chess;

import com.wallhack.chess.board.Board;

import javax.swing.*;
import java.awt.*;

import static com.wallhack.chess.GameStateVerifier.gameState;

public class ChessGame {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            Board board = new Board();
            frame.add(board, BorderLayout.CENTER);

            JPanel messagePanel = new JPanel();
            JLabel messageLabel = new JLabel("Game in progress");
            messagePanel.add(messageLabel);
            frame.add(messagePanel, BorderLayout.NORTH);

            JButton continueButton = new JButton("Continue");
            continueButton.addActionListener(e -> {
                // Handle the action of continuing the game
                // This could involve updating the game state and hiding the message panel
                // For simplicity, we just hide the message panel here
                messagePanel.setVisible(false);
            });
            messagePanel.add(continueButton);

            frame.setSize(800, 800);
            frame.setResizable(false);
            frame.setVisible(true);

            // Check the game state and display appropriate message
            switch (gameState()) {
                case CHECK_TO_BLACK_KING:
                    messageLabel.setText("Black King is in check");
                    messagePanel.setVisible(true);
                    break;
                case CHECK_TO_WHITE_KING:
                    messageLabel.setText("White King is in check");
                    messagePanel.setVisible(true);
                    break;
                default:
                    messagePanel.setVisible(false);
            }
        });
    }
}
