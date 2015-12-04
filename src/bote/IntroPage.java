package bote;

import asciiPanel.Drawable;
import asciiPanel.Render;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntroPage extends Page {

    private final String[] introWords = new String[]{
        "(c)ontinue",
        "(n)ew"
    };

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
        List<Render> temp = new ArrayList<>();
        temp.addAll(Arrays.asList(ImageLib.getBoteLogo(0, 0, getForegroundColor(), getBackgroundColor())));
        temp.addAll(Arrays.asList(ImageLib.getBoat(90, 23, getBackgroundColor())));
        temp.addAll(Arrays.asList(makeRenderArray(30, 25, Color.ORANGE, getBackgroundColor(), introWords)));
        return temp.toArray(new Render[temp.size()]);
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 67://c
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        //load game data
                        c.newPlayer(c.loadGame());
                        c.setPage(new MainPage(c.getPlayer().getX(), c.getPlayer().getY(),
                            c.getScreenWidth(), c.getScreenHeight()));
                    }
                };
            case 78://n
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        //new game
                        c.setPage(new NewPlayerPage());
                    }
                };
        }
        return new Command() {
            @Override
            public void exe(Controller c) {
            }
        };
    }

    @Override
    public void playViewer() {
    }

    private static Render[] makeRenderArray(int x, int y, Color fg, Color bg, String[] img) {
        List<Render> temp = new ArrayList<>();
        for (int row = 0; row < img.length; row++) {
            for (int col = 0; col < img[row].length(); col++) {
                temp.add(new Render(img[row].charAt(col), x + col, y + row, fg, bg));
            }
        }
        return temp.toArray(new Render[temp.size()]);
    }

}
