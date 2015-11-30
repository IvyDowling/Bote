package bote;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Bote extends Canvas implements Runnable {

    private boolean running;
    private static final int HEIGHT = 19, WIDTH = 34, SCALE = 32;
    private static final String NAME = "Bote";

    public int tickCount = 0;

    private JFrame frame;
    private Screen screen;
    private TextArea console;

    private final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

    private Controller control = Controller.getInstance();

    public Bote() {

        frame = new JFrame(NAME);
        screen = Screen.getInstance();
        console = TextArea.getInstance();
        frame.addKeyListener(new Listener());
        frame.setFocusable(true);

        //sets personal bug
        frame.setIconImage(new ImageIcon("res/bug.png").getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(screen, BorderLayout.WEST);
        frame.add(console, null);
        frame.pack();
        frame.setPreferredSize(DIMENSION);
        frame.setResizable(true);
        //A nice visible top left corner point (5,5)
        frame.setLocation(0, 0);
        frame.setVisible(true);
        frame.setSize(DIMENSION);
    }

    public void init() {
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    public static void main(String[] args) {
        new Bote().start();
    }

    public void run() {

        init();

        long lastTime = System.nanoTime();
        final double nsPerTick = 1000000000 / (double) 60;

        int ticks = 0;
        int frames = 0;
        int c = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            screen = Screen.getInstance();

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = false;
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                screen.render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                //EVERY SIXTY TICKS
                render();
                frames = 0;
                ticks = 0;
            }

        }

    }

    public void render(){
        //every 60 ticks
    }
    
    public void tick() {
        //Operated Every tick

        //tick count
        tickCount++;
    }
}