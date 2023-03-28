package com.example.regime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    public static void Connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

        stmt = con.createStatement();
    }
    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    void onHelloButtonClick(ActionEvent event) throws SQLException {

        String username = text2.getText();
        String password = text1.getText();


        Connect();

        try{
            rs = stmt.executeQuery("select * from customer");
            while (rs.next()) {
                if(username.equals("2")&&password.equals("2")){
                    HelloApplication.SceneChanger("AdminWindow");
                }else if(username.equals(rs.getString("username") )) {
                    if (password.equals(rs.getString("password"))) {
                        User.SetUsername(username);
                        User.SetID();
                        HelloApplication.SceneChanger("HomePage");
                    }
                }
            }System.out.println("Greshka");

        } catch (IOException e) {
        }

    }

   @FXML
   void onHelloButtonClick2(ActionEvent event) throws IOException {
        HelloApplication.SceneChanger("SignUp");
    }

}