package com.example.regime;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SignUp {
    @FXML
    private MenuButton MenuButton;
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;

public static String username;

    public static void Connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

        stmt = con.createStatement();
    }
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
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;

    @FXML
    private RadioButton rb5;
    @FXML
    private TextField text_1;

    @FXML
    private TextField text_2;

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private TextField text5;
    @FXML
    private ToggleGroup Group;
    @FXML
    private ToggleGroup Group2;

public static int alg_fish=0,alg_peanuts=0,alg_treenuts=0,alg_eggs=0,alg_milk=0,alg_wheat=0,alg_corn=0,alg_shellfish=0;


    @FXML
    void OnButtonClicked3(ActionEvent e) throws SQLException {
        Connect();
        RadioButton rb=(RadioButton)(Group.getSelectedToggle());
        RadioButton rb2=(RadioButton)(Group2.getSelectedToggle());
        String activity=rb2.getText();
        String gender=rb.getText();
        username = text_1.getText();
        String password = text_2.getText();
        String Age = text3.getText();
        String height = text4.getText();
        String weight = text5.getText();
        String id="";

        stmt.executeUpdate("insert into customer (username,password,Age,height,weight,gender,activity) values ('"+username+"','"+password+"','"+Age+"','"+height+"','"+weight+"','"+gender+"','"+activity+"')");

        String i=getUserID(id);
        System.out.println(i);
        if(ch1.isSelected()){
            System.out.println("Da");

            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET fish=TRUE WHERE customerfk_id='"+i+"'");
alg_fish=1;
        }
        if(ch2.isSelected()){
            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET peanuts=TRUE WHERE customerfk_id='"+i+"'");
            alg_peanuts=1;
        }
        if(ch3.isSelected()){
            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET treenuts=TRUE WHERE customerfk_id='"+i+"'");
            alg_treenuts=1;
        }
        if(ch4.isSelected()){
            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET eggs=TRUE WHERE customerfk_id='"+i+"'");
            alg_eggs=1;
        }
        if(ch5.isSelected()){
            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET milk=TRUE WHERE customerfk_id='"+i+"'");
            alg_milk=1;
        }
        if(ch6.isSelected()){
            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET wheat=TRUE WHERE customerfk_id='"+i+"'");
            alg_wheat=1;
        }
        if(ch7.isSelected()){
            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET corn=TRUE WHERE customerfk_id='"+i+"'");
            alg_corn=1;
        }
        if(ch8.isSelected()){
            stmt.executeUpdate("insert into allergies (customerfk_id) values ('"+i+"')");
            stmt.executeUpdate("UPDATE allergies SET shellfish=TRUE WHERE customerfk_id='"+i+"'");
            alg_shellfish=1;
        }

        FoodRejime();
    }


    public String getUserID(String id) throws SQLException {
        Connect();
        rs = stmt.executeQuery("SELECT idcustomer from customer WHERE username='" + username + "'");
        while (rs.next()) {
            id = rs.getString("idcustomer");

        }
        return id;
    }
    public void OnButtonClicked(ActionEvent event) throws IOException {
        HelloApplication.SceneChanger("LogIn");
    }
    public Double BMR(double weight, double height, double age){
        double BMR=0;
        if(rb1.isSelected()) {
            BMR = 10 * weight + 6.25 * height - 5 * age + 5;
        }else if(rb2.isSelected()){
            BMR = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        if (rb3.isSelected()) {
            BMR *= 1.2;
        } else if (rb4.isSelected()) {
            BMR *= 1.4;
        } else if (rb5.isSelected()) {
            BMR *= 1.5;
        }

        return BMR;

    }
public void FoodRejime() throws SQLException {
        Connect();
    rs = stmt.executeQuery("SELECT age,height,weight from customer WHERE username='" + username + "'");
    while (rs.next()) {
        BMR(Double.valueOf(rs.getString("weight")),Double.valueOf(rs.getString("height")),Double.valueOf(rs.getString("age")));
        System.out.println(BMR(Double.valueOf(rs.getString("weight")),Double.valueOf(rs.getString("height")),Double.valueOf(rs.getString("age"))));
    }
AllergyFilter();
}
public void AllergyFilter() throws SQLException {
        Connect();
        String  a= "SELECT * from food WHERE";
    if(alg_fish == 1){
        a+=" alg_fish=0 and";
    }
    if(alg_peanuts == 1){
        a+=" alg_peanuts=0 and";
    }
    if(alg_treenuts == 1){
        a+=" alg_treenuts=0 and";
    }
    if(alg_eggs == 1){
        a+=" alg_eggs=0 and";
    }
    if(alg_milk == 1){
        a+=" alg_milk=0 and";
    }
    if(alg_wheat == 1){
        a+=" alg_wheat=0 and";
    }
    if(alg_corn == 1){
        a+=" alg_corn=0 and";
    }
    if(alg_shellfish == 1){
        a+=" alg_shellfish=0 and";
    }
    if(a.charAt(a.length()-1)== 'd'){
        a=a.substring(0,a.length()-3);
    }
    if(a.length()<28){
        a=a.substring(0,18);
    }
    System.out.println(a);
    rs=stmt.executeQuery(a);
    ArrayList <String>allowedfoods= new ArrayList<>();
    while(rs.next()){
        allowedfoods.add(rs.getString("food_name"));

    }
    for(int i=0;i<allowedfoods.size();i++){
        System.out.println(allowedfoods.get(i));
    }
}


}




