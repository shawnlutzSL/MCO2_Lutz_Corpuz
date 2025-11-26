package com.example.mco2_supermarketsimulator;

import java.util.ArrayList;

/**
 * This is the abstract class for the equipment, such as there are two types, the basket and cart.
 */
public abstract class Equipment {
    protected int productCapacity;
    protected ArrayList<Product> products;
    protected String type;

    /**
     * This is the constructor for the Equipment, containing all necessities,
     * mainly to be utilized by the shopper.
     * @param productCapacity the maximum capacity of products.
     * @param products the list to have the products in.
     * @param type the type of equipment it is.
     */
    public Equipment(int productCapacity, ArrayList<Product> products, String type) {
        this.productCapacity = productCapacity;
        this.products = products;
        this.type = type;
    }

    public ArrayList<Product> getProducts() {return new ArrayList<>(products);}
    public int getProductCount() {
        return products.size();
    }
    public boolean isFull() {
        return products.size() >= productCapacity;
    }
    public boolean isEmpty() {
        return products.isEmpty();
    }
    public String getType() {
        return type;
    }
    public int getCapacity() {
        return productCapacity;
    }

    /**
     * Adding a product to the equipment.
     * @param product the product to add to the equipment.
     */
    public boolean addProduct(Product product) {
        if (products.size() < productCapacity) {
            products.add(product);
            System.out.println("Successfully added " + product.getName() + "!");
            return true;
        }

        return false;
    }

    /**
     * Removing a product from the equipment.
     * @param product the product to remove from the equipment.
     */
    public void removeProduct(Product product) {
        if (!products.isEmpty()) {
            products.remove(product);
            System.out.println("Successfully removed " + product.getName() + " from " + getType() + "!");
            return;
        }

        System.out.println("Could not remove product!");
    }
}

/**
 * The basket class which inherits from the equipment,
 * this can hold 15 products.
 */
class Basket extends Equipment {
    public Basket() {
        super(15, new ArrayList<>(), "Basket");
    }
}

/**
 * The cart class which inherits from the equipment,
 * this can hold 30 products.
 */
class Cart extends Equipment {
    public Cart() {
        super(30, new ArrayList<>(), "Cart");
    }
}