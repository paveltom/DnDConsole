package BusinessLayer.TilesPackage.Units.Players;
import BusinessLayer.BoardPackage.Board;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Unit;

import java.util.List;

public abstract class Player extends Unit {

    public Integer Level;

    public Player(String[][] p, int x, int y,char s) {
        super(p, x, y,s);
        Level = 1;
        Experience = 0;
    }


    public void LevelUP() {
        this.Experience = this.Experience - 50 * Level;
        this.Level = this.Level + 1;
        this.HealthPool = this.HealthPool + 10 * Level;
        this.HealthAmount = this.HealthPool;
        this.AttackPoints = this.AttackPoints + (4 * this.Level);
        this.DefensePoints = this.DefensePoints + this.Level;
    }

    public abstract int applySpecialAbility(List<Enemy> enemies, List<String> output); //returns the damage that will be caused to randomly (depends on players class) chosen enemies


    public void updateExperience(int experience, List<String> output){
        this.Experience = this.Experience+experience; //overridden with LevelUp statement in each player.Class
    }


    @Override
    public Coordinate actionPerTick(String userInput){
        int x = this.Position.getColumnCoordinate();
        int y = this.Position.getRowCoordinate();
        switch (userInput) {
            case "w":
                return new Coordinate(x, y-1);
            case "a":
                return new Coordinate(x-1, y);
            case "s":
                return new Coordinate(x, y+1);
            case "d":
                return new Coordinate(x+1, y);
            default:
                return new Coordinate(x, y);
        }
    }


    @Override
    public Coordinate actionPerTick(Coordinate c){
        return new Coordinate(-1,-1);
    }

    @Override
    public abstract String actualStats();

    @Override
    public void act(Enemy enemy, Board board){

        board.attackAction(enemy, this);
    }

    @Override
    public void act(Player player, Board board){
        board.specialAction(this, this); //we can erase the second parameter from the signature in Board
    }

    @Override
    public void act(Empty empty, Board board){
        board.moveAction(this, empty);
    }

    @Override
    public void act(Wall wall, Board board){
        board.doNotMoveAction(this, wall); //we can delete all the parameters from this signature in Board
    }

}
