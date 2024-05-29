package com.wallhack.chess.board;

import com.wallhack.chess.Rank;
import com.wallhack.chess.pieces.ChessPiece;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoardUtils {
    private static Board board;

    public BoardUtils(Board board) {
        BoardUtils.board = board;
    }

    public static ArrayList<ChessPiece> getPieceBox(){
        return board.getPieceBoxDirectly();
    }

    public static boolean isNotKing(ChessPiece piece){
        return !piece.getRank().equals(Rank.King);
    }

    public static boolean isValidPosition(Point p) {
        return p.x >= 0 && p.x < 8 && p.y >= 0 && p.y < 8;
    }

    public static Set<Point> getPointSet(int[][] verticalDirections , ChessPiece piece) {
        Set<Point> attackedSquares = new HashSet<>();

        for (int[] dir : verticalDirections) {
            int dx = dir[0];
            int dy = dir[1];

            for (int i = 1; i <= 7; i++) {
                int newX = piece.getCoordinates().x + i * dx;
                int newY = piece.getCoordinates().y + i * dy;

                Point point = new Point(newX, newY);
                if (isValidPosition(point)) {
                    attackedSquares.add(point);

                    ChessPiece pieceAt = getPieceAt(point);
                    if (pieceAt != null && pieceAt.getPlayer() == piece.getPlayer()) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return attackedSquares;
    }

    public static Set<Point> getPathPoints(Point source, Point target) {
        Set<Point> pathPoints = new HashSet<>();

        if (source.x == target.x || source.y == target.y) {
            int deltaX = Integer.compare(target.x, source.x);
            int deltaY = Integer.compare(target.y, source.y);
            //Straight Path
            defineAllPointsForPath(source, target, deltaX, deltaY, pathPoints);
        }

        return pathPoints;
    }

    public static Set<Point> getDiagonalPathPoints(Point source, Point target) {
        Set<Point> pathPoints = new HashSet<>();

        int deltaX = Integer.compare(target.x, source.x);
        int deltaY = Integer.compare(target.y, source.y);

        if (Math.abs(target.x - source.x) == Math.abs(target.y - source.y)) {
            //Diagonal Path
            defineAllPointsForPath(source, target, deltaX, deltaY, pathPoints);
        }

        return pathPoints;
    }

    private static void defineAllPointsForPath(Point source, Point target, int deltaX, int deltaY, Set<Point> pathPoints) {
        int x = source.x + deltaX;
        int y = source.y + deltaY;
        while (x != target.x || y != target.y) {
            pathPoints.add(new Point(x, y));
            x += deltaX;
            y += deltaY;
        }
    }

    public static boolean isPathClear(Point source ,Point coord) {
        var valid = true;
        int deltaX = coord.x - source.x;
        int deltaY = coord.y - source.y;

        for (int i = 1; i < Math.max(Math.abs(deltaX), Math.abs(deltaY)); i++) {
            int x = source.x + i * Integer.compare(deltaX, 0);
            int y = source.y + i * Integer.compare(deltaY, 0);

            if (getPieceAt(new Point(x, y)) != null) {
                valid = false;
            }
        }
        return valid;
    }

    public static ChessPiece getPieceAt(Point coordinates){
        ChessPiece myPiece = null;

        for (ChessPiece piece : getPieceBox()){
            if (piece.getCoordinates().x == coordinates.x && coordinates.y == piece.getCoordinates().y){
                myPiece = piece;
            }
        }
        return myPiece;
    }
}
