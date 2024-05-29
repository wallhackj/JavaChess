package com.wallhack.chess.board;

import com.wallhack.chess.*;
import com.wallhack.chess.pieces.ChessPiece;
import com.wallhack.chess.pieces.PieceFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JLabel;


public class Board extends JPanel {
    private final PieceFactory pieceFactory = new PieceFactory();
    private final ArrayList<ChessPiece> pieceBox = new ArrayList<>();
    private final ArrayList<JLabel> pieceLabels = new ArrayList<>();
    private final int cellSize = 80;
    private final int initialX = 63;
    private final int initialY = 60;
    private final BoardRender boardRender;
    private Point highlightCell;


    public Board() {
        setLayout(new BoardLayoutManager());
        boardRender = new BoardRender(initialX, initialY, cellSize);

        var FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

        pieceByDefault(FEN);

        placePiece();

        BoardUtils boardUtils = new BoardUtils(this);

        MouseHandler mouseHandler = new MouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public ArrayList<ChessPiece> getPieceBoxDirectly() {
        return pieceBox;
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        boardRender.drawBoard(g2);
        boardRender.drawLabel(g2);

        if (highlightCell != null) {
            Point cell = gridToPoint(highlightCell);
            Rectangle bounds = new Rectangle(cell.x, cell.y, cellSize,cellSize);
            g2.setColor(Color.RED);
            g2.draw(bounds);
        }
    }

    private void placePiece(){
        for (int i = 0; i < pieceBox.size(); i++) {
            createAndPositionLabel(i);
        }

        for (int i = 0; i < pieceLabels.size(); i++) {
            JLabel label = pieceLabels.get(i);
            ChessPiece chessPiece = pieceBox.get(i);
            if (label != null && chessPiece != null) {
                Point pieceCoordinates = chessPiece.getCoordinates();
                add(label, pieceCoordinates);
            }
        }
    }

    private void createAndPositionLabel(int index) {
        JLabel label = new JLabel();

        try {
            String imagePath = "com/wallhack/chess/resources/" + pieceBox.get(index).getIndex();
            label.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(imagePath)))));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Eroare la citirea imaginii!");
        }

        Point offset = getBoardOffset();
        Point pieceCoordinates = gridToPoint(pieceBox.get(index).getCoordinates());
        label.setBounds(pieceCoordinates.x + offset.x, pieceCoordinates.y + offset.y, cellSize, cellSize);

        pieceLabels.add(label);
    }

    private void pieceByDefault(String fen) {
        int row = 0;
        int col = 0;

        for (char c : fen.toCharArray()) {
            if (c == '/') {
                row++;
                col = 0;
            } else if (Character.isDigit(c)) {
                col += Character.getNumericValue(c);
            } else {
                pieceBox.add(pieceFactory.create(c, new Point(col,row)));
                col++;
            }
        }
    }

    public Point pointToGrid(Point p) {
        Point point = null;
        if (p != null) {
            var pointX = (p.x - initialX) / cellSize;
            var pointY = (p.y - initialY) / cellSize;
            if (0 <= pointX && pointX <= 7 && 0 <= pointY && pointY <= 7){
                point = new Point(pointX, pointY);
            }
        }
        return point;
    }
    public Point gridToPoint(Point grid) {
        Point p = new Point();
        if (grid != null) {
            p.x = grid.x * cellSize + initialX;
            p.y = grid.y * cellSize + initialY;
        }
        return p;
    }

    protected Point getBoardOffset() {
        int width = getWidth();
        int height = getHeight();
        Point p = new Point();
        p.x = (width - (cellSize * 8)) / 2;
        p.y = (height - (cellSize * 8)) / 2;

        return p;
    }

    public void setPieceGrid(Component comp, Point grid) {
        ((BoardLayoutManager) getLayout()).setPieceGrid(comp, grid);
        invalidate();
        revalidate();
        repaint();
    }

    public void setHightlightCell(Point p) {
        if (highlightCell != p) {
            highlightCell = p;
            repaint();
        }
    }

    public void deleteChessPiece(ChessPiece piece) {
        if (piece != null) {

            for (int i = 0; i < pieceBox.size(); i++) {
                ChessPiece pi = pieceBox.get(i);
                if (pi == piece) {
                    pieceBox.remove(i);
                    JLabel label = pieceLabels.get(i);
                    pieceLabels.remove(i);
                    remove(label);
                    break;
                }
            }
        }

        revalidate();
        repaint();
    }

}