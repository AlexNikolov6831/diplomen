package com.example.regime;

import java.sql.*;
public class User {
    public static String id;
    public static String username;
    public static void SetUsername(String name){
        username = name;
    }
    public static void SetID() throws SQLException {
        id = DataBase.getUserID(username);
    }

}
