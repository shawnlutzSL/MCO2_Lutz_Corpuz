module com.example.mco2_supermarketsimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.mco2_supermarketsimulator to javafx.fxml;
    exports com.example.mco2_supermarketsimulator;
}