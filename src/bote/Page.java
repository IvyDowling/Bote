package bote;

import asciiPanel.Drawable;
import java.awt.Color;

public abstract class Page {

    public abstract Color getBackgroundColor();

    public abstract Color getForegroundColor();

    public abstract Drawable[] getDefaultDraw();

    public abstract Command pageAction(int key);

    public abstract void playViewer();

}
