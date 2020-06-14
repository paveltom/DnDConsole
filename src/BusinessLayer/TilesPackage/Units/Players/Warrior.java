package BusinessLayer.TilesPackage.Units.Players;

public class Warrior extends Player {
    private int coolDown;
    private int coolDowncaunter;

    public Warrior(String[][] p) {
        super(p);
        String s=p[1][7];
        coolDown=Integer.parseInt(s);
        coolDowncaunter=0;
    }
    public void LevelUP()
    {
        super.LevelUP();
        coolDowncaunter=0;
        super.leveluphealth(5,super.level);
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
}
