package bote.game;

public enum Biome {

    OPEN, DEEP, SHALLOW, CLOUDY, WEEDY;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
