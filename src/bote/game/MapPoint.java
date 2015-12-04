package bote.game;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Drawable;
import java.awt.Color;

public abstract class MapPoint implements Drawable {

    protected int x, y;
    protected AsciiCharacterData data;

    public MapPoint(int x, int y) {
        this.x = x;
        this.y = y;
        data = new AsciiCharacterData('-', Color.WHITE, Color.BLACK);
    }

    @Override
    public AsciiCharacterData getData() {
        return data;
    }

    @Override
    public void transform(int x, int y, AsciiCharacterData d) {
        if (x != 0) {
            this.x = this.x + x;
        }
        if (y != 0) {
            this.y = this.y + y;
        }
        if (d != null) {
            if (d.backgroundColor != null) {
                data.backgroundColor = d.backgroundColor;
            }
            if (d.foregroundColor != null) {
                data.foregroundColor = d.foregroundColor;
            }
            if (d.character != ' ') {
                data.character = d.character;
            }
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
