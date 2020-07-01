package BusinessLayer.TilesPackage.Units.Enemies;
import BusinessLayer.BoardPackage.Board;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Units.Players.Player;
import BusinessLayer.TilesPackage.Units.Unit;

public abstract class Enemy extends Unit {

    public Enemy(String[][] boardData, int x, int y,char c)
    {
        super(boardData, x, y,c);
    }



    @Override
    public abstract String actualStats();

    @Override
    public abstract Coordinate actionPerTick(Coordinate currUserPosition);

    public abstract void updateGameTick();

    @Override
    public Coordinate actionPerTick(String s) { return new Coordinate(-1, -1); }


    @Override
    public void act(Enemy enemy, Board board){
        board.doNotAttackAction(this, enemy); // we dont really need this method in Board - just dont do anything in this case
    }

    @Override
    public void act(Player player, Board board){
        board.attackAction(player, this);
    }

    @Override
    public void act(Empty empty, Board board){
        board.moveAction(this, empty);
    }

    @Override
    public void act(Wall wall, Board board){
        board.doNotMoveAction(this, wall);
    }
}
