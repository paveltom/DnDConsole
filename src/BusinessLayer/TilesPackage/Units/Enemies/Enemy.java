package BusinessLayer.TilesPackage.Units.Enemies;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Unit;
import BusinessLayer.Visitor;

public abstract class Enemy extends Unit {

    public Enemy(String[][] boardData, int x, int y,char c)
    {
        super(boardData, x, y,c);
        //Experience is an Unit abstract class field!!!!!!!!!!!!!!!!!!!!!!!!!!
        //experience_value=Integer.parseInt(boardData[1][boardData[1].length]);
    }


    @Override
    public Enemy returnItself(){
        return this;
    }

    @Override
    public Enemy accept(Visitor v){
        return v.visitEnemy(this);
    }


    @Override
    public abstract String actualStats();

    @Override
    public abstract Coordinate actionPerTick(Coordinate currUserPosition);

    public abstract void updateGameTick();

    @Override
    public Coordinate actionPerTick(String s) { return new Coordinate(-1, -1); }

}
