package bote.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private static Player player;
    private String name;
    private int x, y;
    private List<Fish> fishDictionary;

    public Player(String nm) {
        player = new Player();
        this.setName(nm);
        fishDictionary = new ArrayList<>();
    }

    public Player() {
        name = "";
    }

    public final Player setName(String nm) {
        name = nm;
        return player;
    }

    public void addNewFish(Fish f) {
        if (!fishDictionary.contains(f)) {
            fishDictionary.add(f);
        }
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
        return name + " x: " + x + ", y: " + y + "\n" + fishDictionary.toString();
    }

    public static Player getInstance() {
        if (player == null) {
            Player screen = new Player();
        }
        return player;
    }
}
