package com.example.mco2_supermarketsimulator;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This is the class of the Shopper, where all the shopper related things happen,
 * such as movement and interaction.
 */
public class Shopper {
    private final String name;
    private final int age;
    private boolean isMoving = false;
    private double posX, posY;
    private Tile currentTile;
    private final Image image;
    private Equipment equipment;
    private final ArrayList<Product> handCarried;
    private boolean checkedOut;
    private final double stepSpeed = 5;

    /**
     * This is the constructor of the Shopper, it requires necessities like name and all that.
     * @param name the name of the shopper.
     * @param age the age of the shopper, important for determining legalities like discounts and prohibitions.
     * @param image the image texture of the shopper.
     * @param startTile the starting position/tile of the shopper.
     */
    public Shopper(String name, int age, Image image, Tile startTile) {
        this.name = name;
        this.age = age;
        this.image = image;
        this.currentTile = startTile;
        this.posX = startTile.x();
        this.posY = startTile.y();
        this.handCarried = new ArrayList<>();
        this.checkedOut = false;
    }

    // It's all setters, getters, and checkers.
    public boolean isCheckedOut() {return checkedOut;}
    public Image getImage() { return image; }
    public double getPosX() { return posX; }
    public double getPosY() { return posY; }
    public Tile getCurrentTile() { return currentTile; }
    public Equipment getEquipment() {return equipment;}
    public boolean hasEquipment() {return equipment != null;}
    public int getAge() {return age;}
    public void setCheckedOut(boolean newCheckedOut) {this.checkedOut = newCheckedOut;}
    public void setEquipment(Equipment equipment) {if (handCarried.isEmpty()) this.equipment = equipment;}
    public void removeEquipment() {if (equipment != null && equipment.isEmpty()) this.equipment = null;}
    public int getProductCount() {if (equipment != null) return equipment.getProductCount(); return handCarried.size();}
    public ArrayList<Product> getHandCarried() {return new ArrayList<>(handCarried);}
    public String getName() {return name;}

    /**
     * This is the prerequisite to moving the shopper.
     * It checks if the shopper can move first.
     * A shopper can't move continuously to another direction if they are moving already.
     * @param rowOffset this is to know if the shopper is moving up or down.
     * @param colOffset this is to know if the shopper is moving left or right.
     * @param floor this is to know which floor the shopper is on.
     */
    public void tryMoveTile(int rowOffset, int colOffset, Floor floor) {
        if (Math.abs(rowOffset) + Math.abs(colOffset) != 1)
            return;

        if (isMoving) return;

        int targetRow = currentTile.rowIndex() + rowOffset;
        int targetCol = currentTile.colIndex() + colOffset;

        Tile targetTile = floor.getTiles()[targetRow][targetCol];

        if (!targetTile.walkable()) return;

        moveToTile(targetTile);
    }

    /**
     * Moves the shopper smoothly, not instantly, via W A S D controls.
     * @param targetTile the tile to move the shopper to.
     */
    private void moveToTile(Tile targetTile) {
        isMoving = true;
        double targetX = targetTile.x();
        double targetY = targetTile.y();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dx = targetX - posX;
                double dy = targetY - posY;
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < stepSpeed) {

                    posX = targetX;
                    posY = targetY;
                    currentTile = targetTile;
                    isMoving = false;
                    stop();
                    return;
                }

                posX += stepSpeed * (dx / dist);
                posY += stepSpeed * (dy / dist);
            }
        }.start();
    }

    /**
     * The shopper interaction with the tile that was chosen, such as if it is a service or a display, etc.
     *
     * @param supermarket             the supermarket to refer to.
     * @param chosenTile              the tile that was chosen to be interacted with.
     * @param scene                   the current scene.
     * @param displayController       the controller for the ui of the display tiles.
     * @param productSearchController the controller for the ui of the checkout counter.
     */
    public void interact(Supermarket supermarket, Tile chosenTile, Scene scene,
                         DisplayController displayController, ProductSearchController productSearchController) {
        Floor floor = supermarket.getCurrentFloor();

        char currentType = chosenTile.tileType();

        for (char serviceType : floor.getTypesOfServices()) {
            if (currentType == serviceType) {
                Service currentService = floor.getServices().getFirst();
                for (Service service : floor.getServices()) {
                    if (service.location == chosenTile)
                        currentService = service;
                }

                currentService.interact(this, supermarket, scene);
            }
        }

        for (char displayType : floor.getTypesOfDisplays()) {
            if (currentType == displayType) {
                Display currentDisplay = floor.getDisplays().getFirst();
                for (Display display : floor.getDisplays()) {
                    if (display.location == chosenTile)
                        currentDisplay = display;
                }

                currentDisplay.interact(this, supermarket, scene);
            }
        }

    }

    /**
     * Adding a product to the shopper's inventory from a display.
     * @param product the product to add to the shopper.
     * @return returns true if the product was successfully added, and false if otherwise.
     */
    public boolean addProduct(Product product) {
        if (product.getType().equalsIgnoreCase("Alcohol") && age < 18)
            return false;
        if (equipment != null) {
            if (!equipment.isFull()) {
                return equipment.addProduct(product);
            }
        } else if (handCarried.size() < 2) {
            handCarried.add(product);
            System.out.println("Success! Currently holding the following: ");
            for (Product handCarriedProduct : handCarried) {
                System.out.print(handCarriedProduct.getName() + " | ");
            }
            return true;
        } else {
            System.out.println("Your hands are full! Cannot take product.");
        }

        return false;
    }

    /**
     * Returns a product from the shopper's inventory back to the display.
     * @param product the product to return.
     * @param products the ArrayList to put back the product to.
     * @param currentDisplay the current display being interacted with.
     */
    public void removeProduct(Product product, ArrayList<Product> products, Display currentDisplay) {
        if (equipment != null) {
            boolean canRemove = false;
            switch (currentDisplay.getType()) {
                case 'I' -> {
                    for (String tableName : currentDisplay.getNamesOfProductsForTable()) {
                        if (tableName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }
                case 'J' -> {
                    for (String chilledCounterName : currentDisplay.getNamesOfProductsForChilledCounter()) {
                        if (chilledCounterName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }

                case 'K' -> {
                    for (String refrigeratorName : currentDisplay.getNamesOfProductsForRefrigerator()) {
                        if (refrigeratorName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }

                case 'L' -> {
                    for (String shelfName : currentDisplay.getNamesOfProductsForShelf()) {
                        if (shelfName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }
            }
            if (canRemove) {
                products.add(product);
                equipment.removeProduct(product);
            }
        } else if (!handCarried.isEmpty()) {
            boolean canRemove = false;
            switch (currentDisplay.getType()) {
                case 'I' -> {
                    for (String tableName : currentDisplay.getNamesOfProductsForTable()) {
                        if (tableName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }
                case 'J' -> {
                    for (String chilledCounterName : currentDisplay.getNamesOfProductsForChilledCounter()) {
                        if (chilledCounterName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }

                case 'K' -> {
                    for (String refrigeratorName : currentDisplay.getNamesOfProductsForRefrigerator()) {
                        if (refrigeratorName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }

                case 'L' -> {
                    for (String shelfName : currentDisplay.getNamesOfProductsForShelf()) {
                        if (shelfName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                            break;
                        }
                    }
                }
            }
            if (canRemove) {
                products.add(product);
                handCarried.remove(product);
                System.out.println("Successfully not holding " +  product.getName() + " anymore!");
                for (Product handCarriedProduct : handCarried) {
                    System.out.print(handCarriedProduct.getName() + " | ");
                }
            } else {
                System.out.println("You may not return that product here as it is not its respective display.");
            }

        } else {
            System.out.println("There's nothing to return!");
        }

    }

}
