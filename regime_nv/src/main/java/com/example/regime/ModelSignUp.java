package com.example.regime;

import javafx.scene.chart.PieChart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class ModelSignUp {
    public static String activity;
    public static String gender;

    public static String username;
    public static ArrayList<Boolean> allergies;

    public static double BMR=0;
    public static Double calculateBMR() throws SQLException {
        double weight=DataBase.getWeight(User.id);
        double height=DataBase.getHeight(User.id);
        double age=DataBase.getAge(User.id);
        switch(gender){
            case "male":
                BMR = 10 * weight + 6.25 * height - 5 * age + 5;
                break;
            case "female":
                BMR = 10 * weight + 6.25 * height - 5 * age - 161;
                break;
        }
        switch(activity){
            case "none":
                BMR *= 1.2;
                break;
            case "1-2 times a week":
                BMR *= 1.4;
                break;
            case "4 or more times a week":
                BMR *= 1.5;
                break;
        }
        return BMR;
    }
    public static void createProfile(String a, String g, String u, String password, String age, String height, String weight, ArrayList<Boolean> all) throws SQLException {
        activity=a;
        gender=g;
        username=u;
        allergies = all;

        User.username = username;
        User.id = DataBase.insertUser(activity, gender, username, password, age, height, weight);

        ArrayList<String> allergiesNames = new ArrayList<String>(
                Arrays.asList("fish", "peanuts", "treenuts", "eggs", "milk", "wheat", "corn", "shellfish", "vegan"));

        for(int i = 0 ; i < allergies.size(); ++i){
            if(allergies.get(i)){
                DataBase.insertAllergy(allergiesNames.get(i));
            }
        }

        createRegime();
    }
    public static void createRegime() throws SQLException {
        calculateBMR();
        AllergyFilter();
    }
    public static void recreateRegime(ArrayList<Boolean> al,String g,String a) throws SQLException {
        username = User.username;
        gender=g;
        activity=a;
        DataBase.deleteOldMenu(User.id);
        calculateBMR();
        allergies=al;
        AllergyFilter();
    }
    public static void AllergyFilter() throws SQLException {
        ArrayList<String> allowedAll = DataBase.getAllowedAll(allergies);
        Random r = new Random();
        ArrayList<ArrayList> days = new ArrayList<>();
        ArrayList<ArrayList> allowedFoods = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(new ArrayList<String>());
            allowedFoods.add(new ArrayList<String>());
            allowedFoods.get(i).addAll(allowedAll);
            while (days.get(i).size() < 4) {
                String food = (String) allowedFoods.get(i).get(r.nextInt(allowedFoods.get(i).size()));
                days.get(i).add(food);
                allowedFoods.get(i).remove(food);
            }
        }
        ArrayList<ArrayList> dayCals=new ArrayList<>();
        for(int j=0;j<7;j++) {
            dayCals.add(new ArrayList<Double>());
            for (int i = 0; i < 4; i++) {
                dayCals.get(j).add(DataBase.getCalories((String) days.get(j).get(i)));
            }
        }
        ArrayList<ArrayList> dayPor=new ArrayList<>();
        for(int i=0;i<7;i++){
            dayPor.add(new ArrayList<String>());
            dayPor.get(i).add(Math.ceil((BMR * 0.2) /(Double) dayCals.get(i).get(0)));
            dayPor.get(i).add(Math.ceil((BMR * 0.3) /(Double) dayCals.get(i).get(1)));
            dayPor.get(i).add(Math.ceil((BMR * 0.1) /(Double) dayCals.get(i).get(2)));
            dayPor.get(i).add(Math.ceil((BMR * 0.4) /(Double) dayCals.get(i).get(3)));
        }
        ArrayList<ArrayList> mealsid = new ArrayList<>();
        ArrayList<String> daysid = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            mealsid.add(new ArrayList());
            for (int j = 0; j < days.get(i).size(); j++) {
                    mealsid.get(i).add(DataBase.insertMeal(String.valueOf(days.get(i).get(j)), String.valueOf(dayPor.get(i).get(j))));
            }
            daysid.add(DataBase.insertFood((String) mealsid.get(i).get(0),(String) mealsid.get(i).get(1),(String) mealsid.get(i).get(2),(String) mealsid.get(i).get(3)));
        }
        DataBase.insertMenu(daysid.get(0),daysid.get(1),daysid.get(2),daysid.get(3),daysid.get(4),daysid.get(5),daysid.get(6),username);
    }

}
