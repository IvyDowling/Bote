package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Drawable;
import asciiPanel.Line;
import asciiPanel.Render;
import java.awt.Color;
import java.awt.Point;

public class IntroPage extends Page {

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getForegroundColor() {
        return Color.GREEN;
    }

    @Override
    public Drawable[] getDefaultDraw() {
        return new Drawable[]{
            new Drawable() {
                @Override
                public Render[] getRender() {
                    return new Line(new Point(0, 0), new Point(120, 39), new AsciiCharacterData(ImageLib.LIGHT_SHADE, Color.ORANGE, Color.BLACK)).getRender();
                }

                @Override
                public Render[] transform(int x, int y, AsciiCharacterData d) {
                    return new Line(new Point(x, y), new Point(120 - x, 39 - y), d).getRender();

                }
            }
        };
    }

    @Override
    public Command pageAction(int key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playViewer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
