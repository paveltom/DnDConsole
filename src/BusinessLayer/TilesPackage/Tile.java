package BusinessLayer.TilesPackage;

import BusinessLayer.Coordinate;

public interface Tile {
    Coordinate position = new Coordinate(-1,-1);
    Tile character = null;

    //returns a coordinate of a Tile on the playing board
    Coordinate getPosition();

    //returns a current character state of a Tile
    Tile getCharacter();

    //returns a string that represents current Tile on board
    String ToString();
}
