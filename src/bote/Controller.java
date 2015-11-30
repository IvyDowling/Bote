package bote;

import asciiPanel.Drawable;
import asciiPanel.TileTransformer;

public class Controller {

    private static Controller controller = new Controller();
    private static Screen screen;

    public Controller() {
        screen = Screen.getInstance();
    }

    public void render() {
        screen.render();
    }

    public void addAnimation(TileTransformer t) {
        screen.addAnimation(t);
    }

    public void addAnimation(TileTransformer[] t) {
        screen.addAnimation(t);
    }

    public void addDraw(Drawable[] r) {
        screen.addDraw(r);
    }

    public void addDraw(Drawable r) {
        screen.addDraw(r);
    }

    private void execute(Command c) {
        if (c != null) {
            c.exe(getInstance());
        }
    }

    public void clearRenders() {
        screen.clearRenders();
    }

    public void takeInput(int keyCode) {
        switch (keyCode) {
            case 65://a
            case 37://left
                break;
            case 87://w
            case 38://up
                break;
            case 68://d
            case 39://right
                break;
            case 83://s
            case 40://down
                break;
        }
    }

    public int getScreenWidth() {
        return screen.getAsciiPanelWidth();
    }

    public int getScreenHeight() {
        return screen.getAsciiPanelHeight();
    }

    public static Controller getInstance() {
        if (controller == null) {
            Controller controller = new Controller();
        }
        return controller;
    }
}
