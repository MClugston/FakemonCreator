package com.example.fakemoncreator;

public class Movie extends Media{
    public Movie(String title, String genre, int progress, int length) {
        super(title, genre, progress, length);
    }

    @Override
    public String getType(){
        return "Movie";
    }

    @Override
    public String toString() {
        return "(Movie) - " + getTitle()  + " | Genre: " + getGenre() + " | Progress: " + getProgress() +
                " minutes | Length: " + getLength() + " minutes";
    }
}
