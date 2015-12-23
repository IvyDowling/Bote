package bote.game;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import java.util.Random;

public class WorldMap {

    private Random dice;
    private final int WIDTH, HEIGHT;
    private int playerX, playerY;

    public WorldMap(int x, int y, int w, int h) {
        // gives us the centerpoint
        this.playerX = x;
        this.playerY = y;
        WIDTH = w;
        HEIGHT = h;
        this.setSeed(x, y);
    }

    public Biome getCurrentBiome() {
        return new Biotope(playerX, playerY).biome;
    }

    private void setSeed(int x, int y) {
        //Cantor pairing for distinct rand
        //math.stackexchange.com/questions/23503/create-unique-number-from-2-numbers
        long seed = (((x + y) * (x + y + 1)) / 2) + y;
        dice = new Random();
        dice.setSeed(seed);
    }

    /**
     *
     * @param worldX: this is the world value, not the screen x
     * @param worldY: this is the world value, not the screen y
     * @return
     */
    public AsciiCharacterData getDraw(int worldX, int worldY) {
        Random r = new Random();
        r.setSeed((((worldX + worldY) * (worldX + worldY + 1)) / 2) + worldY);
//        if ((dice.nextFloat() * 10) % 1 == 7) {
//        return new Treasure(worldX, worldY).getData();
//        } else 
        if (r.nextFloat() < 0.99995) {
            return new Biotope(worldX, worldY).getData();
        } else {
            return new Island(worldX, worldY).getData();
        }
    }

    /**
     *
     * @param screenX : the screen x
     * @param screenY : the screen y
     * @return the relative world tile at this screen point
     */
    public AsciiCharacterData getDrawOnScreen(int screenX, int screenY) {
        return getDraw(playerX + (screenX - ((WIDTH - 1) / 2)), playerY + (screenY - ((HEIGHT - 1) / 2)));
    }

    public Render[] getRow(int renderY) {
        Render[] temp = new Render[WIDTH];
        for (int renderX = 0; renderX < WIDTH; renderX++) {
            temp[renderX] = new Render(renderX, renderY, this.getDrawOnScreen(renderX, renderY));
        }
        return temp;
    }

    public Render[] getColumn(int renderX) {
        Render[] temp = new Render[HEIGHT];
        for (int renderY = 0; renderY < HEIGHT; renderY++) {
            temp[renderY] = new Render(renderX, renderY, this.getDrawOnScreen(renderX, renderY));
        }
        return temp;
    }

    public AsciiCharacterData[] getTopRow() {
        AsciiCharacterData[] temp = new AsciiCharacterData[WIDTH];
        for (int renderX = 0; renderX < WIDTH; renderX++) {
            temp[renderX] = this.getDrawOnScreen(renderX, 0);
        }
        return temp;
    }

    public AsciiCharacterData[] getBottomRow() {
        AsciiCharacterData[] temp = new AsciiCharacterData[WIDTH];
        for (int renderX = 0; renderX < WIDTH; renderX++) {
            temp[renderX] = this.getDrawOnScreen(renderX, HEIGHT - 1);
        }
        return temp;
    }

    public AsciiCharacterData[] getLeftCol() {
        AsciiCharacterData[] temp = new AsciiCharacterData[HEIGHT];
        for (int renderY = 0; renderY < HEIGHT; renderY++) {
            temp[renderY] = this.getDrawOnScreen(0, renderY);
        }
        return temp;
    }

    public AsciiCharacterData[] getRightCol() {
        AsciiCharacterData[] temp = new AsciiCharacterData[HEIGHT];
        for (int renderY = 0; renderY < HEIGHT; renderY++) {
            temp[renderY] = this.getDrawOnScreen(WIDTH - 1, renderY);
        }
        return temp;
    }

    public AsciiCharacterData[][] getFullDraw() {
        AsciiCharacterData[][] full = new AsciiCharacterData[WIDTH][HEIGHT];
        for (int rows = 0; rows < HEIGHT; rows++) {
            for (int cols = 0; cols < WIDTH; cols++) {
                full[cols][rows] = this.getDrawOnScreen(cols, rows);
            }
        }
        return full;
    }

    public void incPlayerX() {
        playerX = playerX + 1;
    }

    public void decPlayerX() {
        playerX = playerX - 1;
    }

    public void incPlayerY() {
        playerY = playerY + 1;
    }

    public void decPlayerY() {
        playerY = playerY - 1;
    }

}
