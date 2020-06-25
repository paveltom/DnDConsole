package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import java.util.List;

public class Warrior extends Player {

    private int coolDown;
    private int coolDownCounter; //changes on game tick

    public Warrior(String[][] p, int x, int y, String s) {
        super(p, x, y, s.charAt(0));
        String t = p[1][7];
        coolDown = Integer.parseInt(t);
        coolDownCounter = 0;
    }

    @Override
    public int applySpecialAbility(List<Enemy> enemies, List<String> output) {
        if (coolDownCounter > 0) {
            output.add(this.Name + " tried to cast Avenger's Shield, but there is a remaining cooldown: " + this.coolDownCounter + ".");
            return 0;
        }

        this.coolDownCounter = this.coolDown;
        this.HealthAmount = Math.min(this.HealthAmount + (this.DefensePoints * 10), this.HealthPool);

        for (Enemy e : enemies){
            if (!(super.range(this.Position, e.Position) < 3)) enemies.remove(e);
        }
        int index = (int) (Math.random() * enemies.size());
        Enemy chosen = enemies.get(index);
        enemies.clear();
        enemies.add(chosen);

        output.add(this.Name + " used Avenger's Shield, healing for: " + (10 * this.DefensePoints) + ".");
        return this.HealthPool / 10;
    }

    @Override
    public void updateExperience(int experience, List<String> output) {
        super.updateExperience(experience, output);
        if (this.Experience >= 50 * this.Level) this.LevelUP(output);
    }

    public void LevelUP(List<String> output) {
        int health = this.HealthPool;
        int attack = this.AttackPoints;
        int defense = this.DefensePoints;

        super.LevelUP();

        coolDownCounter = 0;
        this.HealthPool = this.HealthPool + (5 * this.Level);
        this.AttackPoints = this.AttackPoints + (2 * this.Level);
        this.DefensePoints = this.DefensePoints + this.Level;

        output.add(this.Name + " reached level " + this.Level + ": +" + (this.HealthPool - health) + " Health, +" + (this.AttackPoints - attack) +
                " Attack, +" + (this.DefensePoints - defense) + " Defense.");
    }

    @Override
    public void updateGameTick() {
        if (coolDownCounter > 0) coolDownCounter = coolDownCounter - 1;
    }

    @Override
    public String actualStats() {
        return Name + "  Health: " + HealthAmount + "/" + HealthPool + "  Attack: " + AttackPoints + "  Defense: " + DefensePoints + "  Level: " + Level + '\n' + "Experience: " + Experience + "/" + 50 + "  Cooldown: " + coolDownCounter + "/" + coolDown;
    }


}
