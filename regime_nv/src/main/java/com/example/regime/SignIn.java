package com.example.regime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;
public class SignIn {
    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    void onHelloButtonClick(ActionEvent event) throws SQLException, IOException {
        String username = text2.getText();
        String password = text1.getText();
        if(DataBase.signIn(username, password)){
            SceneChanger.set("Home");
        }
    }
   @FXML
   void onHelloButtonClick2(ActionEvent event) throws IOException {
        SceneChanger.set("SignUp");
    }
}