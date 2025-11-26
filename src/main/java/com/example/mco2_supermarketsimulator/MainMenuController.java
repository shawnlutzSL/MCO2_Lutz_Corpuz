package com.example.mco2_supermarketsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class MainMenuController {
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private Button playButton;
    private Main mainApp;

    /**
     * Sets the Main instance for reference.
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * This is triggered upon initialization, trying to start the game via the play button.
     */
    @FXML
    private void initialize() {
        playButton.setOnAction(_ -> {
            try {
                startGame();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * This is correlated to actually starting the simulation,
     * it is to check if the name and age of the shopper are valid first.
     */
    private void startGame() throws Exception {
        String name = nameField.getText();
        String ageStr = ageField.getText();
        int age;

        if (name.isEmpty()) {
            showAlert("Please enter your name.");
            return;
        }

        try {
            age = Integer.parseInt(ageStr);
            if (age < 18)
                showAlert("Remember, no alcohol for underage shoppers (Ages below 18)!");
            if (age >= 60)
                showAlert("You're in luck, this supermarket supports senior citizen's discount for senior shoppers!");
        } catch (NumberFormatException ex) {
            showAlert("Please enter a valid age.");
            return;
        }

        mainApp.startSimulation(name, age);
    }

    /**
     * Just a utility to show alerts.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
