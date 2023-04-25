package com.example.regime;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBase {
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    public static void Connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejim", "root", "root");

        stmt = con.createStatement();
    }

    public static boolean signIn(String username, String password){
        try{
            rs = stmt.executeQuery("select * from customer");
            while (rs.next()) {
                if(username.equals("2")&&password.equals("2")){
                    SceneChanger.set("Admin");
                }else if(username.equals(rs.getString("username") )) {
                    if (password.equals(rs.getString("password"))) {
                        User.SetUsername(username);
                        User.SetID();
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public static ArrayList<String> getAllowedAll(ArrayList<Boolean> allergies) throws SQLException {
            ArrayList<String> allergiesNames = new ArrayList<String>(
                    Arrays.asList(" alg_fish=0 and", " alg_peanuts=0 and", " alg_treenuts=0 and", " alg_eggs=0 and", " alg_milk=0 and", " alg_wheat=0 and", " alg_corn=0 and", " alg_shellfish=0 and", " vegan=0 and"));
            String sql = "SELECT * from food WHERE";
            for(int i = 0 ; i < allergies.size(); ++i){
                if(allergies.get(i)){
                    sql += allergiesNames.get(i);
                }
            }
            if (sql.charAt(sql.length() - 1) == 'd') {
                sql = sql.substring(0, sql.length() - 3);
            }
            if (sql.length() < 28) {
                sql = sql.substring(0, 18);
            }
            rs = stmt.executeQuery(sql);
            ArrayList<String> allowedAll = new ArrayList<>();
            while (rs.next()) {
                allowedAll.add(rs.getString("food_name"));
            }
            return allowedAll;
        }
    public static void insertAllergy(String allergy) throws SQLException {
        stmt.executeUpdate("insert into allergies (customerfk_id) values ('" + User.id + "')");
        stmt.executeUpdate("UPDATE allergies SET " + allergy + " = TRUE WHERE customerfk_id='" + User.id + "'");
    }
    public static void updateAllergies(ArrayList<Boolean> allergies) throws SQLException {
        ArrayList<String> allergiesNames = new ArrayList<String>(
                Arrays.asList("fish", "peanuts", "treenuts", "eggs", "milk", "wheat", "corn", "shellfish", "vegan"));
        for (int i = 0; i < allergiesNames.size(); i++) {
            stmt.executeUpdate("UPDATE allergies SET " + allergiesNames.get(i) + " = " + allergies.get(i)+ " WHERE customerfk_id='" + User.id + "'");
        }
    }
    public static void updateNewFood(String id, ArrayList<Boolean> allergies) throws SQLException {
        ArrayList<String> allergiesNames = new ArrayList<String>(
                Arrays.asList("alg_fish", "alg_peanuts", "alg_treenuts", "alg_eggs", "alg_milk", "alg_wheat", "alg_corn", "alg_shellfish", "vegan"));
        for (int i = 0; i < allergiesNames.size(); i++) {
            if(allergies.get(i)){
                stmt.executeUpdate("UPDATE food SET " + allergiesNames.get(i) + "=TRUE WHERE idfood='"+id+"'");
            }
        }
    }
    public static ArrayList<ArrayList> getMenu() throws SQLException {
        ArrayList<ArrayList> days = new ArrayList<>();
        ArrayList<ArrayList> dailyMeals = new ArrayList<>();
        ArrayList<String> daysId = new ArrayList<>();
        ArrayList<String> dayNames = new ArrayList<String>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        ArrayList<String> mealNames = new ArrayList<String>(
                Arrays.asList("breakfast", "lunch", "snack", "dinner"));
        rs = stmt.executeQuery("Select * from menu WHERE customer_id=" + User.id);
        rs.next();
        for (int i = 0; i < dayNames.size(); i++) {
            daysId.add(rs.getString(dayNames.get(i)));
        }

        for (int i = 0; i < dayNames.size(); i++) {
            days.add(new ArrayList<String>());
            rs = stmt.executeQuery("Select * from day WHERE idDay=" + daysId.get(i));
            rs.next();
            for (int j = 0; j < mealNames.size(); j++) {
                days.get(i).add(rs.getString(mealNames.get(j)));
            }
        }


        for (int i = 0; i < dayNames.size(); i++) {
            dailyMeals.add(new ArrayList<String>());
            for (int j = 0; j < mealNames.size(); j++) {
                rs = stmt.executeQuery("Select * from meal WHERE idmeal=" + days.get(i).get(j));
                rs.next();
                dailyMeals.get(i).add(rs.getString("food_name")+" - "+rs.getString("portion").substring(0,1));
            }

        }
        return dailyMeals;
    }
    public static String getReply(String reply) throws SQLException {
        rs = stmt.executeQuery("Select * from feedback WHERE customerf2_id=" + User.id);
        rs.next();
        reply = "";
        try {
            reply = rs.getString("admin_response");
        } catch (Exception e){

        }
        return reply;
    }
    public static ArrayList<FeedbackMessage> getFeedbackMessageList() throws SQLException {
        ArrayList<FeedbackMessage> list = new ArrayList<>();
        rs=stmt.executeQuery("Select * from feedback");
        while(rs.next()){

            list.add(new FeedbackMessage(rs.getString("idFeedBack"),rs.getString("feedback"),rs.getString("admin_response")
                    ,rs.getString("customerf2_id")));
        }
        list = getFeedbackMessageListWithNames(list);
        return list;
    }
    private static ArrayList<FeedbackMessage> getFeedbackMessageListWithNames(ArrayList<FeedbackMessage> list){
        try{
            for(int i=0; i<list.size();i++){
                rs=stmt.executeQuery("Select * from customer WHERE idcustomer='"+list.get(i).username+"'");
                rs.next();
                list.get(i).username=rs.getString("username");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }
    public static void updateUser(String username, String password, String age, String height, String weight, String gender, String activity) throws SQLException {
        stmt.executeUpdate("update customer set username='" + username + "', password='" + password + "', age='" + age +
                "',height='" + height + "',weight='" + weight + "',gender='" + gender + "',activity='" + activity +
                "' where username='" + User.username + "'");
    }
    public static void updateFeedback(String response, String id) throws SQLException {
        stmt.executeUpdate("UPDATE feedback SET admin_response = '" + response + "' WHERE idFeedBack = " + id);
    }
    public static void deleteOldMenu(String id) throws SQLException {
        stmt.executeUpdate("DELETE FROM menu WHERE customer_id = " + id);
    }
    public static void insertFeedback(String feedback) throws SQLException {
        stmt.executeUpdate("insert into feedback (feedback,customerf2_id) values ('"+feedback+"','"+User.id+"')");
    }
    public static String insertUser(String activity, String gender, String username, String password, String age, String height, String weight) throws SQLException {
        stmt.executeUpdate("insert into customer (username,password,Age,height,weight,gender,activity) values " +
                "('" + username + "','" + password + "','" + age + "','" + height + "','" + weight + "','" + gender + "','" + activity + "')");
        rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
        rs.next();
        return rs.getString("last_insert_id()");
    }
    public static String getUserID(String username) throws SQLException {
        rs = stmt.executeQuery("SELECT idcustomer from customer WHERE username='" + username + "'");
        rs.next();
        return rs.getString("idcustomer");
    }
    public static String insertMeal(String food_name,String portion) throws SQLException {
        stmt.executeUpdate("INSERT INTO meal (food_name,portion) values ('" + food_name + "','" + portion + "')");
        rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
        rs.next();
        return rs.getString("last_insert_id()");
    }
    public static String insertNewFood(String foodname, String protein, String carbohydrates, String fats, String calories, ArrayList<Boolean> allergies) throws SQLException {
        stmt.executeUpdate("insert into food (food_name,protein,carbohydrates,fat,calories)" +
                " values ('"+foodname+"','"+protein+"','"+carbohydrates+"','"+fats+"','"+calories+"')");
        rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
        rs.next();
        String foodid = rs.getString("last_insert_id()");
        updateNewFood(foodid, allergies);
        return foodid;
    }
    public static String insertFood(String breakfast,String lunch,String snack,String dinner) throws SQLException {
        stmt.executeUpdate("INSERT INTO day (breakfast,lunch,snack,dinner) values ('" + breakfast + "','" + lunch + "','" + snack + "','" + dinner + "')");
        rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
        rs.next();
        return rs.getString("last_insert_id()");
    }
    public static void insertMenu(String monday,String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday,String username) throws SQLException {
        stmt.executeUpdate("INSERT INTO menu(Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday,customer_id) values ('" + monday + "','" + tuesday + "','" + wednesday + "','"
                + thursday + "','" + friday + "','" + saturday + "','" + sunday + "','" + User.id + "')");
    }
    public static Double getAge(String id) throws SQLException {
        rs = stmt.executeQuery("SELECT age from customer WHERE idcustomer='" + id + "'");
        rs.next();
        return Double.parseDouble(rs.getString("age"));
    }
    public static Double getHeight(String id) throws SQLException {
        rs = stmt.executeQuery("SELECT height from customer WHERE idcustomer='" + id + "'");
        rs.next();
        return Double.parseDouble(rs.getString("height"));
    }
    public static Double getWeight(String id) throws SQLException {
        rs = stmt.executeQuery("SELECT weight from customer WHERE idcustomer='" + id + "'");
        rs.next();
        return Double.parseDouble(rs.getString("weight"));
    }
    public static Double getCalories(String foodName) throws SQLException {
        rs = stmt.executeQuery("SELECT calories FROM food WHERE food_name='" + foodName + "'");
        rs.next();
        return Double.parseDouble(rs.getString("calories"));
    }
    public static void deleteFeedbackMessage(String id) throws SQLException {
        String j=getUserID(id);
        stmt.executeUpdate("DELETE FROM feedback WHERE customerf2_id=" + j);
    }

}
