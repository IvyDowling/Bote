package bote;

import asciiPanel.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private static final int HEIGHT = 41, WIDTH = 121, SCALE = 32;
    private static final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private static final int STARTING_HEIGHT = 75, STARTING_WIDTH = 80;
    private static Screen screen = new Screen();
    private static AsciiPanel asciiPanel;
    private List<TileTransformer> transformList;
    private final View view;
    private TileTransformer filter;
    private AsciiCharacterData[][] lastRender = new AsciiCharacterData[][]{};

    public Screen() {
        this.setSize(DIMENSION);
        this.setBounds(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        this.add(asciiPanel = new AsciiPanel(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        view = new View(WIDTH, HEIGHT);
        transformList = new ArrayList<>();
        asciiPanel.setBackground(Color.BLACK);
        asciiPanel.setForeground(Color.WHITE);
        System.out.println(view.toString());
        filter = new TileTransformer() {
            @Override
            public void transformTile(int x, int y, AsciiCharacterData data) {
            }
        };
    }

    public void addAnimation(TileTransformer t) {
        transformList.add(t);
    }

    public void addAnimation(TileTransformer[] t) {
        for (TileTransformer tile : t) {
            this.addAnimation(tile);
        }
    }

    public void transform(int x, int y, AsciiCharacterData data) {
        view.transform(x, y, data);
    }

    public void filter(TileTransformer t) {
        if (t != null) {
            filter = t;
        }
    }

    public void transform(TileTransformer t) {
        view.transform(t);
    }

    public void addDraw(Render r) {
        if (r != null) {
            view.add(r.x, r.y, r.getData());
        }
    }

    public void addDraw(Render[] r) {
        for (Render rnd : r) {
            this.addDraw(rnd);
        }
    }

    public void addDraw(Render[][] r) {
        for (Render[] r1 : r) {
            this.addDraw(r1);
        }
    }

    public void addDraw(AsciiCharacterData[][] d) {
        for (int x = 0; x < d.length; x++) {
            for (int y = 0; y < d[x].length; y++) {
                this.addDraw(new Render(x, y, d[x][y]));
            }
        }
    }

    public void render() {
        AsciiCharacterData[][] draws = view.getDraw();
        for (int x = 0; x < draws.length; x++) {
            for (int y = 0; y < draws[x].length; y++) {
                asciiPanel.write(x, y, draws[x][y]);
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

    public void clearView() {
        view.clear();
        this.clearScreen();
    }

    public void clearScreen() {
        asciiPanel.clear(' ');
    }

    public static Screen getInstance() {
        if (screen == null) {
            Screen screen = new Screen();
        }
        return screen;
    }

    public void setForegroundColor(Color fg) {
        if (fg != null) {
            view.setForegroundColor(fg);
            asciiPanel.setDefaultForegroundColor(fg);
            asciiPanel.clear();
            asciiPanel.withEachTile(new TileTransformer() {
                @Override
                public void transformTile(int x, int y, AsciiCharacterData data) {
                    data.foregroundColor = asciiPanel.getDefaultForegroundColor();
                }
            });
        }
    }

    public void setBackgroundColor(Color bg) {
        if (bg != null) {
            view.setBackgroundColor(bg);
            asciiPanel.setDefaultBackgroundColor(bg);
            asciiPanel.clear();
            asciiPanel.withEachTile(new TileTransformer() {
                @Override
                public void transformTile(int x, int y, AsciiCharacterData data) {
                    data.backgroundColor = asciiPanel.getDefaultBackgroundColor();
                }
            });
        }
    }

    public int getAsciiPanelWidth() {
        return WIDTH;
    }

    public int getAsciiPanelHeight() {
        return HEIGHT;
    }
}
