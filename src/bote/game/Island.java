package bote.game;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import bote.ImageLib;
import java.awt.Color;
import java.util.Random;

public class Island extends MapPoint {

    private Random dice;
    private Color PALETTE;

    public Island(int x, int y) {
        super(x, y);
        PALETTE = new Color(255, 200, 100); // nice brown
        //Cantor pairing for distinct rand
        //math.stackexchange.com/questions/23503/create-unique-number-from-2-numbers
        long seed = (((x + y) * (x + y + 1)) / 2) + y;
        dice = new Random();
        dice.setSeed(seed);
        super.biome = makeBiome();//make the biome, then the data
        super.data = makeData();
    }

    private AsciiCharacterData makeData() {
        return new AsciiCharacterData(this.getChar(), getColor(), Color.ORANGE);
    }

    private Biome makeBiome() {
        if (super.x % 1000 > 800) {
            return Biome.WEEDY;
        }
        if (super.x % 1000 > 600) {
            return Biome.CLOUDY;
        }
        if (super.x % 1000 > 400) {
            return Biome.DEEP;
        }
        if (super.x % 1000 > 200) {
            return Biome.SHALLOW;
        }
        return super.biome;
    }

    private Color getColor() {
        int delta = 50;// dont wanna make this + PALETTE.getBlue > 255
        return new Color(0,
                PALETTE.getGreen() + (int) (dice.nextInt(delta) / 2),
                PALETTE.getBlue() + (int) (dice.nextInt(delta) / 2));
    }

    private char getChar() {
        if (dice.nextBoolean()) {
            if (dice.nextBoolean()) {
                return ImageLib.SUN;
            } else {
                return ImageLib.DOT;
            }
        } else {
            if (dice.nextBoolean()) {
                return ImageLib.HOLLOW_DOT;
            } else {
                return ImageLib.CIRCLE;
            }
        }
    }
}
