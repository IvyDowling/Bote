package asciiPanel;

public interface Drawable {

    public Render getRender();

    public Render transform(int x, int y, AsciiCharacterData d);

    public int getX();

    public int getY();
}
