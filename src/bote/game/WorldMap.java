package bote.game;

import asciiPanel.Drawable;
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

    public Drawable getDraw(int x, int y) {
        Random r = new Random();
        r.setSeed((((x + y) * (x + y + 1)) / 2) + y);
        if (r.nextFloat() < 0.99995) {
            return new Biotope(x, y);
        } else {
            return new Island(x, y);
        }
    }

    public Drawable[][] getFullDraw(int x, int y) {
        //the x y we are given is the midpoint, so we need to 
        //x - width/2 & y - height/2 to get starting index
        Drawable[][] full = new Drawable[height][width];
        int row = (x - (width / 2));
        for (int r = 0; r < height; r++) {
            int col = (y - (height / 2));
            for (int c = 0; c < width; c++) {
                full[r][c] = this.getDraw(row, col);
                col++;
            }
            row++;
        }
        return full;
    }

}
