module com.example.regime {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.regime to javafx.fxml;
    exports com.example.regime;
}