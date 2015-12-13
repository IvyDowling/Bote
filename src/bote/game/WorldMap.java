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

    public AsciiCharacterData getDraw(int worldX, int worldY) {
//        Random r = new Random();
//        r.setSeed((((worldX + worldY) * (worldX + worldY + 1)) / 2) + worldY);
        if (dice.nextFloat() < 0.99995) {
            return new Biotope(worldX, worldY).getData();
        } else {
            return new Island(worldX, worldY).getData();
        }
    }

    public Render[] getRow(int renderY) {
        Render[] temp = new Render[width];
        for (int renderX = 0; renderX < width; renderX++) {
            temp[renderX] = new Render(renderX, renderY, this.getDraw(x + renderX, y));
        }
        return temp;
    }

    public Render[] getColumn(int renderX) {
        Render[] temp = new Render[height];
        for (int renderY = 0; renderY < height; renderY++) {
            temp[renderY] = new Render(renderX, renderY, this.getDraw(x, y + renderY));
        }
        return temp;
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

    public void incPlayerX() {
        x = x + 1;
    }

    public void decPlayerX() {
        x = x - 1;
    }

    public void incPlayerY() {
        y = y + 1;
    }

    public void decPlayerY() {
        y = y - 1;
    }

}
