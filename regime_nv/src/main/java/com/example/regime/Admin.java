package com.example.regime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Admin {

    public ArrayList<FeedbackMessage> list=new ArrayList<FeedbackMessage>();
    public static int currentfeedback=0;

    @FXML
    private TextArea adtxt;

    @FXML
    private Label label;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private TextField text5;

    @FXML
    private TextField text6;

    @FXML
    private TextArea txt;
    @FXML
    private javafx.scene.control.MenuButton MenuButton;



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
    private CheckMenuItem vegan;

    @FXML
    public void initialize() throws SQLException {
        init();
        FeedbackChanger();
    }
    public void init() throws SQLException {

        list = DataBase.getFeedbackMessageList();

    }
    public void OnButtonClicked(ActionEvent event) throws SQLException {
        String foodname = text1.getText();
        String protein = text2.getText();
        String carbohydrates = text3.getText();
        String fats = text4.getText();
        String calories = text5.getText();

        ArrayList<Boolean> allergies = new ArrayList<Boolean>(
                Arrays.asList(ch1.isSelected(), ch2.isSelected(), ch3.isSelected(), ch4.isSelected(),
                        ch5.isSelected(), ch6.isSelected(), ch8.isSelected(), ch8.isSelected(), vegan.isSelected()));
        DataBase.insertNewFood(foodname, protein, carbohydrates, fats, calories, allergies);

        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");

    }

    public void OnButtonClicked2(ActionEvent event) throws IOException {
        SceneChanger.set("SignIn");
    }

    public void OnButtonClicked3(ActionEvent event) throws SQLException {
        DataBase.deleteFeedbackMessage(list.get(currentfeedback).username);
    }

    public void OnButtonClicked4(ActionEvent event) throws SQLException {
        DataBase.updateFeedback(adtxt.getText(), list.get(currentfeedback).id);

    }


    public void OnButtonClicked6(ActionEvent event) {
        if(currentfeedback+1 < list.size()){
            currentfeedback++;
        }
        FeedbackChanger();
    }

    public void FeedbackChanger(){
            label.setText(list.get(currentfeedback).username);
            txt.setText(list.get(currentfeedback).feedback);
    }
}
