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
    private Player CurrPlayer;
    private List<Enemy> EnemyList;
    private List<Unit> Listeners;
    private boolean PlayerAlive;
    private Creator BoardCreator;
    private List<String> Output;
    private String PlayerSelection;  //String of characters' selection options
    private boolean TickCheck = false; //?????????????????????????????


    public Board() {
        this.BoardCreator = new Creator();
        this.CurrBoard = new Tile[0][];
        this.Output = new ArrayList<String>();
        this.EnemyList = new ArrayList<Enemy>();
        this.PlayerSelection = BoardCreator.PlayerSelection();
        this.Listeners = new ArrayList<Unit>();
        this.SubscribeListener(this.CurrPlayer); //subscribing current player to Listener pattern logic
    }
    
    public String getPlayerSelection(){ return PlayerSelection;}
    
    public void crateBoard(String [][] level, int playerChoice) {
        CurrBoard = BoardCreator.createBoard(level, playerChoice);
        EnemyList = BoardCreator.getEnemyList();
        CurrPlayer = BoardCreator.getCurrPlayer();
        for(Enemy e : this.EnemyList) this.SubscribeListener(e); //subscribing the enemies to Listener pattern logic
    }
    
    public Unit getCurrPlayer(){ return CurrPlayer;}
    
    public List<String> gameTick(String userInput)
    {
        //         dont forget to clear the Output list!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        if(!userInput.equals("q")) {
            Coordinate userActionCoordinate = this.CurrPlayer.actionPerTick(userInput);
            Tile userActionTile = this.CurrBoard[userActionCoordinate.getRowCoordinate()][userActionCoordinate.getColumnCoordinate()];
            this.Action(this.CurrPlayer, userActionTile);
        }

        for(Enemy e : this.EnemyList){
            e.actionPerTick()
        }

//        foreach(){
//               if(current is dead) = > remove from list
//               if(player is dead) = > break the game + return current output list
//        }

        //add board print to output
        //

        this.updateGameTick();
        return this.Output;
    }



    private void Action(Tile t1, Tile t2) { // ==>  for overloading purposes
    }

    private void Action (Unit currCharacter, Wall tile){ // ==> doesn't move

    }


    private void Action (Enemy e1, Enemy e2) { // => in case enemy meets enemy

    }

    private void Action (Unit attacker, Unit defender) {
        this.Output.add(attacker.Name + " engaged in combat with " + defender.Name + ".");
        this.attackDefend(attacker, defender);
        this.updateActualStatus(); //observer pattern update method
        this.Output.add(attacker.actualStats());
        this.Output.add(defender.actualStats());
        // attack\defend string are added in this.attackDefend method

        this.PlayerAlive = this.CurrPlayer.ActualStatus;

        if (!this.PlayerAlive){
            this.Output.add(this.CurrPlayer.Name+" was killed by " + attacker.Name+". \nYou lost.");
            this.CurrPlayer.TileSymbol = 'X';
        }
        else if(!defender.ActualStatus){
            this.Output.add(defender.Name + " died. "+attacker.Name+" gained "+ defender.Experience +" experience.");
            this.CurrPlayer.updateExperience(defender.Experience, this.Output);// => updateExperience returns List<String>
        }
        //add exchange of places in case of dying
    }


    private void Action (Player currPlayer, Player currPlayerClone){  // ==>  in case of special ability
        List<Enemy> enemiesToAttack = new ArrayList<Enemy>(this.EnemyList);
        int attack = currPlayer.applySpecialAbility(enemiesToAttack, this.Output); //both lists are updated in player class itself
        for(Enemy e : enemiesToAttack) {
            int defend = randomize(e.DefensePoints);
            int diff = attack - defend;
            if (diff < 0) diff = 0;
            e.HealthAmount = e.HealthAmount - diff;
            this.Output.add(e.Name + " rolled " + defend + " defense points.");
            this.Output.add(currPlayer.Name + " dealt " + diff + " damage to "+e.Name+".");
            this.updateActualStatus(); //observer pattern update method
            if (!e.ActualStatus) {
                this.Output.add(e.Name + " died. " + currPlayer.Name + " gained " + e.Experience + " experience.");
                currPlayer.updateExperience(e.Experience, this.Output);// => updateExperience returns List<String>
            }
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


   private int randomize(int bound) {
       int rand = (int) (Math.random() * bound) + 1; // maybe it is possible to avoid this casting
       return rand;
   }

    private void updateActualStatus() { //observer pattern update method => all the listeners update their actualStatus (= alive/dead (=true/false))
        for(Unit u : this.Listeners) u.updateActualStatus();
    }

    private void updateGameTick() { //observer pattern update method => all the listeners update their tickDepended fields (for exmp. Warrior's cooldown)
        for(Unit u : this.Listeners) u.updateGameTick();
    }

    public void SubscribeListener(Unit toSubscribe){
        this.Listeners.add(toSubscribe);
    }

    public void UnsubscribeListener(Unit toUnsubscribe){
        this.Listeners.add(toUnsubscribe);
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
