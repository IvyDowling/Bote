package bote.game;

import java.util.Random;

public class Map {
    private Random dice;
    private int x, y;
    
    public Map(int x, int y){ // gives us the centerpoint
        dice = new Random();
        this.x = x;
        this.y = y;
    }
}
