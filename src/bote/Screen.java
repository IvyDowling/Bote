package bote;

import asciiPanel.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private static final int HEIGHT = 41, WIDTH = 121, SCALE = 32;
    private static final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private static final int STARTING_HEIGHT = 75, STARTING_WIDTH = 80;
    private static Screen screen = new Screen();
    private static AsciiPanel asciiPanel;
    private List<Drawable> drawList;
    private List<TileTransformer> transformList;

    public Screen() {
        this.setSize(DIMENSION);
        this.setBounds(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        this.add(asciiPanel = new AsciiPanel(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        drawList = new LinkedList<>();
        transformList = new LinkedList<>();
        asciiPanel.setBackground(Color.BLACK);
        asciiPanel.setForeground(Color.WHITE);
    }

    public void addAnimation(TileTransformer t) {
        transformList.add(t);
    }

    public void addAnimation(TileTransformer[] t) {
        for (TileTransformer tile : t) {
            this.addAnimation(tile);
        }
    }

    public void addDraw(Drawable d) {
        drawList.add(d);
    }

    public void addDraw(Drawable[] d) {
        for (Drawable draw : d) {
            this.addDraw(draw);
        }
    }

    public void render() {
        if (!drawList.isEmpty()) {
            Drawable[] tempDraw = drawList.toArray(new Drawable[drawList.size()]);
            for (Drawable draw : tempDraw) {
                for (Render r : draw.getRender()) {
                    asciiPanel.write(r);
                }
            }
        }
        if (!transformList.isEmpty()) {
            TileTransformer[] tempTransformer = transformList.toArray(new TileTransformer[transformList.size()]);
            transformList.clear();
            for (TileTransformer t : tempTransformer) {
                asciiPanel.withEachTile(t);
            }
        }
        this.repaint();
    }

    public void clearRenders() {
        drawList.clear();
        asciiPanel.clear();
    }

    public static Screen getInstance() {
        if (screen == null) {
            Screen screen = new Screen();
        }
        return screen;
    }

    public void setForegroundColor(Color fg) {
        asciiPanel.setDefaultForegroundColor(fg);
        asciiPanel.clear();
        asciiPanel.withEachTile(new TileTransformer(){
            @Override
            public void transformTile(int x, int y, AsciiCharacterData data) {
                data.foregroundColor = asciiPanel.getDefaultForegroundColor();
            }
        });
    }

    public void setBackgroundColor(Color bg) {
        asciiPanel.setDefaultBackgroundColor(bg);
        asciiPanel.clear();
        asciiPanel.withEachTile(new TileTransformer(){
            @Override
            public void transformTile(int x, int y, AsciiCharacterData data) {
                data.backgroundColor = asciiPanel.getDefaultBackgroundColor();
            }
        });
    }

    public int getAsciiPanelWidth() {
        return WIDTH;
    }

    public int getAsciiPanelHeight() {
        return HEIGHT;
    }
}
