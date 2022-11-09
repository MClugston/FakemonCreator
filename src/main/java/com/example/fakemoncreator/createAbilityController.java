package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createAbilityController {

    @FXML
    private TextField nameText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Label output;

    @FXML
    protected void createAbility(){
        if(AbilitySet.getAbilitySet().alreadyInSet(nameText.getText())){ //If name is taken
            output.setText("An ability with that name already exists.");
        } else if((nameText.getText().trim().equals("")) || descriptionText.getText().trim().equals("")){ //If either are blank when trimmed
            output.setText("Please input something into both fields");
        } else{
            AbilitySet.getAbilitySet().addAbility(new Ability(nameText.getText(), descriptionText.getText()));
            //Close the popup
            Stage stage = (Stage) output.getScene().getWindow();
            stage.close();
        }

        //Save changes
        Controller.saveAbilities();
    }
}
