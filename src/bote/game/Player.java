package bote.game;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;

    public Player(String nm) {
        name = nm;
    }

    public Player(char[] nm) {
        name = "";
        for (char c : nm) {
            name += c;
        }
    }

    public String getName() {
        return name;
    }
}
