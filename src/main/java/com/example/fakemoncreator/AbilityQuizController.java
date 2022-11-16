package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Stack;

// Utilizes Stack data structure

public class AbilityQuizController {

    @FXML
    private TextArea descriptionText;
    @FXML
    private TextField nameText;
    @FXML
    private Button button;

    private Stack<Ability> testAbilities = new Stack<>();
    private int correct;

    Ability current;

    Ability[] allAbilities = AbilitySet.getRawAbilitySet().toArray(new Ability[0]);

    // Reset variables and fill Stack
    public void initialize(){
        correct = 0;
        button.setText("Guess Ability");
        for(int i=0; i<10; i++){
            int random = (int)(Math.random()*AbilitySet.getRawAbilitySet().size());
            testAbilities.add(allAbilities[random]);
        }
        nextAbility();
    }

    // Pop ability and set text respectively
    private void nextAbility(){
        current = testAbilities.pop();
        descriptionText.setText(current.getEffect());
    }

    @FXML
    protected void guessAbility(){
        if(current.getName().trim().equalsIgnoreCase(nameText.getText().trim())){ // If name is correct, add a point
            correct++;
            nameText.setText("Correct!");
        } else{ // Otherwise, tell the user the correct answer
            nameText.setText("Incorrect. Correct answer was " + current.getName());
        }
        if(button.getText().equals("Reset")){ // If it is now a reset button, reset
            initialize();
        } else if(testAbilities.isEmpty()) { // Else if it is the 10th click, tell the user their score and make it a reset button
            descriptionText.setText("You got " + correct + " out of 10 abilities correct. Press Reset to try again!");
            button.setText("Reset");
        } else { // Otherwise, go to next ability
            nextAbility();
        }
    }
}
