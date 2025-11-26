package com.example.mco2_supermarketsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * This is the controller for the Checkout Counter, pretty much all the functionality is here.
 */
public class CheckoutController {
    private Shopper shopper;
    private double totalAmount;
    @FXML private Label changeAmountLabel;
    @FXML private Label totalAmountLabel;
    @FXML private TextField amountField;
    @FXML private AnchorPane rootPane;
    @FXML private TableView<Product> productTable;
    @FXML private Label emptyLabel;
    @FXML private Button payButton;
    @FXML private Button continueButton;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colType;
    @FXML private TableColumn<Product, String> colBrand;
    @FXML private TableColumn<Product, String> colSerial;
    @FXML private TableColumn<Product, Double> colPrice;


    /**
     * This triggers upon initialization, setting up important values.
     */
    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        payButton.setOnAction(_ -> handlePay());
        continueButton.setOnAction(_ -> hide());
        amountField.textProperty().addListener((_, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                amountField.setText(oldValue);
            }
        });
    }

    /**
     * This sets the content for the productTable.
     */
    public void setProducts() {
        hideEmptyMessage();
        payButton.setVisible(true);
        totalAmount = 0.0;
        if (!shopper.getHandCarried().isEmpty()) {
            productTable.getItems().setAll(shopper.getHandCarried());
            for (Product product : shopper.getHandCarried())
                totalAmount += product.getPrice();
        }
        else if (shopper.getEquipment() != null && !shopper.getEquipment().getProducts().isEmpty()) {
            productTable.getItems().setAll(shopper.getEquipment().getProducts());
            for (Product product : shopper.getEquipment().getProducts())
                totalAmount += product.getPrice();
        } else {
            payButton.setVisible(false);
            showEmptyMessage();
        }

        totalAmountLabel.setText("PHP " + totalAmount);
    }


    /**
     * This handles the payment, checking if it's sufficient and all, if so, a receipt will also be generated.
     */
    @FXML
    private void handlePay() {
        double payAmount = Double.parseDouble(amountField.getText());
        if (totalAmount <= payAmount) {
            double changeAmount = payAmount - totalAmount;
            changeAmountLabel.setText("PHP " +  String.format("%.2f", changeAmount));
            Receipt receipt = new Receipt(shopper);
            receipt.computeTotal();
            showAlert("Successfully paid and checked out!");
            hide();
        } else {
            showAlert("That's not enough money, please enter a sufficient amount!");
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
     * Just an effect for the button in the UI.
     */
    @FXML
    private void animateGrow(javafx.scene.input.MouseEvent e) {
        var node = (javafx.scene.Node) e.getSource();
        node.setScaleX(1.08);
        node.setScaleY(1.08);
    }

    /**
     * Just an effect for the button in the UI.
     */
    @FXML
    private void animateShrink(javafx.scene.input.MouseEvent e) {
        var node = (javafx.scene.Node) e.getSource();
        node.setScaleX(1.0);
        node.setScaleY(1.0);
    }

    /**
     * This shows in case the content is empty, it is another utility.
     */
    public void showEmptyMessage() {
        emptyLabel.setVisible(true);
        productTable.setVisible(false);
    }

    /**
     * Hides the empty message just in case, it is another utility.
     */
    public void hideEmptyMessage() {
        emptyLabel.setVisible(false);
        productTable.setVisible(true);
    }

    /**
     * Shows the UI, it is another utility.
     */
    public void show() {
        if (shopper.isCheckedOut())
            showAlert("You already checked out and successfully paid! You may not check out again.");
        else
            rootPane.setVisible(true);
    }

    /**
     * Hides the UI, it is another utility.
     */
    public void hide() {
        rootPane.setVisible(false);
    }


    public void setShopper(Shopper shopper) {
        this.shopper = shopper;
    }

}
