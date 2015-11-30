package asciiPanel;

import java.awt.Color;

public class Render {

    public char data;
    public int x, y;
    public Color foreground, background;
    
    public Render(char s,int x, int y, Color fore, Color bk){
        data = s;
        this.x = x;
        this.y = y;
        foreground = fore;
        background = bk;
    }
    
}
