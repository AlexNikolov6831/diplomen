package com.example.regime;

import java.sql.*;

public class Exchange {
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;

    public static void Connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

        stmt = con.createStatement();
    }
    public static void Exchange(){

    }

}
