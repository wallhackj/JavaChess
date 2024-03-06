package com.wallhack.chess;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardLayoutManager implements LayoutManager2 {
    private final Map<Component, Point> mapGrid;
    private final int GRID_SIZE = 80;

    public BoardLayoutManager() {
        mapGrid = new HashMap<>(25);
    }

    public void setPieceGrid(Component comp, Point grid) {
        mapGrid.put(comp, grid);
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof Point) {
            mapGrid.put(comp, (Point) constraints);
        } else {
            throw new IllegalArgumentException("Unexpected constraints, expected java.awt.Point, got " + constraints);
        }
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(GRID_SIZE * 8, GRID_SIZE * 8);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        mapGrid.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(GRID_SIZE * 8, GRID_SIZE * 8);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(GRID_SIZE * 8, GRID_SIZE * 8);
    }

    @Override
    public void layoutContainer(Container parent) {
        for (Component comp : parent.getComponents()) {
            Point p = mapGrid.get(comp);
            if (p == null) {
                comp.setBounds(0, 0, 0, 0); // Remove from sight :P
            } else {
                int initialX = 45;
                int x = p.x * GRID_SIZE + initialX;
                int initialY = 55;
                int y = p.y * GRID_SIZE + initialY;
                comp.setBounds(x, y, GRID_SIZE, GRID_SIZE);
            }
        }
    }
}

