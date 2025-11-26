package com.example.mco2_supermarketsimulator;

import javafx.scene.image.Image;

/**
 * This is the constructor for the Tile.
 *
 * @param x        the x position of the tile.
 * @param y        the y position of the tile.
 * @param tileType the image texture of the tile.
 * @param walkable the true or false value to determine if the tile is walkable or not.
 * @param rowIndex the row index of the tile array it is in.
 * @param colIndex the column index of the tile array it is in.
 */
public record Tile(double x, double y, char tileType, Image image, boolean walkable, int rowIndex, int colIndex) { }
