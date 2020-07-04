package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.TilesPackage.Units.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Hunter extends Player {
    private int range;
    private int arrows_count;
    private int ticks_count=0;

    public Hunter(String[][] p, int x, int y, String s) {
        super(p, x, y, s.charAt(0));
        this.range=Integer.parseInt(p[1][8]);
        this.arrows_count=Integer.parseInt(p[1][7]);
    }

    public void LevelUP(List<String> output) {
        int health = this.HealthPool;
        int attack = this.AttackPoints;
        int defense = this.DefensePoints;
        int arrows_count = this.arrows_count;

        super.LevelUP();

        this.arrows_count=arrows_count+arrows_count*10;
        this.AttackPoints=attack+(2*Level);
        this.DefensePoints=defense+Level;

        output.add(this.Name + " reached level " + this.Level + ": +" + (this.HealthPool - health) + " Health, +" + (this.AttackPoints - attack) +
                " Attack, +" + (this.DefensePoints - defense) + " Defense, +" + (this.arrows_count - arrows_count) + " arrows.");
        if (this.Experience >= (50 * this.Level)) this.updateExperience(0, output);
    }
    @Override
    public int applySpecialAbility(List<Enemy> enemies, List<String> output) {
        if(arrows_count==0)
        {
            enemies.clear();
            output.add(this.Name + " tried to cast Shoot, but there was 0 arrows.");
            return 0;
        }
        arrows_count=arrows_count-1;
        List<Enemy> wantedEnemies = new ArrayList<>(enemies);
        for (Enemy e : enemies){
            if (!(super.range(this.Position, e.Position) <  this.range)) wantedEnemies.remove(e);
        }
        enemies.clear();

        if (wantedEnemies.size() == 0) return 0;

        Enemy min = wantedEnemies.get(0);
        for(Enemy e: wantedEnemies)
        {
            if((super.range(this.Position, e.Position) < super.range(this.Position, min.Position))) min = e;
        }

        enemies.add(min);
        return this.AttackPoints;
    }

    @Override
    public String actualStats() {
        return Name + "  Health: " + HealthAmount + "/" + HealthPool + "  Attack: " + AttackPoints + "  Defense: " + DefensePoints + "  Level: " +
                Level + '\n' + "Experience: " + Experience + "/" + (50*this.Level) + "  Arrows:" + arrows_count+ "  Range: " + range + ".";
    }

    @Override
    public void updateGameTick() {
        if(ticks_count==10)
        {
            arrows_count=arrows_count+Level;
            ticks_count=0;
        }
        else {
            ticks_count=ticks_count+1;
        }
    }
    @Override
    public void updateExperience(int experience, List<String> output) {
        super.updateExperience(experience, output);
        if (this.Experience >= 50 * this.Level) this.LevelUP(output);
    }
}
