package BusinessLayer.TilesPackage.Units;

import BusinessLayer.Coordinate;
import BusinessLayer.Subscriber;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;

import java.util.List;

public abstract class Unit extends Tile implements Subscriber {

    protected String Name;
    protected Integer HealthPool; //maximum health
    protected Integer HealthAmount; //current health
    protected Integer Health; // get\set?????
    protected Integer AttackPoints;
    protected Integer DefensePoints;

    public Unit(String[][] p, int x, int y) { // add position and tileSymbol declare
        super(new Coordinate(x, y), '@');
        Name = p[1][1];
        this.HealthAmount = Integer.parseInt(p[1][3]);
        this.HealthPool = this.HealthAmount;
        this.AttackPoints = Integer.parseInt(p[1][4]);
        this.DefensePoints = Integer.parseInt(p[1][5]);
    }

    //getters for all the fields?????????

    //public randomizeValue

    public abstract String actualStats();

    //public abstract Coordinate actionPerTick(String s);
    public abstract Coordinate actionPerTick();//??????????????do we need it?

    //abstract move
    //abstract

    @Override
    public void update(Object context) {
    }

}
