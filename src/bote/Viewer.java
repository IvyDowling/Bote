package bote;

import asciiPanel.Render;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Timer;

public class Viewer {

    private final List<Render[]> vid;
    private int index = 0;
    private boolean isPlaying, isFinished;
    private int delay = 1000; //milliseconds

    public Viewer(int milliDelay) {
        vid = new LinkedList<>();
        isPlaying = isFinished = false;
        delay = milliDelay;
    }

    public Viewer() {
        vid = new LinkedList<>();
        isPlaying = false;
    }

    public Viewer(List<Render[]> v) {
        vid = v;
        isPlaying = false;
    }

    public Render[] getCurrentRender() {
        if (index == vid.size()) {
            isPlaying = false;
        }
        if (isPlaying) {
            return vid.get(index);
        }
        return new Render[]{};
    }

    public Viewer addRenderArray(Render[] r) {
        vid.add(r);
        return this;
    }

    public Viewer addRenderArray(List<Render[]> r) {
        vid.addAll(r);
        return this;
    }

    public void play() {
        isPlaying = true;
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                nextFrame();
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    public void stop() {
        isPlaying = false;
        isFinished = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
    
    public boolean isFinished(){
        return isFinished;
    }

    public void nextFrame() {
        if (index < vid.size()) {
            index++;
        } else {
            this.stop();
        }
    }
}
