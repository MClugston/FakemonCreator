package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class deleteAbilityController {
    @FXML
    private ComboBox<Ability> abilityBox;
    @FXML
    private Label output;

    public void initialize(){
        // Auto-fill ComboBox with user-created Abilities
        for(Ability a: AbilitySet.getAbilitySet()){
            if(!(a.getClass().equals(AbilitySet.getAbilitySet().getAbility("Adaptability").getClass()))){
                abilityBox.getItems().add(a);
            }
        }
        abilityBox.getSelectionModel().selectFirst();
    }

    @FXML
    protected void deleteAbility(){
        Ability selected = abilityBox.getValue();
        boolean inUse = false;

        for(Fakemon f: FakemonList.getFakemonListInstance()){ //Check if any fakemon uses this ability
            if(f.getAbility().equals(selected)){
                inUse=true;
            }
        }

        if(inUse){ //Don't delete if something uses it
            output.setText("You can't delete an ability that is currently in use.");
        } else if(selected!=null) { // Will be null if ComboBox is empty
            AbilitySet.getAbilitySet().removeAbility(selected);

            //Close popup
            Stage stage = (Stage) abilityBox.getScene().getWindow();
            stage.close();

            //Save changes
            Controller.saveAll();
        }
    }
}
