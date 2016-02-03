package bote.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Aquarium implements Serializable {

    private String[] normalTypes;
    private String[] rareTypes;
    private ConcurrentHashMap<Biome, List<Fish>> found;
    private Random dice;

    public Aquarium(long seed) {
        this();
        dice.setSeed(seed);
    }

    public Aquarium() {
        normalTypes = new String[]{
            "tuna", "trout", "bass", "eel", "octopus",
            "squid", "shark", "carp", "ray", "dolphin",
            "flounder"
        };
        rareTypes = new String[]{
            "giant squid", "whale"
        };
        dice = new Random();
        //setting up the hashtable with all the biomes ready
        found = new ConcurrentHashMap<Biome, List<Fish>>();
        found.put(Biome.OPEN, new ArrayList<Fish>());
        found.put(Biome.DEEP, new ArrayList<Fish>());
        found.put(Biome.SHALLOW, new ArrayList<Fish>());
        found.put(Biome.WEEDY, new ArrayList<Fish>());
        found.put(Biome.CLOUDY, new ArrayList<Fish>());
    }

    public Fish[] getFishInBiome(Biome b) {
        return found.get(b).toArray(new Fish[found.get(b).size()]);
    }

    public Fish goFishing(Biome b) {
        if (found.get(b).isEmpty()) {
            switch (b) {
                case DEEP:
                    return deep();
                case SHALLOW:
                    return shallow();
                case WEEDY:
                    return weedy();
                case CLOUDY:
                    return cloudy();
                case OPEN:
                    return open();
            }
        } else {
            if (dice.nextFloat() > 0.7) { // 30% catch chance
                if (dice.nextFloat() > 0.5 && found.get(b).size() < 12) { // new or old? we dont need more than 12 in an area
                    switch (b) {
                        case DEEP:
                            return deep();
                        case SHALLOW:
                            return shallow();
                        case WEEDY:
                            return weedy();
                        case CLOUDY:
                            return cloudy();
                        case OPEN:
                            return open();
                    }
                } else {
                    return found.get(b).get(dice.nextInt(found.get(b).size()));
                }
            }
        }
        return null;
    }

    private Fish open() {
        float score = dice.nextFloat();
        Fish temp = new Fish(randomName(), Biome.OPEN, dice.nextInt(150));
        found.get(Biome.OPEN).add(temp);
        return temp;
    }

    private Fish deep() {
        float score = dice.nextFloat();
        Fish temp;
        if (score > 0.9999999999) {
            temp = new Fish(randomPrefix() + " " + rareTypes[dice.nextInt(rareTypes.length)], Biome.DEEP, dice.nextInt(400));
        } else {
            temp = new Fish(randomName(), Biome.DEEP, dice.nextInt(400));
        }
        found.get(Biome.DEEP).add(temp);
        return temp;
    }

    private Fish shallow() {
        float score = dice.nextFloat();
        Fish temp = new Fish(randomName(), Biome.SHALLOW, dice.nextInt(20));
        found.get(Biome.SHALLOW).add(temp);
        return temp;
    }

    private Fish weedy() {
        float score = dice.nextFloat();
        Fish temp = new Fish(randomName('m'), Biome.WEEDY, dice.nextInt(150));
        found.get(Biome.WEEDY).add(temp);
        return temp;
    }

    private Fish cloudy() {
        float score = dice.nextFloat();
        Fish temp = new Fish(randomName(), Biome.CLOUDY, dice.nextInt(200));
        found.get(Biome.CLOUDY).add(temp);
        return temp;
    }

    private String randomName(char startsWith) {
        String temp = "" + startsWith;
        String vowl = "aeiou";
        String cnsn = "qwrtypsdfghjklmnbvcxz";
        int len = dice.nextInt(6) + 3; // 3 min
        boolean nextVowl = !vowl.contains(startsWith + "");
        for (int i = 0; i < len; i++) {
            if (nextVowl) {
                temp += vowl.charAt(dice.nextInt(vowl.length()));
            } else {
                temp += cnsn.charAt(dice.nextInt(cnsn.length()));
            }
            nextVowl = !nextVowl;
        }
        return temp + " " + normalTypes[dice.nextInt(normalTypes.length)];
    }

    private String randomName() {
        String temp = "";
        String vowl = "aeiou";
        String cnsn = "qwrtypsdfghjklmnbvcxz";
        int len = dice.nextInt(6) + 3; // 3 min
        boolean nextVowl = dice.nextBoolean();
        for (int i = 0; i < len; i++) {
            if (nextVowl) {
                temp += vowl.charAt(dice.nextInt(vowl.length()));
            } else {
                temp += cnsn.charAt(dice.nextInt(cnsn.length()));
            }
            nextVowl = !nextVowl;
        }
        return temp + " " + normalTypes[dice.nextInt(normalTypes.length)];
    }

    private String randomPrefix() {
        String temp = "";
        String vowl = "aeiou";
        String cnsn = "qwrtypsdfghjklmnbvcxz";
        int len = dice.nextInt(6) + 3; // 3 min
        boolean nextVowl = dice.nextBoolean();
        for (int i = 0; i < len; i++) {
            if (nextVowl) {
                temp += vowl.charAt(dice.nextInt(vowl.length()));
            } else {
                temp += cnsn.charAt(dice.nextInt(cnsn.length()));
            }
            nextVowl = !nextVowl;
        }
        return temp;
    }

    @Override
    public String toString() {
        String data = "aquarium:\n";
        for (Entry e : found.entrySet()) {
            data += "\n";
            for (Fish f : (List<Fish>) e.getValue()) {
                data += f.toString() + ", ";
            }
        }
        return data;
    }
}
