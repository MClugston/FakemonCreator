package com.example.fakemoncreator;

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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fakemonCreator-view.fxml"));
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

        try{
            //Initialize abilitySet from official abilities file
            File abilityFile = new File("abilities.txt");
            Scanner scanner = new Scanner(abilityFile);
            AbilitySet abilitySet = AbilitySet.getAbilitySet();
            while(scanner.hasNextLine()){
                String nextLine = scanner.nextLine();
                int comma = nextLine.indexOf(",");
                String name = nextLine.substring(0,comma);
                String description = nextLine.substring(comma+2,nextLine.length()-1);
                abilitySet.addAbility(new OfficialAbility(name, description));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            //Initialize moveSet from official moves file
            File moveFile = new File("moves.txt");
            Scanner scanner = new Scanner(moveFile);
            MoveSet moveSet = MoveSet.getMoveSet();
            while(scanner.hasNextLine()){
                String nextLine = scanner.nextLine();
                int comma = nextLine.indexOf(",");
                String name = nextLine.substring(0,comma);
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int power = Integer.parseInt(nextLine.substring(0,comma));
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                String type = nextLine.substring(0,comma);
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                String category = nextLine.substring(0,comma);
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int accuracy = Integer.parseInt(nextLine.substring(0,comma));
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int pp = Integer.parseInt(nextLine.substring(0,comma));
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                nextLine = nextLine.substring(comma+1);
                String description = nextLine;
                moveSet.addMove(new OfficialMove(name, type, category, power, accuracy, pp, description));
            }
            System.out.println(moveSet);
        } catch (Exception e){
            e.printStackTrace();
        }

        FakemonList.getFakemonListInstance().addFakemon(new Fakemon("Wawa", "FIRE", "ICE", AbilitySet.getAbilitySet().getAbility("Stalwart"), 10,20,30,40,50,60,"He's awesome"));
        FakemonList.getFakemonListInstance().getFakemon("Wawa").addMove(MoveSet.getMoveSet().getMove("Fire Punch"));
        FakemonList.getFakemonListInstance().getFakemon("Wawa").addMove(MoveSet.getMoveSet().getMove("Thunder fang"));
        FakemonList.getFakemonListInstance().getFakemon("Wawa").addMove(MoveSet.getMoveSet().getMove("rolLiNg KiCk"));
        System.out.println(FakemonList.getFakemonListInstance().getFakemon("Wawa"));

        launch(args); //Launch JavaFX
    }
}