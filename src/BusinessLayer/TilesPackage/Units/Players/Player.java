package BusinessLayer.TilesPackage.Units.Players;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Unit;
import java.util.List;

public class Player extends Unit {

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

    public int applySpecialAbility(List<Enemy> enemies, List<String> output){ //returns the damage that will be caused to randomly (depends on players class) chosen enemies
        //special ability logic and prints
        throw new UnsupportedOperationException();
    }

    public void updateExperience(int experience, List<String> output){

    }


    //updatePosition

    @Override
    public Coordinate actionPerTick(){
        throw new UnsupportedOperationException();
    }

    @Override
    public String status() {
        return null;
    }

    @Override
    public String actualStats() {
        throw new UnsupportedOperationException();
    }

}
