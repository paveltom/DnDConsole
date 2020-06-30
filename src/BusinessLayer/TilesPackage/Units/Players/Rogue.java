package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.TilesPackage.Units.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Rogue extends Player {

    private int cost;
    private int energy = 100;

    public Rogue(String[][] p, int x, int y, String s) {
        super(p, x, y, s.charAt(0));
        cost = Integer.parseInt(p[1][6]);
    }


    public void LevelUP(List<String> output) {
        int health = this.HealthPool;
        int attack = this.AttackPoints;
        int defense = this.DefensePoints;

        super.LevelUP();

        energy = 100;
        AttackPoints = AttackPoints + (3 * Level);

        output.add(this.Name + " reached level " + this.Level + ": +" + (this.HealthPool - health) + " Health, +" + (this.AttackPoints - attack) +
                " Attack, +" + (this.DefensePoints - defense) + " Defense."); //make sure it is printable in appropriate way in Console
    }


    @Override
    public int applySpecialAbility(List<Enemy> enemies, List<String> output) {
        if (energy < cost) {
            output.add(this.Name + " tried to cast Fan of Knives, but there was not enough energy: " + this.energy + "/" + this.cost + ".");
            return 0;
        }

        energy = energy - cost;

        List<Enemy> wantedEnemies = new ArrayList<>(enemies);
        for (Enemy e : enemies){ //sorting the 'enemies' so only the Enemies in the range of 2 will not be removed
            if (!(super.range(this.Position, e.Position) < 2)) wantedEnemies.remove(e);
        }
        enemies.clear();
        for (Enemy e : wantedEnemies) enemies.add(e);

        output.add(this.Name + " cast Fan of Knives.");
        return this.AttackPoints;
    }


    @Override
    public void updateGameTick() {
        energy = Math.min(energy + 10, 100);
    }



    @Override
    public void updateExperience(int experience, List<String> output) {
        super.updateExperience(experience, output);
        if (this.Experience >= 50 * this.Level) this.LevelUP(output);
    }


    @Override
    public String actualStats() {
        return Name + "  Health: " + HealthAmount + "/" + HealthPool + "  Attack: " + AttackPoints + "  Defense: " + DefensePoints + "  Level: " +
                Level + '\n' + "Experience: " + Experience + "/" + (50*this.Level) + "  Energy:" + energy;
    }
}
