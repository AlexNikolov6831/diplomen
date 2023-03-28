package com.example.regime;

import java.lang.constant.Constable;
import java.sql.*;

public class User {
    public static String id;
    public static String username;
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    public static void Connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

        stmt = con.createStatement();
    }
    public static void SetUsername(String name){
        username = name;

    }
public static void SetID() throws SQLException {
        Connect();
    rs=stmt.executeQuery("Select * from customer WHERE username='"+username+"'");
    while(rs.next()){
id=rs.getString("idcustomer");
        System.out.println(id);
    }

}

}
