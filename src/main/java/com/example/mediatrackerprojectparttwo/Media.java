package com.example.mediatrackerprojectparttwo;

import java.io.Serializable;

public class Media implements Comparable<Media>, Serializable {
    private final String title;
    private String genre;
    private int length; // Length in episodes/minutes/pages respectively
    private int progress; // How many episodes/minutes/pages the user has watched/read

    //Full Constructor
    public Media(String title, String genre, int progress, int length) {
        this.title = title;
        this.genre = genre;
        this.progress = progress;
        this.length = length;
    }

    // Simple getters and setters for all variables

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getType(){
        return "Other";
    }

    // Final version will have multiple sorting methods, but for now only alphabetically by name
    @Override
    public int compareTo(Media m) {
        return this.title.compareToIgnoreCase(m.getTitle());
    }

    //Add each element if it is not null or 0. Otherwise, do nothing.
    @Override
    public String toString() {
        return "(Other) - " + title  + " | Genre: " + genre + " | Progress: " + progress + " | Length: " + length ;
    }
}