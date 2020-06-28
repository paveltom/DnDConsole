package BusinessLayer;

import BusinessLayer.BoardPackage.Board;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;
import BusinessLayer.TilesPackage.Units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Visitor {

    public Enemy visitEnemy (Enemy enemy) { return enemy; }

    public Wall visitWall (Wall wall) { return wall; }

    public Empty visitEmpty (Empty empty) { return empty; }

    public Player visitPlayer (Player player) { return player; }

    public Tile visitTile (Tile tile) { return tile; }


}
