package com.example.regime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Settings {
    @FXML
    private ComboBox<String> box;

    @FXML
    private ToggleGroup Group;

    @FXML
    private ToggleGroup Group2;

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private TextField text5;

    @FXML
    private TextField text_1;

    @FXML
    private TextField text_2;
    @FXML
    private CheckMenuItem ch1;

    @FXML
    private CheckMenuItem ch2;

    @FXML
    private CheckMenuItem ch3;

    @FXML
    private CheckMenuItem ch4;

    @FXML
    private CheckMenuItem ch5;

    @FXML
    private CheckMenuItem ch6;

    @FXML
    private CheckMenuItem ch7;

    @FXML
    private CheckMenuItem ch8;

    @FXML
    private CheckBox checkbox;


    public void OnButtonClicked(ActionEvent event) throws IOException {
        SceneChanger.set("Home");
    }

    public void OnButtonClicked3(ActionEvent event) throws SQLException {
        RadioButton rb = (RadioButton) (Group.getSelectedToggle());
        RadioButton rb2 = (RadioButton) (Group2.getSelectedToggle());
        String activity = rb2.getText();
        String gender = rb.getText();
        ArrayList<Boolean> allergies = new ArrayList<Boolean>(
                Arrays.asList(ch1.isSelected(), ch2.isSelected(), ch3.isSelected(), ch4.isSelected(),
                        ch5.isSelected(), ch6.isSelected(), ch8.isSelected(), ch8.isSelected(), checkbox.isSelected()));
        DataBase.updateAllergies(allergies);
        DataBase.updateUser(text_1.getText(), text_2.getText(), text3.getText(), text4.getText(),  text5.getText(), gender ,  activity);

        ModelSignUp.recreateRegime(allergies,gender,activity);
        User.username=text_1.getText();
    }
}


