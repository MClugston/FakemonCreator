package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class deleteMoveController {
    @FXML
    private ComboBox<Move> moveBox;

    // Auto-fill ComboBox with user-created Moves
    public void initialize(){
        for(Move m: MoveSet.getMoveSet()){
            if(!(m.getClass().equals(MoveSet.getMoveSet().getMove("Tackle").getClass()))){
                moveBox.getItems().add(m);
            }
        }
        moveBox.getSelectionModel().selectFirst();
    }

    // Delete the selected Move
    @FXML
    protected void deleteMove(){
        Move selected = moveBox.getValue();

        // Will be null if ComboBox is empty
        if(selected!=null) {
            MoveSet.getMoveSet().removeMove(selected);
        }

        // Close popup
        Stage stage = (Stage) moveBox.getScene().getWindow();
        stage.close();

        //Save changes
        Controller.saveMoves();
    }
}
