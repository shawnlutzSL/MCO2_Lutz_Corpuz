package com.example.mco2_supermarketsimulator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Controller for handling all service interactions.
 */
public class ServiceController {

    /**
     * This is just a continuation from the interact method of service.
     */
    public void interact(Service service, Shopper shopper, Supermarket market) {
        System.out.println("Thank you for triggering me, AKA: " + service.getType());

        switch (service.getType()) {
            case 'C' -> handleBasket(shopper);
            case 'D' -> handleCart(shopper);
            case 'E' -> handleProductSearch(market);
            case 'F' -> handleStairs(market);
            case 'G' -> handleCheckout(shopper);
            case 'H' -> handleExit(shopper);
        }
    }

    /**
     * This is for displaying alerts for information.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * This handles the basket interaction, taking and returning the basket.
     */
    private void handleBasket(Shopper shopper) {
        if (!shopper.hasEquipment() && shopper.getHandCarried().isEmpty()) {
            shopper.setEquipment(new Basket());
            System.out.println("Got a basket!");
            showAlert("Got a basket!");
        } else if (shopper.hasEquipment() && shopper.getEquipment() instanceof Basket
                && shopper.getEquipment().isEmpty()) {
            shopper.removeEquipment();
            System.out.println("Returned basket.");
            showAlert("Returned basket.");
        } else {
            System.out.println("Can't get/return basket now.");
            showAlert("Can't get/return basket now.");
        }
    }

    /**
     * This handles the cart interaction, taking and returning the cart.
     */
    private void handleCart(Shopper shopper) {
        if (!shopper.hasEquipment() && shopper.getHandCarried().isEmpty()) {
            shopper.setEquipment(new Cart());
            System.out.println("Got a cart!");
            showAlert("Got a cart!");
        } else if (shopper.hasEquipment() && shopper.getEquipment() instanceof Cart
                && shopper.getEquipment().isEmpty()) {
            shopper.removeEquipment();
            System.out.println("Returned cart.");
            showAlert("Returned cart!");
        } else {
            System.out.println("Can't get/return cart now.");
            showAlert("Can't get/return cart now.");
        }
    }

    /**
     * This handles the checkout interaction, removing the equipment of the shopper once checked out.
     */
    private void handleCheckout(Shopper shopper) {
        if (shopper.getProductCount() == 0) {
            System.out.println("No products to checkout.");
            return;
        }

        System.out.println("=== CHECKOUT ===");
        System.out.println("Thank you for shopping! You may now exit the supermarket!");

        if (shopper.hasEquipment()) {
            shopper.removeEquipment();
        }
    }

    /**
     * Handles the stairs interaction, moves up and down the ground floor and second floor depending on the current floor.
     */
    private void handleStairs(Supermarket supermarket) {
        if (supermarket.getCurrentFloor().getName().equals("GF")) {
            System.out.println("Going up..!");
            supermarket.setCurrentFloor(supermarket.getFloors().get(1));
        } else if (supermarket.getCurrentFloor().getName().equals("2F")) {
            System.out.println("Going down..!");
            supermarket.setCurrentFloor(supermarket.getFloors().getFirst());
        }
    }

    /**
     * Handles the exit interaction, if the shopper still has equipment or has not checked out whilst having products,
     * then the shopper cannot exit.
     */
    private void handleExit(Shopper shopper) {
        boolean canExit = true;

        if (shopper.hasEquipment()) {
            canExit = false;
        } else if (!shopper.getHandCarried().isEmpty() && !shopper.isCheckedOut()) {
            canExit = false;
        }

        if (!canExit) {
            showAlert("You may not exit the supermarket.\nYou must checkout items or put back equipment first");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit?");
        alert.setHeaderText(null);
        alert.setContentText("Would you like to exit the supermarket and go back to the main menu?");

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            System.out.println("Exiting...");
            Stage stage = Main.getStage();
            if (stage != null) {
                try {
                    FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/com/example/mco2_supermarketsimulator/main-menu-ui.fxml"));
                    Parent menuUI = menuLoader.load();

                    Image bg = new Image(Objects.requireNonNull(getClass().getResource("/assets/ZZmainmenu.png")).toExternalForm());
                    ImageView bgView = new ImageView(bg);
                    bgView.setFitWidth(792);
                    bgView.setFitHeight(792);
                    bgView.setPreserveRatio(false);

                    if (menuUI instanceof AnchorPane anchorPane) {
                        anchorPane.getChildren().addFirst(bgView);
                    }

                    Scene menuScene = new Scene(menuUI);
                    stage.setScene(menuScene);
                    stage.setResizable(false);
                    stage.show();

                    MainMenuController menuController = menuLoader.getController();
                    menuController.setMainApp(Main.getInstance());

                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    /**
     * Handles the product search, but only in the terminal as there is already a controller for the main functionality.
     */
    private void handleProductSearch(Supermarket supermarket) {
        String choice = " ";
        boolean found = false;

        for (Display display : supermarket.getCurrentFloor().getDisplays()) {
            for (Product product : display.getProducts()) {
                if (choice.equalsIgnoreCase(product.getName())) {
                    System.out.println(display.getAddress());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Product not found, it is either out of stock or not sold!");
        }
    }
}
