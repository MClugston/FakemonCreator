package com.example.fakemoncreator;

public class Show extends Media{
    public Show(String title, String genre, int progress, int length) {
        super(title, genre, progress, length);
    }

    @Override
    public String getType(){
        return "Show";
    }

    @Override
    public String toString() {
        return "(Show) - " + getTitle()  + " | Genre: " + getGenre() + " | Progress: " + getProgress() +
                " episodes | Length: " + getLength() + " episodes";
    }
}
