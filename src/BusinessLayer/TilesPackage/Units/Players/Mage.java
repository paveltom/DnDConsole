package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.TilesPackage.Units.Enemies.Enemy;

import java.util.List;

public class Mage extends Player {
    private int manaPool;
    private int currMana;
    private int manaCost;
    private int spellPower;
    private int hitCount;
    private int abilityRange;

    public Mage(String[][] boardData, int x, int y, String s) {
        super(boardData, x, y, s.charAt(0));
        this.manaPool = Integer.parseInt(boardData[1][5]);
        this.currMana = this.manaCost / 4;
        this.manaCost = Integer.parseInt(boardData[1][6]);
        this.spellPower = Integer.parseInt(boardData[1][7]);
        this.hitCount = Integer.parseInt(boardData[1][8]);
        abilityRange = Integer.parseInt(boardData[1][9]);
    }

    public void LevelUP(List<String> output) {
        super.LevelUP();
        this.manaPool = this.manaPool + (25 * Level);
        this.currMana = Math.min(this.currMana + this.manaPool / 4, this.manaPool);
        this.spellPower = this.spellPower + (10 * Level);
    }

    @Override
    public int applySpecialAbility(List<Enemy> enemies, List<String> output) {
        if (this.currMana < this.manaCost) {
            enemies.clear();
            output.add(this.Name + " tried to cast Blizzard, but there was not enough mana: " + this.currMana + "/" + this.manaPool + ".");
            return 0;
        }

        this.currMana = this.currMana - this.manaCost;

        int hits = 0;
        for (Enemy e : enemies){
            if (!(super.range(this.Position, e.Position) < this.abilityRange)) enemies.remove(e);
        }

        //add same enemy to the list untill enemies.size<hits

        return this.spellPower;


        /*

        for (Enemy e : enemies){
            if (!(super.range(this.Position, e.Position) < 3)) enemies.remove(e);
        }
        int index = (int) (Math.random() * enemies.size());
        Enemy chosen = enemies.get(index);
        enemies.clear();
        enemies.add(chosen);

        output.add(this.Name + " used Avenger's Shield, healing for: " + (10 * this.DefensePoints) + ".");
        return this.HealthPool / 10;

 */


    }


    public void Ontickgame()
    {
        this.currMana=Math.min(this.manaPool,this.currMana+Level);
    }
    public void Onabilitycost() throws Exception {
        if (this.currMana < this.manaCost) throw new Exception();
        else {
            this.currMana = this.currMana - this.manaCost;
            int hit = 0;
            while (hit < this.hitCount) {////////////////////

            }
        }
    }

    @Override
    public String actualStats () {
        return Name + "  Health: " + HealthAmount + "/" + HealthPool + "  Attack: " + AttackPoints + "  Defense: " + DefensePoints +
                "  Level: " + Level + '\n' + "Experience: " + Experience + "/" + 50 + "  Mana: " + this.manaPool / 4 + "/" + this.manaPool;
    }

    @Override
    public void updateGameTick() {

    }
}
