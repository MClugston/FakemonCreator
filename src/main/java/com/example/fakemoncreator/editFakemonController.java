package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class editFakemonController {

    @FXML
    private ComboBox<Fakemon> fakemonBox;

    @FXML
    private TextField fakemonName;

    @FXML
    private ComboBox<Ability> abilityComboBox;

    @FXML
    private ComboBox<String> primaryTypeBox;
    @FXML
    private ComboBox<String> secondaryTypeBox;

    @FXML
    private TextField hpText;
    @FXML
    private TextField atkText;
    @FXML
    private TextField defText;
    @FXML
    private TextField spAtkText;
    @FXML
    private TextField spDefText;
    @FXML
    private TextField speText;

    @FXML
    private TextArea descriptionText;

    @FXML
    private Label output;

    // Setup all default values
    public void initialize(){
        abilityComboBox.getItems().addAll(AbilitySet.getRawAbilitySet());

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

        //Fill ComboBoxes
        fakemonBox.getItems().addAll(FakemonList.getMonList());
        primaryTypeBox.getItems().addAll(types);
        secondaryTypeBox.getItems().addAll(types);
        secondaryTypeBox.getItems().addAll("None");

        // Default ComboBoxes
        fakemonBox.getSelectionModel().selectFirst();
        abilityComboBox.getSelectionModel().selectFirst();
        primaryTypeBox.getSelectionModel().selectFirst();
        secondaryTypeBox.getSelectionModel().selectFirst();

        // Check if the user has created any fakemon
        if(fakemonBox.getItems().size()>0){
            fillFields();
        } else{
            descriptionText.setText("You have not created any fakemon. No operations in this tab will function. Please redirect " +
                    "yourself to the Create A Fakemon button on the main menu.");
        }
    }

    // Auto-fill all fields when a fakemon is selected from the ComboBox
    @FXML
    protected void fillFields(){
        if(fakemonBox.getValue()!=null){
            Fakemon f = fakemonBox.getValue();

            fakemonName.setText(f.getName());
            abilityComboBox.getSelectionModel().select(f.getAbility());

            primaryTypeBox.getSelectionModel().select(f.getPrimaryType());
            secondaryTypeBox.getSelectionModel().select(f.getSecondaryType());

            hpText.setText(f.getHp() + "");
            speText.setText(f.getSpeed() + "");
            atkText.setText(f.getAttack() + "");
            spAtkText.setText(f.getSpecialAttack() + "");
            defText.setText(f.getDefense() + "");
            spDefText.setText(f.getSpecialDefense() + "");

            descriptionText.setText(f.getDescription());
        }
    }

    // Very similar to createFakemon, but with some additional checks
    @FXML
    protected void editFakemon() {
        if (fakemonBox.getValue() != null) {
            //Initialize all values
            String oldName = fakemonBox.getValue().getName();
            FakemonList fakemonList = FakemonList.getFakemonListInstance();

            String name;
            Ability ability;
            String primaryType;
            String secondaryType;
            int hp;
            int atk;
            int def;
            int spAtk;
            int spDef;
            int spe;
            String description;

            try {
                //Get all values from fields
                hp = Integer.parseInt(hpText.getText());
                atk = Integer.parseInt(atkText.getText());
                def = Integer.parseInt(defText.getText());
                spAtk = Integer.parseInt(spAtkText.getText());
                spDef = Integer.parseInt(spDefText.getText());
                spe = Integer.parseInt(speText.getText());

                name = fakemonName.getText();
                ability = abilityComboBox.getValue();
                primaryType = primaryTypeBox.getValue();
                secondaryType = secondaryTypeBox.getValue();
                description = descriptionText.getText();

                if (primaryType.equalsIgnoreCase(secondaryType)) { //If primary and secondary types are the same, inform user
                    output.setText("Primary and secondary types cannot be the same.");
                } else if ((fakemonList.alreadyInList(name)) && !(oldName.equals(name))) { //If name is in use, inform user
                    output.setText("Name is already in use. Please select a different name.");
                } else if ((name.trim().equals("")) || description.trim().equals("")) { // If name or description is blank
                    output.setText("Please input something into the Name and Description boxes");
                } else if (description.contains("!)@(#*$&%^")){
                    output.setText("Your description can't contain !)@(#*$&%^");
                } else if(abilityComboBox.getValue()==null){
                    output.setText("Please select an ability.");
                } else { //Otherwise, create Fakemon
                    // Get the move list from old fakemon
                    MyLinkedList<Move> moveList = new MyLinkedList<>();
                    if(fakemonList.getFakemon(oldName).getMoveList().size()>0){
                        moveList.addAll(fakemonList.getFakemon(oldName).getMoveList());
                    }
                    // Delete the old fakemon, make a new one, add moves if necessary
                    fakemonList.removeFakemon(oldName);
                    fakemonList.addFakemon(new Fakemon(name, primaryType, secondaryType, ability, hp, atk, def, spAtk, spDef, spe, description));
                    if(moveList.size()>0){
                        FakemonList.getFakemonListInstance().getFakemon(name).addMoves(moveList);
                    }
                    //Save changes
                    SaveFakemon.saveFakemon();

                    //Close the popup
                    Stage stage = (Stage) output.getScene().getWindow();
                    stage.close();
                }

            } catch (NumberFormatException e) { // If stat fields aren't numbers, tell user
                output.setText("Invalid stats. Please enter only numbers.");
            }
        }
    }
}
