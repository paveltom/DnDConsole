package BusinessLayer;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    //getleft
    public int getColumnCoordinate(){ return this.x;}
    public int getRowCoordinate(){ return this.y;}

    public void setPosition(int column, int row) {
        this.x = column;
        this.y = row;
    }

    public String ToString(){
        return "<"+x+","+y+">";
    }
}
