package BusinessLayer.TilesPackage.Units;

import BusinessLayer.Coordinate;
import BusinessLayer.Subscriber;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;

import java.util.List;

public abstract class Unit extends Tile implements Subscriber {

    private String name;
    private Integer healthPool;
    private Integer healthAmount;
    private Integer health; // get\set?????
    protected Integer attackPoints;
    private Integer defensePoints;

    public Unit(String[][] p, int x, int y) { // add position and tileSymbol declare
        super(new Coordinate(x,y), '@');
        name=p[1][1];
        healthAmount=Integer.parseInt(p[1][3]);
        healthPool=healthAmount;
        attackPoints=Integer.parseInt(p[1][4]);
        defensePoints=Integer.parseInt(p[1][5]);
    }

    //getters for all the fields?????????


    public abstract String actualStats();

    //public abstract Coordinate actionPerTick(String s);
    public abstract Coordinate actionPerTick();//??????????????do we need it?

    //abstract move
    //abstract
    public void leveluphealth(int i,int level)
    {
        healthPool=healthPool+i*level;
    }

    @Override
    public void update(Object context) {
    }

}
