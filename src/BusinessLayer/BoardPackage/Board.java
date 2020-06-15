package BusinessLayer.BoardPackage;

import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Unit;

import java.util.List;

public class Board {
    private Tile[][] CurrBoard;
    private Unit Player;
    private List<Enemy> EnemyList;
    private boolean PlayerAlive;
    private Creator create;
    private boolean tickcheck=false;


    public Board()
    {
        create = new Creator();
        CurrBoard =new Tile[0][];
    }
    public void crateBoard (String [][] level,int player)
    {
        CurrBoard=create.createBoard(level,player);
        EnemyList=create.getEnemyList();
    }
    public void GameTick(String s)
    {

    }
    public void PlayerTick(String s) throws Exception {
        if(s!="e") {
            if (s == "d")
                checkRightTile();
            else if (s == "a")
                checkLeftTile();
            else if (s == "s")
                checkDownTile();
            else if (s == "w")
                checkUPTile();
            if(!tickcheck)
                throw new Exception();
        }
        
    }
    public void checkRightTile()
    {
        Coordinate temp = Player.getPosition();
        int x=temp.getRowCoordinate();
        int y=temp.getolumnCoordinate();
        if(!(CurrBoard[x][y+1].getCharacter()instanceof Wall)) {
            if (CurrBoard[x][y + 1].getCharacter() instanceof Empty)///////////////////////////////////
            {
                Tile change = CurrBoard[x][y + 1];
                CurrBoard[x][y + 1] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            } else Combat(Player,"R");
        }
    }
    public void checkLeftTile()
    {
        Coordinate temp = Player.getPosition();
        int x=temp.getRowCoordinate();
        int y=temp.getolumnCoordinate();
        if(!(CurrBoard[x][y-1].getCharacter()instanceof Wall)) {
            if (CurrBoard[x][y - 1].getCharacter() instanceof Empty)/////////////////////////////////////
            {
                Tile change = CurrBoard[x][y - 1];
                CurrBoard[x][y - 1] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            } else Combat(Player,"L");
        }

    }
    public void checkDownTile()
    {
        Coordinate temp = Player.getPosition();
        int x=temp.getRowCoordinate();
        int y=temp.getolumnCoordinate();
        if(!(CurrBoard[x-1][y].getCharacter()instanceof Wall)) {
            if (CurrBoard[x-1][y].getCharacter() instanceof Empty)/////////////////////////////////////
            {
                Tile change = CurrBoard[x][y - 1];
                CurrBoard[x-1][y] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            }else Combat(Player,"D");
        }

    }
    public void checkUPTile()
    {
        Coordinate temp = Player.getPosition();
        int x=temp.getRowCoordinate();
        int y=temp.getolumnCoordinate();
        if(!(CurrBoard[x+1][y].getCharacter()instanceof Wall)) {
            if (CurrBoard[x+1][y].getCharacter() instanceof Empty)/////////////////////////////////////
            {
                Tile change = CurrBoard[x+1][y];
                CurrBoard[x+1][y] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            }
            else Combat(Player,"U");
        }
    }
    private void Combat(Tile A,String derc){}
}
