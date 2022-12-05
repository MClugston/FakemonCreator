package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class editMoveController {

    @FXML
    private TextField nameText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Label output;

    @FXML
    private ComboBox<Move> moveBox;

    @FXML
    private ComboBox<String> typeText;
    @FXML
    private ComboBox<String> categoryText;

    @FXML
    private TextField powerText;
    @FXML
    private TextField accuracyText;
    @FXML
    private TextField ppText;

    private boolean inUse = false;

    public void initialize(){
        List<String> types = new ArrayList<>();
        types.add("Bug");
        types.add("Dark");
        types.add("Dragon");
        types.add("Electric");
        types.add("Fairy");
        types.add("Fighting");
        types.add("Fire");
        types.add("Flying");
        types.add("Ghost");
        types.add("Grass");
        types.add("Ground");
        types.add("Ice");
        types.add("Normal");
        types.add("Poison");
        types.add("Psychic");
        types.add("Rock");
        types.add("Steel");
        types.add("Water");

        // Fill ComboBoxes
        for(Move m: MoveSet.getMoveSet()){ // Add only moves of the user's creation (not OfficialMoves)
            if(!(m.getClass().equals(MoveSet.getMoveSet().getMove("Tackle").getClass()))){
                moveBox.getItems().add(m);
            }
        }

        typeText.getItems().addAll(types);
        categoryText.getItems().add("Physical");
        categoryText.getItems().add("Special");
        categoryText.getItems().add("Status");

        // Default ComboBoxes
        typeText.getSelectionModel().selectFirst();
        categoryText.getSelectionModel().selectFirst();
        moveBox.getSelectionModel().selectFirst();

        //Check if the user has created any moves
        if(moveBox.getItems().size()>0) {
            fillFields();
        } else{
            descriptionText.setText("You have not created any moves. No operations in this tab will function. " +
                    "Please redirect yourself to the Create A Move button on the main menu.");
        }
    }

    // Auto-fill all fields when a Move is selected from the ComboBox
    @FXML
    protected void fillFields(){
        if(moveBox.getValue()!=null){
            inUse = false;

            Move move = moveBox.getValue();
            nameText.setText(move.getName());

            typeText.getSelectionModel().select(move.getType());
            categoryText.getSelectionModel().select(move.getCategory());
            powerText.setText(move.getPower() + "");
            accuracyText.setText(move.getAccuracy() + "");
            ppText.setText(move.getPP() + "");

            descriptionText.setText(move.getDescription());

            for(Fakemon f: FakemonList.getFakemonListInstance()){ // If a move is in use,
                if(f.getMoveList().contains(moveBox.getValue())){
                    inUse = true;
                    output.setText("You can't edit a move that is currently in use."); // inform the user that they can't edit it
                }
            }
        }
    }

    // Delete old move and create a new one
    @FXML
    protected void editMove(){
        if(moveBox.getItems().size()>0) {
            String oldName = moveBox.getValue().getName();

            String name;
            String type;
            String category;
            String description;

            int power;
            int accuracy;
            int pp;

            try {
                name = nameText.getText();
                type = typeText.getValue();
                category = categoryText.getValue();
                description = descriptionText.getText();

                power = Integer.parseInt(powerText.getText());
                accuracy = Integer.parseInt(accuracyText.getText());
                pp = Integer.parseInt(ppText.getText());

                MoveSet moveSet = MoveSet.getMoveSet();

                if (!(oldName.equals(name)) && (moveSet.alreadyInSet(name))) { // Check if name is in use
                    output.setText("A move with that name already exists.");
                } else if ((name.trim().equals("")) || description.trim().equals("")) { // Check if fields are blank
                    output.setText("Please input something into the Name and Description boxes");
                } else if(inUse) {
                    output.setText("You can't edit a move that is currently in use.");
                } else { // Otherwise, delete old move & create new move
                    moveSet.removeMove(oldName);
                    moveSet.addMove(new Move(name, type, category, power, accuracy, pp, description));
                    //Close the popup
                    Stage stage = (Stage) output.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException e) { //If non-numbers in number fields
                output.setText("Please enter numbers for Power, Accuracy, and PP.");
            }
        }

        //Save changes
        Controller.saveAll();
    }
}
