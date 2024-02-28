package com.wallhack.chess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PieceRender {
    private final HashMap<String, Image> pieceBox = new HashMap<>();

    private final ArrayList<String> pieceNames = new ArrayList<>(List
            .of("pawn_dark", "pawn_light", "knight_dark",
                    "knight_light", "bishop_dark", "bishop_light",
                    "rook_dark", "rook_light", "queen_dark", "queen_light",
                    "king_dark", "king_light"));


    private Image loadImage(String imgName){
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("resources" + imgName);

        //de utilizat Log4j pentru logare in viitor
        if (url != null) {
            try {
                File imgFile = new File(url.toURI());
                return ImageIO.read(imgFile);
            }catch (URISyntaxException e){
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private void setImage(){
        for (String pieceName : pieceNames) {
            if (loadImage(pieceName) != null) {
                pieceBox.put(pieceName, loadImage(pieceName));
            }
        }

    }

    public void drawPiece() {
        setImage();
        System.out.println("ceva");
    }


}
