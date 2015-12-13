package bote;

import asciiPanel.Render;
import asciiPanel.TileTransformer;
import java.awt.Color;

public abstract class Page {
    
    protected Viewer viewer;

    public abstract Color getBackgroundColor();

    public abstract Color getForegroundColor();

    public abstract Render[][] getDefaultDraw();
    
    public abstract TileTransformer[] getDefaultAnimation();

    public abstract Command pageAction(int key);

    public abstract Viewer playViewer();

}
