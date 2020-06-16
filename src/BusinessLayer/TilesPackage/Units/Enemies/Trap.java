package BusinessLayer.TilesPackage.Units.Enemies;

public class Trap extends Enemy {

    public Trap(String[][] boardData, int x, int y) {
        super(boardData, x, y);
    }

    //toString: if visible return 'B'\'Q'\'D', else return '.'
}
