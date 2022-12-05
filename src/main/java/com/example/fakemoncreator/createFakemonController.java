package com.example.fakemoncreator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class createFakemonController {

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
    private ComboBox<Move> moveComboBox;
    @FXML
    private TextArea moveOutput;

    @FXML
    private TextArea descriptionText;

    @FXML
    private Label output1;
    @FXML
    private Label output2;

    private final List<Move> fakemonMoveList = new ArrayList<>();

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

        primaryTypeBox.getItems().addAll(types);
        secondaryTypeBox.getItems().addAll(types);
        secondaryTypeBox.getItems().addAll("None");
        moveComboBox.getItems().addAll(MoveSet.getRawMoveSet());

        abilityComboBox.getSelectionModel().selectFirst();
        primaryTypeBox.getSelectionModel().selectFirst();
        secondaryTypeBox.getSelectionModel().selectFirst();
        moveComboBox.getSelectionModel().selectFirst();
    }

    //Add a move to the list that will be added to the finished Fakemon
    @FXML
    protected void addMove(){
        if(!(fakemonMoveList.contains(moveComboBox.getValue()))) { //If move isn't already in the list
            fakemonMoveList.add(moveComboBox.getValue());
        }
        moveOutput.setText("");
        for (Move m: fakemonMoveList){
            moveOutput.appendText(m.getName() + "\n");
        }
    }

    @FXML
    protected void createFakemon(){
        //Initialize all values
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

        try{
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

            if(primaryType.equalsIgnoreCase(secondaryType)){ //If primary and secondary types are the same, inform user
                output1.setText("Primary and secondary");
                output2.setText("types cannot be the same.");
            } else if(FakemonList.getFakemonListInstance().alreadyInList(name)){ //If name is in use, inform user
                output1.setText("Name is already in use.");
                output2.setText("Please select a different name.");
            } else if((name.trim().equals("")) || description.trim().equals("")){
                output1.setText("Please input something");
                output2.setText("into the Name and Description boxes");
            } else if (description.contains("!)@(#*$&%^")){
                output1.setText("Due to how data is stored,");
                output2.setText("Your description can't contain !)@(#*$&%^");
            }else { //Otherwise, create Fakemon
                FakemonList.getFakemonListInstance().addFakemon(new Fakemon(name, primaryType, secondaryType, ability, hp, atk, def, spAtk, spDef, spe, description));
                if(fakemonMoveList.size()>0){ // Add moves (if any)
                    FakemonList.getFakemonListInstance().getFakemon(name).addMoves(fakemonMoveList);
                }

                //Close the popup
                Stage stage = (Stage) output1.getScene().getWindow();
                stage.close();
            }

        } catch(NumberFormatException e){ // If stat fields aren't numbers, tell user
            output1.setText("");
            output2.setText("Invalid stats. Please enter only numbers.");
        }

        //Save changes
        Controller.saveAll();
    }
}
