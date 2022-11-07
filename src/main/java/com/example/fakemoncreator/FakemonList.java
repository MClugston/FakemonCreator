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

    //Return internal List
    public static List<Fakemon> getMonList(){
        return monList;
    }

    //Add item only if the title is not used
    public boolean addFakemon(Fakemon fakemon){
        if(!alreadyInList(fakemon.getName())){
            monList.add(fakemon);
            return true;
        }
        return false;
    }

    //Remove by name
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

    //Remove by Fakemon
    public boolean removeFakemon(Fakemon f){
        return monList.remove(f);
    }

    //Access individual items of the list via name
    public Fakemon getFakemon(String name){
        for(Fakemon f : monList){
            if(f.getName().equalsIgnoreCase(name)){
                return f;
            }
        }
        System.out.println("An item with that name was not found in the list.");
        return null;
    }

    //Check if another item has the same name; only used internally
    public boolean alreadyInList(String name){
        for(Fakemon f : monList){
            if(f.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void sort(){
        HeapSort<Fakemon> sort = new HeapSort<>();
        sort.sort(monList);
    }

    @Override
    public Iterator<Fakemon> iterator() {
        return monList.iterator();
    }

    @Override
    public String toString() {
        sort();
        String output = "";
        for(Fakemon f : monList){
            output += f.toString() + "\n";
        }
        return output;
    }
}
