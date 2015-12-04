package bote.game;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Drawable;
import asciiPanel.Render;

public abstract class MapPoint implements Drawable {

    private int x, y;
    private AsciiCharacterData data;

    @Override
    public Render getRender() {
        return new Render(x, y, data);
    }

    @Override
    public Render transform(int x, int y, AsciiCharacterData d) {
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
        return getRender();
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
