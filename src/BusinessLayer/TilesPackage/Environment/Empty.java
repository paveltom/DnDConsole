package BusinessLayer.TilesPackage.Environment;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Tile;

public class Empty extends Tile {

    public Empty(int x,int y) {
        super(new Coordinate(x, y), '.');
    }
}
