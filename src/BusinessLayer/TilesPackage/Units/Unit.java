package BusinessLayer.TilesPackage.Units;

import BusinessLayer.Coordinate;
import BusinessLayer.Subscriber;
import BusinessLayer.TilesPackage.Tile;

public abstract class Unit extends Tile implements Subscriber {

    public Integer Experience;
    public String Name;
    public Integer HealthPool; //maximum health
    public Integer HealthAmount; //current health
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
        this.ActualStatus = true;
    }

    public abstract String actualStats();

    public abstract Coordinate actionPerTick(String s);

    public abstract Coordinate actionPerTick(Coordinate c);

    public int range(Coordinate c1, Coordinate c2) {
        Double result = Math.sqrt(Math.pow((c1.getColumnCoordinate() - c2.getColumnCoordinate()), 2) + Math.pow((c1.getRowCoordinate() - c2.getRowCoordinate()), 2));
        return result.intValue();
    }

    @Override
    public void updateActualStatus() {
        if (this.HealthAmount <= 0) this.ActualStatus = false;
    }

    @Override
    public abstract void updateGameTick();

}
