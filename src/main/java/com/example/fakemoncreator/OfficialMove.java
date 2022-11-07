package com.example.fakemoncreator;

public class OfficialMove extends Move{
    public OfficialMove(String name, String type, String category, int power, int accuracy, int pp, String description) {
        super(name, type, category, power, accuracy, pp, description);
    }

    @Override
    public String toString() {
        return super.toString() + " Official Move.";
    }
}
