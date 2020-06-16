package BusinessLayer.TilesPackage.Units.Players;

public class Rogue extends Player {
   private int cost;
   private int energy=100;

    public Rogue(String[][] p, int x, int y) {
        super(p, x, y);
        cost=Integer.parseInt(p[1][5]);
    }
    public void levelUP()
    {
        super.LevelUP();
        energy=100;
        attackPoints=attackPoints+(3*Level);
    }
}
