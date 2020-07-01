package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.TilesPackage.Units.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player {
    private int manaPool;
    private int currMana;
    private int manaCost;
    private int spellPower;
    private int hitCount;
    private int abilityRange;

    public Mage(String[][] boardData, int x, int y, String s) {
        super(boardData, x, y, s.charAt(0));
        this.manaPool = Integer.parseInt(boardData[1][7]);
        this.manaCost = Integer.parseInt(boardData[1][8]);
        this.currMana = this.manaPool / 4;
        this.spellPower = Integer.parseInt(boardData[1][9]);
        this.hitCount = Integer.parseInt(boardData[1][10]);
        this.abilityRange = Integer.parseInt(boardData[1][11]);
    }


    public void LevelUP(List<String> output) {
        int health = this.HealthPool;
        int attack = this.AttackPoints;
        int defense = this.DefensePoints;
        int maxMana = this.manaPool;
        int spellPwr = this.spellPower;

        super.LevelUP();

        this.manaPool = this.manaPool + (25 * Level);
        this.currMana = Math.min(this.currMana + this.manaPool / 4, this.manaPool);
        this.spellPower = this.spellPower + (10 * Level);

        output.add(this.Name + " reached level " + this.Level + ": +" + (this.HealthPool - health) + " Health, +" + (this.AttackPoints - attack) +
                " Attack, +" + (this.DefensePoints - defense) + " Defense, +" + (this.manaPool - maxMana) + " maximum mana, +" +
                (this.spellPower - spellPwr) + " spell power."); //make sure it is printable in appropriate way in Console
        if (this.Experience >= (50 * this.Level)) this.updateExperience(0, output);
    }


    @Override
    public int applySpecialAbility(List<Enemy> enemies, List<String> output) {
        if (this.currMana < this.manaCost) { //in case there is no enough 'mana'
            enemies.clear();
            output.add(this.Name + " tried to cast Blizzard, but there was not enough mana: " + this.currMana + "/" + this.manaPool + ".");
            return 0;
        }

        this.currMana = this.currMana - this.manaCost;

        List<Enemy> wantedEnemies = new ArrayList<>(enemies);
        for (Enemy e : enemies){
            if (!(super.range(this.Position, e.Position) <  this.abilityRange)) wantedEnemies.remove(e); //change it back!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
        enemies.clear();
        if (!(wantedEnemies.size() == 0)) {
            for (Enemy e : wantedEnemies) enemies.add(e);

            //next algorithm adjusting 'enemies' list size to amount of 'hits' available to this Mage and
            // then randomize the order the Enemies in 'enemies' list will be attacked.
            int random;

            while (this.hitCount > enemies.size()) {
                random = this.randomize(enemies.size());
                enemies.add(enemies.get(random));
            }

            while (this.hitCount < enemies.size()) {
                random = this.randomize(enemies.size());
                enemies.remove(enemies.get(random));
            }

            this.shuffleList(enemies);
        }

        output.add(this.Name + " cast Blizzard.");
        return this.spellPower;
    }

    @Override
    public void updateExperience(int experience, List<String> output) {
        super.updateExperience(experience, output);
        if (this.Experience >= 50 * this.Level) this.LevelUP(output);
    }

    @Override
    public void updateGameTick() {
        this.currMana = Math.min(this.manaPool, this.currMana + Level);
    }


    @Override
    public String actualStats () {
        return Name + "  Health: " + HealthAmount + "/" + HealthPool + "  Attack: " + AttackPoints + "  Defense: " + DefensePoints + "  Level: " + Level +
                '\n' + "Experience: " + Experience + "/" + (50*this.Level) + "  Mana: " + this.currMana + "/" + this.manaPool + "  Spell Power: "+this.spellPower;
    }


    private static void shuffleList(List<Enemy> list) { //shuffling the List using random indexes
        int n = list.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(list, i, change);
        }
    }
    private static void swap(List<Enemy> a, int i, int change) { //help-method of shuffleList
        Enemy temp = a.get(i);
        a.set(i, a.get(change));
        a.set(change, temp);
    }


    private int randomize(int bound) { //randomize an number !not! including the bound
        int rand = (int) (Math.random() * bound); // maybe it is possible to avoid this casting
        return rand;
    }
}
