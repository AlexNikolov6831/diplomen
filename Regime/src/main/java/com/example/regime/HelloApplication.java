package com.example.regime;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage current_stage;
    public static Stage getStage(){

        return current_stage;
    }
    public static void SceneChanger(String change)throws IOException{
        System.out.println(change);
        switch (change){
            case "HomePage":
                FXMLLoader HomePage = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
                HelloApplication.getStage().setScene(new Scene(HomePage.load()));
                break;
            case "SignUp":
                FXMLLoader SignUp = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
                HelloApplication.getStage().setScene(new Scene(SignUp.load()));
                break;
            case "LogIn":
                FXMLLoader LogIn= new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                HelloApplication.getStage().setScene(new Scene(LogIn.load()));
                break;
            case "AdminWindow":
                FXMLLoader AdminWindow= new FXMLLoader(HelloApplication.class.getResource("AdminWindow.fxml"));
                HelloApplication.getStage().setScene(new Scene(AdminWindow.load()));
                break;
            case "FeedBack":
                FXMLLoader FeedBack= new FXMLLoader(HelloApplication.class.getResource("FeedBack.fxml"));
                HelloApplication.getStage().setScene(new Scene(FeedBack.load()));
                break;
                case "Settings":
                    FXMLLoader Settings= new FXMLLoader(HelloApplication.class.getResource("Settings.fxml"));
                HelloApplication.getStage().setScene(new Scene(Settings.load()));
                break;
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        current_stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        current_stage.setTitle("FoodRegime");
        current_stage.setScene(scene);
        current_stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}