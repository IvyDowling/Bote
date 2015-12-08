package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageLib {

    private static int WIDTH = 121;
    private static int HEIGHT = 41;

    //using this class to organize the unicode cp437 characters we get
    public static final char H_BAR = '\u00C4';
    public static final char V_BAR = '\u00B3';
    public static final char DOUBLE_V_BAR = '\u00BA';
    public static final char DOUBLE_H_BAR = '\u00CD';
    public static final char B_L_CORNER = '\u00C0';
    public static final char T_L_CORNER = '\u00DA';
    public static final char B_R_CORNER = '\u00D9';
    public static final char T_R_CORNER = '\u00BF';
    public static final char B_L_DOUBLE_CORNER = '\u00C8';
    public static final char T_L_DOUBLE_CORNER = '\u00C9';
    public static final char B_R_DOUBLE_CORNER = '\u00BC';
    public static final char T_R_DOUBLE_CORNER = '\u00BB';
    public static final char CROSS = '\u00C5';
    public static final char DOUBLE_CROSS = '\u00CE';
    //non block symbols
    public static final char LIGHT_SHADE = '\u00B0';
    public static final char MID_SHADE = '\u00B1';
    public static final char DARK_SHADE = '\u00B2';
    public static final char SUN = '\u000F';
    public static final char CIRCLE = '\u0009';
    public static final char CENT = '\u009B';
    public static final char ALPHA = '\u00E0';
    public static final char BETA = '\u00E1';
    public static final char CAP_SIGMA = '\u00E4';
    public static final char SIGMA = '\u00E5';
    public static final char PHI = '\u00E8';
    public static final char DOUBLE_S = '\u0015';
    public static final char HOUSE = '\u007F';
    public static final char DOT = '\u00F9';
    public static final char HOLLOW_DOT = '\u00F8';
    public static final char TRIPLE_BAR = '\u00F0';

    //Strings
    private static final String[] boteAscii = {
        "          _____                   _______           _____                    _____          ",
        "         /\\    \\                 /::\\    \\         /\\    \\                  /\\    \\         ",
        "        /::\\    \\               /::::\\    \\       /::\\    \\                /::\\    \\        ",
        "       /::::\\    \\             /::::::\\    \\      \\:::\\    \\              /::::\\    \\       ",
        "      /::::::\\    \\           /::::::::\\    \\      \\:::\\    \\            /::::::\\    \\      ",
        "     /:::/\\:::\\    \\         /:::/~~\\:::\\    \\      \\:::\\    \\          /:::/\\:::\\    \\     ",
        "    /:::/__\\:::\\    \\       /:::/    \\:::\\    \\      \\:::\\    \\        /:::/__\\:::\\    \\    ",
        "   /::::\\   \\:::\\    \\     /:::/    / \\:::\\    \\     /::::\\    \\      /::::\\   \\:::\\    \\   ",
        "  /::::::\\   \\:::\\    \\   /:::/____/   \\:::\\____\\   /::::::\\    \\    /::::::\\   \\:::\\    \\  ",
        " /:::/\\:::\\   \\:::\\ ___\\ |:::|    |     |:::|    | /:::/\\:::\\    \\  /:::/\\:::\\   \\:::\\    \\ ",
        "/:::/__\\:::\\   \\:::|    ||:::|____|     |:::|    |/:::/  \\:::\\____\\/:::/__\\:::\\   \\:::\\____\\",
        "\\:::\\   \\:::\\  /:::|____| \\:::\\    \\   /:::/    //:::/    \\::/    /\\:::\\   \\:::\\   \\::/    /",
        " \\:::\\   \\:::\\/:::/    /   \\:::\\    \\ /:::/    //:::/    / \\/____/  \\:::\\   \\:::\\   \\/____/ ",
        "  \\:::\\   \\::::::/    /     \\:::\\    /:::/    //:::/    /            \\:::\\   \\:::\\    \\     ",
        "   \\:::\\   \\::::/    /       \\:::\\__/:::/    //:::/    /              \\:::\\   \\:::\\____\\    ",
        "    \\:::\\  /:::/    /         \\::::::::/    / \\::/    /                \\:::\\   \\::/    /    ",
        "     \\:::\\/:::/    /           \\::::::/    /   \\/____/                  \\:::\\   \\/____/     ",
        "      \\::::::/    /             \\::::/    /                              \\:::\\    \\         ",
        "       \\::::/    /               \\::/____/                                \\:::\\____\\        ",
        "        \\::/____/                 ~~                                       \\::/    /        ",
        "         ~~                                                                 \\/____/         "};

    private static final String[] boat = {
        "         " + DOUBLE_V_BAR + "    ",
        "         " + DOUBLE_V_BAR + "    ",
        "         " + DOUBLE_V_BAR + "    ",
        "         " + DOUBLE_V_BAR + "    ",
        "         " + DOUBLE_V_BAR + "    ",
        "         " + DOUBLE_V_BAR + "     " + T_L_CORNER + H_BAR + H_BAR + H_BAR + T_R_CORNER,
        "" + T_L_CORNER + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + H_BAR + B_R_CORNER + "   " + V_BAR,
        "" + B_L_CORNER + T_R_CORNER + "                 " + V_BAR
    };

    public static final Render[][] getBoteLogo(int x, int y, Color fg, Color bg) {
        return makeRenderArray(x, y, fg, bg, boteAscii);
    }

//    public static final Render[][] getBoat(int x, int y, Color bg) {
//        List<Render> temp = new LinkedList<>();
//        temp.addAll(Arrays.asList(makeRenderArray(x, y, new Color(100, 70, 70), bg, boat)));
//        for (int i = 0; i < 26; i++) {
//            temp.add(new Render(x + i - 2, y + boat.length, new AsciiCharacterData('~', Color.BLUE, new Color(0, 100, 255))));
//        }
//        temp.add(new Render(x + 10, y, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 10, y + 1, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 11, y + 1, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 10, y + 2, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 11, y + 2, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 12, y + 2, new AsciiCharacterData(MID_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 10, y + 3, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 11, y + 3, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 12, y + 3, new AsciiCharacterData(MID_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 10, y + 4, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 11, y + 4, new AsciiCharacterData(LIGHT_SHADE, Color.GRAY, Color.WHITE)));
//        temp.add(new Render(x + 12, y + 4, new AsciiCharacterData(MID_SHADE, Color.GRAY, Color.WHITE)));
//
//        return temp.toArray(new Render[temp.size()]);
//    }
    public static final Render[][] getGrad(int width, int height) {
        Render[][] temp = new Render[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < temp[y].length; x++) {
                if (x > (2 * width / 3)) {
                    temp[x][y] = new Render(x, y, new AsciiCharacterData(DARK_SHADE, Color.WHITE, Color.BLUE));
                } else if (x > (width / 3)) {
                    temp[x][y] = new Render(x, y, new AsciiCharacterData(MID_SHADE, Color.BLUE, Color.CYAN));
                } else {
                    temp[x][y] = new Render(x, y, new AsciiCharacterData(LIGHT_SHADE, Color.WHITE, Color.BLUE));
                }
            }
        }
        return temp;
    }

    public static final Render[][] getGrad() {
        return getGrad(WIDTH, HEIGHT);
    }

    public static final Render[][] getEmpty(Color color) {
        Render[][] temp = new Render[HEIGHT][WIDTH];
        for (int r = 0; r < HEIGHT - 1; r++) {
            for (int c = 0; c < temp[r].length - 1; c++) {
                temp[r][c] = new Render(c, r, new AsciiCharacterData(' ', color, color));
            }
        }
        return temp;
    }

    public static final Render[][] getIntro() {
        Render[][] temp = new Render[HEIGHT][WIDTH];
        for (int r = 0; r < boteAscii.length; r++) {
            temp[r] = makeRenderArray(0, r, Color.WHITE, Color.CYAN, boteAscii[r]);
        }
        return temp;
    }

    private static Render[] makeRenderArray(int x, int y, Color fg, Color bg, String img) {
        Render[] temp = new Render[img.length()];
        for (int i = 0; i < img.length(); i++) {
            temp[i] = new Render(img.charAt(i), x + i, y, fg, bg);
        }
        return temp;
    }

    private static Render[][] makeRenderArray(int x, int y, Color fg, Color bg, String[] img) {
        List<Render[]> temp = new ArrayList<>();
        for (int row = 0; row < img.length; row++) {
            for (int col = 0; col < img[row].length(); col++) {
                temp.add(makeRenderArray(x, y, fg, bg, img[row]));
            }
        }
        return temp.toArray(new Render[temp.size()][]);
    }
}
