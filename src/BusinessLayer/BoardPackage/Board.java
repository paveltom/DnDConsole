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
    private List<String> Output;
    private String playerselection;  //????????????????????
    private boolean tickcheck = false; //?????????????????????????????


    public Board() {
        this.BoardCreator = new Creator();
        this.CurrBoard = new Tile[0][];
        this.Output = new ArrayList<String>();
        this.EnemyList = new ArrayList<Enemy>();
        playerselection=BoardCreator.PlayerSelection();
    }
    
    public String playerselection(){ return playerselection;}
    
    public void crateBoard(String [][] level, int playerChoice) {
        CurrBoard = BoardCreator.createBoard(level, playerChoice);
        EnemyList = BoardCreator.getEnemyList();
        CurrPlayer=BoardCreator.getCurrPlayer();
    }
    
    public Unit getCurrPlayer(){ return CurrPlayer;}
    
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




         dont forget to clear the Output list!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         */
    }



    private void Action(Tile t1, Tile t2) { // ==>  for overloading purposes
    }

    private void Action (Unit attacker, Unit defender) {
        this.attackDefend(attacker, defender);
        this.updateActualStatus(); //observer pattern update method

        this.Output.add(attacker.Name + " engaged in combat with " + defender.Name + ".");
        this.Output.add(attacker.actualStats());
        this.Output.add(defender.actualStats());
        //attack / defend string are added in this.attackDefend method

        this.PlayerAlive = this.CurrPlayer.ActualStatus;

        if (!this.PlayerAlive) this.Output.add(this.CurrPlayer.Name+" is dead. Game Over.");
        else if(!defender.ActualStatus){
            this.Output.add(defender.Name + " died. "+attacker.Name+" gained "+ defender.Experience +" experience.");
            //this.Output.AddAll(this.CurrPlayer.updateExperience(defender.Experience)); => updateExperience returns List<String>
        }
    }


    private void Action (Player currPlayer, Player currPlayerClone){  // ==>  in case of special ability
        //output.add (currPlayer.SpecAbilityPrint);
        List<Enemy> enemiesToAttack = new ArrayList<Enemy>(this.EnemyList);
        int attack = currPlayer.applySpecialAbility(this.EnemyList); //the list is updated in player class itself
        for(Enemy e : enemiesToAttack) {

            // Maybe this.Action (currPlayer, e);??????????????????????

            this.attackDefend(currPlayer, e);
            this.updateActualStatus(); //observer pattern update method
        }
        
    }

    private void Action (Unit currCharacter, Empty tile) { //  ==>  in case of movement
        int columnToGo = tile.getPosition().getColumnCoordinate();
        int rowToGo = tile.getPosition().getRowCoordinate();
        int columnToLeave = currCharacter.getPosition().getColumnCoordinate();
        int rowToLeave = currCharacter.getPosition().getRowCoordinate();
        this.CurrBoard[rowToGo][columnToGo] = currCharacter;
        this.CurrBoard[rowToLeave][columnToLeave] = tile;
        currCharacter.getPosition().setPosition(columnToGo, rowToGo);
        tile.getPosition().setPosition(columnToLeave, rowToLeave);
    }

    private void Action (Unit currCharacter, Wall tile){ // ==> doesn't move
        
    }

    private void attackDefend (Unit attacker, Unit defender){
        int attack = randomize(attacker.AttackPoints);
        int defend = randomize(defender.DefensePoints);
        int diff = attack - defend;
        if (diff < 0) diff = 0;
        defender.HealthAmount = defender.HealthAmount - diff;
        this.Output.add(attacker.Name + " rolled " + attack + " attack points.");
        this.Output.add(defender.Name + " rolled " + defend + " defense points.");
        this.Output.add(attacker.Name + " dealt " + diff + " damage to "+defender.Name+".");
    }


   private int randomize(int bound){
        throw new UnsupportedOperationException();
        //return Math.Random(up to bound)
   }

    private void updateActualStatus() { //observer pattern update method => all the listeners update their actualStatus (= alive/dead (=true/false))
        throw new UnsupportedOperationException();
    }

    private void updateGameTick() { //observer pattern update method => all the listeners update their tickDepended fields (for exmp. Warrior's cooldown)
        throw new UnsupportedOperationException();
    }

    //combatResultMethod that adds strings to output in the end of an combat action (specialAbility or combat)































































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
        Coordinate temp = CurrPlayer.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x][y+1].ToString().equals("#"))) {
            if (CurrBoard[x][y + 1].ToString().equals("."))///////////////////////////////////
            {
                Tile change = CurrBoard[x][y + 1];
                CurrBoard[x][y + 1] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            } else Combat(CurrPlayer,"R");
        }
        tickcheck=true;
    }
    public void checkLeftTile()
    {
        Coordinate temp = CurrPlayer.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x][y-1].ToString().equals("#"))) {
            if (CurrBoard[x][y - 1].ToString().equals("."))/////////////////////////////////////
            {
                Tile change = CurrBoard[x][y - 1];
                CurrBoard[x][y - 1] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            } else Combat(CurrPlayer,"L");
        }
        tickcheck=true;
    }
    public void checkDownTile()
    {
        Coordinate temp = CurrPlayer.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x-1][y].ToString().equals("#"))) {
            if (CurrBoard[x-1][y].ToString().equals("."))/////////////////////////////////////
            {
                Tile change = CurrBoard[x][y - 1];
                CurrBoard[x-1][y] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            }else Combat(CurrPlayer,"D");
        }
        tickcheck=true;
    }
    public void checkUPTile()
    {
        Coordinate temp = CurrPlayer.getPosition();
        int y=temp.getRowCoordinate();
        int x=temp.getolumnCoordinate();
        if(!(CurrBoard[x+1][y].ToString().equals("#"))) {
            if (CurrBoard[x+1][y].ToString().equals("."))/////////////////////////////////////
            {
                Tile change = CurrBoard[x+1][y];
                CurrBoard[x+1][y] = CurrBoard[x][y];
                CurrBoard[x][y] = change;
            }
            else Combat(CurrPlayer,"U");
        }
        tickcheck=true;
    }
    private void Combat(Tile A,String derc){}
}
