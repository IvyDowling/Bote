package asciiPanel;

public interface Drawable {

    public Render[] getRender();

    public Render[] transform(int x, int y, AsciiCharacterData d);
}
