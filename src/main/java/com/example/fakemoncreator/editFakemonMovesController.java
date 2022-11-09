package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class editFakemonMovesController {

    @FXML
    private ComboBox<Fakemon> fakemonBox;

    @FXML
    private ComboBox<Move> allMovesBox;
    @FXML
    private ComboBox<Move> fakemonMovesBox;

    @FXML
    private TextArea output;

    // Setup all default values
    public void initialize(){
        // Setup Fakemon ComboBox
        fakemonBox.getItems().addAll(FakemonList.getMonList());
        fakemonBox.getSelectionModel().selectFirst();

        // Add moves ComboBox
        allMovesBox.getItems().addAll(MoveSet.getRawMoveSet());
        allMovesBox.getSelectionModel().selectFirst();

        // Remove Moves ComboBox
        fillFields();

        // Check if the user has created any fakemon
        if(fakemonBox.getItems().size()>0){
            fillFields();
        }
    }

    @FXML
    protected void addMove(){
        if(fakemonBox.getValue()!=null) {
            fakemonBox.getValue().addMove(allMovesBox.getValue());
            fillFields();
        }

        //Save changes
        Controller.saveFakemon();
    }

    @FXML
    protected void removeMove(){
        if((fakemonBox.getValue()!=null) && (fakemonMovesBox.getValue()!=null)){
            fakemonBox.getValue().removeMove(fakemonMovesBox.getValue());
        }
        fillFields();

        //Save changes
        Controller.saveFakemon();
    }

    // Auto-fill all fields when a fakemon is selected from the ComboBox
    @FXML
    protected void fillFields(){
        if(fakemonBox.getValue()!=null){
            Fakemon f = fakemonBox.getValue();

            fakemonMovesBox.getItems().clear();

            fakemonMovesBox.getItems().addAll(f.getMoveList());
            fakemonMovesBox.getSelectionModel().selectFirst();

            output.setText("");
            for (Move m: f.getMoveList()){
                output.appendText(m.getName() + "\n");
            }

        } else{
            output.setText("You have not created any fakemon. No operations in this tab will function. Please redirect " +
                    "yourself to the Create A Fakemon button on the main menu.");
        }
    }

    // Close the page
    @FXML
    protected void close() {
        Controller.saveFakemon();
        Stage stage = (Stage) fakemonMovesBox.getScene().getWindow();
        stage.close();
    }
}
