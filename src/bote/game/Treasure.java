package bote.game;

import asciiPanel.AsciiCharacterData;
import java.awt.Color;
import java.util.Random;

public class Treasure extends MapPoint {

    private final Random dice;
    private final Color PALETTE;

    public Treasure(int x, int y) {
        super(x, y);
        PALETTE = Color.MAGENTA;// nice blue
        //Cantor pairing for distinct rand
        //math.stackexchange.com/questions/23503/create-unique-number-from-2-numbers
        long seed = (((x + y) * (x + y + 1)) / 2) + y;
        dice = new Random();
        dice.setSeed(seed);
        super.data = getData(x, y);
    }

    private AsciiCharacterData getData(int x, int y) {
        System.out.print(x % 10);
        return new AsciiCharacterData(Integer.toString(x % 10 + 9).charAt(0), getColor(), getColor());
    }

    private Color getColor() {
        return new Color((int) (dice.nextInt(PALETTE.getRed() + 1)),
                0, (int) (dice.nextInt(PALETTE.getBlue() + 1)));
    }

}
