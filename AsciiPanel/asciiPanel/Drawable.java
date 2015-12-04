package asciiPanel;

public interface Drawable {

    public AsciiCharacterData getData();

    public void transform(int x, int y, AsciiCharacterData d);

    public int getX();

    public int getY();
}
