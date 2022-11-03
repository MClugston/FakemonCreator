package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

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
    private Line separator;

    @FXML
    private RadioButton lightMode;
    @FXML
    private RadioButton darkMode;

    public void initialize(){

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

    }

    @FXML
    protected void editFakemon(){

    }

    @FXML
    protected void viewFakemonList(){
        output.setText(FakemonList.getFakemonListInstance().toString());
    }

    //Abilities

    @FXML
    protected void createAbility(){

    }

    @FXML
    protected void deleteAbility(){

    }

    @FXML
    protected void editAbility(){

    }

    @FXML
    protected void viewAbilityList(){
        output.setText(AbilitySet.getAbilitySet().toString());
    }

    //Moves

    @FXML
    protected void createMove(){

    }

    @FXML
    protected void deleteMove(){

    }

    @FXML
    protected void editMove(){

    }

    @FXML
    protected void viewMoveList(){
        output.setText(MoveSet.getMoveSet().toString());
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
            header.setText("Your list was successfully deleted.");
        }
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
}