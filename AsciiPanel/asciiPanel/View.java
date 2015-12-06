package asciiPanel;

import java.awt.Color;

public class View {

    private AsciiCharacterData[][] view;
    private int width, height;

    public View(int width, int height) {
        this.width = width;
        this.height = height;
        view = new AsciiCharacterData[width][height];
        this.clear();
    }

    public View(AsciiCharacterData[][] d) {
        this(d.length, d[0].length);
        this.add(d);
    }

    public final void add(int x, int y, AsciiCharacterData d) {
        AsciiCharacterData temp = d;
        if (temp != null) {
            if (d.backgroundColor == null) {
                temp = new AsciiCharacterData(
                        temp.character,
                        temp.foregroundColor,
                        Color.BLACK
                );
            }
            if (d.foregroundColor == null) {
                temp = new AsciiCharacterData(
                        temp.character,
                        Color.BLACK,
                        temp.backgroundColor
                );
            }
            view[x][y] = temp;
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

    public final void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width - 1; col++) {
                view[col][row] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
            }
        }
    }

    private void transformDown() {
        for (int row = 0; row < height - 1; row++) {
            for (int col = 0; col < width; col++) {
                view[row][col] = view[row + 1][col];
            }
        }
        for (int col = 0; col < width; col++) {
            view[view.length][col] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
        }
    }

    private void transformUp() {
        for (int row = 1; row < height; row++) {
            for (int col = 0; col < width; col++) {
                view[row][col] = view[row - 1][col];
            }
        }
        for (int col = 0; col < width; col++) {
            view[0][col] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
        }
    }

    private void transformRight() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col + 1 > width) {
                    view[row][0] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
                } else {
                    view[row][col] = view[row][col + 1];
                }
            }
        }
    }

    private void transformLeft() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col - 1 < 0) {
                    view[row][width - 1] = new AsciiCharacterData(' ', Color.BLACK, Color.BLACK);
                } else {
                    view[row][col] = view[row][col - 1];
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
            for (int i = 0; i < y; i++) {
                this.transformDown();
            }
        }
        if (x > 0) {
            for (int i = 0; i < x; i++) {
                this.transformRight();
            }
        }
        if (x < 0) {
            for (int i = 0; i < x; i++) {
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
                System.out.print(view[col][row].character);
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
}
