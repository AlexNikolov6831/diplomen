package com.example.regime;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SceneChanger {
    public static Scene scene;
    public static String css;
    private static Stage current_stage;

    public static Stage getStage() {
        return current_stage;
    }

    public static void setStage(Stage s) {
        current_stage = s;
    }

    public static void set (String change) throws IOException {
        switch (change) {
            case "Home":
                FXMLLoader HomePage = new FXMLLoader(Main.class.getResource("Home.fxml"));
                scene = new Scene(HomePage.load());
                getStage().setScene(scene);
                css = Main.class.getResource("style.css").toExternalForm();
                scene.getStylesheets().add(css);
                break;
            case "SignUp":
                FXMLLoader SignUp = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
                scene = new Scene(SignUp.load());
                getStage().setScene(scene);
                css = Main.class.getResource("style.css").toExternalForm();
                scene.getStylesheets().add(css);

                break;
            case "SignIn":
                FXMLLoader LogIn = new FXMLLoader(Main.class.getResource("SignIn.fxml"));
                scene = new Scene(LogIn.load());
                getStage().setScene(scene);
                css = Main.class.getResource("style.css").toExternalForm();
                scene.getStylesheets().add(css);
                break;
            case "Admin":
                FXMLLoader Admin = new FXMLLoader(Main.class.getResource("Admin.fxml"));
                scene = new Scene(Admin.load());
                getStage().setScene(scene);
                css = Main.class.getResource("style.css").toExternalForm();
                scene.getStylesheets().add(css);
                break;
            case "Settings":
                FXMLLoader Settings = new FXMLLoader(Main.class.getResource("Settings.fxml"));
                getStage().setScene(new Scene(Settings.load()));
                break;

        }

    }
}
