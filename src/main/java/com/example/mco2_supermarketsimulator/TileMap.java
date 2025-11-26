package com.example.mco2_supermarketsimulator;

/**
 * Tile Map for the construction of the tiles for each floor.
 * Legend for TileMap:
 * A - Wall
 * B - Entrance
 * C - Cart Station
 * D - Basket Station
 * E - Product Search
 * F - Stairs
 * G - Checkout Counter
 * H - Exit
 * I - Table
 * J - Chilled Counter
 * K - Refrigerator
 * L - Shelf
 * M - Empty
 */
public enum TileMap {
    GroundFloor_GF(new char[][] {
        {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
        {'A', 'J', 'J', 'J', 'J', 'J', 'J', 'M', 'J', 'J', 'J', 'J', 'J', 'J', 'M', 'J', 'J', 'J', 'J', 'J', 'J', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'F', 'M', 'M', 'M', 'M', 'M', 'M', 'E', 'M', 'M', 'M', 'M', 'E', 'M', 'M', 'M', 'M', 'M', 'M', 'F', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A', 'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'A', 'G', 'A', 'G', 'A', 'G', 'A', 'G', 'M', 'A', 'A', 'M', 'G', 'A', 'G', 'A', 'G', 'A', 'G', 'A', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'D', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'C', 'A'},
        {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'H', 'B', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}
    }),

    SecondFloor_2F(new char[][] {
        {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
        {'A', 'D', 'M', 'K', 'K', 'K', 'K', 'M', 'M', 'K', 'K', 'K', 'K', 'M', 'M', 'K', 'K', 'K', 'K', 'M', 'C', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'M', 'I', 'I', 'M', 'M', 'L', 'L', 'M', 'M', 'L', 'L', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'F', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'F', 'A'},
        {'A', 'M', 'M', 'M', 'A', 'A', 'M', 'M', 'M', 'M', 'A', 'A', 'M', 'M', 'M', 'M', 'A', 'A', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'A', 'A', 'M', 'M', 'M', 'M', 'A', 'A', 'M', 'M', 'M', 'M', 'A', 'A', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'A'},
        {'A', 'E', 'M', 'I', 'I', 'I', 'I', 'I', 'M', 'I', 'I', 'I', 'I', 'M', 'I', 'I', 'I', 'I', 'I', 'M', 'E', 'A'},
        {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}
    });

    private final char[][] map;
    TileMap(char[][] map) {this.map = map;}
    public char[][] getMap() {return map;}

}
