package com.example.mco2_supermarketsimulator;

import javafx.scene.Scene;

/**
 * The class constructor of Service, utilizes Amenity as well, this is just a model though.
 */
public class Service extends Amenity {
    private final ServiceController controller;

    public Service(char type, Tile location) {
        super(type, location);
        this.controller = new ServiceController();
    }

    /**
     * Overrides the interaction from Amenity since both services and displays have unique interaction with the shopper.
     */
    @Override
    public void interact(Shopper shopper, Supermarket market, Scene scene) {
        controller.interact(this, shopper, market);
    }
}
