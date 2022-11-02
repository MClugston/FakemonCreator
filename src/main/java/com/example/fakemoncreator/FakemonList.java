package com.example.fakemoncreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FakemonList implements Iterable<Fakemon>{
    //Instance for singleton
    private static FakemonList instance = null;
    private static List<Fakemon> monList;

    //Private to make a singleton
    private FakemonList(){
        monList = new ArrayList<>();
    }

    //Return singleton
    public static FakemonList getFakemonListInstance(){
        if(instance == null){
            instance = new FakemonList();
        }
        return instance;
    }

    //Add item only if the title is not used
    public boolean addFakemon(Fakemon fakemon){
        if(!alreadyInList(fakemon.getName())){
            monList.add(fakemon);
            return true;
        }
        return false;
    }

    //Remove item, but by name rather than object
    public boolean removeFakemon(String name){
        if(alreadyInList(name)){
            for(Fakemon f : monList){
                if(f.getName().equalsIgnoreCase(name)){
                    monList.remove(f);
                    return true;
                }
            }
        }
        return false;
    }

    //Easy way to access individual items of the list through the title
    public Fakemon getFakemon(String name){
        for(Fakemon f : monList){
            if(f.getName().equalsIgnoreCase(name)){
                return f;
            }
        }
        System.out.println("An item with that name was not found in the list.");
        return null;
    }

    //Check if another item has the same title; not available to the user
    public boolean alreadyInList(String name){
        for(Fakemon f : monList){
            if(f.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Fakemon> iterator() {
        return monList.iterator();
    }
}
