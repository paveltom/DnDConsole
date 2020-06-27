package BusinessLayer.TilesPackage.Units.Enemies;

import BusinessLayer.Coordinate;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

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
        int x = this.Position.getColumnCoordinate();
        int y = this.Position.getRowCoordinate();

        if (range(this.getPosition(), currUserPosition) < this.visionRange) {
            int dx = this.getPosition().getColumnCoordinate() - currUserPosition.getColumnCoordinate();
            int dy = this.getPosition().getRowCoordinate() - currUserPosition.getRowCoordinate();
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) return new Coordinate(x - 1, y); //moveleft
                else return new Coordinate(x + 1, y); //move right
            } else {
                if (dy > 0) return new Coordinate(x, y - 1); //move up
                else return new Coordinate(x, y + 1); // move down
            }
        } else {
            List<Coordinate> randomAction = new ArrayList<>();
            randomAction.add(new Coordinate(x - 1, y));
            randomAction.add(new Coordinate(x + 1, y));
            randomAction.add(new Coordinate(x, y - 1));
            randomAction.add(new Coordinate(x, y + 1));
            randomAction.add(new Coordinate(x, y));
            int rnd = (int) (Math.random() * 5); // maybe it is possible to avoid this casting
            return randomAction.get(rnd); //perform random action
        }
    }

    public void updateGameTick() {
    }

    public int getVisionRange() {
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
