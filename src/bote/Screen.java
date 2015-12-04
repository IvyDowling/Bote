package bote;

import asciiPanel.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private static final int HEIGHT = 41, WIDTH = 121, SCALE = 32;
    private static final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private static final int STARTING_HEIGHT = 75, STARTING_WIDTH = 80;
    private static Screen screen = new Screen();
    private static AsciiPanel asciiPanel;
    private List<TileTransformer> transformList;
    private Drawable[][] draws;

    public Screen() {
        this.setSize(DIMENSION);
        this.setBounds(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        this.add(asciiPanel = new AsciiPanel(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        draws = new Drawable[WIDTH][HEIGHT];
        transformList = new ArrayList<>();
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
        draws[d.getY()][d.getX()] = d;
    }

    public void addDraw(Drawable[] d) {
        for (Drawable draw : d) {
            this.addDraw(draw);
        }
    }

    public void addDraw(Drawable[][] d) {
        draws = d;
    }

    public void render() {
        for (int row = 0; row < draws.length; row++) {
            for (int col = 0; col < draws[row].length; col++) {
                if (draws[row][col] != null) {
                    asciiPanel.write(new Render(col, row, draws[row][col].getData()));
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

    public void clearDraws() {
        draws = new Drawable[HEIGHT][WIDTH];
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
        asciiPanel.setDefaultForegroundColor(fg);
        asciiPanel.clear();
        asciiPanel.withEachTile(new TileTransformer() {
            @Override
            public void transformTile(int x, int y, AsciiCharacterData data) {
                data.foregroundColor = asciiPanel.getDefaultForegroundColor();
            }
        });
    }

    public void setBackgroundColor(Color bg) {
        asciiPanel.setDefaultBackgroundColor(bg);
        asciiPanel.clear();
        asciiPanel.withEachTile(new TileTransformer() {
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
