package com.example.regime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Home {
    @FXML
    private ListView<String> list2;

    @FXML
    private ListView<String> list3;

    @FXML
    private ListView<String> list4;

    @FXML
    private ListView<String> list5;

    @FXML
    private ListView<String> list6;

    @FXML
    private ListView<String> list7;

    @FXML
    private ListView<String> list1;
    @FXML
    private TextArea txt;
    @FXML
    private Label label;
    @FXML
    private TextArea txt1;
    @FXML
    private MenuButton Menu;
    public String reply;
    public ArrayList<FeedbackMessage> list=new ArrayList<FeedbackMessage>();

    public void OnButtonClicked(ActionEvent event) throws IOException, SQLException {
        DataBase.insertFeedback(txt1.getText());
        txt1.setText("");
    }

    public void OnButtonClicked4(ActionEvent event) throws IOException {
        SceneChanger.set("Settings");

    }

    public void OnButtonClicked2(ActionEvent event) throws IOException {
        SceneChanger.set("SignIn");
    }

    public void OnButtonClicked3(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void initialize() throws SQLException {
        Menu.setText(User.username);
        LoadMenu();
        AdminReply();
    }
    public void AdminReply() throws SQLException {
        reply=DataBase.getReply(reply);
        if(reply==null||reply.length()==0) {
            txt.setVisible(false);
            label.setVisible(false);
        }
    }

    public void LoadMenu() throws SQLException {
        txt.setText(DataBase.getReply(reply));
        ArrayList<ListView> lists = new ArrayList<>(List.of(list1, list2, list3, list4, list5, list6, list7));
        ArrayList<ArrayList> menu = DataBase.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            for (int j = 0; j < menu.get(i).size(); j++) {
                lists.get(i).getItems().add(menu.get(i).get(j));
            }
        }

    }

}

