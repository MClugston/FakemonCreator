package com.example.fakemoncreator;

import java.util.*;

public class AbilitySet implements Iterable<Ability>{
    // Instance for singleton
    private static AbilitySet instance = null;
    private static BST<Ability> abilityTreeSet;

    // Private to ensure only 1 is created
    private AbilitySet(){
        abilityTreeSet = new BST<>();
    }

    // Return the AbilitySet
    public static AbilitySet getAbilitySet(){
        if(instance == null){
            instance = new AbilitySet();
        }
        return instance;
    }

    //Return the internal TreeSet
    public static BST<Ability> getRawAbilitySet(){
        return abilityTreeSet;
    }

    //Add ability
    public boolean addAbility(Ability ability){
        return abilityTreeSet.add(ability);
    }

    //Remove ability by name
    public boolean removeAbility(String name){
        for(Ability a : abilityTreeSet){
            if(a.getName().equalsIgnoreCase(name)){
                abilityTreeSet.remove(a);
                return true;
            }
        }
        return false;
    }

    //Remove by Ability
    public boolean removeAbility(Ability ability){
        return abilityTreeSet.remove(ability);
    }

    //Access  items via name
    public Ability getAbility(String name){
        for(Ability a : abilityTreeSet){
            if(a.getName().equalsIgnoreCase(name)){
                return a;
            }
        }
        System.out.println("An item with that name was not found in the list.");
        return null;
    }

    //Check if another item has the same name; only used internally
    public boolean alreadyInSet(String name){
        for(Ability a : abilityTreeSet){
            if(a.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Ability> iterator() {
        return abilityTreeSet.iterator();
    }

    @Override
    public String toString() {
        String output = "";
        for (Ability a: abilityTreeSet){
            output += a.toString() + "\n";
        }
        return output;
    }
}
