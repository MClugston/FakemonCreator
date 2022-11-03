package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class createMoveController {

    @FXML
    protected TextField nameText;
    @FXML
    protected TextArea descriptionText;
    @FXML
    protected Label output;

    @FXML
    protected ComboBox<String> typeText;
    @FXML
    protected ComboBox<String> categoryText;

    @FXML
    protected TextField powerText;
    @FXML
    protected TextField accuracyText;
    @FXML
    protected TextField ppText;

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

        typeText.getItems().addAll(types);
        categoryText.getItems().add("Physical");
        categoryText.getItems().add("Special");
        categoryText.getItems().add("Status");

        typeText.getSelectionModel().selectFirst();
        categoryText.getSelectionModel().selectFirst();
    }

    @FXML
    protected void createMove(){
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

            if(MoveSet.getMoveSet().alreadyInSet(name)){
                output.setText("A move with that name already exists.");
            } else {
                MoveSet.getMoveSet().addMove(new Move(name, type, category, power, accuracy, pp, description));
                //Close the popup
                Stage stage = (Stage) output.getScene().getWindow();
                stage.close();
            }
        } catch (NumberFormatException e){ //If non-numbers in number fields
            output.setText("Please enter numbers for Power, Accuracy, and PP.");
        }
    }
}
