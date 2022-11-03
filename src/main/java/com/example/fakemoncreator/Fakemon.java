package com.example.fakemoncreator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fakemon implements Comparable<Fakemon>{
    private String name;
    private String primaryType;
    private String secondaryType;
    private Ability ability;
    private final List<Move> moveList;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private int statTotal;
    private String description;

    public Fakemon(String name, String primaryType, String secondaryType, Ability ability,
                   int hp, int attack, int defense, int specialAttack,
                   int specialDefense, int speed, String description) {
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.ability = ability;
        this.moveList = new ArrayList<>();
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.statTotal = hp + attack + defense + specialAttack + specialDefense + speed;
        this.description = description;
    }

    //Get and set methods
    public String getName() {
        return name;
    }

    public String setName(String name) { //Fails if name is taken
        if (FakemonList.getFakemonListInstance().alreadyInList(name)) {
            return "A Fakemon with this name already exists.";
        }
        String oldName = this.name;
        this.name = name;
        return "Name changed to " + name + " (Was " + oldName + ")";
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public String setPrimaryType(String primaryType) {
        String oldPrimaryType = this.primaryType;
        this.primaryType = primaryType;
        return "Primary type changed to " + primaryType + " (Was " + oldPrimaryType + ")";
    }

    public String getSecondaryType() {
        if (secondaryType == null) {
            return "No secondary type.";
        }
        return secondaryType;
    }

    public String setSecondaryType(String secondaryType) {
        String oldSecondaryType = this.getSecondaryType(); // Prevent potential null pointer
        this.secondaryType = secondaryType;
        return "Secondary type changed to " + secondaryType + " (Was " + oldSecondaryType + ")";
    }

    public Ability getAbility() {
        return ability;
    }

    public String setAbility(Ability ability) {
        Ability oldAbility = this.ability;
        this.ability = ability;
        return "Ability changed to " + ability.getName() + " (Was " + oldAbility.getName() + ")";
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public int getHp() {
        return hp;
    }

    public String setHp(int hp) {
        int oldHP = this.hp;
        this.hp = hp;
        return "HP stat changed to " + hp + " (Was " + oldHP + ")";
    }

    public int getAttack() {
        return attack;
    }

    public String setAttack(int attack) {
        int oldAttack = this.attack;
        this.attack = attack;
        return "Attack stat changed to " + attack + " (Was " + oldAttack + ")";
    }

    public int getDefense() {
        return defense;
    }

    public String setDefense(int defense) {
        int oldDefense = this.defense;
        this.defense = defense;
        return "Defense stat changed to " + defense + " (Was " + oldDefense + ")";
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public String setSpecialAttack(int specialAttack) {
        int oldSpecialAttack = this.specialAttack;
        this.specialAttack = specialAttack;
        return "Special attack stat changed to " + specialAttack + " (Was " + oldSpecialAttack + ")";
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public String setSpecialDefense(int specialDefense) {
        int oldSpecialDefense = this.specialDefense;
        this.specialDefense = specialDefense;
        return "Special defense stat changed to " + specialDefense + " (Was " + oldSpecialDefense + ")";
    }

    public int getSpeed() {
        return speed;
    }

    public String setSpeed(int speed) {
        int oldSpeed = this.speed;
        this.speed = speed;
        return "Speed stat changed to " + speed + " (Was " + oldSpeed + ")";
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        return "Description changed to " + description + " (Was " + oldDescription + ")";
    }

    // Basic adds and removes for moveList
    public String addMove(Move move) {
        moveList.add(move);
        return "Move " + move.getName() + " successfully added to " + this.getName();
    }

    public String removeMove(Move move) {
        moveList.remove(move);
        return "Move " + move.getName() + " successfully removed from " + this.getName();
    }

    public String addMoves(Collection<Move> moves) {
        moveList.addAll(moves);
        String output = "";
        for (Move m : moves) {
            output += m.getName() + ", ";
        }
        return output + "successfully added to " + this.getName();
    }

    public String removeMoves(Collection<Move> moves) {
        moveList.removeAll(moves);
        String output = "";
        for (Move m : moves) {
            output += m.getName() + ", ";
        }
        return output + "successfully removed from " + this.getName();
    }

    @Override
    public String toString() {
        String output = name + ": ";
        if (secondaryType != null) {
            output += "A " + primaryType + "/" + secondaryType;
        } else {
            output += primaryType;
        }
        output += " type with the ability " + ability.getName() + ". ";
        output += hp + " HP; " + attack + " Atk; " + defense + " Def; "
                + specialAttack + " SpAtk; " + specialDefense + " SpDef; "
                + speed + " Spd; Base stat total of " + statTotal + ". ";
        if(moveList.size()>0){
            output += "It can learn the moves ";
            for (int i=0; i<moveList.size(); i++) {
                output += moveList.get(i).getName();
                if (i == (moveList.size() - 2)) {
                    output += ", and ";
                } else if (i == (moveList.size() - 1)) {
                    output += ". ";
                } else {
                    output += ", ";
                }
            }
        }
        output += description;
        return output;
    }

    @Override
    public int compareTo(Fakemon o) {
        return this.getName().compareTo(o.getName());
    }
}
