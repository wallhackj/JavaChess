package com.wallhack.chess;

public class Coordinates {
    private int pos_X;
    private int pos_Y;

    public Coordinates(int pos_X, int pos_Y) {
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
    }

    public void setPos_X(int pos_X) {
        this.pos_X = pos_X;
    }

    public void setPos_Y(int pos_Y) {
        this.pos_Y = pos_Y;
    }

    public int getPos_X() {
        return pos_X;
    }

    public int getPos_Y() {
        return pos_Y;
    }


}
