package BusinessLayer.TilesPackage.Units.Players;

public class Warrior extends Player {
    public int coolDown;
    public int coolDowncaunter;

    public Warrior(String[][] p, int x, int y, String s) {
        super(p, x, y,s.charAt(0));
        String t=p[1][7];
        coolDown=Integer.parseInt(t);
        coolDowncaunter=0;
    }
    public void LevelUP()
    {
        super.LevelUP();
        coolDowncaunter=0;
        //self levelUp method implement here
    }
    public void Ongametick()
    {
        coolDowncaunter=coolDowncaunter-1;
    }
    public void Onabilitycast() throws Exception {
        if(coolDowncaunter>0) throw new Exception();
        else {
            coolDowncaunter=coolDown;
        }
    }
    @Override
    public String status()
    {
        return Name+"  Health: "+HealthAmount+"/"+HealthPool+"  Attack: "+AttackPoints+"  Defense: "+DefensePoints+"  Level: "+Level+'\n'+"Experience: "+Experience+"/"+50+"  Cooldown: "+coolDowncaunter+"/"+coolDown;
    }
}
