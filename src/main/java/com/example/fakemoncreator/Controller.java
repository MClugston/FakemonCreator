package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Controller {

    @FXML
    private TextArea output;

    public void initialize(){
        // Initialize the internal list and sets to prevent potential null-pointer exceptions
        FakemonList fakemonList = FakemonList.getFakemonListInstance();
        MoveSet moveSet = MoveSet.getMoveSet();
        AbilitySet abilitySet = AbilitySet.getAbilitySet();
    }

    //Fakemon

    @FXML
    protected void createFakemon(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createFakemon-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Create a Fakemon");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void deleteFakemon(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deleteFakemon-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Delete a Fakemon");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void editFakemon(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editFakemon-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Edit a Fakemon");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void editFakemonMoves(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editFakemonMoves-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Edit a Fakemon's Moves");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void viewFakemonList(){
        output.setText(FakemonList.getFakemonListInstance().toString());
    }

    //Abilities

    @FXML
    protected void createAbility(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createAbility-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Create an Ability");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void deleteAbility(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deleteAbility-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Delete an Ability");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void editAbility(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editAbility-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Edit an Ability");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void viewAbilityList(){
        output.setText(AbilitySet.getAbilitySet().toString());
    }

    @FXML
    protected void viewMyAbilities(){
        output.setText("");
        for(Ability a: AbilitySet.getAbilitySet()){
            if(!(a.getClass().equals(AbilitySet.getAbilitySet().getAbility("Adaptability").getClass()))){
                output.setText(output.getText() + a.toString() + "\n");
            }
        }
    }

    //Moves

    @FXML
    protected void createMove(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createMove-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Create a Move");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void deleteMove(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deleteMove-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Delete a Move");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void editMove(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editMove-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 520);
            Stage stage = new Stage();
            stage.setTitle("Edit a Move");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void viewMoveList(){
        output.setText(MoveSet.getMoveSet().toString());
    }

    @FXML
    protected void viewMyMoves(){
        output.setText("");
        for(Move m: MoveSet.getMoveSet()){
            if(!(m.getClass().equals(MoveSet.getMoveSet().getMove("Tackle").getClass()))){
                output.setText(output.getText() + m.toString() + "\n");
            }
        }
    }

    // Methods to save everything to its respective file
    public static void saveAll(){
        saveAbilities();
        saveMoves();
        saveFakemon();
    }

    // Save abilitySet to abilities.txt
    public static void saveAbilities(){
        File abilityFile = new File("abilities.txt");
        HashSet<Ability> unofficial = new HashSet<>();
        AbilitySet abilitySet = AbilitySet.getAbilitySet();

        try{
            FileWriter writer = new FileWriter(abilityFile);
            writer.write(""); // Clear the file
            writer = new FileWriter(abilityFile, true);

            for(Ability a : abilitySet){
                // If it is an official ability, write it first
                if(a.getClass().equals(abilitySet.getAbility("Adaptability").getClass())){
                    writer.write(a.getName() + ",\"" + a.getEffect() + "\"\n");
                } else{
                    unofficial.add(a);
                }
            }
            // Keep all unofficial abilities at the end to make startup simpler
            for(Ability a : unofficial){
                writer.write(a.getName() + ",\"" + a.getEffect() + "\"\n");
            }
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Save moveSet to moves.txt
    public static void saveMoves(){
        File moveFile = new File("moves.txt");
        HashSet<Move> unofficial = new HashSet<>();
        MoveSet moveSet = MoveSet.getMoveSet();

        try{
            FileWriter writer = new FileWriter(moveFile);
            writer.write(""); // Clear the file
            writer = new FileWriter(moveFile, true);

            for(Move m : moveSet){
                // If it is an official move, write it first
                if(m.getClass().equals(moveSet.getMove("Absorb").getClass())){
                    writer.write(m.getName() + "," + m.getPower() + "," + m.getType().toUpperCase() +
                            "," + m.getCategory() + "," + m.getAccuracy() + "," + m.getPP() + "," + m.getDescription() + "\n");
                } else{
                    unofficial.add(m);
                }
            }
            // Keep all unofficial moves at the end to make startup simpler
            for(Move m : unofficial){
                writer.write(m.getName() + "," + m.getPower() + "," + m.getType().toUpperCase() +
                        "," + m.getCategory() + "," + m.getAccuracy() + "," + m.getPP() + "," + m.getDescription() + "\n");
            }
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // Save fakemonList to fakemon.txt
    public static void saveFakemon(){
        File fakemonFile = new File("fakemon.txt");
        FakemonList fakemonList = FakemonList.getFakemonListInstance();

        try{
            FileWriter writer = new FileWriter(fakemonFile);
            writer.write(""); // Clear the file
            writer = new FileWriter(fakemonFile, true);

            for(Fakemon f : fakemonList){
                writer.write(f.getName() + "," + f.getAbility().getName() + "," + f.getPrimaryType() + "," +
                        f.getSecondaryType() + "," + f.getHp() + "," + f.getAttack() + "," + f.getDefense() + "," +
                        f.getSpecialAttack() + "," + f.getSpecialDefense() + "," + f.getSpeed() + "," +
                        f.getDescription() + "!)@(#*$&%^");
                // Print entire moveList at the end
                for(Move m: f.getMoveList()){
                    writer.write("," + m.getName());
                }
                writer.write("\n");
            }

            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}