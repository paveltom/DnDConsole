package BusinessLayer.TilesPackage.Units.Enemies;

import BusinessLayer.Coordinate;
import org.w3c.dom.Node;

public class Monster extends Enemy {

    private int visionRange;

    public Monster(String[][] boardData, int x, int y, String s) {
        super(boardData, x, y, s.charAt(0));
        this.visionRange = Integer.parseInt(boardData[1][5]);
        Experience = Integer.parseInt(boardData[1][6]);
    }


    public String toString() {
        if (Name.charAt(0) == 'L') {
            if (visionRange == 3)
                return "s";
            else return "k";
        } else if (Name.charAt(0) == 'Q') {
            if (visionRange == 5)
                return "q";
            else return "C";
        } else if (Name.charAt(0) == 'W') {
            if (visionRange == 3)
                return "z";
            else return "w";
        } else if (Name.charAt(0) == 'T')
            return "M";
        else if (Name.charAt(0) == 'B')
            return "b";
        else if (Name.charAt(0) == 'G')
            return "g";
        else return "K";
    }

    public Coordinate actionPerTick(Coordinate currUserPosition) {
        if (range(this.getPosition(), currUserPosition) < this.visionRange){
            int dx = this.getPosition().getColumnCoordinate() - currUserPosition.getColumnCoordinate();
            int dy = this.getPosition().getRowCoordinate() - currUserPosition.getRowCoordinate();
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) return null;//moveleft
                else return null; //move right
            }
            else {
                if (dy > 0) return null; //move up
                else return null; // move down
            }
        }
        else return null; //perform random action
        
        /*
        if range(monster, player) < vision range then
           dx ← enemyX − playerX
           dy ← enemyY − playerY
           if |dx| > |dy| then
              if dx > 0 then
                 Move left
              else
                 Move right
           else
              if dy > 0 then
                 Move up
              else
                 Move down
        else
           Perform a random movement action: left, right, up, down or stay at the same place.
         */

    }

    public void updateGameTick() {
    }

    public int getvisionRange() {
        return visionRange;
    }


    @Override
    public Coordinate actionPerTick(String s) {
        return new Coordinate(-1, -1);
    }


    @Override
    public String actualStats() {
        return Name + "  Health: " + HealthAmount + "/" + HealthPool + "  Attack: " + AttackPoints + "  Defense: " + DefensePoints +
                "  Experience Value: " + Experience + '\n' + "Vision Range: " + visionRange;
    }
}
