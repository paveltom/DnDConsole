package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Units.Unit;

public class Player extends Unit {

    protected Integer Experience;
    protected Integer Level;

    public Player(String[][] p, int x, int y) {
        super(p, x, y);
        Level = 1;
        Experience = 0;
    }

    public void LevelUP() {
        Experience = Experience - 50 * Level;
        Level = Level + 1;
        this.levelUpHealth(10, Level);
    }

    public void levelUpHealth(int i,int level) {
        this.healthPool = this.healthPool + i * level;
    }


    @Override
    public String actualStats() {
        throw new UnsupportedOperationException();
    }


    @Override
    public Coordinate actionPerTick(){
        throw new UnsupportedOperationException();
    }

}
