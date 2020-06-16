package BusinessLayer.TilesPackage.Units.Enemies;

import BusinessLayer.Coordinate;
import BusinessLayer.Subscriber;
import BusinessLayer.TilesPackage.Units.Unit;

public class Enemy extends Unit {
    private Unit currEnemy;

    public Enemy(String[][] boardData, int x, int y) {
        super(boardData, x, y);
    }

    public Enemy Enemy(String[][] e){
        if(e[1][0]=="Monster")
            return new Monster(e);
        else return new Trap(e);
    }

    @Override
    public String actualStats() {
        return null;
    }

    @Override
    public Coordinate actionPerTick() {
        return null;
    }
}
