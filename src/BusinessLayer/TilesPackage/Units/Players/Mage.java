package BusinessLayer.TilesPackage.Units.Players;

public class Mage extends Player {
    public int manapool;
    public int curmana;
    public int manacost;
    public int spellpower;
    public int hitcount;
    public int range;

    public Mage(String[][] boardData, int x, int y, String s) {
        super(boardData, x, y,s.charAt(0));
        manapool=Integer.parseInt(boardData[1][5]);
        curmana=manapool/4;
        manacost=Integer.parseInt(boardData[1][6]);
        spellpower=Integer.parseInt(boardData[1][7]);
        hitcount=Integer.parseInt(boardData[1][8]);
        range=Integer.parseInt(boardData[1][9]);
    }

    public void LevelUP()
    {
        super.LevelUP();
        manapool=manapool+(25*Level);
        curmana=Math.min(curmana+manapool/4,manapool);
        spellpower=spellpower+(10*Level);
    }

//    @Override
//    public void LevelUP() {
//        super.LevelUP();
//    }


    public void Ontickgame()
    {
        curmana=Math.min(manapool,curmana+Level);
    }
    public void Onabilitycost() throws Exception {
        if(curmana<manacost) throw new Exception();
        else {
            curmana=curmana-manacost;
            int hit=0;
            while(hit<hitcount){////////////////////

            }
        }
    }
    @Override
    public String status ()
    {
        return Name+"  Health: "+HealthAmount+"/"+HealthPool+"  Attack: "+AttackPoints+"  Defense: "+DefensePoints+"  Level: "+Level+'\n'+"Experience: "+Experience+"/"+50+"  Mana: "+manapool/4+"/"+manapool;
    }
}
