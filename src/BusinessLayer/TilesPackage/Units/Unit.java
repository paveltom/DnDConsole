package BusinessLayer.TilesPackage.Units;

import BusinessLayer.Coordinate;
import BusinessLayer.Subscriber;
import BusinessLayer.TilesPackage.Tile;

import java.util.List;

public abstract class Unit implements Tile, Subscriber {

    private String name;
    private Integer healthPool;
    private Integer healthAmount;
    private Integer health; // get\set?????
    private Integer attackPoints;
    private Integer defensePoints;

    //getters for all the fields?????????

    public Unit(List<Object[]> unitStats){

    }

    public abstract String actualStats();

    public abstract Coordinate actionPerTick();//??????????????do we need it?

    //abstract move
    //abstract

    @Override
    public void update(Object context) {
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public Tile getCharacter() {
        return character;
    }

    @Override
    public String ToString() {
        return null;
    }


}
