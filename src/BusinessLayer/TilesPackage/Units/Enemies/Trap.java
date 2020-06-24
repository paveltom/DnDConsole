package BusinessLayer.TilesPackage.Units.Enemies;

import BusinessLayer.Coordinate;

public class Trap extends Enemy {
    private int visibility_time;
    private int invisibility_time;
    private int tick_count;
    private boolean visible;

    public Trap(String[][] boardData, int x, int y, String s) {
        super(boardData, x, y,s.charAt(0));
        experience_value=Integer.parseInt(boardData[1][5]);
        visibility_time=Integer.parseInt(boardData[1][6]);
        invisibility_time=Integer.parseInt(boardData[1][7]);
        tick_count=visibility_time;
        visible=tick_count<visibility_time;
    }

    public String toString ()
    {
        visible=tick_count<visibility_time;
        if(visible)
            return Name.charAt(0)+"";
        else return '.'+"";
    }
    //toString: if visible return 'B'\'Q'\'D', else return '.'

    @Override
    public Coordinate actionPerTick(Object currentUserPositionCoordinate) {
        if(tick_count==(visibility_time+invisibility_time))
            tick_count=0;
        else tick_count++;
        return null;
    }
    @Override
    public String status()
    {
        return Name+"  Health: "+HealthAmount+"/"+HealthPool+"  Attack: "+AttackPoints+"  Defense: "+DefensePoints+"  Experience Value: "+experience_value;
    }
}
