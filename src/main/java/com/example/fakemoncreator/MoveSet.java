package com.example.fakemoncreator;

import java.util.*;

public class MoveSet implements Iterable<Move>{
    // Instance for singleton
    private static MoveSet instance = null;
    private static TreeSet<Move> moveTreeSet;

    // Private to ensure only 1 is created
    private MoveSet(){
        moveTreeSet = new TreeSet<>();
    }

    // Return the MoveSet
    public static MoveSet getMoveSet(){
        if(instance == null){
            instance = new MoveSet();
        }
        return instance;
    }

    //Return the internal TreeSet
    public static TreeSet<Move> getRawMoveSet(){
        return moveTreeSet;
    }

    //Add move
    public boolean addMove(Move move){
        return moveTreeSet.add(move);
    }

    //Remove move by name
    public boolean removeMove(String name){
        for(Move m : moveTreeSet){
            if(m.getName().equalsIgnoreCase(name)){
                moveTreeSet.remove(m);
                return true;
            }
        }
        return false;
    }

    //Remove move by Move
    public boolean removeMove(Move move){
        return moveTreeSet.remove(move);
    }

    //Access individual items of the set through the title
    public Move getMove(String name){
        for(Move m : moveTreeSet){
            if(m.getName().equalsIgnoreCase(name)){
                return m;
            }
        }
        System.out.println("An item with that name was not found in the list.");
        return null;
    }

    //Check if another item has the same name; only used internally
    public boolean alreadyInSet(String name){
        for(Move m : moveTreeSet){
            if(m.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Move> iterator() {
        return moveTreeSet.iterator();
    }

    @Override
    public String toString() {
        String output = "";
        for (Move m: moveTreeSet){
            output += m.toString() + "\n";
        }
        return output;
    }
}
