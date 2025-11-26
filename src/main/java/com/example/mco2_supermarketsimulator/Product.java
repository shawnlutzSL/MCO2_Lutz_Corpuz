package com.example.mco2_supermarketsimulator;

/**
 * This is the Product class, this is what is stored within the displays and can be utilized by the shopper.
 */
public class Product {
    private final String serialNumber;
    private final String name;
    private final String type;
    private final String brand;
    private final double price;
    private final boolean beverageCheck;
    private final boolean consumableCheck;

    /**
     * This is the constructor of the Product.
     * @param serialNumber the serial number of the product.
     * @param name the name of the product.
     * @param price the price of the product.
     * @param type the type of the product.
     * @param brand the brand of the product.
     * @param beverageCheck the true or false value to check if it is the product is a beverage or not.
     * @param consumableCheck the true or false value to check if it is a consumable or not.
     */
    public Product(String serialNumber, String name, double price, String type, String brand,
                   boolean beverageCheck, boolean consumableCheck) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.type = type;
        this.brand = brand;
        this.beverageCheck = beverageCheck;
        this.consumableCheck = consumableCheck;
    }

    public boolean isBeverage() {
        return beverageCheck;
    }
    public boolean isConsumable() {
        return consumableCheck;
    }
    public String getSerial() {
        return serialNumber;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public String getBrand() {
        return brand;
    }

}
