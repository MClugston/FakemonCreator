package com.example.fakemoncreator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
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
        loadAbilities();
        loadMoves();
        loadFakemon();

        launch(args); //Launch JavaFX
    }

    //Initialize abilitySet from abilities.txt
    public static void loadAbilities(){
        try{
            File abilityFile = new File("abilities.txt");
            Scanner scanner = new Scanner(abilityFile);
            AbilitySet abilitySet = AbilitySet.getAbilitySet();

            /* The first 260 abilities are official. Split them at the comma to get the name and
            description, then combine into an OfficialAbility */
            for(int i=0; i<260; i++){
                String nextLine = scanner.nextLine();
                int comma = nextLine.indexOf(",");
                String name = nextLine.substring(0,comma);
                String description = nextLine.substring(comma+2,nextLine.length()-1);
                abilitySet.addAbility(new OfficialAbility(name, description));
            } // After 260, do the same but create an Ability object
            while(scanner.hasNextLine()){
                String nextLine = scanner.nextLine();
                int comma = nextLine.indexOf(",");
                String name = nextLine.substring(0,comma);
                String description = nextLine.substring(comma+2,nextLine.length()-1);
                abilitySet.addAbility(new Ability(name, description));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //Initialize moveSet from moves.txt
    public static void loadMoves(){
        try {
            File moveFile = new File("moves.txt");
            Scanner scanner = new Scanner(moveFile);
            MoveSet moveSet = MoveSet.getMoveSet();

            //First 733 moves are official
            for(int i=0; i<733; i++){

                //Get entire next line as a string
                String nextLine = scanner.nextLine();

                //From beginning to comma is the name
                int comma = nextLine.indexOf(",");
                String name = nextLine.substring(0,comma);

                /* Remove the comma and everything before it --> redetermine comma location -->
                use next substring(0,comma) to get the wanted value --> repeat for all fields */
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
                String description = nextLine;

                // Combine all obtained values into one move
                moveSet.addMove(new OfficialMove(name, type, category, power, accuracy, pp, description));
            } // After 733, do the same but create Move objects.
            while(scanner.hasNextLine()){
                //Get entire next line as a string
                String nextLine = scanner.nextLine();

                //From beginning to comma is the name
                int comma = nextLine.indexOf(",");
                String name = nextLine.substring(0,comma);

                /* Remove the comma and everything before it --> redetermine comma location -->
                use next substring(0,comma) to get the wanted value --> repeat for all fields */
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
                String description = nextLine;

                // Combine all obtained values into one move
                moveSet.addMove(new Move(name, type, category, power, accuracy, pp, description));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Initialize fakemonList from fakemon.txt
    public static void loadFakemon(){
        try {
            File fakemonFile = new File("fakemon.txt");
            Scanner scanner = new Scanner(fakemonFile);
            FakemonList fakemonList = FakemonList.getFakemonListInstance();

            while(scanner.hasNextLine()){

                String nextLine = scanner.nextLine();

                //From beginning to comma is the name
                int comma = nextLine.indexOf(",");
                String name = nextLine.substring(0,comma);

                /* Remove the comma and everything before it --> redetermine comma location -->
                use next substring(0,comma) to get the wanted value --> repeat for all fields */
                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                String ability = nextLine.substring(0,comma);

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                String primaryType = nextLine.substring(0,comma);

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                String secondaryType = nextLine.substring(0,comma);

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int hp = Integer.parseInt(nextLine.substring(0,comma));

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int atk = Integer.parseInt(nextLine.substring(0,comma));

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int def = Integer.parseInt(nextLine.substring(0,comma));

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int spAtk = Integer.parseInt(nextLine.substring(0,comma));

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int spDef = Integer.parseInt(nextLine.substring(0,comma));

                nextLine = nextLine.substring(comma+1);
                comma = nextLine.indexOf(",");
                int spe = Integer.parseInt(nextLine.substring(0,comma));

                /* Because the description may contain a comma, I used a random sequence of special characters to
                indicate the end of the description. Instead of the comma, this check uses said sequence to ensure
                any commas will remain as part of that description, and will not be considered the end of the description.*/
                nextLine = nextLine.substring(comma+1);
                int endDesc = nextLine.indexOf("!)@(#*$&%^");
                String description = nextLine.substring(0,endDesc);
                nextLine = nextLine.substring(endDesc+11);

                //After the description, there is any number of moves
                HashSet<Move> moves = new HashSet<>();
                while(!(nextLine.trim().equals(""))){
                    comma = nextLine.indexOf(",");
                    if(comma!=-1) {
                        moves.add(MoveSet.getMoveSet().getMove(nextLine.substring(0, comma).trim()));
                        nextLine = nextLine.substring(comma + 1);
                    } else{
                        moves.add(MoveSet.getMoveSet().getMove(nextLine.trim()));
                        nextLine = "";
                    }
                }

                fakemonList.addFakemon(new Fakemon(name, primaryType, secondaryType, AbilitySet.getAbilitySet().getAbility(ability),
                        hp, atk, def, spAtk, spDef, spe, description));

                if(moves.size() > 0){
                    fakemonList.getFakemon(name).addMoves(moves);
                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
