public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    //getleft
    public int getolumnCoordinate(){ return this.x;}
    public int getRowCoordinate(){ return this.y;}

    public String ToString(){
        return "<"+x+","+y+">";
    }

    //getright
    //tostring


}
