package com.example.mco2_supermarketsimulator;

import java.util.Arrays;
import java.util.List;

/**
 * This is just the different types of products there are.
 */
public enum ProductType {
    FRUIT(Arrays.asList("Apple", "Banana", "Orange", "Mango", "Pineapple")),
    VEGETABLE(Arrays.asList("Carrot", "Broccoli", "Spinach", "Potato", "Lettuce")),
    BREAD(Arrays.asList("White", "Whole Wheat", "Sourdough", "Rye", "Multigrain")),
    EGGS(Arrays.asList("Brown", "White", "Free-range", "Organic", "Omega-3")),
    MILK(Arrays.asList("Whole", "Skim", "Almond", "Oat", "Soy")),
    FROZEN_FOOD(Arrays.asList("Pizza", "Ice Cream", "Fries", "Vegetable Mix", "Frozen Meals")),
    CHEESE(Arrays.asList("Cheddar", "Mozzarella", "Parmesan", "Swiss", "Brie")),
    CHICKEN(Arrays.asList("Whole", "Breast", "Thigh", "Drumstick", "Wings")),
    BEEF(Arrays.asList("Ground", "Steak", "Ribs", "Brisket", "Chuck")),
    SEAFOOD(Arrays.asList("Salmon", "Tuna", "Shrimp", "Lobster", "Clams")),
    CEREAL(Arrays.asList("Cornflakes", "Oats", "Granola", "Rice Krispies", "Cheerios")),
    NOODLES(Arrays.asList("Ramen", "Spaghetti", "Udon", "Soba", "Macaroni")),
    SNACKS(Arrays.asList("Chips", "Pretzels", "Popcorn", "Nuts", "Granola Bars")),
    CANNED_GOODS(Arrays.asList("Beans", "Tomatoes", "Tuna", "Peas", "Corn")),
    CONDIMENTS(Arrays.asList("Ketchup", "Mayonnaise", "Mustard", "Barbecue Sauce", "Hot Sauce")),
    SOFT_DRINK(Arrays.asList("Coke", "Pepsi", "Sprite", "Fanta", "Lemonade")),
    JUICE(Arrays.asList("Orange", "Apple", "Grape", "Cranberry", "Carrot")),
    ALCOHOL(Arrays.asList("Beer", "Wine", "Whiskey", "Vodka", "Rum")),
    CLEANING_AGENTS(Arrays.asList("Bleach", "Dish Soap", "Window Cleaner", "All-Purpose Cleaner", "Floor Cleaner")),
    HOME_ESSENTIALS(Arrays.asList("Toilet Paper", "Paper Towels", "Trash Bags", "Dish Sponge", "Batteries")),
    HAIR_CARE(Arrays.asList("Shampoo", "Conditioner", "Hair Gel", "Hair Oil", "Dry Shampoo")),
    BODY_CARE(Arrays.asList("Body Wash", "Lotion", "Hand Cream", "Scrub", "Shaving Cream")),
    DENTAL_CARE(Arrays.asList("Toothpaste", "Toothbrush", "Floss", "Mouthwash", "Whitening Strips")),
    CLOTHES(Arrays.asList("Shirts", "Pants", "Jackets", "Socks", "Sweaters")),
    STATIONERY(Arrays.asList("Pens", "Notebooks", "Markers", "Highlighters", "Erasers")),
    PET_FOOD(Arrays.asList("Dry Food", "Wet Food", "Treats", "Biscuits", "Canned Food"));

    private final List<String> productTypes;
    ProductType(List<String> productTypes) {
        this.productTypes = productTypes;
    }
    public List<String> getProductTypes() {
        return productTypes;
    }

}
