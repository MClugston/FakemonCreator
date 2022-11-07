package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class deleteAbilityController {
    @FXML
    private ComboBox<Ability> abilityBox;

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

        // Will be null if ComboBox is empty
        if(selected!=null) {
            AbilitySet.getAbilitySet().removeAbility(selected);
        }

        //Close popup
        Stage stage = (Stage) abilityBox.getScene().getWindow();
        stage.close();

        //Save changes
        Controller.saveAbilities();
    }
}
