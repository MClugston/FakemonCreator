package com.example.fakemoncreator;

public class OfficialAbility extends Ability{
    public OfficialAbility(String name, String effect) {
        super(name, effect);
    }

    @Override
    public String toString() {
        return super.toString() + " Official ability.";
    }
}
