module com.example.maddexjavafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.maddexjavafinal to javafx.fxml;
    exports com.example.maddexjavafinal;
}