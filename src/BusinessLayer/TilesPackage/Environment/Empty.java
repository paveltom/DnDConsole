package BusinessLayer.TilesPackage.Environment;

import BusinessLayer.BoardPackage.Board;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;

public class Empty extends Tile {

    public Empty(int x,int y) {
        super(new Coordinate(x, y), '.');
    }

    @Override
    public void act(Player unit, Board board) {board.moveAction(unit, this);}

    @Override
    public void act(Enemy unit, Board board) {board.moveAction(unit, this);}



}
