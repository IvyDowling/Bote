package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Drawable;
import asciiPanel.Line;
import asciiPanel.Render;
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntroPage extends Page {

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getForegroundColor() {
        return new Color(0, 170, 255);
    }

    @Override
    public Drawable[] getDefaultDraw() {
        List<Render> temp = new LinkedList<>();
        temp.addAll(Arrays.asList(ImageLib.getBoteLogo(0, 0, getForegroundColor(), getBackgroundColor())));
        temp.addAll(Arrays.asList(ImageLib.getBoat(90, 20,getBackgroundColor())));
        return temp.toArray(new Render[temp.size()]);
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
