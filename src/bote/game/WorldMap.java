package bote.game;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import java.util.Random;

public class WorldMap {

    private Random dice;
    private int width, height;
    private int x, y;

    public WorldMap(int x, int y, int w, int h) {
        // gives us the centerpoint
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        this.setSeed(x, y);
    }

    private void setSeed(int x, int y) {
        //Cantor pairing for distinct rand
        //math.stackexchange.com/questions/23503/create-unique-number-from-2-numbers
        long seed = (((x + y) * (x + y + 1)) / 2) + y;
        dice = new Random();
        dice.setSeed(seed);
    }

    public AsciiCharacterData getDraw(int x, int y) {
        Random r = new Random();
        r.setSeed((((x + y) * (x + y + 1)) / 2) + y);
        if (r.nextFloat() < 0.99995) {
            return new Biotope(x, y).getData();
        } else {
            return new Island(x, y).getData();
        }
    }

    public AsciiCharacterData[][] getFullDraw(int xs, int ys) {
        //the x y we are given is the midpoint, so we need to 
        //x - width/2 & y - height/2 to get starting index
        AsciiCharacterData[][] full = new AsciiCharacterData[width][height];
        int row = (xs - (width / 2));
        for (int x = 0; x < width; x++) {
            int col = (ys - (height / 2));
            for (int y = 0; y < height; y++) {
                full[x][y] = this.getDraw(row, col);
                col++;
            }
            row++;
        }
        return full;
    }

}
