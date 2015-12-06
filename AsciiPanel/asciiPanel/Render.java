package asciiPanel;

import java.awt.Color;
import java.awt.Point;

public class Render{

    public int x, y;
    public AsciiCharacterData charData;

    public Render(char c, int x, int y, Color fg, Color bg) {
        this(x, y, new AsciiCharacterData(c, fg, bg));
    }

    public Render(int x, int y, AsciiCharacterData d) {
        charData = d;
        this.x = x;
        this.y = y;
    }

    public Render(Point p, AsciiCharacterData d) {
        this(p.x, p.y, d);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public AsciiCharacterData getData() {
        return charData;
    }

    public void transform(int x, int y, AsciiCharacterData d) {
        this.x = this.x + x;
        this.y = this.y + y;
        if (d != null) {
            if (d.backgroundColor != null) {
                this.charData.backgroundColor = d.backgroundColor;
            }
            if (d.foregroundColor != null) {
                this.charData.foregroundColor = d.foregroundColor;
            }
            if (d.character != ' ') {
                this.charData.character = d.character;
            }
        }
    }
}
