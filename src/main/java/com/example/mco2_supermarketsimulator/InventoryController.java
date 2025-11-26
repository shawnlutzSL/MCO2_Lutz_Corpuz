package com.example.mco2_supermarketsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * This is the controller for the inventory of the player.
 */
public class InventoryController {
    private Shopper shopper;
    @FXML private AnchorPane rootPane;
    @FXML private TableView<Product> productTable;
    @FXML private Label emptyLabel;
    @FXML private Label equipmentLabel;
    @FXML private Button continueButton;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colType;
    @FXML private TableColumn<Product, String> colBrand;
    @FXML private TableColumn<Product, String> colSerial;
    @FXML private TableColumn<Product, Double> colPrice;

    /**
     * This is triggered upon initialization, setting cell values for the table and the button action event(s).
     */
    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        continueButton.setOnAction(_ -> hide());
    }

    /**
     * Just another utility for the UI.
     */
    @FXML
    private void animateGrow(javafx.scene.input.MouseEvent e) {
        var node = (javafx.scene.Node) e.getSource();
        node.setScaleX(1.08);
        node.setScaleY(1.08);
    }

    /**
     * Just another utility for the UI.
     */
    @FXML
    private void animateShrink(javafx.scene.input.MouseEvent e) {
        var node = (javafx.scene.Node) e.getSource();
        node.setScaleX(1.0);
        node.setScaleY(1.0);
    }

    /**
     * Sets the content of the table and the current Equipment depending on what
     * the shopper has, whether it is a cart, basket, or none (hand carry).
     */
    public void setProducts() {
        hideEmptyMessage();
        if (shopper.hasEquipment()) {
            if (shopper.getEquipment().getCapacity() == 15)
                equipmentLabel.setText("Equipment: Basket");
            if (shopper.getEquipment().getCapacity() == 30)
                equipmentLabel.setText("Equipment: Cart");
        } else
            equipmentLabel.setText("Equipment: None");
        if (!shopper.getHandCarried().isEmpty()) {
            productTable.getItems().setAll(shopper.getHandCarried());
        }
        else if (shopper.getEquipment() != null && !shopper.getEquipment().getProducts().isEmpty()) {
            productTable.getItems().setAll(shopper.getEquipment().getProducts());
        } else {
            showEmptyMessage();
        }
    }

    /**
     * Just another utility to show if the content is empty.
     */
    public void showEmptyMessage() {
        emptyLabel.setVisible(true);
        productTable.setVisible(false);
    }

    /**
     * Just another utility to hide the empty message just in case it is still up.
     */
    public void hideEmptyMessage() {
        emptyLabel.setVisible(false);
        productTable.setVisible(true);
    }

    /**
     * Shows the Inventory UI.
     */
    public void show() {
        rootPane.setVisible(true);
    }

    /**
     * Hides the Inventory UI.
     */
    public void hide() {
        rootPane.setVisible(false);
    }

    /**
     * Sets the current shopper.
     */
    public void setShopper(Shopper shopper) {
        this.shopper = shopper;
    }
}
