package bote;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

    private static Controller controller;
    private boolean debugOutput = false;

    public Listener() {
        controller = Controller.getInstance();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        controller.takeInput(e.getKeyCode());
        if (debugOutput) {
            char keyChar = e.getKeyChar();
            System.out.print(keyChar);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void debug() {
        debugOutput = true;
    }

}
