package BusinessLayer.TilesPackage.Units.Players;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Tile;
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
        this.Experience = this.Experience+experience; //override with LevelUp statement in each player.Class
    }


    //updatePosition

    @Override
    public Coordinate actionPerTick(String userInput){
        int x = this.Position.getColumnCoordinate();
        int y = this.Position.getRowCoordinate();
        switch (userInput) {
            case "w":
                return new Coordinate(x, y+1);
            case "a":
                return new Coordinate(x-1, y);
            case "s":
                return new Coordinate(x, y-1);
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


}
