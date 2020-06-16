package BusinessLayer.BoardPackage;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Unit;
import BusinessLayer.TilesPackage.Units.Players.Player;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private Tile[][] CurrBoard;
    private Unit CurrPlayer;
    private List<Enemy> EnemyList;
    private boolean PlayerAlive;
    private Creator BoardCreator;
    private List<String>Output;
    private boolean tickcheck = false; //?????????????????????????????


    public Board() {
        this.BoardCreator = new Creator();
        this.CurrBoard = new Tile[0][];
        this.Output = new ArrayList<String>();
        this.EnemyList = new ArrayList<Enemy>();
    }

    public void crateBoard(String [][] level, int playerChoice) {
        CurrBoard = BoardCreator.createBoard(level, playerChoice);
        EnemyList = BoardCreator.getEnemyList();
    }

    public void gameTick(String userInput)
    {
        /*
        if (!userInput.Equals("q")){  //filtrate nonAction choice ==>  but there is a need to perform the perTick updates
            Tile toMoveTo = player.ActionPerTick(userInput);
            this.Action(player, toMoveTo);
        }

        foreach(){
               if(current is dead) = > remove from list
               if(player is dead) = > break the game + return current output list
        }



         */
    }



    private void Action(Tile t1, Tile t2) { // ==>  for overloading purposes
    }

    private void Action (Unit attacker, Unit defender) {
        int attack = randomize(attacker.AttackPoints);
        int defend = randomize(defender.DefensePoints);
        int diff = randomize(attacker.AttackPoints) - randomize(defender.DefensePoints);
        if (diff < 0) diff = 0;
        defender.HealthAmount = defender.HealthAmount - diff;

        this.updateActualStatus(); //observer pattern update method

        this.Output.add(attacker.Name + " engaged in combat with " + defender.Name + ".");
        this.Output.add(attacker.actualStats());
        this.Output.add(defender.actualStats());
        this.Output.add(attacker.Name + " rolled " + attack + " attack points.");
        this.Output.add(defender.Name + " rolled " + defend + " defense points.");
        this.Output.add(attacker.Name + " dealt " + diff + " damage to "+defender.Name+".");

        this.PlayerAlive = this.CurrPlayer.ActualStatus;

        if (!this.PlayerAlive) this.Output.add(this.CurrPlayer.Name+" is dead. Game Over.");
        else if(!defender.ActualStatus){
            this.Output.add(defender.Name + " died. "+attacker.Name+" gained "+ defender.Experience +" experience.");
            //this.Output.AddAll(this.CurrPlayer.updateExperience(defender.Experience)); => updateExperience returns List<String>
        }
    }


    private void Action (Player currPlayer){  // ==>  in case of special ability{

    }

    private void Action (Unit currCharacter, Empty tile) { //  ==>  in case of movement

    }

    private void Action (Unit currCharacter, Wall tile){ // ==> doesn't move

    }


   private int randomize(int bound){
        throw new UnsupportedOperationException();
        //return Math.Random(up to bound)
   }

    private void updateActualStatus() { //observer pattern update method => all the listeners update their actualStatus (= alive/dead (=true/false))
        throw new UnsupportedOperationException();
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
            tickcheck=false;
        }
        /////// special ability!!!!!!
    }


    public void checkRightTile()
    {
        Coordinate temp = Player.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x][y+1].ToString().equals("#"))) {
            if (CurrBoard[x][y + 1].ToString().equals("."))///////////////////////////////////
            {
                Tile change = CurrBoard[x][y + 1];
                CurrBoard[x][y + 1] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            } else Combat(Player,"R");
        }
        tickcheck=true;
    }
    public void checkLeftTile()
    {
        Coordinate temp = Player.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x][y-1].ToString().equals("#"))) {
            if (CurrBoard[x][y - 1].ToString().equals("."))/////////////////////////////////////
            {
                Tile change = CurrBoard[x][y - 1];
                CurrBoard[x][y - 1] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            } else Combat(Player,"L");
        }
        tickcheck=true;
    }
    public void checkDownTile()
    {
        Coordinate temp = Player.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x-1][y].ToString().equals("#"))) {
            if (CurrBoard[x-1][y].ToString().equals("."))/////////////////////////////////////
            {
                Tile change = CurrBoard[x][y - 1];
                CurrBoard[x-1][y] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            }else Combat(Player,"D");
        }
        tickcheck=true;
    }
    public void checkUPTile()
    {
        Coordinate temp = Player.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x+1][y].ToString().equals("#"))) {
            if (CurrBoard[x+1][y].ToString().equals("."))/////////////////////////////////////
            {
                Tile change = CurrBoard[x+1][y];
                CurrBoard[x+1][y] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            }
            else Combat(Player,"U");
        }
        tickcheck=true;
    }
    private void Combat(Tile A,String derc){}
}
