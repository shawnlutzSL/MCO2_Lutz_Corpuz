package com.example.mco2_supermarketsimulator;

import javafx.scene.Scene;

/**
 * This is the abstract class for all Amenities, such as displays and services.
 */
public abstract class Amenity {
    protected char type;
    protected Tile location;

    /**
     * This is the constructor for the Amenity class, it has its type and tile location.
     * @param type the type from the tile map used.
     * @param location the tile location the amenity is in.
     */
    public Amenity(char type, Tile location) {
        this.type = type;
        this.location = location;
    }

    /**
     * This is the general interaction method, utilized by the shopper to interact with other tiles.
     * @param shopper the shopper who is interacting.
     * @param market the current market of the simulation.
     * @param scene the current scene.
     */
    public abstract void interact(Shopper shopper, Supermarket market, Scene scene);
    public char getType() {return type;}
    public Tile getLocation() {
        return location;
    }
}
