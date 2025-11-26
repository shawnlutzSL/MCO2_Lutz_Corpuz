package com.example.mco2_supermarketsimulator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the Floor class, it contains everything that is mainly needed such as the tiles, displays, and services.
 */
public class Floor {
    private final Tile[][] tiles = new Tile[Main.ROW_COUNT][Main.COL_COUNT];
    private final char[][] tileMap;
    private final ArrayList<Image> tileImages;
    private final char[] typesOfServices = {'C', 'D', 'E', 'F', 'G', 'H'};
    private final char[] typesOfDisplays = {'I', 'J', 'K', 'L'};
    private final ArrayList<Service> services;
    private final ArrayList<Display> displays;
    private final String name;
    private static int count;

    /**
     * This is the constructor for the Floor, to have all its necessities.
     * @param tileMap the tile map to refer to.
     * @param tileImages the image textures of the tiles.
     * @param name the name of the floor, such as "GF" or "2F".
     */
    public Floor(char[][] tileMap, ArrayList<Image> tileImages, String name) {
        this.tileMap = tileMap;
        this.tileImages = tileImages;
        this.services = new ArrayList<>();
        this.displays = new ArrayList<>();
        this.name = name;
        generateTiles();
    }

    public Tile[][] getTiles() {return tiles;}
    public char[] getTypesOfServices() {return typesOfServices;}
    public char[] getTypesOfDisplays() {return typesOfDisplays;}
    public ArrayList<Service> getServices() {return services;}
    public ArrayList<Display> getDisplays() {return displays;}
    public String getName() {return name;}

    /**
     * This is to generate all the tiles of the floor upon initialization, along with its services and displays.
     */
    private void generateTiles() {
        String displayName = null;
        int tableCount = 0;
        int chilledCounterCount = 0;
        int refrigeratorCount = 0;
        int shelfCount = 0;
        int currentCount = 0;
        int sectionCount = 0;
        int x;
        int y;
        char tileType;
        Image image;
        Tile tile;
        boolean walkable;

        y = 0;
        for (int i = 0; i < Main.ROW_COUNT; i++) {
            x = 0;
            for (int j = 0; j < Main.COL_COUNT; j++) {
                tileType = tileMap[i][j];
                walkable = true;
                image = null;

                if (j > 0 && j < 3)
                    sectionCount = 1;
                if (j > 3 && j < 6)
                    sectionCount = 2;
                if (j > 6 && j < 9)
                    sectionCount = 3;
                if (j > 10 && j < 13)
                    sectionCount = 4;
                if (j > 13 && j < 16)
                    sectionCount = 5;
                if (j > 16)
                    sectionCount = 6;

                switch (tileType) {
                    case 'A' -> {
                        image = tileImages.get(12);
                        walkable = false;
                    }
                    case 'B' -> image = tileImages.get(4);
                    case 'C' -> image = tileImages.get(1);
                    case 'D' -> image = tileImages.getFirst();
                    case 'E' -> image = tileImages.get(7);
                    case 'F' -> image = tileImages.get(10);
                    case 'G' -> image = tileImages.get(2);
                    case 'H' -> image = tileImages.get(5);
                    case 'I' -> {
                        image = tileImages.get(11);
                        walkable = false;
                    }
                    case 'J' -> {
                        image = tileImages.get(3);
                        walkable = false;
                    }
                    case 'K' -> {
                        image = tileImages.get(8);
                        walkable = false;
                    }
                    case 'L' -> {
                        image = tileImages.get(9);
                        walkable = false;
                    }
                }

                tile = new Tile(x, y, tileType, image, walkable, i, j);
                tiles[i][j] = tile;
                x += Main.TILE_SIZE;

                if (tileType == 'B' || tileType == 'C' || tileType == 'D' || tileType == 'E' ||
                        tileType == 'F' || tileType == 'G' || tileType == 'H') {
                    Service service = new Service(tile.tileType(), tile);
                    services.add(service);
                }

                switch (tileType) {
                    case 'I' -> {
                        displayName = "Table";
                        tableCount++;
                        currentCount = tableCount;
                    }
                    case 'J' -> {
                        displayName = "Chilled Counter";
                        chilledCounterCount++;
                        currentCount = chilledCounterCount;
                    }
                    case 'K' -> {
                        displayName = "Refrigerator";
                        refrigeratorCount++;
                        currentCount = refrigeratorCount;
                    }
                    case 'L' -> {
                        displayName = "Shelf";
                        shelfCount++;
                        currentCount = shelfCount;
                    }
                }

                if (tileType == 'I' || tileType == 'J' || tileType == 'K' || tileType == 'L') {
                    String address = name + ", " + "Section " + sectionCount + ", " + displayName + " " + currentCount;
                    Display display = new Display(tile.tileType(), tile, address);
                    displays.add(display);
                }
            }
            y += Main.TILE_SIZE;
        }
    }

    /**
     * To be utilized in the GameController for drawing all the tiles.
     */
    public void drawTiles(GraphicsContext gc) {
        Tile tile;
        for (int i = 0; i < Main.ROW_COUNT; i++) {
            for (int j = 0; j < Main.COL_COUNT; j++) {
                tile = tiles[i][j];
                gc.drawImage(tileImages.get(6), tile.x(), tile.y());
                gc.drawImage(tile.image(), tile.x(), tile.y());
            }
        }
    }

    /**
     * Generates all the random products for each display, including all its details such as name, serial number, etc.
     */
    public void generateProducts() {
        for (Display display : displays) {
            Random random = new Random();
            int currentCapacity = random.nextInt(display.getProductCapacity() + 1);

            if (currentCapacity != 0) {
                String randomName = null;
                int randomNameIndex;
                switch (display.getType()) {
                    case 'I' -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForTable().length);
                        randomName = display.getNamesOfProductsForTable()[randomNameIndex].toUpperCase();
                    }
                    case 'J' -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForChilledCounter().length);
                        randomName = display.getNamesOfProductsForChilledCounter()[randomNameIndex].toUpperCase();
                    }

                    case 'K' -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForRefrigerator().length);
                        randomName = display.getNamesOfProductsForRefrigerator()[randomNameIndex].toUpperCase();
                    }

                    case 'L' -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForShelf().length);
                        randomName = display.getNamesOfProductsForShelf()[randomNameIndex].toUpperCase();
                    }
                }

                if (randomName != null) {
                    ProductType productType = ProductType.valueOf(randomName);
                    Brand brand = Brand.valueOf(randomName);
                    for (int i = 0; i < currentCapacity; i++) {
                        double MIN_PRICE = 150.0;
                        double MAX_PRICE = 750.0;
                        double randomPrice = MIN_PRICE + (MAX_PRICE - MIN_PRICE) * random.nextDouble();
                        randomPrice = (double) Math.round(randomPrice * MIN_PRICE) / MIN_PRICE;
                        randomPrice = Math.round(randomPrice * 100.0) / 100.0;
                        String currentSerialNumber = "" + randomName.charAt(0) + randomName.charAt(1) + randomName.charAt(2) + String.format("%05d", count);
                        count++;
                        int randomProductTypeIndex = random.nextInt(productType.getProductTypes().size());
                        int randomBrandIndex = random.nextInt(brand.getBrands().size());
                        String randomProductType = productType.getProductTypes().get(randomProductTypeIndex);
                        String randomBrand = brand.getBrands().get(randomBrandIndex);
                        boolean isBeverage = false;
                        boolean isConsumable = false;
                        switch (randomName) {
                            case "FRUIT", "VEGETABLE", "BREAD", "EGGS", "MILK", "FROZEN_FOOD",
                                 "CHEESE", "CHICKEN", "BEEF", "SEAFOOD", "CEREAL", "NOODLES",
                                 "SNACKS", "CANNED_GOODS", "CONDIMENTS" -> isConsumable = true;
                            case "SOFT_DRINK", "JUICE", "ALCOHOL" -> isBeverage = true;
                        }

                        Product product = new Product(currentSerialNumber, randomName, randomPrice, randomProductType, randomBrand, isBeverage, isConsumable);
                        display.addProduct(product);
                    }
                }
            }
        }
    }

}
