package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class deleteFakemonController {
    @FXML
    private ComboBox<Fakemon> fakemonBox;

    // Auto-fill ComboBox with all fakemon
    public void initialize(){
        fakemonBox.getItems().addAll(FakemonList.getMonList());
        fakemonBox.getSelectionModel().selectFirst();
    }

    @FXML
    protected void deleteFakemon(){
        Fakemon selected = fakemonBox.getValue();

        if(selected!=null) { // Will be null if ComboBox is empty
            FakemonList.getFakemonListInstance().removeFakemon(selected);
        }

        //Close popup
        Stage stage = (Stage) fakemonBox.getScene().getWindow();
        stage.close();

        //Save changes
        Controller.saveAll();
    }
}
