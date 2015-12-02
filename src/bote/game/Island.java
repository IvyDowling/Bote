package bote.game;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Drawable;
import asciiPanel.Render;

public class Island implements Drawable {

    private Render[] island;

    public Island() {
        
    }

    @Override
    public Render[] getRender() {
        return island;
    }

    @Override
    public Render[] transform(int x, int y, AsciiCharacterData d) {
        Render[] render = island;
        for (Render r : render) {
            if (x != 0) {
                r.x = r.x + x;
            }
            if (y != 0) {
                r.y = r.y + y;
            }
            if (d != null) {
                if (d.backgroundColor != null) {
                    r.charData.backgroundColor = d.backgroundColor;
                }
                if (d.foregroundColor != null) {
                    r.charData.foregroundColor = d.foregroundColor;
                }
                if (d.character != ' ') {
                    r.charData.character = d.character;
                }
            }
        }

        return render;
    }

}
