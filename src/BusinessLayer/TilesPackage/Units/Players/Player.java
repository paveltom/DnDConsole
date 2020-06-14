package BusinessLayer.TilesPackage.Units.Players;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Units.Unit;

public class Player extends Unit {

    private Integer experience;
    protected Integer level;

    public Player(String[][] p){
        super(p);
        if(p[1][0]=="Warrior")
            new Warrior(p);
        else if(p[1][0]=="Warrior") {
            new Mage(p);
        }
        new Rogue(p);

        level=1;
        experience=0;
    }
    public void LevelUP()
    {
        experience=experience-50*level;
        level=level+1;
        super.leveluphealth(10,level);
    }

    @Override
    public String actualStats() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Coordinate actionPerTick(){
        throw new UnsupportedOperationException();
    }

}
