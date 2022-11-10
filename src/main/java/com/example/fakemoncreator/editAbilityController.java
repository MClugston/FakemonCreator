package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editAbilityController {

    @FXML
    private TextField nameText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private ComboBox<Ability> abilityBox;
    @FXML
    private Label output;

    private boolean inUse = false;

    public void initialize(){
        // Fill AbilityBox with user-created abilities (non-OfficialAbility)
        for(Ability a: AbilitySet.getAbilitySet()){
            if(!(a.getClass().equals(AbilitySet.getAbilitySet().getAbility("Adaptability").getClass()))){
                abilityBox.getItems().add(a);
            }
        }
        abilityBox.getSelectionModel().selectFirst();

        // Check if user has created any abilities
        if(abilityBox.getItems().size()>0) {
            fillFields();
        } else{
            nameText.setText("You have not created any abilities.");
            descriptionText.setText("No operations in this tab will function. Please redirect " +
                    "yourself to the Create An Ability button on the main menu.");
        }
    }

    // Auto-fill fields when an abilitiy is selected from the ComboBox
    @FXML
    protected void fillFields(){
        if(abilityBox.getValue()!=null){
            inUse = false;

            nameText.setText(abilityBox.getValue().getName());
            descriptionText.setText(abilityBox.getValue().getEffect());

            for(Fakemon f: FakemonList.getFakemonListInstance()){ // If an ability is in use,
                if(f.getAbility().equals(abilityBox.getValue())){
                    inUse = true;
                    output.setText("You can't edit an ability that is currently in use."); // inform the user that they can't edit it
                }
            }
        }
    }

    // Delete old ability and create a new one
    @FXML
    protected void editAbility(){
        if(abilityBox.getItems().size()>0){
            // Create variables
            String oldName = abilityBox.getValue().getName();
            String newName = nameText.getText();
            AbilitySet abilitySet = AbilitySet.getAbilitySet();

            if(!(newName.equals(oldName)) && (abilitySet.alreadyInSet(newName))) { //If name is in use
                output.setText("That name is already used by a different ability.");
            } else if ((newName.trim().equals("")) || descriptionText.getText().trim().equals("")) { //If either are blank when trimmed
                output.setText("Please input something into both fields");
            } else if(inUse){
                output.setText("You can't edit an ability that is currently in use.");
            } else {
                // Due to the nature of a Treeset, it will not sort after using set methods, so create a new object to keep it sorted
                abilitySet.removeAbility(oldName);
                abilitySet.addAbility(new Ability(newName, descriptionText.getText()));

                //Save changes
                Controller.saveAbilities();

                Stage stage = (Stage) nameText.getScene().getWindow();
                stage.close();
            }
        }
    }
}
