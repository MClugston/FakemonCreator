package com.example.fakemoncreator;

import java.util.Collection;

public class Fakemon implements Comparable<Fakemon>{
    private String name;
    private String primaryType;
    private String secondaryType;
    private Ability ability;
    private final MyLinkedList<Move> moveList;
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
        this.moveList = new MyLinkedList<>();
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.statTotal = hp + attack + defense + specialAttack + specialDefense + speed;
        this.description = description;
    }

    //Get methods
    public String getName() {
        return name;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public String getSecondaryType() {
        if (secondaryType == null) {
            return "No secondary type.";
        }
        return secondaryType;
    }

    public Ability getAbility() {
        return ability;
    }

    public MyLinkedList<Move> getMoveList() {
        return moveList;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDescription() {
        return description;
    }

    // Basic adds and removes for moveList
    public void addMove(Move move) {
        if(!knowsMove(move)) {
            moveList.add(move);
        }
    }

    public boolean removeMove(Move move) {
        return moveList.remove(move);
    }

    public boolean addMoves(Collection<Move> moves) {
        return moveList.addAll(moves);
    }

    // Call contains
    private boolean knowsMove(Move move){
        return moveList.contains(move);
    }

    @Override
    public String toString() {
        String output = name + ": ";
        if (!(secondaryType.equals("None"))) {
            output += "A " + primaryType.toLowerCase() + "/" + secondaryType.toLowerCase();
        } else {
            output += primaryType.toLowerCase();
        }
        output += " type ";
        output += " with the ability " + ability.getName() + ". ";
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
    public int compareTo(Fakemon f) {
        return this.getName().compareToIgnoreCase(f.getName());
    }
}
