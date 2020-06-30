package BusinessLayer.TilesPackage.Units.Enemies;

import BusinessLayer.Coordinate;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean visible;

    public Trap(String[][] boardData, int x, int y, String s) {
        super(boardData, x, y, s.charAt(0));
        this.Experience = Integer.parseInt(boardData[1][5]);
        this.visibilityTime = Integer.parseInt(boardData[1][6]);
        this.invisibilityTime = Integer.parseInt(boardData[1][7]);
        this.tickCount = this.visibilityTime;
        this.visible = true;
    }


    @Override
    public Coordinate actionPerTick(Coordinate currUserPosition) {
        if (this.range(this.getPosition(), currUserPosition) < 2) return currUserPosition;
        return new Coordinate(0, 0);
    }


    @Override
    public void updateGameTick() {
        this.tickCount = this.tickCount - 1;

        if (this.tickCount <= 0){
            if (this.visible){
                this.visible = false;
                this.tickCount = this.invisibilityTime;
            }
            else {
                this.visible = true;
                this.tickCount = this.visibilityTime;
            }
        }
    }


    @Override
    public String toString() {
        if (visible)
            return Name.charAt(0) + "";
        else return '.' + "";
    }


    @Override
    public String actualStats() {
        return Name + "  Health: " + HealthAmount + "/" + HealthPool + "  Attack: " + AttackPoints + "  Defense: " + DefensePoints + "  Experience Value: " + Experience;
    }


    @Override
    public Coordinate actionPerTick(String s) {
        return new Coordinate(-1, -1);
    }
}
