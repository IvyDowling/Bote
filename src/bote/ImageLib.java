package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ImageLib {

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
    public static final char LIGHT_SHADE = '\u00B0';
    public static final char MID_SHADE = '\u00B1';
    public static final char DARK_SHADE = '\u00B2';
    public static final char SUN = '\u000F';
    public static final char CIRCLE = '\u0009';

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
    /*
     |
     |
     |
     |
    
    
     */

    public static final Render[] getBoteLogo(int x, int y, Color fg, Color bg) {
        return makeRenderArray(x, y, fg, bg, boteAscii);
    }

    public static final Render[] getBoat(int x, int y, Color bg) {
        List<Render> temp = new LinkedList<>();
        temp.addAll(Arrays.asList(makeRenderArray(x, y, new Color(100, 70, 70), bg, boat)));
        for (int i = 0; i < 26; i++) {
            temp.add(new Render(x + i - 2, y + boat.length, new AsciiCharacterData('~', Color.BLUE, new Color(0, 100, 255))));
        }
        return temp.toArray(new Render[temp.size()]);
    }

    private static Render[] makeRenderArray(int x, int y, Color fg, Color bg, String[] img) {
        List<Render> temp = new LinkedList<>();
        for (int row = 0; row < img.length; row++) {
            for (int col = 0; col < img[row].length(); col++) {
                temp.add(new Render(img[row].charAt(col), x + col, y + row, fg, bg));
            }
        }
        return temp.toArray(new Render[temp.size()]);
    }
}
