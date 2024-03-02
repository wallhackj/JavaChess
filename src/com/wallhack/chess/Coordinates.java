package com.wallhack.chess;

public class Coordinates {
    private final int pos_X;
    private final int pos_Y;

    public Coordinates(int pos_X, int pos_Y) {
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
    }

    public int getPos_X() {
        return pos_X;
    }

    public int getPos_Y() {
        return pos_Y;
    }
}
