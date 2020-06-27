package BusinessLayer.TilesPackage.Environment;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Tile;

public class Wall extends Tile {

    public Wall(int x,int y) {
        super(new Coordinate(x, y), '#');
    }

    @Override
    public Wall returnItself(){
        return this;
    }
}
