package com.wallhack.chess;

import com.wallhack.chess.board.Board;

import javax.swing.*;
import java.awt.*;

public class ChessGame {
    private static boolean state = false;
    private static JFrame mainFrame;
    private static Board myBoard = new Board();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            gameWindows(myBoard);
            GameStateVerifier gameStateVerifier = new GameStateVerifier();

            Timer timer = new Timer(1000, _ -> {
                if (!state) {
                    if (gameStateVerifier.gameState() == GameStates.CHECKMATE_TO_WHITE_KING || gameStateVerifier.gameState() == GameStates.CHECKMATE_TO_BLACK_KING) {
                        state = true;
                        endWindow();
                    }
                }
            });
            timer.start();
        });
    }

    private static void gameWindows(Board board) {
        mainFrame = new JFrame("Chess Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(board, BorderLayout.CENTER);
        mainFrame.setSize(800, 800);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    private static void endWindow() {
        JFrame endFrame = new JFrame("Game Over");
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(endFrame);
        endFrame.setSize(300, 100);
        endFrame.setLocationRelativeTo(mainFrame);
        endFrame.setVisible(true);
    }

    private static void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JLabel label = new JLabel("You want to restart?");

        JButton yesButton = new JButton("Yes!");
        JButton noButton = new JButton("No!");

        yesButton.addActionListener(_ -> {
            frame.dispose();
            mainFrame.dispose();
            state = false;
            myBoard = new Board();
            gameWindows(myBoard);

        });

        noButton.addActionListener(_ -> frame.dispose());

        panel.add(label);
        panel.add(yesButton);
        panel.add(noButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}
