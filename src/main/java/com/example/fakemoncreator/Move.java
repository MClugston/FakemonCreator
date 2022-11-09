package com.example.fakemoncreator;

public class Move implements Comparable<Move>{
    private String name;
    private String type;
    private String category;
    private int power;
    private int accuracy;
    private int pp;
    private String description;

    public Move(String name, String type, String category, int power, int accuracy, int pp, String description) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPP() {
        return pp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String output = name + ": " + "a " + type + " type " + category + " move. ";
        if(power==0){
            output += "Non-damaging, ";
        } else if (power==1) {
            output += "Variable power, ";
        } else{
            output += power + " power, ";
        }
        if(accuracy==0){
            output += "never misses, ";
        } else{
            output += accuracy + " accuracy, ";
        }
        output +=  pp + " PP. " + description;
        return output;
    }

    @Override
    public int compareTo(Move m) {
        return this.name.compareToIgnoreCase(m.getName());
    }
}
