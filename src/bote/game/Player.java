package bote.game;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private int x, y;

    public Player(String nm) {
        name = nm;
    }

    public String getName() {
        return name;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incX() {
        x = x + 1;
    }

    public void decX() {
        x = x - 1;
    }

    public void incY() {
        y = y + 1;
    }

    public void decY() {
        y = y - 1;
    }

    @Override
    public String toString() {
        return name;
    }
}
