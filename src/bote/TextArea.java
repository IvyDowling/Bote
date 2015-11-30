package cathedral;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;

public class TextArea extends JTextArea {

    private static final int HEIGHT = 240, WIDTH = 360, SCALE = 2, ROWS = 22, COLUMNS = 30;
    private final Dimension TEXT_AREA = new Dimension(250, (HEIGHT * SCALE));
    private static TextArea textArea = new TextArea();
    Font font = new Font("Monospaced", Font.PLAIN, 12);

    public TextArea() {
        this.setRows(ROWS);
        this.setColumns(COLUMNS);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setEditable(false);
        this.setFont(font);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHighlighter(null);
    }

    public static TextArea getInstance() {
        if (textArea == null) {
            textArea = new TextArea();
        }
        return textArea;
    }

    public void write(String s) {
        this.insert("-" + s + "\n", 0);
    }

}
