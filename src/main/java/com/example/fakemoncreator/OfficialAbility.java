package com.example.fakemoncreator;

public class OfficialAbility extends Ability{
    public OfficialAbility(String name, String effect) {
        super(name, effect);
    }

    @Override
    public String setEffect(String effect) {
        return "You can't change the effect of an official ability.";
    }

    @Override
    public String setName(String name) {
        return "You can't change the name of an official ability.";
    }
}
