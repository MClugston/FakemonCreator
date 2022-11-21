package com.example.fakemoncreator;

public class Entry{
    int key;
    Object value;
    Entry next;

    public Entry(int key, Object value){
        this.key = key;
        this.value = value;
        next = null;
    }

    public Entry(){
        next = null;
    }

    public int getKey(){
        return key;
    }

    public Object getValue(){
        return value;
    }
}