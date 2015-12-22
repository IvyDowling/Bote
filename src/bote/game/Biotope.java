package bote.game;

import asciiPanel.AsciiCharacterData;
import java.awt.Color;
import java.util.Random;

public class Biotope extends MapPoint {

    private Random dice;
    private Color palette;

    public Biotope(int x, int y) {
        super(x, y);
        //Cantor pairing for distinct rand
        //math.stackexchange.com/questions/23503/create-unique-number-from-2-numbers
        long seed = (((x + y) * (x + y + 1)) / 2) + y;
        dice = new Random();
        dice.setSeed(seed);
        super.biome = makeBiome();
        palette = makePalette();
        super.data = makeData(x, y);
    }

    private Color makePalette() {
        switch (super.biome) {
            case DEEP:
                return new Color(51, 51, 153);//dark blue
            case SHALLOW:
                return new Color(0, 204, 255);//light blue
            case WEEDY:
                return new Color(51, 153, 102);//very green
            case CLOUDY:
                return new Color(0, 204, 153);// green-blue
            default:
                return new Color(0, 100, 200);// nice blue
        }
    }

    private AsciiCharacterData makeData(int x, int y) {
        return new AsciiCharacterData('~', getColor(), getColor());
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
        int green = dice.nextInt(delta) / 2;
        int blue = dice.nextInt(delta) / 2;
        if ((palette.getGreen() + green) > 255) {
            green = 0;
        }
        if ((palette.getBlue() + blue) > 255) {
            blue = 0;
        }
        return new Color(0,
                palette.getGreen() + green,
                palette.getBlue() + blue);
    }
}
