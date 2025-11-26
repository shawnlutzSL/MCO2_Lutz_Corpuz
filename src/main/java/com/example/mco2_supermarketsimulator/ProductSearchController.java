package com.example.mco2_supermarketsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

/**
 * This is the controller for the Product Search, which handles all the functionality basically.
 */
public class ProductSearchController {
    private Floor floor;
    @FXML private AnchorPane rootPane;
    @FXML private TextField searchField;
    @FXML private TableView<Display> displayTable;
    @FXML private Label emptyLabel;
    @FXML private Button searchButton;
    @FXML private Button continueButton;
    @FXML private TableColumn<Product, String> colAddress;

    /**
     * This is triggered upon initialization, setting table values and button action events.
     */
    @FXML
    private void initialize() {
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        searchButton.setOnAction(_ -> handleConfirmSelection());
        continueButton.setOnAction(_ -> hide());
    }

    /**
     * This handles the searching of the product, displaying the address of all displays
     * which have the product being searched for.
     */
    @FXML
    private void handleConfirmSelection() {
        hideEmptyMessage();
        ArrayList<Display> displays = new ArrayList<>();

        for (Display currentDisplay : floor.getDisplays()) {
            for (Product currentProduct : currentDisplay.getProducts()) {
                if (searchField.getText().equalsIgnoreCase(currentProduct.getName()) ||
                        searchField.getText().equalsIgnoreCase(currentProduct.getType())) {
                    if (!displays.contains(currentDisplay)) {
                        displays.add(currentDisplay);
                    }
                }
            }
        }

        if (displays.isEmpty()) {
            showEmptyMessage();
        } else {
            displayTable.getItems().setAll(displays);
        }
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
     * Pops up when there's no content within the table.
     */
    public void showEmptyMessage() {
        emptyLabel.setVisible(true);
        displayTable.setVisible(false);
    }

    /**
     * Hides the empty message in case it is still up.
     */
    public void hideEmptyMessage() {
        emptyLabel.setVisible(false);
        displayTable.setVisible(true);
    }

    /**
     * Shows the UI of the Product Search.
     */
    public void show() {
        rootPane.setVisible(true);
    }

    /**
     * Hides the UI of the Product Search.
     */
    public void hide() {
        rootPane.setVisible(false);
    }

    /**
     * Sets the current floor to refer to.
     */
    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
