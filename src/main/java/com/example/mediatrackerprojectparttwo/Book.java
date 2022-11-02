package com.example.mediatrackerprojectparttwo;

public class Book extends Media{

    public Book(String title, String genre, int progress, int length) {
        super(title, genre, progress, length);
    }

    @Override
    public String getType(){
        return "Book";
    }

    @Override
    public String toString() {
        return "(Book) - " + getTitle()  + " | Genre: " + getGenre() + " | Progress: " + getProgress() +
                " pages | Length: " + getLength() + " pages";
    }

    //Several methods were removed from each extended class because they didn't end up being used.
}
