package com.example.mediatrackerprojectparttwo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private Pane background;

    @FXML
    private Label header;
    @FXML
    private TextArea output;
    @FXML
    private TextField title;
    @FXML
    private ComboBox<String> mediaType;
    @FXML
    private TextField genre;
    @FXML
    private TextField length;
    @FXML
    private TextField progress;

    @FXML
    private Line separator;

    @FXML
    private TextField titleSearch;
    @FXML
    private ComboBox<String> mediaTypeSearch;
    @FXML
    private TextField genreSearch;

    @FXML
    private RadioButton lightMode;
    @FXML
    private RadioButton darkMode;

    private boolean helpScreenShowing;

    public void initialize(){
        displayOutput();
        resetLabels();
    }

    @FXML
    protected void onAddButtonClick() {
        //Use the user input to create a media object
        MediaList mediaList = MediaList.getMediaListInstance();
        //Only proceed if the mediaType is book, movie, or show
        if (mediaType.getValue().equalsIgnoreCase("book") || mediaType.getValue().equalsIgnoreCase("movie")
                || mediaType.getValue().equalsIgnoreCase("show") || mediaType.getValue().equalsIgnoreCase("other")) {
            //Use all fields to add a media object
            boolean added = false;
            boolean alreadyInList = mediaList.alreadyInList(title.getText());
            try {
                if (mediaType.getValue().equalsIgnoreCase("book")) {
                    added = mediaList.addMedia(new Book(title.getText(), genre.getText(),
                            Integer.parseInt(progress.getText()), Integer.parseInt(length.getText())));
                } else if (mediaType.getValue().equalsIgnoreCase("movie")) {
                    added = mediaList.addMedia(new Movie(title.getText(), genre.getText(),
                            Integer.parseInt(progress.getText()), Integer.parseInt(length.getText())));
                } else if (mediaType.getValue().equalsIgnoreCase("show")) {
                    added = mediaList.addMedia(new Show(title.getText(), genre.getText(),
                            Integer.parseInt(progress.getText()), Integer.parseInt(length.getText())));
                } else {
                    added = mediaList.addMedia(new Media(title.getText(), genre.getText(),
                            Integer.parseInt(progress.getText()), Integer.parseInt(length.getText())));
                }
            } catch(NumberFormatException e){
                header.setText("Please input a number in the progress and length fields");
            }
            if (added) { //If the add was successful, inform the user and update the output
                try{ //Also add new item data to the save file
                    FileWriter writer = new FileWriter("saveFile.txt", true);
                    writer.write(mediaType.getValue() + "\n" + title.getText() + "\n" +
                    genre.getText() + "\n" + progress.getText() + "\n" + length.getText() + "\n");
                    writer.close();
                }
                catch(IOException e){
                    System.out.println("ERROR in creating PrintWriter");
                }
                header.setText("\"" + title.getText() + "\" was added to your list!");
                mediaList.sort();
                displayOutput();
            } if(alreadyInList) { //If add was unsuccessful, inform the user that it's already in the list
                header.setText("\"" + title.getText() + "\" is already in the list.");
            }
        } else { //If media type is invalid, inform the user
            header.setText("Invalid Media Type: Must be book, movie, or show.");
        }
        resetLabels();

    }

    @FXML
    protected void onRemoveButtonClick() {
        //If title exists, remove it
        MediaList mediaList = MediaList.getMediaListInstance();
        if(mediaList.removeMedia(title.getText())){
            header.setText("\"" + title.getText() + "\" was successfully removed!");
            save();
        } else{ //Otherwise, inform the user
            header.setText("\"" + title.getText() + "\" is not in the list.");
        }
        resetLabels();
        displayOutput();
    }

    @FXML
    protected void onEditButtonClick() {
        MediaList mediaList = MediaList.getMediaListInstance();
        //If title exists, set all other fields for that title to new input
        boolean edited = false;
        if(mediaList.alreadyInList(title.getText())){
            if(!genre.getText().equalsIgnoreCase("Input Genre")){
                mediaList.getMedia(title.getText()).setGenre(genre.getText());
                edited=true;
            }
            if(!length.getText().equalsIgnoreCase("Input Length")){
                mediaList.getMedia(title.getText()).setLength(Integer.parseInt(length.getText()));
                edited=true;
            }
            if(!progress.getText().equalsIgnoreCase("Input Progress")){
                mediaList.getMedia(title.getText()).setProgress(Integer.parseInt(progress.getText()));
                edited=true;
            }
            save();
            if(edited){
                header.setText("\"" + title.getText() + "\" was successfully edited!");
            } else{
                header.setText("No changes were made to \"" + title.getText() + ".");
            }
            resetLabels();
        } else{ //Otherwise, inform the user
            header.setText("\"" + title.getText() + "\" is not in the list. Just click \"Add An Item\" and " +
                    "it will be added automatically!");
        }
        displayOutput();
    }

    @FXML
    private void onHelpButtonClick(){
        if(helpScreenShowing){
            output.setText("");
            displayOutput();
            helpScreenShowing=false;
        } else{
            output.setText(Help.about());
            helpScreenShowing=true;
        }
    }

    @FXML
    private void promptReset(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING!");
        alert.setHeaderText("Clear your entire list?");
        alert.setContentText("This will permanently delete your list. " +
                "This process can not be reversed. Only click \"Yes\" if you are absolutely certain.");
        ButtonType confirm = new ButtonType("Yes");
        ButtonType deny = new ButtonType("No");
        alert.getButtonTypes().setAll(confirm, deny);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == confirm){
            try{
                FileWriter writer = new FileWriter("saveFile.txt");
                writer.write("");
            } catch(IOException e){
                System.out.println("Error in deleting save data.");
            }
            output.setText("");
            MediaList.deleteList();
            header.setText("Your list was successfully deleted.");
        }
    }

    @FXML
    protected void displayOutput(){
        output.setText("");
        MediaList mediaList = MediaList.getMediaListInstance();
        mediaList.sort();
        for(Media m: mediaList){
            output.setText(output.getText() + m + "\n");
        }
    }

    public void displayBySearch(){
        output.setText("");
        MediaList mediaList = MediaList.getMediaListInstance();
        mediaList.sort();
        //If mediatype is all
        if(mediaTypeSearch.getValue().equals("All")){
            if(!titleSearch.getText().equalsIgnoreCase("Search By Title") && !genreSearch.getText().equalsIgnoreCase("Search By Genre")){ //If both other fields are filled
                for(Media m: mediaList){
                    if(m.getTitle().toLowerCase().contains(titleSearch.getText().toLowerCase()) && m.getGenre().toLowerCase().contains(genreSearch.getText().toLowerCase())){
                        output.setText(output.getText() + m + "\n");
                    }
                }
            } else if(!titleSearch.getText().equalsIgnoreCase("Search By Title")){ //If only title is filled
                for(Media m: mediaList){
                    if(m.getTitle().toLowerCase().contains(titleSearch.getText().toLowerCase())){
                        output.setText(output.getText() + m + "\n");
                    }
                }
            } else if(!genreSearch.getText().equalsIgnoreCase("Search By Genre")){ //If only genre is filled
                for(Media m: mediaList){
                    if(m.getGenre().toLowerCase().contains(genreSearch.getText().toLowerCase())){
                        output.setText(output.getText() + m + "\n");
                    }
                }
            } else{ //If nothing is filled, display normally
                displayOutput();
            }

        //If mediatype is not all
        } else{
            if(!titleSearch.getText().equalsIgnoreCase("Search By Title") && !genreSearch.getText().equalsIgnoreCase("Search By Genre")){ //If both other fields are filled
                for(Media m: mediaList){
                    if(m.getTitle().toLowerCase().contains(titleSearch.getText().toLowerCase()) && m.getGenre().toLowerCase().contains(genreSearch.getText().toLowerCase()) && m.getType().equalsIgnoreCase(mediaTypeSearch.getValue())){
                        output.setText(output.getText() + m + "\n");
                    }
                }
            } else if(!titleSearch.getText().equalsIgnoreCase("Search By Title")){ //If title is filled
                for(Media m: mediaList){
                    if(m.getTitle().toLowerCase().contains(titleSearch.getText().toLowerCase()) && m.getType().equalsIgnoreCase(mediaTypeSearch.getValue())){
                        output.setText(output.getText() + m + "\n");
                    }
                }
            } else if(!genreSearch.getText().equalsIgnoreCase("Search By Genre")){ //If genre is filled
                for(Media m: mediaList){
                    if(m.getGenre().toLowerCase().contains(genreSearch.getText().toLowerCase()) && m.getType().equalsIgnoreCase(mediaTypeSearch.getValue())){
                        output.setText(output.getText() + m + "\n");
                    }
                }
            } else{ //If only mediaType is filled
                for(Media m: mediaList){
                    if(m.getType().equalsIgnoreCase(mediaTypeSearch.getValue())){
                        output.setText(output.getText() + m + "\n");
                    }
                }
            }
        }
        resetLabels();
    }

    @FXML
    private void lightModeButton(){
        background.setStyle("-fx-background-color:#ffffff");
        output.setStyle("-fx-control-inner-background:#FFFFFF;");
        output.setStyle("-fx-text-fill:#000000");
        header.setStyle("-fx-text-fill:#000000");
        lightMode.setStyle("-fx-text-fill:#000000");
        darkMode.setStyle("-fx-text-fill:#000000");
        separator.setStyle("-fx-stroke:#000000");
    }

    @FXML
    private void darkModeButton(){
        background.setStyle("-fx-background-color:#121212");
        output.setStyle("-fx-control-inner-background:#242424; -fx-text-fill: #cdc6ff; ");
        header.setStyle("-fx-text-fill:#cdc6ff");
        lightMode.setStyle("-fx-text-fill:#cdc6ff");
        darkMode.setStyle("-fx-text-fill:#cdc6ff");
        separator.setStyle("-fx-stroke:#ffffff");
    }

    private void resetLabels(){
        //Reset all fields to default
        title.setText("Input Title");
        mediaType.getSelectionModel().selectFirst();
        genre.setText("Input Genre");
        length.setText("Input Length");
        progress.setText("Input Progress");

        mediaTypeSearch.getSelectionModel().selectFirst();
        titleSearch.setText("Search By Title");
        genreSearch.setText("Search By Genre");
    }

    private void save(){ //Update file to be the same as temporary list
        File saveFile = new File("saveFile.txt");
        try {
            MediaList mediaList = MediaList.getMediaListInstance();
            FileWriter writer = new FileWriter(saveFile);
            writer.write(""); //Clear the file
            writer = new FileWriter(saveFile, true);
            output.setText(""); //Clear the output
            for(Media m: mediaList){
                if(m.getClass().equals(Book.class)){
                    writer.write("Book" + "\n");
                } else if(m.getClass().equals(Movie.class)){
                    writer.write("Movie" + "\n");
                } else if(m.getClass().equals(Show.class)){
                    writer.write("Show" + "\n");
                } else{
                    writer.write("Media" + "\n");
                }
                writer.write(m.getTitle() + "\n" +
                        m.getGenre() + "\n" + m.getProgress() + "\n" + m.getLength() + "\n");

                output.setText(output.getText() + "\n" + m); //And add every item in the list to the output
            }
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}