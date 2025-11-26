package com.example.mco2_supermarketsimulator;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * GameController class, this is used for controlling the main bulk functionality of the simulator
 * That includes user input, starting up the simulation, running it, and loading in textures
 * Most importantly, it also draws the game objects such as the tiles and the player.
 */
public class GameController {
    private final DisplayController displayController;
    private final GraphicsContext gc;
    private Supermarket supermarket;
    private Shopper shopper;
    private final ArrayList<Image> tileImages = new ArrayList<>();

    /**
     * This is the constructor of the game controller, its params should contain the majority.
     * of what the game needs to run.
     *
     * @param scene                   the scene node to attach other nodes to.
     * @param gc                      the canvas to draw on basically.
     * @param displayController       the controller for the UI in regard to the display tiles.
     * @param productSearchController the controller for UI in regard to the product search.
     * @param inventoryController     the controller for UI in regard to the inventory, to be activated via Spacebar.
     */
    public GameController(Scene scene, GraphicsContext gc, DisplayController displayController,
                          ProductSearchController productSearchController,
                          CheckoutController checkoutController, InventoryController inventoryController,
                          String name, int age) {
        this.gc = gc;
        this.displayController = displayController;
        loadTileImages();
        initSimulation(name, age);
        setupInput(scene, displayController, productSearchController, checkoutController, inventoryController);
    }

    /**
     * Loads in the game assets - textures.
     */
    private void loadTileImages() {
        File dir = new File("src/main/resources/assets");
        File[] files = dir.listFiles((_, name) -> name.endsWith(".png"));
        if (files != null) {
            for (File file : files)
                tileImages.add(new Image(file.toURI().toString()));
        }
    }

    /**
     * Starts up a new simulation of the game.
     * @param name the name of the shopper to start the simulation with.
     * @param age the age of the shopper to start the simulation with.
     */
    private void initSimulation(String name, int age) {
        supermarket = new Supermarket(tileImages);
        shopper = new Shopper(name, age, tileImages.get(13), supermarket.getCurrentFloor().getTiles()[21][11]);
        displayController.setShopper(shopper);
        System.out.println(shopper.getEquipment());
        System.out.println(shopper.getAge());
        System.out.println(shopper.getName());
        System.out.println(supermarket.getCurrentFloor().getServices().getFirst().getLocation());
        System.out.println(supermarket.getCurrentFloor().getDisplays().getLast().getAddress());
    }

    /**
     * Initializes all user inputs required for the simulation, such as mouse clicks and key inputs.
     * @param scene the current scene to use.
     * @param displayController the controller for the ui of the display tiles.
     */
    private void setupInput(Scene scene, DisplayController displayController,
                            ProductSearchController productSearchController,
                            CheckoutController checkoutController, InventoryController inventoryController) {
        scene.setOnMouseClicked(e -> {
            Tile chosenTile = null;
            double mouseX = e.getX();
            double mouseY = e.getY();

            for (Tile[] row : supermarket.getCurrentFloor().getTiles()) {
                for (Tile tile : row) {
                    if (mouseX >= tile.x() && mouseX <= tile.x() + Main.TILE_SIZE &&
                            mouseY >= tile.y() && mouseY <= tile.y() + Main.TILE_SIZE) {
                        chosenTile = tile;
                    }
                }
            }

            if (chosenTile == null) return;

            double offset = (double) Main.TILE_SIZE / 1.5;
            double distance = Math.sqrt(Math.pow(shopper.getCurrentTile().x() + offset - mouseX, 2)
                + Math.pow(shopper.getCurrentTile().y() + offset - mouseY, 2));

            if (distance < Main.TILE_SIZE + offset) {
                for (Display display : supermarket.getCurrentFloor().getDisplays()) {
                    if (display.location == chosenTile) {
                        shopper.interact(supermarket, chosenTile, scene, displayController, productSearchController);
                        this.displayController.setDisplay(display);
                        displayController.setDisplayTitle(display.getAddress());
                        this.displayController.setProducts();

                        if (display.getProducts().isEmpty()) {
                            displayController.showEmptyMessage();
                        } else {
                            displayController.hideEmptyMessage();
                        }

                        displayController.show();
                    }
                }

                for (Service service : supermarket.getCurrentFloor().getServices()) {
                    if (service.location == chosenTile) {
                        shopper.interact(supermarket, chosenTile, scene, displayController, productSearchController);
                        if (service.getType() == 'E') {
                            productSearchController.setFloor(supermarket.getCurrentFloor());
                            productSearchController.show();
                        }
                        if (service.getType() == 'G') {
                            checkoutController.setShopper(shopper);
                            checkoutController.setProducts();
                            checkoutController.show();
                        }
                    }
                }
            }

        });

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W -> shopper.tryMoveTile(-1, 0, supermarket.getCurrentFloor());
                case A -> shopper.tryMoveTile(0, -1, supermarket.getCurrentFloor());
                case S -> shopper.tryMoveTile(+1, 0, supermarket.getCurrentFloor());
                case D -> shopper.tryMoveTile(0, +1, supermarket.getCurrentFloor());
                case SPACE -> {
                    inventoryController.setShopper(shopper);
                    inventoryController.setProducts();
                    inventoryController.show();
                }
            }

        });

    }

    /**
     * Draws every game asset except UI, such as the tiles and the player.
     */
    public void render() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        supermarket.getCurrentFloor().drawTiles(gc);
        gc.drawImage(shopper.getImage(), shopper.getPosX(), shopper.getPosY());
    }
}
