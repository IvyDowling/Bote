package bote.game;

import java.io.Serializable;

public class DayClock implements Serializable {

    //24hour clock
    private int hour;
    private int min;
    private final int SUNRISE = 7;
    private final int SUNSET = 20;

    public DayClock() {
        //noon -- why not
        hour = 12;
        min = 0;
    }

    public boolean isSunrise() {
        return hour == SUNRISE && min == 0;
    }

    public boolean isSunset() {
        return hour == SUNSET && min == 0;
    }

    public boolean isDay() {
        return hour > 7 && hour < 20;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public void setHour(int h) {
        if (h < 24) {
            hour = h;
        }
    }

    public void setMin(int m) {
        if (min < 60) {
            min = m;
        }
    }

    public DayClock tick() {
        if (min >= 59) {
            this.hourTick();
            min = 0;
        } else {
            min++;
        }
        return this;
    }

    public void hourTick() {
        if (hour >= 23) {
            hour = 0;
        } else {
            hour++;
        }
    }
}
