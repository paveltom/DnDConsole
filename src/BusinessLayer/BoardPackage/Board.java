package BusinessLayer.BoardPackage;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
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
        this.PlayerAlive = true;
    }
    
    public String getPlayerSelection(){ return PlayerSelection;}
    
    public void crateBoard(String [][] level, int playerChoice) {
        CurrBoard = BoardCreator.createBoard(level, playerChoice);
        EnemyList = BoardCreator.getEnemyList();
        CurrPlayer = BoardCreator.getCurrPlayer();
        this.SubscribeListener(this.CurrPlayer); //subscribing current player to Listener pattern logic
        for(Enemy e : this.EnemyList) this.SubscribeListener(e); //subscribing the enemies to Listener pattern logic
    }
    
    public Player getCurrPlayer(){ return CurrPlayer;}
    public boolean PlayerAlive(){ return PlayerAlive;}
    public int getEnemySize(){ return EnemyList.size();}
    public void setCurrPlayer(Player updatepalyer){ this.CurrPlayer=updatepalyer;}
    
    public List<String> gameTick(String userInput)
    {
        this.Output.clear();
        if(!userInput.equals("q")) {
            Coordinate userActionCoordinate = this.CurrPlayer.actionPerTick(userInput);
            Tile userActionTile = this.CurrBoard[userActionCoordinate.getRowCoordinate()][userActionCoordinate.getColumnCoordinate()];
            userActionTile.act(this.CurrPlayer, this);
        }

        for(Enemy e : this.EnemyList){
            Coordinate enemyActionCoordinate = e.actionPerTick(this.CurrPlayer.getPosition());
            Tile enemyActionTile = this.CurrBoard[enemyActionCoordinate.getRowCoordinate()][enemyActionCoordinate.getColumnCoordinate()];
//            this.Action(e, enemyActionTile);
            enemyActionTile.act(e, this);
            if (!this.PlayerAlive) break;
        }

        this.updateGameTick(); //add to every Player's action method an boolean statement that validates player is alive + make sure this call does not adding any string to this.Output
        this.Output.add(this.toString());
        this.Output.add(this.CurrPlayer.actualStats());
        if (!this.PlayerAlive) this.Output.add("GAME OVER...");
        return this.Output;
    }

    public void doNotMoveAction (Unit currCharacter, Tile tile){ // ==> doesn't move
    }


    public void doNotAttackAction (Enemy e1, Enemy e2) { // => in case enemy meets enemy
    }


    public void attackAction (Unit attacker, Unit defender) {
        this.Output.add(attacker.Name + " engaged in combat with " + defender.Name + ".");
        this.Output.add(attacker.actualStats());
        this.Output.add(defender.actualStats());
        this.attackDefend(attacker, defender); // attack\defend string are added in this.attackDefend method

        this.updateActualStatus(); //observer pattern update method

        this.PlayerAlive = this.CurrPlayer.ActualStatus;

        if (!this.PlayerAlive){
            this.Output.add(this.CurrPlayer.Name+" was killed by " + attacker.Name+". \nYou lost.");
            this.CurrPlayer.TileSymbol = 'X';
        }
        else if(!defender.ActualStatus){
            this.Output.add(defender.Name + " died. "+attacker.Name+" gained "+ defender.Experience +" experience.");
            this.CurrPlayer.updateExperience(defender.Experience, this.Output);// => updateExperience returns List<String>
            this.EnemyList.remove(defender);
            this.UnsubscribeListener(defender);
            int x = defender.getPosition().getColumnCoordinate();
            int y = defender.getPosition().getRowCoordinate();
            Empty deadEnemy = new Empty(x, y);//defender changes to empty and sended to action of movement
            this.CurrBoard[y][x] = deadEnemy;
            this.moveAction(this.CurrPlayer, deadEnemy);
        }
    }


    public void specialAction (Player currPlayer, Player currPlayerClone){  // ==>  in case of special ability
        List<Enemy> enemiesToAttack = new ArrayList<Enemy>(this.EnemyList);
        int attack = currPlayer.applySpecialAbility(enemiesToAttack, this.Output); //both lists are updated in player class itself
        for(Enemy e : enemiesToAttack) {
            int defend = randomize(e.DefensePoints);
            int diff = attack - defend;
            if (diff < 0) diff = 0;
            e.HealthAmount = e.HealthAmount - diff;
            this.Output.add(e.Name + " rolled " + defend + " defense points.");
            this.Output.add(currPlayer.Name + " dealt " + diff + " damage to "+e.Name+"."); //maybe change the message to 'hit' so it'll be different from combat message
            this.updateActualStatus(); //observer pattern update method
            if (!e.ActualStatus) {
                this.Output.add(e.Name + " died. " + currPlayer.Name + " gained " + e.Experience + " experience.");
                currPlayer.updateExperience(e.Experience, this.Output);// => updateExperience returns List<String>
                this.UnsubscribeListener(e);
            }
        }
    }


    public void moveAction (Unit currCharacter, Tile tile) { //  ==>  in case of movement
        int columnToGo = tile.getPosition().getColumnCoordinate();
        int rowToGo = tile.getPosition().getRowCoordinate();
        int columnToLeave = currCharacter.getPosition().getColumnCoordinate();
        int rowToLeave = currCharacter.getPosition().getRowCoordinate();
        this.CurrBoard[rowToGo][columnToGo] = currCharacter;
        this.CurrBoard[rowToLeave][columnToLeave] = tile;
        currCharacter.getPosition().setPosition(columnToGo, rowToGo);
        tile.getPosition().setPosition(columnToLeave, rowToLeave);
    }

    private void attackDefend (Unit attacker, Unit defender){ //combat system help-method
        int attack = randomize(attacker.AttackPoints);
        int defend = randomize(defender.DefensePoints);
        int diff = attack - defend;
        if (diff < 0) diff = 0;
        defender.HealthAmount = defender.HealthAmount - diff;
        this.Output.add(attacker.Name + " rolled " + attack + " attack points.");
        this.Output.add(defender.Name + " rolled " + defend + " defense points.");
        this.Output.add(attacker.Name + " dealt " + diff + " damage to "+defender.Name+".");
    }


    public String toString() { //also exists in Board class. Remove from here.
        String output = "";
        for (int a = 0; a < this.CurrBoard.length; a++) {
            for (int j = 0; j < this.CurrBoard[a].length; j++) {
                Tile temp = this.CurrBoard[a][j];
                output += temp.toString();
            }
            output += '\n';
        }
        return output;
    }


   private int randomize(int bound) { //randomize an number including the bound
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

}
