package com.example.mco2_supermarketsimulator;

import javafx.scene.Scene;

import java.util.ArrayList;

/**
 * This is the class for Display, it also contains the names of the different products types available.
 */
public class Display extends Amenity {
    private final ArrayList<Product> products;
    private final int productCapacity;
    private final String address;
    private final String[] namesOfProductsForTable = {"Fruit", "Vegetable", "Bread", "Eggs"};
    private final String[] namesOfProductsForRefrigerator = {"Milk", "Frozen_Food", "Cheese"};
    private final String[] namesOfProductsForChilledCounter = {"Chicken", "Beef", "Seafood"};
    private final String[] namesOfProductsForShelf = {
    "Cereal", "Noodles", "Snacks", "Canned_Goods",
    "Condiments", "Soft_drink", "Juice", "Alcohol",
    "Cleaning_Agents", "Home_Essentials", "Hair_Care",
    "Body_Care", "Dental_Care", "Clothes", "Stationery", "Pet_Food"
    };

    /**
     * This is the constructor of the Display, it has a location and address,
     * particularly for the shopper's convenience.
     * @param type this is the type of display it is, like a shelf or so.
     * @param location this is the tile location the display is on.
     * @param address this is the address of the display, stating its floor, section, and count.
     */
    public Display(char type, Tile location, String address) {
        super(type, location);
        this.address = address;
        this.products = new ArrayList<>();
        switch (type) {
            case 'I' -> this.productCapacity = 4;
            case 'J' -> this.productCapacity = 3;
            case 'K' -> this.productCapacity = 3 * 3;
            case 'L' -> this.productCapacity = 4 * 2;
            default -> this.productCapacity = 0;
        }
    }

    /**
     * This is the interaction between the shopper and the display.
     * @param shopper the shopper being interacting with the display.
     * @param supermarket the current supermarket in the simulation.
     * @param scene the current scene in the simulation.
     */
    @Override
    public void interact(Shopper shopper, Supermarket supermarket, Scene scene) {
        System.out.println("Thank you for triggering me, AKA: " + address);
    }

    public String getAddress() {return address;}
    public ArrayList<Product> getProducts() {return products;}
    public int getProductCapacity() {return productCapacity;}
    public String[] getNamesOfProductsForTable() {return namesOfProductsForTable;}
    public String[] getNamesOfProductsForRefrigerator() {return namesOfProductsForRefrigerator;}
    public String[] getNamesOfProductsForChilledCounter() {return namesOfProductsForChilledCounter;}
    public String[] getNamesOfProductsForShelf() {return namesOfProductsForShelf;}
    public void addProduct(Product product) {products.add(product);}

}