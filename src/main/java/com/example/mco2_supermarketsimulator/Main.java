package com.example.mco2_supermarketsimulator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This is the Main class, where the actual running of the program happens.
 * It utilizes the Application class from javafx via inheritance.
 */
public class Main extends Application {
    public static final int TILE_SIZE = 36;
    public static final int ROW_COUNT = 22;
    public static final int COL_COUNT = 22;
    private static final double screenWidth = 792;
    private static final double screenHeight = 792;
    private static Stage stage = null;
    private static Main instance = null;

    public static Stage getStage() {return stage;}
    public static Main getInstance() {return instance;}

    /**
     * This is the actual method that starts the program, hence the initialization of important
     * variables like the canvas, scene, and the loading of FXML UIs utilizing javafx.
     * @param stage this is basically the window, like a JFrame.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Main.instance = this;
        showMainMenu();
    }

    /**
     * This is to show the main menu, it is triggered upon the start of the program.
     */
    public void showMainMenu() throws IOException {
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("main-menu-ui.fxml"));
        Parent menuUI = menuLoader.load();
        MainMenuController menuController = menuLoader.getController();
        menuController.setMainApp(this);

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
    }

    /**
     * This is to start a fresh new simulation, with a new shopper, supermarket, everything.
     * @param name the name of the shopper.
     * @param age the age of the shopper.
     */
    public void startSimulation(String name, int age) throws Exception {
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        FXMLLoader displayLoader = new FXMLLoader(getClass().getResource("display-ui.fxml"));
        Parent displayUI = displayLoader.load();
        displayUI.setVisible(false);
        DisplayController displayController = displayLoader.getController();

        FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("product-search-ui.fxml"));
        Parent productSearchUI = searchLoader.load();
        productSearchUI.setVisible(false);
        ProductSearchController productSearchController = searchLoader.getController();

        FXMLLoader checkoutLoader = new FXMLLoader(getClass().getResource("checkout-ui.fxml"));
        Parent checkoutUI = checkoutLoader.load();
        checkoutUI.setVisible(false);
        CheckoutController checkoutController = checkoutLoader.getController();

        FXMLLoader inventoryLoader = new FXMLLoader(getClass().getResource("inventory-ui.fxml"));
        Parent inventoryUI = inventoryLoader.load();
        inventoryUI.setVisible(false);
        InventoryController inventoryController = inventoryLoader.getController();

        root.getChildren().addAll(displayUI, productSearchUI, checkoutUI, inventoryUI);

        Scene scene = new Scene(root);
        GameController controller = new GameController(scene, gc, displayController,
                productSearchController, checkoutController, inventoryController, name, age);

        stage.setScene(scene);
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.render();
            }
        }.start();
    }


}
