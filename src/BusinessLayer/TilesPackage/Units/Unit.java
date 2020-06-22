package BusinessLayer.TilesPackage.Units;

import BusinessLayer.Coordinate;
import BusinessLayer.Subscriber;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Players.Player;

import java.util.List;

public abstract class Unit extends Tile implements Subscriber {

    public Integer Experience;
    public String Name;
    public Integer HealthPool; //maximum health
    public Integer HealthAmount; //current health
    public Integer Health; // get\set?????
    public Integer AttackPoints;
    public Integer DefensePoints;
    public boolean ActualStatus;

    public Unit(String[][] p, int x, int y) { // add position and tileSymbol declare
        super(new Coordinate(x, y), '@');
        this.Name = p[1][1];
        this.HealthAmount = Integer.parseInt(p[1][2]);
        this.HealthPool = this.HealthAmount;
        this.AttackPoints = Integer.parseInt(p[1][3]);
        this.DefensePoints = Integer.parseInt(p[1][4]);
    }

    //updateStatus - listener pattern
    //updateTick

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
    public abstract String status();
}
