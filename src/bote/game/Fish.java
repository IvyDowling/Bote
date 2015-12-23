package bote.game;

public class Fish {

    private String name;
    private Biome biome;

    public Fish(String n, Biome b) {
        name = n;
        biome = b;
    }

    public String getName() {
        return name;
    }

    public Biome getBiome() {
        return biome;
    }
}
