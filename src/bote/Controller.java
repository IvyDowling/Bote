package bote;

import asciiPanel.Drawable;
import asciiPanel.TileTransformer;
import bote.game.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller {

    private static final Controller controller = new Controller();
    private static Screen screen;
    private static TextArea console;
    private Player player;
    private Page page;

    public Controller() {
        screen = Screen.getInstance();
        console = TextArea.getInstance();
        setPage(new IntroPage());
    }

    public final void setPage(Page p) {
        page = p;
        screen.clearScreen();
        screen.clearRenders();
        screen.setBackgroundColor(page.getBackgroundColor());
        screen.setForegroundColor(page.getForegroundColor());
        this.addDraw(page.getDefaultDraw());
    }

    public void console(String out) {
        console.write(out);
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
        console.write(keyCode + " pressed");
        execute(page.pageAction(keyCode));
//        switch (keyCode) {
//            case 65://a
//            case 37://left
//                break;
//            case 87://w
//            case 38://up
//                break;
//            case 68://d
//            case 39://right
//                break;
//            case 83://s
//            case 40://down
//                break;
//        }
    }
    
    public Player getPlayer(){
        return player;
    }

    public void newPlayer(Player p) {
        player = p;
        this.save();
    }

    public Player loadGame() {
        Player load = null;
        try {
            FileInputStream fIn = new FileInputStream("sv.data");
            ObjectInputStream objIn = new ObjectInputStream(fIn);
            load = (Player) objIn.readObject();
        } catch (Exception e) {
            System.out.println("I didn't load it");
        }
        System.out.println(load.toString());
        return load;
    }

    public void save() {
        try {
            File f = new File("sv.data");
            f.delete();
        } catch (Exception io) {
            System.out.println("couldn't delete the file");
        }
        try {
            FileOutputStream fOut = new FileOutputStream("sv.data");
            ObjectOutputStream objOut = new ObjectOutputStream(fOut);
            objOut.writeObject(player);
            fOut.close();
            objOut.close();
        } catch (Exception e) {
            System.out.println("I didn't save it");
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
