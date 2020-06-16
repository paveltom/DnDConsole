package BusinessLayer.TilesPackage.Units.Enemies;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Units.Unit;

public class Enemy extends Unit {

    public Enemy(String[][] boardData, int x, int y) {
        super(boardData, x, y);
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
