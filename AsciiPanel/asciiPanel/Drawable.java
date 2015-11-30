package asciiPanel;

public interface Drawable {

    public Render[] getRender();

    public void transform(int x, int y, AsciiCharacterData d);
}
