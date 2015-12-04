package bote.game;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import java.awt.Color;
import java.util.Random;

public class Biotope extends MapPoint {

    private Random dice;
    private Color PALETTE;

    public Biotope(int x, int y) {
        super(x, y);
        PALETTE = new Color(0, 100, 200);// nice blue
        //Cantor pairing for distinct rand
        //math.stackexchange.com/questions/23503/create-unique-number-from-2-numbers
        long seed = (((x + y) * (x + y + 1)) / 2) + y;
        dice = new Random();
        dice.setSeed(seed);
        super.data = getData(x,y);
    }
    
    private AsciiCharacterData getData(int x, int y) {
        return new AsciiCharacterData('~', getColor(), getColor());
    }

    private Color getColor() {
        int delta = 50;// dont wanna make this + PALETTE.getBlue > 255
        return new Color(0,
                PALETTE.getGreen() + (int) (dice.nextInt(delta) / 2),
                PALETTE.getBlue() + (int) (dice.nextInt(delta) / 2));
    }
}
