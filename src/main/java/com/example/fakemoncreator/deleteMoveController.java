package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class deleteMoveController {
    @FXML
    private ComboBox<Move> moveBox;
    @FXML
    private Label output;

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
        boolean inUse = false;

        for(Fakemon f: FakemonList.getFakemonListInstance()){ // Check if the move is in use
            if(f.getMoveList().contains(selected)){
                inUse = true;
            }
        }

        if(inUse){
            output.setText("You can't delete a move that is currently in use.");
        } else if(selected!=null) { // Will be null if ComboBox is empty
            MoveSet.getMoveSet().removeMove(selected);

            // Close popup
            Stage stage = (Stage) moveBox.getScene().getWindow();
            stage.close();

            //Save changes
            Controller.saveMoves();
        }
    }
}
