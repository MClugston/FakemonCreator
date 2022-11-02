package com.example.mediatrackerprojectparttwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Main extends Application implements Serializable {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mediaTracker-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Media Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            File saveFile = new File("saveFile.txt");
            if (saveFile.createNewFile()) {
                System.out.println("File created: " + saveFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try { //Initialize mediaList from saveFile
            Scanner scanner = new Scanner(new File("saveFile.txt"));
            MediaList mediaList = MediaList.getMediaListInstance();
            while(scanner.hasNext()) {
                String type = scanner.nextLine();
                if(type.equalsIgnoreCase("book")){
                    mediaList.addMedia(new Book(scanner.nextLine(), scanner.nextLine(),
                            Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine())));
                } else if (type.equalsIgnoreCase("movie")){
                    mediaList.addMedia(new Movie(scanner.nextLine(), scanner.nextLine(),
                            Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine())));
                } else if (type.equalsIgnoreCase("show")){
                    mediaList.addMedia(new Show(scanner.nextLine(), scanner.nextLine(),
                            Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine())));
                } else{
                    mediaList.addMedia(new Media(scanner.nextLine(), scanner.nextLine(),
                            Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine())));
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("ERROR in Scanner Creation on Startup");
        }
        launch(args); //Launch JavaFX

    }
}