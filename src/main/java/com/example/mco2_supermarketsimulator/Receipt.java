package com.example.mco2_supermarketsimulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This is the receipt class, to have the receipt of the shopper upon checkout.
 */
public class Receipt {
    private final Shopper shopper;
    private double totalPrice;
    private double discountedPrice;
    private List<Product> purchasedProducts;

    /**
     * This is the constructor of the Receipt, all it just needs is the shopper.
     * @param shopper the shopper who checked out.
     */
    public Receipt(Shopper shopper) {
        this.shopper = shopper;
        this.totalPrice = 0;
        this.discountedPrice = 0;
    }

    /**
     * Computing the total of all the products of the shopper.
     */
    public void computeTotal() {
        if (shopper.getProductCount() < 3)
            purchasedProducts = shopper.getHandCarried();
        else
            purchasedProducts = shopper.getEquipment().getProducts();
        System.out.printf("%-20s %-20s %-25s %-20s %10s%n", "Name", "Product", "Brand", "Serial", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------------------\n");
        int i = 0;
        for (Product product : purchasedProducts) {
            i++;
            System.out.printf("%-20s %-20s %-25s %-20s PHP %8.2f%n", "(" + i + ") " + product.getName(), product.getType(), product.getBrand(), product.getSerial(), product.getPrice());
            totalPrice += product.getPrice();
            if (shopper.getAge() >= 60) {
                if (product.isConsumable())
                    discountedPrice += product.getPrice() * 0.8;
                else if (product.isBeverage() && !product.getName().equalsIgnoreCase("ALCOHOL"))
                    discountedPrice += product.getPrice() * 0.9;
                else
                    discountedPrice += product.getPrice();
            }
        }
        System.out.print("\nTotal Price:\t\tPHP ");
        System.out.printf("%8.2f%n", totalPrice);
        if (shopper.getAge() >= 60) {
            System.out.print("Discounted Price:\t\tPHP ");
            System.out.printf("%8.2f%n", discountedPrice);
        }

        shopper.setCheckedOut(true);
        saveToTxtFile();
    }

    /**
     * Saving the Receipt output to a txt file called "receipt.txt"
     */
    private void saveToTxtFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.txt"))) {
            writer.write(String.format("%-20s %-20s %-25s %-20s %10s%n", "Name", "Product", "Brand", "Serial", "Price"));
            writer.write("----------------------------------------------------------------------------------------------------------------\n");

            int i = 0;
            for (Product product : purchasedProducts) {
                i++;
                String productLine = String.format("%-20s %-20s %-25s %-20s PHP %8.2f%n", "(" + i + ") " +
                product.getName(), product.getType(), product.getBrand(), product.getSerial(), product.getPrice());
                writer.write(productLine);
            }

            writer.write(String.format("\nTotal Price:\tPHP %8.2f%n", totalPrice));
            if (discountedPrice != 0)
                writer.write(String.format("Discounted Price:\tPHP %8.2f%n", discountedPrice));

        } catch (IOException e) {
            System.out.println("Did not successfully make the receipt :(");
        }
    }
}
