package com.example.regime;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.sql.*;

public class Settings {

    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    public static void Connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

        stmt = con.createStatement();
    }
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





    public void OnButtonClicked(ActionEvent event) throws IOException {
        HelloApplication.SceneChanger("HomePage");
    }

    public void OnButtonClicked3(ActionEvent event) throws SQLException {
        RadioButton rb=(RadioButton)(Group.getSelectedToggle());
        RadioButton rb2=(RadioButton)(Group2.getSelectedToggle());
        String activity=rb2.getText();
        String gender=rb.getText();
        Connect();
        rs = stmt.executeQuery("select * from customer");


    stmt.executeUpdate("update customer set username='"+text_1.getText()+"', password='"+text_2.getText()+"', age='"+text3.getText()+"',height='"+text4.getText()+"',weight='"+text5.getText()+"',gender='"+gender+"',activity='"+activity+"' where username='"+User.username+"'");

    }
}
