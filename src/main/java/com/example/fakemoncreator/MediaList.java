package com.example.fakemoncreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MediaList implements Iterable<Media>{
    //Instance for singleton
    private static MediaList instance = null;
    private static List<Media> watchList;

    //Private to make a singleton
    private MediaList(){
        watchList = new ArrayList<>();
    }

    //Return singleton
    public static MediaList getMediaListInstance(){
        if(instance == null){
            instance = new MediaList();
        }
        return instance;
    }

    //Add item only if the title is not used
    public boolean addMedia(Media media){
        if(!alreadyInList(media.getTitle())){
            watchList.add(media);
            sort();
            return true;
        }
        return false;
    }

    //deleteList added for new "Delete List" button
    public static void deleteList(){
        instance = new MediaList();
        watchList = new ArrayList<>();
    }

    //Remove item, but by name rather than object
    public boolean removeMedia(String title){
        if(alreadyInList(title)){
            for(Media m : watchList){
                if(m.getTitle().equalsIgnoreCase(title)){
                    watchList.remove(m);
                    return true;
                }
            }
        }
        return false;
    }

    //Easy way to access individual items of the list through the title
    public Media getMedia(String title){
        for(Media m : watchList){
            if(m.getTitle().equalsIgnoreCase(title)){
                return m;
            }
        }
        System.out.println("An item with that name was not found in the list.");
        return null;
    }

    //Check if another item has the same title; not available to the user
    public boolean alreadyInList(String name){
        for(Media m : watchList){
            if(m.getTitle().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    //Sort alphabetically via Collections
    public void sort(){
        Collections.sort(watchList);
    }

    //Iterator
    @Override
    public Iterator<Media> iterator() {
        return watchList.iterator();
    }
}
