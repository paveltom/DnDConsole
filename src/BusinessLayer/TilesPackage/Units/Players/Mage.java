package BusinessLayer.TilesPackage.Units.Players;

public class Mage extends Player {
    private int manapool;
    private int curmana;
    private int manacost;
    private int spellpower;
    private int hitcount;
    private int range;

    public Mage(String[][] boardData, int x, int y) {
        super(boardData, x, y);
        manapool=Integer.parseInt(boardData[1][5]);
        curmana=manacost;
        manacost=Integer.parseInt(boardData[1][6]);
        spellpower=Integer.parseInt(boardData[1][7]);
        hitcount=Integer.parseInt(boardData[1][8]);
        range=Integer.parseInt(boardData[1][9]);
    }
    public void levelUp()
    {
        super.LevelUP();
        manapool=manapool+(25*Level);
        curmana=Math.min(curmana+manapool/4,manapool);
        spellpower=spellpower+(10*Level);
    }
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
}
