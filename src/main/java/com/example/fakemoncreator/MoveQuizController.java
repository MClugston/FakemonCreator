package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MoveQuizController {

    @FXML
    private TextField typeText;
    @FXML
    private TextField categoryText;
    @FXML
    private TextField powerText;
    @FXML
    private TextField accuracyText;
    @FXML
    private TextField ppText;

    @FXML
    private TextArea descriptionText;

    @FXML
    private TextField nameText;
    @FXML
    private Button button;

    private MyHashtable<Move> testMoves = new MyHashtable<>(10);
    private int correct;

    int currentIndex;

    Move[] allMoves = MoveSet.getRawMoveSet().toArray(new Move[0]);

    // Reset variables and fill Hashtable
    public void initialize(){
        correct = 0;
        currentIndex = 1;
        button.setText("Guess Move");
        for(int i=1; i<11; i++){
            int random = (int)(Math.random()*AbilitySet.getRawAbilitySet().size());
            testMoves.put(i, allMoves[random]);
        }
        nameText.setText("Input guess");
        nextMove();
    }

    // Retrieve move and fill all fields
    private void nextMove(){
        Move currentMove = testMoves.get(currentIndex);

        typeText.setText(currentMove.getType());
        categoryText.setText(currentMove.getCategory());
        ppText.setText(currentMove.getPP() + "");
        powerText.setText(currentMove.getPower() + "");
        accuracyText.setText(currentMove.getAccuracy() + "");
        descriptionText.setText(currentMove.getDescription());

        currentIndex++;
    }

    @FXML
    protected void guessMove(){
        if(testMoves.get(currentIndex-1).getName().trim().equalsIgnoreCase(nameText.getText().trim())){ // If name is correct, add a point
            correct++;
            nameText.setText("Correct!");
        } else{ // Otherwise, tell the user the correct answer
            nameText.setText("Incorrect. Correct answer was " + testMoves.get(currentIndex-1).getName());
        }
        if(button.getText().equals("Reset")){ // If it is now a reset button, reset
            initialize();
        } else if(currentIndex > 10) { // Else if it is the 10th click, tell the user their score and make it a reset button
            descriptionText.setText("You got " + correct + " out of 10 moves correct. Press Reset to try again!");
            button.setText("Reset");
        } else { // Otherwise, go to next move
            nextMove();
        }
    }
}
