package com.example.fakemoncreator;

public class OfficialMove extends Move{
    public OfficialMove(String name, String type, String category, int power, int accuracy, int pp, String description) {
        super(name, type, category, power, accuracy, pp, description);
    }

    @Override
    public String setName(String name) {
        return "You can't change the name of an official move.";
    }

    @Override
    public String setType(String type) {
        return "You can't change the type of an official move.";
    }

    @Override
    public String setCategory(String category) {
        return "You can't change the category of an official move.";
    }

    @Override
    public String setPower(int power) {
        return "You can't change the power of an official move.";
    }

    @Override
    public String setAccuracy(int accuracy) {
        return "You can't change the accuracy of an official move.";
    }

    @Override
    public String setPp(int pp) {
        return "You can't change the PP of an official move.";
    }

    @Override
    public String setDescription(String description) {
        return "You can't change the description of an official move.";
    }
}
