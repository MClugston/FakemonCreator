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

    public String setName(String name) {
        String oldName = this.name;
        this.name = name;
        return "Name was changed to " + name + " (Was " + oldName + ")";
    }

    public String getType() {
        return type;
    }

    public String setType(String type) {
        String oldType = this.type;
        this.type = type;
        return "Type was changed to " + type + " (Was " + oldType + ")";
    }

    public String getCategory() {
        return category;
    }

    public String setCategory(String category) {
        String oldCategory = this.category;
        this.category = category;
        return "Category was changed to " + category + " (Was " + oldCategory + ")";
    }

    public int getPower() {
        return power;
    }

    public String setPower(int power) {
        int oldPower = this.power;
        this.power = power;
        return "Power was changed to " + power + " (Was " + oldPower + ")";
    }

    public int getAccuracy() {
        return accuracy;
    }

    public String setAccuracy(int accuracy) {
        int oldAccuracy = this.accuracy;
        this.accuracy = accuracy;
        return "Accuracy was changed to " + accuracy + " (Was " + oldAccuracy + ")";
    }

    public int getPp() {
        return pp;
    }

    public String setPp(int pp) {
        int oldPP = this.pp;
        this.pp = pp;
        return "PP was changed to " + pp + " (Was " + oldPP + ")";
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        return "Description was change to " + description + " (Was " + oldDescription + ")";
    }

    @Override
    public String toString() {
        String output = name + ": " + "a " + type + " type " + category + " move. ";
        if(category.equalsIgnoreCase("status")){
            output += "Non-damaging, ";
        } else if(power==1 && accuracy==30){
            output += "One-Hit KO, ";
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
    public int compareTo(Move o) {
        return this.name.compareTo(o.getName());
    }
}
