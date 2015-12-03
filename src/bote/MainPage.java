package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Drawable;
import asciiPanel.Render;
import java.awt.Color;

public class MainPage extends Page {

    @Override
    public Color getBackgroundColor() {
        return new Color(50, 120, 255);
    }

    @Override
    public Color getForegroundColor() {
        return Color.WHITE;
    }

    @Override
    public Drawable[] getDefaultDraw() {
        return new Render[]{new Render(40, 3, new AsciiCharacterData(ImageLib.ALPHA, new Color(100, 100, 0), this.getBackgroundColor()))};
    }

    @Override
    public Command pageAction(int key) {
        return new Command() {
            @Override
            public void exe(Controller c) {
                c.console(c.getPlayer().getName());
            }
        };
    }

    @Override
    public void playViewer() {
    }

}
