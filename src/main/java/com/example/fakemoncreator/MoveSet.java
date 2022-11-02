package com.example.fakemoncreator;

import java.util.*;

public class MoveSet implements Iterable<Move>{
    // Instance for singleton
    private static MoveSet instance = null;
    private static TreeSet<Move> thisSet;

    // Private to ensure only 1 is created
    private MoveSet(){
        thisSet = new TreeSet<>();
    }

    // Return the list
    public static MoveSet getMoveSet(){
        if(instance == null){
            instance = new MoveSet();
        }
        return instance;
    }

    //Add move
    public boolean addMove(Move move){
        return thisSet.add(move);
    }

    //Remove move by name
    public boolean removeMove(String name){
        for(Move m : thisSet){
            if(m.getName().equalsIgnoreCase(name)){
                thisSet.remove(m);
                return true;
            }
        }
        return false;
    }

    //Access individual items of the set through the title
    public Move getMove(String name){
        for(Move m : thisSet){
            if(m.getName().equalsIgnoreCase(name)){
                return m;
            }
        }
        System.out.println("An item with that name was not found in the list.");
        return null;
    }

    @Override
    public Iterator<Move> iterator() {
        return thisSet.iterator();
    }

    @Override
    public String toString() {
        String output = "";
        for (Move m: thisSet){
            output += m.toString() + "\n";
        }
        return output;
    }
}
