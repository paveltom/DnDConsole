package BusinessLayer.TilesPackage.Units.Players;

public class Rogue extends Player {
    public int cost;
    public int energy=100;

    public Rogue(String[][] p, int x, int y) {
        super(p, x, y);
        cost=Integer.parseInt(p[1][5]);
    }
    public void LevelUP() {
        super.LevelUP();
        energy = 100;
        AttackPoints = AttackPoints + (3 * Level);
    }
}
