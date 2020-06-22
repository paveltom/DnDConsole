package BusinessLayer.TilesPackage;

import BusinessLayer.Coordinate;

public class Tile {

    public Coordinate Position;
    public char TileSymbol;

    public Tile(Coordinate position, char tileSymbol) {
        this.Position = position;
        this.TileSymbol = tileSymbol;
    }

    //returns a coordinate of a Tile on the playing board
    public Coordinate getPosition() {
        return Position;
    }

    //returns a string that represents current Tile on board
    public String ToString() {
        return this.TileSymbol + "";
    }

}
