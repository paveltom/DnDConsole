package BusinessLayer.TilesPackage.Environment;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.Visitor;

public class Empty extends Tile {

    public Empty(int x,int y) {
        super(new Coordinate(x, y), '.');
    }

    @Override
    public Empty returnItself(){
        return this;
    }

    @Override
    public Empty accept(Visitor v){
        return v.visitEmpty(this);
    }
}
