package com.example.regime;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static com.example.regime.User.username;


public class HomePage {

    @FXML
    private TextArea txt;
    @FXML
    private Label label;
    @FXML
    private TextArea txt1;
    @FXML
    private MenuButton Menu;
    public static ResultSet rs;
    public static Connection con;
    public static Statement stmt;
    public String reply;
    public ArrayList<FeedbackMessage> list=new ArrayList<FeedbackMessage>();
    public static void Connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

        stmt = con.createStatement();
    }

    public void OnButtonClicked(ActionEvent event) throws IOException, SQLException {
        Connect();
        stmt.executeUpdate("insert into feedback (feedback,customerf2_id) values ('"+txt1.getText()+"','"+User.id+"')");
    }

    public void OnButtonClicked4(ActionEvent event) throws IOException {
        HelloApplication.SceneChanger("Settings");

    }

    public void OnButtonClicked2(ActionEvent event) throws IOException {
        HelloApplication.SceneChanger("LogIn");
    }

    public void OnButtonClicked3(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    public void initialize() throws SQLException {
        Menu.setText(User.username);
        init();
        AdminReply();
    }
    public void AdminReply(){
        if(reply==null||reply.length()==0) {
            txt.setVisible(false);
            label.setVisible(false);
        }
    }

    public void init() throws SQLException {
        Connect();
        System.out.println(User.id+"sdad");
        rs=stmt.executeQuery("Select * from feedback WHERE customerf2_id="+User.id);
        while(rs.next()){

            reply=rs.getString("admin_response");
            txt.setText(reply);
        }



    }
}

