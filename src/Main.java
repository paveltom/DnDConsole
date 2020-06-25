import BusinessLayer.BoardPackage.Board;
import java.util.Scanner;
import BusinessLayer.BoardPackage.Creator;
import BusinessLayer.Coordinate;
import BusinessLayer.TilesPackage.Environment.Empty;
import BusinessLayer.TilesPackage.Tile;
import BusinessLayer.TilesPackage.Units.Players.Mage;
import BusinessLayer.TilesPackage.Units.Players.Player;
import BusinessLayer.TilesPackage.Units.Players.Warrior;
import BusinessLayer.TilesPackage.Units.Unit;
import ServiceLayer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Main {
    public static String [] Translate (String c)
    {
        int k=0;
        String [] output=new String[c.length()];
        String temp = "@#.skqzbgwMCKBQD";
        for(int i=0;i<c.length();i++)
        {
            k=temp.indexOf(c.charAt(i));
            output[i]=temp.charAt(k)+"";
        }
        return output;
    }
    public static String [][] readfile(String[] args) throws FileNotFoundException {
        for(int a=0;a<args.length;a++){System.out.println(args[a]);}
        File test = new File("levels/level3.txt");
        if(!test.exists()) System.out.println(test.getAbsolutePath());/////////////////////
        int i=0;
        Scanner myscanner = new Scanner(test);
        Scanner run1 =  new Scanner(test);
        while (run1.hasNextLine()){ run1.nextLine(); i++;}
        Scanner run = new Scanner(test);
        String [][] level=new String[i][];
        String temp = "";
        int k=0;
        while(run.hasNextLine()) {
            temp = run.nextLine();
            String[] trans;
            trans=Translate(temp);
            level[k] = trans;
            k++;
        }
        return level;
    }
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scan = new Scanner(System.in);
//        Board b = new Board();
//        String [][] level =readfile(args);
//        System.out.println(b.getPlayerSelection());
//        String p = "";
//        while (p == "") {
//            p = scan.nextLine();
//            if (!(p.compareTo("0") > 0 & p.compareTo("7") < 0)) {
//                System.out.println("Select a player according to numbers 1 to 6");
//                p = "";
//            }
//        }
//        b.crateBoard(level,Integer.parseInt(p));
//        Unit t = b.getCurrPlayer();
//        System.out.println("You Selected:"+'\n'+b.getCurrPlayer().Name);
//        for(int a=0;a<level.length;a++) {
//            for (int j = 0; j < level[a].length; j++)
//                System.out.print(level[a][j]);
//            System.out.println();
//        }
//        //String temp =t.actualStats();
//        System.out.println(b.getCurrPlayer().actualStats());
//        p="";
//        while (p == "") {
//            p = scan.nextLine();
//            if (!(p.equals("w") |p.equals("s")|p.equals("a")|p.equals("d")|p.equals("e")|p.equals("q"))) {
//                System.out.println("Select a valid actin or movement command");
//                p = "";
//            }
//        }
//        b.gameTick(p);
        service s = new service();
        s.newgame(args);
        s.newgame(args);
    }
}
