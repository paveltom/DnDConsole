package BusinessLayer.TilesPackage.Units.Enemies;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Units.Unit;

public class Enemy extends Unit {
    protected int experience_value;

    public Enemy(String[][] boardData, int x, int y)
    {
        super(boardData, x, y);
        //experience_value=Integer.parseInt(boardData[1][boardData[1].length]);
    }

    @Override
    public String actualStats() {
        return null;
    }

    @Override
    public Coordinate actionPerTick() {
        return null;
    }

    @Override
    public String status() {
        return null;
    }
}
