package BusinessLayer.TilesPackage.Units.Enemies;

import BusinessLayer.Coordinate;
import org.w3c.dom.Node;

public class Monster extends Enemy {
    private int vision_range;

    public Monster(String[][] boardData, int x, int y)
    {
        super(boardData, x, y);
        this.vision_range=Integer.parseInt(boardData[1][5]);
        experience_value=Integer.parseInt(boardData[1][6]);
    }
    public String toString(){
        if(Name.charAt(0)=='L') {
            if (vision_range == 3)
                return "s";
            else return "k";
        }
        else if(Name.charAt(0)=='Q') {
            if (vision_range == 5)
                return "q";
            else return "C";
        }
        else if(Name.charAt(0)=='W') {
            if (vision_range == 3)
                return "z";
            else return "w";
        }
        else if(Name.charAt(0)=='T')
            return "M";
        else if(Name.charAt(0)=='B')
            return "b";
        else if(Name.charAt(0)=='G')
            return "g";
        else if(Name.charAt(0)=='K')
            return "K";
    }
    @Override
    public Coordinate actionPerTick() {
        return null;
    }
}
