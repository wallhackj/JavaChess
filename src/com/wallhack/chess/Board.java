package com.wallhack.chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javax.swing.JLabel;


public class Board extends JPanel {
    private final PieceFactory pieceFactory = new PieceFactory();
    private final HashMap<String, ChessPiece> pieceBox = new HashMap<>(32);

    public HashMap<String, JLabel> pieceLabels = new HashMap<>(32);
    private final int cellSize = 80;
    private final int initialX = 63;
    private final int initialY = 60;
    private Point highlightCell;
    private final BoardRender boardRender;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new Board());
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public Board() {
        setLayout(new BoardLayoutManager());

        boardRender = new BoardRender(initialX, initialY, cellSize);

        pieceByDefault();

        for (String piece : pieceBox.keySet()) {
            createAndPositionLabel(piece);
        }

        for (String piece : pieceLabels.keySet()) {
            JLabel label = pieceLabels.get(piece);
            ChessPiece chessPiece = pieceBox.get(piece);
            if (label != null && chessPiece != null) {
                Point pieceCoordinates = new Point(chessPiece.getCoordinates().y, chessPiece.getCoordinates().x);
                add(label , pieceCoordinates);
            }
        }


        MouseHandler mouseHandler = new MouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

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

    private void createAndPositionLabel(String piece) {
        JLabel label = new JLabel();

        try {
            label.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource(pieceBox.get(piece).getIndex())))));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Eroare la citirea imaginii!");
        }

        Point offset = getBoardOffset();
        Point pieceCoordinates = gridToPoint(pieceBox.get(piece).getCoordinates());
        label.setBounds(pieceCoordinates.x + offset.x, pieceCoordinates.y + offset.y, cellSize, cellSize);

        pieceLabels.put(piece, label);
    }

    private void pieceByDefault () {
        String[][] defaultPositions = {
                {"LR1", "LN1", "LB1", "LQ", "LK", "LB2", "LN2", "LR2"},
                {"LP1", "LP2", "LP3", "LP4", "LP5", "LP6", "LP7", "LP8"},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {"Bp1", "Bp2", "Bp3", "Bp4", "Bp5", "Bp6", "Bp7", "Bp8"},
                {"Br1", "Bn1", "Bb1", "Bq", "Bk", "Bb2", "Bn2", "Br2"}
        };
        for (int i = 0; i < defaultPositions.length; i++) {
            for (int j = 0; j < defaultPositions.length; j++) {
                String pieceType = defaultPositions[i][j];
                if (!pieceType.equals(" ")) {
                    pieceBox.put(pieceType, pieceFactory.create(pieceType.charAt(1), new Point(i, j)));
                }
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

    public void setPieceGrid(Component comp, Point grid) {
        ((BoardLayoutManager) getLayout()).setPieceGrid(comp, grid);
        invalidate();
        revalidate();
        repaint();
    }
    protected Point getBoardOffset() {
        int width = getWidth();
        int height = getHeight();
        Point p = new Point();
        p.x = (width - (cellSize * 8)) / 2;
        p.y = (height - (cellSize * 8)) / 2;

        return p;
    }

    public void setHightlightCell(Point p) {
        if (highlightCell != p) {
            highlightCell = p;
            repaint();
        }
    }

}