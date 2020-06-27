package BusinessLayer.TilesPackage;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;

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



    public Tile returnItself(){
        return this;
    }

    public Tile returnItself(Tile t){
        return this;
    }

    public Empty returnItself(Empty e){
        return e.returnItself();
    }

    public Wall returnItself(Wall w){
        return w.returnItself();
    }

    public Enemy returnItself(Enemy en){
        return en.returnItself();
    }

    public Player returnItself(Player p){
        return p.returnItself();
    }

}
