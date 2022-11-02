package com.example.fakemoncreator;

public class Ability implements Comparable<Ability> {
    private String name;
    private String effect;

    public Ability(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        String oldName = this.name;
        this.name = name;
        return "Name was changed to " + name + " (Was " + oldName + ")";
    }

    public String getEffect() {
        return effect;
    }

    public String setEffect(String effect) {
        String oldEffect = this.effect;
        this.effect = effect;
        return "Effect was changed to " + effect + " (Was " + oldEffect + ")";
    }

    @Override
    public String toString() {
        return name + ": " + effect;
    }

    @Override
    public int compareTo(Ability o) {
        return this.name.compareTo(o.getName());
    }
}
