module com.example.maddexjavafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.google.gson;

    exports com.example.maddexjavafinal;
    exports com.example.maddexjavafinal.tabs;
    opens com.example.maddexjavafinal.tabs to javafx.fxml;

}