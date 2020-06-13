package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Units.Unit;

public class Player extends Unit {

    private Integer experience;
    private Integer level;

    public Player(){
        this.experience = 0;
        this.level = 1;
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
