package asciiPanel;

import java.awt.Color;

public class View {

    private AsciiCharacterData[][] view;
    private int width, height;
    private Color fgColor, bgColor;

    public View(int wdth, int hght, Color fg, Color bg) {
        this.width = wdth;
        this.height = hght;
        view = new AsciiCharacterData[width][height];
        fgColor = fg;
        bgColor = bg;
        this.clear();
    }

    public View(int wdth, int hght) {
        this.width = wdth;
        this.height = hght;
        view = new AsciiCharacterData[width][height];
        this.clear();
        fgColor = Color.BLACK;
        bgColor = Color.BLACK;
    }

    public View(AsciiCharacterData[][] d) {
        this(d.length, d[0].length);
        this.add(d);
    }

    public final void add(int x, int y, AsciiCharacterData d) {
        if (d != null) {
            AsciiCharacterData temp = d;
            if (d.backgroundColor == null) {
                temp = new AsciiCharacterData(
                        temp.character,
                        temp.foregroundColor,
                        bgColor
                );
            }
            if (d.foregroundColor == null) {
                temp = new AsciiCharacterData(
                        temp.character,
                        fgColor,
                        temp.backgroundColor
                );
            }
            view[x][y] = temp;
        } else {
            System.out.println("'");
            clear(x, y);
        }
    }

    public final void add(int y, AsciiCharacterData[] ln) {
        for (int x = 0; x < width; x++) {
            this.add(x, y, ln[x]);
        }
    }

    public final void add(AsciiCharacterData[][] d) {
        for (int y = 0; y < height; y++) {
            this.add(y, d[y]);
        }
    }

    public void clear(int x, int y) {
        view[x][y] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
    }

    public final void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                view[x][y] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
            }
        }
    }

    private void transformUp() {
        for (int y = height - 1; y > 0; y--) {
            for (int x = 0; x < width; x++) {
                if (y - 1 < 0) {
                    view[x][0] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
                } else {
                    view[x][y] = view[x][y - 1];
                }
            }
        }
    }

    private void transformDown() {
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                view[x][y] = view[x][y + 1];
            }
        }
        for (int x = 0; x < width; x++) {
            view[x][height - 1] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
        }
    }

    private void transformRight() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width - 1; x++) {
                if (x + 1 > width) {
                    view[0][y] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
                } else {
                    view[x][y] = view[x + 1][y];
                }
            }
        }
    }

    private void transformLeft() {
        for (int y = 0; y < height; y++) {
            for (int x = width - 1; x > 0; x--) {
                if (x - 1 < 0) {
                    view[0][y] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
                } else {
                    view[x][y] = view[x - 1][y];
                }
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     * @param data: if character is ' ' then it is ignored, also if a color is
     * null it is ignored
     */
    public void transform(int x, int y, AsciiCharacterData data) {
        if (y > 0) {
            for (int i = 0; i < y; i++) {
                this.transformUp();
            }
        }
        if (y < 0) {
            for (int i = y; i < 0; i++) {
                this.transformDown();
            }
        }
        if (x > 0) {
            for (int i = 0; i < x; i++) {
                this.transformRight();
            }
        }
        if (x < 0) {
            for (int i = x; i < 0; i++) {
                this.transformLeft();
            }
        }
        if (data != null) {
            if (data.character != ' ' && data.backgroundColor != null && data.foregroundColor != null) {
                for (int row = 0; row < height - 1; row++) {
                    for (int col = 0; col < width; col++) {
                        view[row][col] = data;
                    }
                }
            } else if (data.character != ' ' && data.foregroundColor != null) {
                for (int row = 0; row < height - 1; row++) {
                    for (int col = 0; col < width; col++) {
                        view[row][col] = new AsciiCharacterData(
                                view[row][col].character,
                                view[row][col].foregroundColor,
                                data.backgroundColor
                        );
                    }
                }
            } else if (data.character != ' ' && data.backgroundColor != null) {
                for (int row = 0; row < height - 1; row++) {
                    for (int col = 0; col < width; col++) {
                        view[row][col] = new AsciiCharacterData(
                                view[row][col].character,
                                data.foregroundColor,
                                view[row][col].backgroundColor);
                    }
                }
            } else if (data.backgroundColor != null && data.foregroundColor != null) {
                for (int row = 0; row < height - 1; row++) {
                    for (int col = 0; col < width; col++) {
                        view[row][col] = new AsciiCharacterData(
                                view[row][col].character,
                                data.foregroundColor,
                                data.backgroundColor);
                    }
                }
            } else if (data.character != ' ') {
                for (int row = 0; row < height - 1; row++) {
                    for (int col = 0; col < width; col++) {
                        view[row][col] = new AsciiCharacterData(
                                data.character,
                                view[row][col].foregroundColor,
                                view[row][col].backgroundColor);
                    }
                }
            } else if (data.foregroundColor != null) {
                for (int row = 0; row < height - 1; row++) {
                    for (int col = 0; col < width; col++) {
                        view[row][col] = new AsciiCharacterData(
                                view[row][col].character,
                                view[row][col].foregroundColor,
                                data.backgroundColor);
                    }
                }
            } else if (data.backgroundColor != null) {
                for (int row = 0; row < height - 1; row++) {
                    for (int col = 0; col < width; col++) {
                        view[row][col] = new AsciiCharacterData(
                                view[row][col].character,
                                data.foregroundColor,
                                view[row][col].backgroundColor);
                    }
                }
            }
        }
    }

    public AsciiCharacterData[][] getDraw() {
        return view;
    }

    public Render[][] getRender() {
        Render[][] temp = new Render[width][height];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                temp[col][row] = new Render(col, row, view[col][row]);
            }
        }
        return temp;
    }

    public int getHeight() {
        return view.length;
    }

    public int getWidth() {
        return view[0].length;
    }

    public void setBackgroundColor(Color bg) {
        if (bg != null) {
            bgColor = bg;
        }
    }

    public void setForegroundColor(Color fg) {
        if (fg != null) {
            fgColor = fg;
        }
    }

    @Override
    public String toString() {
        return "view of w: " + getWidth() + ", h: " + getHeight();
    }
}
