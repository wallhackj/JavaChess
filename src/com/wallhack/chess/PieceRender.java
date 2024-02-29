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

    private void drawImage(Graphics2D g2, String piece, int x , int y, int intitialX, int initialY, int size) {
        loadImage();
        g2.drawImage(images.get(6), 43 ,130, size, size, null);
    }

    public void drawPiece(Graphics2D g2, String name,int initialX, int initialY, int cellSize){
        drawImage(g2, name, 1,1 ,initialX, initialY, cellSize);
    }


}
