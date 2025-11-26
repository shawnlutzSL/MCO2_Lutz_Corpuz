package com.example.mco2_supermarketsimulator;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This is the supermarket class, it contains the floors mainly.
 */
public class Supermarket {
    private final ArrayList<Floor> floors = new ArrayList<>();
    private Floor currentFloor;

    /**
     * This is the constructor for the Supermarket,
     * it also initializes the two floors, ground and second floor.
     * @param tileImages the tile image textures to refer to for the floor.
     */
    public Supermarket(ArrayList<Image> tileImages) {
        Floor firstFloor = new Floor(TileMap.GroundFloor_GF.getMap(), tileImages, "GF");
        Floor secondFloor = new Floor(TileMap.SecondFloor_2F.getMap(), tileImages, "2F");
        firstFloor.generateProducts();
        secondFloor.generateProducts();
        floors.add(firstFloor);
        floors.add(secondFloor);
        this.currentFloor = floors.getFirst();
    }

    public ArrayList<Floor> getFloors() {return floors;}
    public Floor getCurrentFloor() {return currentFloor;}
    public void setCurrentFloor(Floor currentFloor) {this.currentFloor = currentFloor;}

}
