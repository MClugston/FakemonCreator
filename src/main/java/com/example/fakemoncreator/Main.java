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
        } catch (Exception e){
            e.printStackTrace();
        }

        FakemonList.getFakemonListInstance().addFakemon(new Fakemon("A", "FIRE", "ICE", AbilitySet.getAbilitySet().getAbility("Stalwart"), 10,20,30,40,50,60,"He's awesome"));
        FakemonList.getFakemonListInstance().addFakemon(new Fakemon("B", "FIRE", "ICE", AbilitySet.getAbilitySet().getAbility("Stalwart"), 10,20,30,40,50,60,"He's awesome"));
        FakemonList.getFakemonListInstance().addFakemon(new Fakemon("Z", "FIRE", "ICE", AbilitySet.getAbilitySet().getAbility("Stalwart"), 10,20,30,40,50,60,"He's awesome"));
        FakemonList.getFakemonListInstance().addFakemon(new Fakemon("C", "FIRE", "ICE", AbilitySet.getAbilitySet().getAbility("Stalwart"), 10,20,30,40,50,60,"He's awesome"));
        FakemonList.getFakemonListInstance().addFakemon(new Fakemon("F", "FIRE", "ICE", AbilitySet.getAbilitySet().getAbility("Stalwart"), 10,20,30,40,50,60,"He's awesome"));

        HeapSort<Fakemon> sort = new HeapSort<>();
        sort.sort(FakemonList.getMonList());

        System.out.println(FakemonList.getFakemonListInstance().toString());

        launch(args); //Launch JavaFX
    }
}