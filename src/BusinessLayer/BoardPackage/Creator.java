package BusinessLayer.BoardPackage;

import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Environment.Wall;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Enemies.Enemy;
import BusinessLayer.TilesPackage.Units.Enemies.Monster;
import BusinessLayer.TilesPackage.Units.Enemies.Trap;
import BusinessLayer.TilesPackage.Units.Players.Mage;
import BusinessLayer.TilesPackage.Units.Players.Player;
import BusinessLayer.TilesPackage.Units.Players.Rogue;
import BusinessLayer.TilesPackage.Units.Players.Warrior;
import BusinessLayer.TilesPackage.Units.Unit;

import java.util.*;

public class Creator {
    private final String w = "Warrior";
    private final String ma = "Mage";
    private final String r ="Rogue";
    private final String t = "Trap";
    private final String m = "Monster";
    private final String[] wd = {"Type:","Name:","Health:","Attack:","Defense:","Level:","Experience:","Cooldown:"};
    private final String[] md = {"Type:","Name:","Health:","Attack:","Defense:","Level:","Experience:","Mana:","Spell Power:"};
    private final String[] rd = {"Type:","Name:","Health:","Attack:","Defense:","Level:","Experience:","Energy:"};
    private final String[] Md = {"Type:","Name:","Health:","Attack:","Defense:","Vision Range","Experience Value:"};
    private final String[] td = {"Type:","Name:","Health:","Attack:","Defense:","Experience Value:","Visibility Time:","Invisibility Time:"};
    private final String[] jon_snow ={w,"Jon Snow","300","30","4","1","50","3"};
    private final String[] the_hound ={w,"The Hound","400","20","6","1","50","5"};
    private final String[] melisandre ={ma,"Melisandre","100","5","1","1","50","300","30","15","5","6"};
    private final String[] thoros_of_myr ={ma,"Thoros of Myr","250","25","4","1","50","150","20","20","3","4"};
    private final String[] arya_stark ={r,"Arya Stark","150","40","2","1","50","100"};
    private final String[] bronn ={r,"Bronn","250","35","3","1","50","100"};
    private final String[] lannister_solider ={m,"Lannister Solider","80","8","3","3","25"};
    private final String[] lannister_knight ={m,"Lannister Knight","200","14","8","4","50"};
    private final String[] queens_guard ={m,"Queen’s Guard","400","20","15","5","100"};
    private final String[] wright ={m,"Wright","1000","30","15","3","100"};
    private final String[] bear_wright ={m,"Bear-Wright","1000","75","30","4","250"};
    private final String[] giant_wright ={m,"Giant-Wright","1500","100","40","5","500"};
    private final String[] white_walker ={m,"White Walker","2000","150","50","6","1000"};
    private final String[] the_mountain ={m,"The Mountain","1000","60","25","6","500"};
    private final String[] queen_cersei ={m,"Queen Cersei","100","10","10","1","1000"};
    private final String[] nights_king ={m,"Night’s King","5000","300","150","8","5000"};
    private final String[] bonus_trap ={t,"Bonus Trap","1","1","1","250","1","5"};
    private final String[] queens_trap ={t,"Queen’s Trap","250","50","10","100","3","7"};
    private final String[] death_trap ={t,"Death Trap","1","1","1","250","1","10"};
    private Map<String,String[][]> CharactersDataBase;
    private List<Enemy> EnemyList;
    private Player CurrPlayer;
    private Tile [][] board;
    private String playerSelection = "Select Player:" + '\n';
    private int ExpD=0;
    private int ManaD;

    public Creator(){
        CharactersDataBase = new HashMap<>();
        EnemyList = new ArrayList<>();
        setjon_snow();
        setthe_hound();
        setmelisandre();
        setthoros_of_myr();
        setarya_stark();
        setbronn();
        setlannister_solider();
        setLannister_knight();
        setQueens_guard();
        setWright();
        setBear_wright();
        setGiant_wright();
        setWhite_walker();
        setThe_mountain();
        setQueen_cersei();
        setNights_king();
        setBonus_trap();
        setQueens_trap();
        setDeath_trap();
        setEmpty();
        setWall();
    }
    public String toString() { //also exists in Board class. Remove from here.
        String output = "";
        for (int a = 0; a < board.length; a++) {
            for (int j = 0; j < board[a].length; j++) {
                Tile temp = board[a][j];
                output += temp.toString();
            }
            output += '\n';
        }
        return output;
    }
    public Map<String,String[][]> getMap () {return CharactersDataBase;}////////////////////
    public Player getCurrPlayer(){return CurrPlayer;}
    public String PlayerSelection()
    {
        int i=1;
        String [][] curr = CharactersDataBase.get(i+"");
        while(curr!=null)
        {
            int run = curr[0].length;
            playerSelection+=i+". ";
            for(int j=1;j<curr[0].length;j++) {
                if (curr[0][j] != "Health:" & curr[0][j] != "Experience:" & curr[0][j] != "Cooldown:"& curr[0][j] != "Mana:"& curr[0][j] != "Energy:") {
                    playerSelection += curr[0][j] + " '" + curr[1][j] + "'  ";
                }
                else if (curr[0][j] == "Health:" | curr[0][j] == "Energy:" ) {
                    playerSelection += curr[0][j] + " '" + curr[1][j] + "/" + curr[1][j]+"'  ";
                }
                else if (curr[0][j] == "Experience:" | curr[0][j] == "Cooldown:") {
                    playerSelection += curr[0][j] + " '" + ExpD + "/" + curr[1][j] + "'  ";
                }
                else if (curr[0][j] == "Mana:") {
                    ManaD=Integer.parseInt(curr[1][j])/4;
                    playerSelection += curr[0][j] + " '" + ManaD + "/" + curr[1][j]+"'  ";
                }
                if(j==4)
                    playerSelection+='\n';
            }
            playerSelection+='\n';
            i++;
            curr=CharactersDataBase.get(i+"");
        }
        return playerSelection;
    }
    public Tile [][] createBoard(String[][] level,int p)
    {
        board = new Tile[level.length][];
        Tile[] Currline;
        for(int i=0;i<level.length;i++)
        {
            Currline=new Tile[level[i].length];
            for(int j=0;j<level[i].length;j++)
            {
                String type;
                String[][] curr;
                if(level[i][j].equals("@"))
                {
                    curr=CharactersDataBase.get(p+"");
                    type = curr[1][0];
                    if (type.equals("Warrior")) {
                        CurrPlayer = new Warrior(curr, j, i,level[i][j]);
                        Currline[j]=CurrPlayer;
                    }
                    else if (type.equals("Mage")) {
                        CurrPlayer = new Mage(curr, j, i,level[i][j]);
                        Currline[j]=CurrPlayer;
                    }
                    else if (type.equals("Rogue")) {
                        CurrPlayer = new Rogue(curr, j, i,level[i][j]);
                        Currline[j]=CurrPlayer;
                    }
                }
                else {
                    curr = CharactersDataBase.get(level[i][j]);
                    type = curr[1][0];
                    if (type.equals("Empty"))
                        Currline[j] = new Empty(j,i);
                    else if (type.equals("Wall"))
                        Currline[j] = new Wall(j,i);
                    else if (type.equals("Trap")) {
                        Enemy t= new Trap(curr,j,i,level[i][j]);
                        EnemyList.add(t);
                        Currline[j] = t;
                    }
                    else if (type.equals("Monster")) {
                        Enemy m = new Monster(curr,j,i,level[i][j]);
                        EnemyList.add(m);
                        Currline[j]=m;
                    }
                }
            }
            board[i]=Currline;
        }
        return board;
    }

    public List<Enemy> getEnemyList(){return EnemyList;}
    private void setEmpty()
    {
        String[][] e =new String[2][2];
        e[1][0]="Empty";
        CharactersDataBase.put(".",e);
    }
    private void setWall()
    {
        String[][] w =new String[2][2];
        w[1][0]="Wall";
        CharactersDataBase.put("#",w);
    }
    private void setjon_snow()
    {
        String[][] p1 = new String[2][];
        p1[0]=wd;
        p1[1]=jon_snow;
        CharactersDataBase.put("1",p1);
    }
    private void setthe_hound()
    {
        String[][] p2 = new String[2][];
        p2[0]=wd;
        p2[1]=the_hound;
        CharactersDataBase.put("2",p2);
    }
    private void setmelisandre()
    {
        String[][] p3 = new String[2][];
        p3[0]=md;
        p3[1]=melisandre;
        CharactersDataBase.put("3",p3);
    }
    private void setthoros_of_myr()
    {
        String[][] p4 = new String[2][];
        p4[0]=md;
        p4[1]=thoros_of_myr;
        CharactersDataBase.put("4",p4);
    }
    private void setarya_stark()
    {
        String[][] p5 = new String[2][];
        p5[0]=rd;
        p5[1]=arya_stark;
        CharactersDataBase.put("5",p5);
    }
    private void setbronn()
    {
        String[][] p6 = new String[2][];
        p6[0]=rd;
        p6[1]=bronn;
        CharactersDataBase.put("6",p6);
    }
    private void setlannister_solider()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=lannister_solider;
        CharactersDataBase.put("s",m1);
    }
    private void setLannister_knight()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=lannister_knight;
        CharactersDataBase.put("k",m1);
    }
    private void setQueens_guard()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=queens_guard;
        CharactersDataBase.put("q",m1);
    }
    private void setWright()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=wright;
        CharactersDataBase.put("z",m1);
    }
    private void setBear_wright()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=bear_wright;
        CharactersDataBase.put("b",m1);
    }
    private void setGiant_wright()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=giant_wright;
        CharactersDataBase.put("g",m1);
    }
    private void setWhite_walker()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=white_walker;
        CharactersDataBase.put("w",m1);
    }
    private void setThe_mountain()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=the_mountain;
        CharactersDataBase.put("M",m1);
    }
    private void setQueen_cersei()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=queen_cersei;
        CharactersDataBase.put("C",m1);
    }
    private void setNights_king()
    {
        String[][] m1 = new String[2][];
        m1[0]=Md;
        m1[1]=nights_king;
        CharactersDataBase.put("K",m1);
    }
    private void setBonus_trap()
    {
        String[][] m1 = new String[2][];
        m1[0]=td;
        m1[1]=bonus_trap;
        CharactersDataBase.put("B",m1);
    }
    private void setQueens_trap()
    {
        String[][] m1 = new String[2][];
        m1[0]=td;
        m1[1]=queens_trap;
        CharactersDataBase.put("Q",m1);
    }
    private void setDeath_trap()
    {
        String[][] m1 = new String[2][];
        m1[0]=td;
        m1[1]=death_trap;
        CharactersDataBase.put("D",m1);
    }


}
