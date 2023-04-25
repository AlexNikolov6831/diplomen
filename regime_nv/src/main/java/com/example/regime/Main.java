package com.example.regime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        DataBase.Connect();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = Main.class.getResource("style.css").toExternalForm(); scene.getStylesheets().add(css);
        stage.setTitle("FoodRegime");
        stage.setScene(scene);
        stage.show();
        SceneChanger.setStage(stage);
    }
    public static void main(String[] args) {
        launch();
    }
}