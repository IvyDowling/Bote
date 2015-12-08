package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Timer;

public class Viewer {

    private final List<AsciiCharacterData[][]> vid;
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

    public Viewer(List<AsciiCharacterData[][]> v) {
        vid = v;
        isPlaying = false;
    }

    public AsciiCharacterData[][] getCurrentRender() {
        if (index == vid.size()) {
            isPlaying = false;
        }
        if (isPlaying) {
            return vid.get(index);
        }
        return new AsciiCharacterData[][]{};
    }

    public Viewer addRenderArray(AsciiCharacterData[][] r) {
        vid.add(r);
        return this;
    }

    public Viewer addRenderArray(List<AsciiCharacterData[][]> r) {
        vid.addAll(r);
        return this;
    }

    public Viewer play() {
        isPlaying = true;
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                nextFrame();
            }
        };
        new Timer(delay, taskPerformer).start();
        return this;
    }

    public void stop() {
        isPlaying = false;
        isFinished = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isFinished() {
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
