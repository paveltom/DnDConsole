package BusinessLayer.TilesPackage.Units.Players;

public class Rogue extends Player {
    public int cost;
    public int energy=100;

    public Rogue(String[][] p, int x, int y, String s) {
        super(p, x, y,s.charAt(0));
        cost=Integer.parseInt(p[1][5]);
    }
    public void LevelUP() {
        super.LevelUP();
        energy = 100;
        AttackPoints = AttackPoints + (3 * Level);
    }

//    public void Ongametick()
//    {
//        energy=Math.min(energy+10,100);
//    }

//    public void On_ability_cast()
//    {
//        if(energy<cost)
//            System.out.println("Error Massage");
//        else {
//            energy=energy-cost;
//
//        }
//    }
    @Override
    public String actualStats ()
    {
        return Name+"  Health: "+HealthAmount+"/"+HealthPool+"  Attack: "+AttackPoints+"  Defense: "+DefensePoints+"  Level: "+Level+'\n'+"Experience: "+Experience+"/"+50+"  Energy:"+energy;
    }
}
