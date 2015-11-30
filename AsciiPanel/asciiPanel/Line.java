package asciiPanel;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Line implements Drawable {

    private Point start, end;
    private AsciiCharacterData[] asciiData;

    public Line(Point p1, Point p2, AsciiCharacterData[] d) {
        if (p1.x < p2.x) { //'start' is the lower x val
            start = p1;
            end = p2;
        } else {
            start = p2;
            end = p1;
        }
        //we really want this to not happen
        if (d.length > 1 && d.length < width()) {
            throw new IllegalArgumentException("ascii data array is too short for the line lenght!");
        }
        asciiData = d;
    }

    public Line(Point p1, Point p2, AsciiCharacterData d) {
        this(p1, p2, new AsciiCharacterData[]{d});
    }

    /*
     we're using Bresenham's line algorithm:
     we need to detect and adjust for octants
     pubilc Point switchFromOctantZeroTo(octant, x, y) 
     ---switch(octant)  
     -------case 0: return (x, y)
     -------case 1: return (y, x)
     -------case 6: return (y, -x)
     -------case 7: return (x, -y)
     Octants are flipped on y because of screens top left (0,0) so we have to
     work pretty hard to get the axis to work.
     ..|6/ this is the adjusted octant range
     ..|/7 so when a line has the (slope > -1) it will appear reflected over y
     --+-- and look like a line with (slope > 1) on a standard plane
     ..|\0
     ..|1\
     */
    private Render[] fillRender(Point p1, Point p2, AsciiCharacterData[] data) {
        List<Render> temp = new LinkedList<>();
        double slope = getSlope(start, end);
        if (slope == 0) {
            //the line is straight, either vertical or horizontal
            if (start.y == end.y) {
                //horiz
                for (int i = start.x; i < end.x; i++) {
                    temp.add(new Render(i, start.y, data[i % data.length]));
                }
            } else {
                //vert
                for (int i = start.y; i < end.y; i++) {
                    temp.add(new Render(start.x, i, data[i % data.length]));
                }
            }
        } else if (slope > 1) {
            int dx = end.x - start.x;
            int dy = end.y - start.y;
            // using '2 *()' to eliminate a 1/2 in original formula
            int dif = 2 * dy - dx;
            temp.add(new Render(start.x, start.y, data[0]));
            int y = start.y;
            int x = start.x;
            for (int i = y; i < end.y + 1; i++) {
                temp.add(new Render(x, i, data[i % data.length]));
                dif = dif + (2 * dx);
                if (dif > 0) {
                    x = x + 1;
                    dif = dif - (2 * dy);
                }
            }
        } else if (slope > 0) {
            int dx = end.x - start.x;
            int dy = end.y - start.y;
            // using '2 *()' to eliminate a 1/2 in original formula
            int dif = 2 * dy - dx;
            temp.add(new Render(start.x, start.y, data[0]));
            int y = start.y;
            int x = start.x;
            for (int i = x; i < end.x + 1; i++) {
                temp.add(new Render(i, y, data[i % data.length]));
                dif = dif + (2 * dy);
                if (dif > 0) {
                    y = y + 1;
                    dif = dif - (2 * dx);
                }
            }
        } else if (slope < -1) {
            int dx = end.x - start.x;
            int dy = start.y - end.y;
            // using '2 *()' to eliminate a 1/2 in original formula
            int dif = 2 * dy - dx;
            temp.add(new Render(start.x, start.y, data[0]));
            int y = start.y;
            int x = start.x;
            for (int i = y; i > end.y + 1; i--) {
                temp.add(new Render(x, i, data[i % data.length]));
                dif = dif + (2 * dx);
                if (dif > 0) {
                    x = x + 1;
                    dif = dif - (2 * dy);
                }
            }
        } else if (slope < 0) {
            int dx = end.x - start.x;
            int dy = start.y - end.y;
            // using '2 *()' to eliminate a 1/2 in original formula
            int dif = 2 * dy - dx;
            temp.add(new Render(start.x, start.y, data[0]));
            int y = start.y;
            int x = start.x;
            for (int i = x; i < end.x + 1; i++) {
                temp.add(new Render(i, y, data[i % data.length]));
                dif = dif + (2 * dy);
                if (dif > 0) {
                    y = y - 1;
                    dif = dif - (2 * dx);
                }
            }
        }
        return (Render[]) temp.toArray(new Render[temp.size()]);
    }

    public double getSlope(Point p1, Point p2) {
        if (p1.x == p2.x || p1.y == p2.y) {
            return 0;
        }
        return ((double) p2.y - p1.y) / ((double) p2.x - p1.x);
    }

    //Drawable impl
    @Override
    public Render[] transform(int x, int y, AsciiCharacterData d) {
        start.x = start.x + x;
        start.y = start.y + y;
        end.x = end.x + x;
        end.y = end.y + y;
        for (AsciiCharacterData data : asciiData) {
            data = d;
        }
        return getRender();
    }

    @Override
    public Render[] getRender() {
        return fillRender(start, end, asciiData);
    }
    //drawable

    public final int width() {
        //we're always going to be using
        //the side of the line with no overlaps as width,
        //here it is the x val bc we use x to increment in the bresen alg
        if (start.x == end.x) {
            return 1;
        }
        return Math.abs(end.x - start.x) + 1;
    }

    public final int height() {
        if (start.y == end.y) {
            return 1;
        }
        return Math.abs(end.y - start.y) + 1;
    }

    public int getStartX() {
        return start.x;
    }

    public int getStartY() {
        return start.y;
    }

    public int getEndX() {
        return end.x;
    }

    public int getEndY() {
        return end.y;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public AsciiCharacterData[] getAsciiData() {
        return asciiData;
    }

    @Override
    public String toString() {
        String temp = "";
        for (AsciiCharacterData r : asciiData) {
            if (r != null) {
                temp += " " + r.character + "\n";
            }
        }
        return start.toString() + " to " + end.toString() + " with data '" + temp + "'";
    }

}
