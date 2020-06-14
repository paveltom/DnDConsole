package BusinessLayer.TilesPackage.Environment;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Tile;

public class Empty implements Tile {
    private Coordinate position;
    private Tile character;
    public Empty(int x,int y)
    {
        position=new Coordinate(x,y);
        character=new Empty();
    }
    public Empty() { }

    @Override
    public Coordinate getPosition() {
        return this.position;
    }

    @Override
    public Tile getCharacter() {
        return ;
    }

    @Override
    public String ToString() {
        return "_";
    }
}
