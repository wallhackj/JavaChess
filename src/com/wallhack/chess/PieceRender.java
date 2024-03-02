package com.wallhack.chess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class PieceRender {
    ArrayList<Image> images = new ArrayList<>();

    private void loadImage(){
        File directoryFile = new File("src/com/wallhack/chess/resources");
        File[] files = directoryFile.listFiles();

        if (files != null) {
            for (File file : files) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    images.add(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void drawImage(Graphics2D g2, int pieceIndex, int x , int y, int size) {
        loadImage();
        try {
            int primeX = 45;
            int primeY = 55;
            g2.drawImage(images.get(pieceIndex), primeX + (size * x), primeY + (size * y), size, size, null);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }

    public void drawPiece(Graphics2D g2, int index, int initialX, int initialY, int cellSize){
        drawImage(g2, index, initialX, initialY, cellSize);
    }

    public void pieceByDefault (Graphics2D g2, int cellSize) {
        PieceFactory pieceFactory = new PieceFactory();

        char[][] defaultPositions = {
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}
        };

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char pieceType = defaultPositions[y][x];
                if (pieceType != ' ') {
                    ChessPiece piece = pieceFactory.create(pieceType, new Coordinates(x, y));
                    drawPiece(g2, piece.getIndex(), piece.getCoordinates().getPos_X(), piece.getCoordinates().getPos_Y(), cellSize);
                }
            }
        }
    }


}
