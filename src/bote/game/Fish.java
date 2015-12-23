package bote.game;

public class Fish {

    private String name;
    private Biome biome;
    private int weight;

    public Fish(String n, Biome b, int w) {
        name = n;
        biome = b;
        weight = w;
    }

    public String getName() {
        return name;
    }

    public Biome getBiome() {
        return biome;
    }

    @Override
    public String toString() {
        return name + " " + biome + " " + weight;
    }
}
