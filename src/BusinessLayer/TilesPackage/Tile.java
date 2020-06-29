package BusinessLayer.TilesPackage;

import BusinessLayer.BoardPackage.Board;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;
import BusinessLayer.TilesPackage.Units.Unit;

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

    public String toString() {
        return TileSymbol + "";
    }

    public void act(Player tile, Board board){ System.out.println("nope"); }; //for Overriding purposes

    public void act(Enemy tile, Board board){ System.out.println("nope"); }; //for Overriding purposes

    public void act(Wall tile, Board board){ System.out.println("nope"); }; //for Overriding purposes

    public void act(Empty tile, Board board){ System.out.println("nope"); }; //for Overriding purposes

}
