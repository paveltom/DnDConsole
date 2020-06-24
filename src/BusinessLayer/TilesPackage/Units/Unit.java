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
    public Integer Health; // get\set?????   do we need it at all????????????????
    public Integer AttackPoints;
    public Integer DefensePoints;
    public boolean ActualStatus;

    public Unit(String[][] p, int x, int y,char symbol) { // add position and tileSymbol declare
        super(new Coordinate(x, y), symbol);
        this.Name = p[1][1];
        this.HealthAmount = Integer.parseInt(p[1][2]);
        this.HealthPool = this.HealthAmount;
        this.AttackPoints = Integer.parseInt(p[1][3]);
        this.DefensePoints = Integer.parseInt(p[1][4]);
    }

    public abstract Tile ActionPerTick(String userInput);

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
    public void updateActualStatus() {
        if (this.HealthAmount <= 0) this.ActualStatus = false;
    }

    @Override
    public void updateGameTick() {
        //insert here actions per tick of current character (player / enemy)
    }



    public abstract String status();
}
