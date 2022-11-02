package com.example.fakemoncreator;

public class Help {
    public static String about(){
        return ("The purpose of this software is to keep track of every book you read, "
                + "every show you watch, and every movie you watch.\nThis program allows "
                + "you to add, remove, and TV shows, movies, books, or anything else while also "
                + "keeping some useful information about them.\nThis includes titles, genres, "
                + "as well as their length and your progress through them. Titles can't be repeated; " +
                "they must be unique.\n" +
                "To add an item, simply fill in the first row of available fields and click \"Add An Item\"" +
                "\nTo remove an item, simply enter the title and click \"Remove An Item\"" +
                "\nTo edit an item, enter the title of the item you wish to edit and input the values " +
                "that you wish to change. Anything left with the default text will not be edited. Note that " +
                "the media type can not be changed." +
                "\nTo remove the Help text, click the help button again." +
                "\nTo search for a specific item, enter the title, media type, or genre in the appropriate lower field," +
                " then click \"Search\"" +
                "\nSimply select a radio button to switch between light mode and dark mode.");
    }
}
