package com.example.mco2_supermarketsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * This is the controller for the Display.
 */
public class DisplayController {
    private Shopper shopper;
    private Display display;
    private boolean isReturning;
    @FXML private AnchorPane rootPane;
    @FXML private Label displayTitle;
    @FXML private TableView<Product> productTable;
    @FXML private Label emptyLabel;
    @FXML private Button takeButton;
    @FXML private Button returnButton;
    @FXML private Button confirmButton;
    @FXML private Button continueButton;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colType;
    @FXML private TableColumn<Product, String> colBrand;
    @FXML private TableColumn<Product, String> colSerial;
    @FXML private TableColumn<Product, Double> colPrice;

    /**
     * This triggers upon initialization, settings some important values such as action events and cell values for the table.
     */
    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        takeButton.setOnAction(_ -> handleTake());
        returnButton.setOnAction(_ -> handleReturn());
        confirmButton.setOnAction(_ -> handleConfirmSelection());
        continueButton.setOnAction(_ -> hide());
        isReturning = false;
    }

    /**
     * This triggers when the takeButton is clicked, it's to switch the mode of the shopper to NOT returning.
     */
    @FXML
    private void handleTake() {
        isReturning = false;
        setProducts();
    }

    /**
     * This triggers when the returnButton is clicked, it's to switch the mode of the shopper to returning.
     */
    @FXML
    private void handleReturn() {
        isReturning = true;
        setProducts();
    }

    /**
     * This triggers when the confirmButton is clicked, it's to either add or remove the product depending on the current mode.
     */
    @FXML
    private void handleConfirmSelection() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;

        if (isReturning) {
            shopper.removeProduct(selectedProduct, display.getProducts(),display);
            productTable.getItems().remove(selectedProduct);
            showAlert("Successfully returned the product: " + selectedProduct.getName() + "!");
        } else {
            if (shopper.addProduct(selectedProduct)) {
                productTable.getItems().remove(selectedProduct);
                display.getProducts().remove(selectedProduct);
                showAlert("Successfully took the product: " + selectedProduct.getName() + "!");
            } else {
                showAlert("Could not take the product: " + selectedProduct.getName());
            }
        }
    }

    /**
     * Just a utility for making alerts.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Just a utility for the button effects.
     */
    @FXML
    private void animateGrow(javafx.scene.input.MouseEvent e) {
        var node = (javafx.scene.Node) e.getSource();
        node.setScaleX(1.08);
        node.setScaleY(1.08);
    }

    /**
     * Just a utility for the button effects.
     */
    @FXML
    private void animateShrink(javafx.scene.input.MouseEvent e) {
        var node = (javafx.scene.Node) e.getSource();
        node.setScaleX(1.0);
        node.setScaleY(1.0);
    }

    /**
     * Sets the display title, in this case it's to the display address.
     */
    public void setDisplayTitle(String title) {
        displayTitle.setText(title);
    }

    /**
     * Sets the content of the productTable depending on the mode, if returning then it's the shopper's inventory,
     * if not, then it is the display's inventory.
     */
    public void setProducts() {
        if (!isReturning)
            productTable.getItems().setAll(display.getProducts());
        else
            if (!shopper.getHandCarried().isEmpty())
                productTable.getItems().setAll(shopper.getHandCarried());
            else if (shopper.getEquipment() != null && !shopper.getEquipment().getProducts().isEmpty()) {
                productTable.getItems().setAll(shopper.getEquipment().getProducts());
            } else {
                showEmptyMessage();
            }
    }

    /**
     * Just a utility for showing if the content is empty.
     */
    public void showEmptyMessage() {
        emptyLabel.setVisible(true);
        productTable.setVisible(false);
    }

    /**
     * Just a utility to hide the empty message in case it is still active.
     */
    public void hideEmptyMessage() {
        emptyLabel.setVisible(false);
        productTable.setVisible(true);
    }

    /**
     * Just a utility to show the UI.
     */
    public void show() {
        rootPane.setVisible(true);
    }

    /**
     * Just a utility to hide the UI.
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

    /**
     * Sets the current display.
     */
    public void setDisplay(Display display) {
        this.display = display;
    }
}
