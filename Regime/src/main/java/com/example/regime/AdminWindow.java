package com.example.regime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class AdminWindow {
    public ArrayList<FeedbackMessage> list=new ArrayList<FeedbackMessage>();
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
public static void Connect() throws SQLException {
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

    stmt = con.createStatement();
}public static int currentfeedback=0;

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
    public void initialize() throws SQLException {
        init();
        FeedbackChanger();
    }
    public void init() throws SQLException {
        Connect();
        rs=stmt.executeQuery("Select * from feedback");
        while(rs.next()){

            list.add(new FeedbackMessage(rs.getString("idFeedBack"),rs.getString("feedback"),rs.getString("admin_response"),rs.getString("customerf2_id")));
        }
        try{
            for(int i=0; i<list.size();i++){
                rs=stmt.executeQuery("Select * from customer WHERE idcustomer='"+list.get(i).username+"'");
                rs.next();
                list.get(i).username=rs.getString("username");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }

    }
    public void OnButtonClicked(ActionEvent event) throws SQLException {
        String foodname = text1.getText();
        String protein = text2.getText();
        String carbohydrates = text3.getText();
        String fats = text4.getText();
        String calories = text5.getText();

        Connect();

        stmt.executeUpdate("insert into food (food_name,protein,carbohydrates,fat,calories) values ('"+foodname+"','"+protein+"','"+carbohydrates+"','"+fats+"','"+calories+"')");

        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");

    }

    public void OnButtonClicked2(ActionEvent event) throws IOException {
        HelloApplication.SceneChanger("LogIn");

    }

    public void OnButtonClicked3(ActionEvent event) {
    }

    public void OnButtonClicked4(ActionEvent event) throws SQLException {
      Connect();
        stmt.executeUpdate("UPDATE feedback SET admin_response = '"+txt.getText()+"' WHERE idFeedBack = "+ list.get(currentfeedback).id);

    }



    public void OnButtonClicked6(ActionEvent event) {
        currentfeedback++;
        //da ne moje da otidesh na nevuzmojen index <list lenght
FeedbackChanger();
    }
public void FeedbackChanger(){
        label.setText(list.get(currentfeedback).username);
        txt.setText(list.get(currentfeedback).feedback);
}
}
