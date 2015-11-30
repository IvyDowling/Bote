package asciiPanel;

import java.awt.Color;

public class AsciiCharacterData {

    public char character;
    public Color foregroundColor;
    public Color backgroundColor;

    public AsciiCharacterData(char c, Color fg, Color bg) {
        character = c;
        foregroundColor = fg;
        backgroundColor = bg;
    }
}
