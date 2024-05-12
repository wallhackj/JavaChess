package com.wallhack.chess.board;

import java.awt.*;

public class BoardRender {

    private final int initialX;
    private final int initialY;
    private final int cellSize;

    public BoardRender(int initialX, int initialY, int cellSize) {
        this.initialX = initialX;
        this.initialY = initialY;
        this.cellSize = cellSize;
    }

    public void drawBoard(Graphics2D g2) {
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

    public void drawLabel(Graphics2D g2){
        char[] litere = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        char[] cifre = {'1', '2', '3', '4', '5', '6', '7', '8'};

        Font font = new Font("default", Font.BOLD, 20);
        g2.setFont(font);

        var pozitionX = initialX;
        var pozitionY = initialY;

        for(int i = 0; i < litere.length; i++){
            g2.drawChars(litere, i, 1, pozitionX + 30, pozitionY - 10);
            pozitionX += 80;
        }
        for(int i = 0; i < cifre.length; i++){
            g2.drawChars(cifre, i, 1, pozitionX - 670, pozitionY + 45);
            pozitionY += 80;
        }
    }
}
