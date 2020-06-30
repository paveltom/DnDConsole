package BusinessLayer.TilesPackage.Environment;

import BusinessLayer.BoardPackage.Board;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;

public class Wall extends Tile {

    public Wall(int x,int y) {
        super(new Coordinate(x, y), '#');
    }

    @Override
    public void act(Player unit, Board board) {board.doNotMoveAction(unit, this);}

    @Override
    public void act(Enemy unit, Board board) {board.doNotMoveAction(unit, this);}

}
