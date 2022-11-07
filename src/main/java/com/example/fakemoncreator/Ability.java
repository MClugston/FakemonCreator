package com.example.fakemoncreator;

public class Ability implements Comparable<Ability> {
    private String name;
    private String effect;

    public Ability(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    //Basic get and set methods
    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return name + ": " + effect;
    }

    @Override
    public int compareTo(Ability o) {
        return this.name.compareToIgnoreCase(o.getName());
    }
}
