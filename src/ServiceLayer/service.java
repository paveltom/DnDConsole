package ServiceLayer;

import BusinessLayer.BoardPackage.Board;
import BusinessLayer.TilesPackage.Units.Unit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class service {
    private int index=0;
    private Board b;
    private String input;
    private Scanner scan;
    private String [][] level;

    public service()
    {
        scan = new Scanner(System.in);
        b = new Board();
    }
    public void newgame(String[] args) throws FileNotFoundException {
        level =readfile(args);
        System.out.println(b.getPlayerSelection());
        selecetplayer();
        for(int a=0;a<level.length;a++) {
            for (int j = 0; j < level[a].length; j++)
                System.out.print(level[a][j]);
            System.out.println();
        }
        System.out.println(b.getCurrPlayer().actualStats());
    }
    private void selecetplayer()
    {
        input="";
        input = scan.nextLine();
        if (!(input.compareTo("0") > 0 & input.compareTo("7") < 0)) {
            System.out.println("Select a player according to numbers 1 to 6");
            selecetplayer();
        }
        b.crateBoard(level,Integer.parseInt(input));
        System.out.println("You Selected:"+'\n'+b.getCurrPlayer().Name);
    }
    public void gameTick ()
    {
        input="";
        input = scan.nextLine();
        if (!(input.equals("w") |input.equals("s")|input.equals("a")|input.equals("d")|input.equals("e")|input.equals("q"))) {
            System.out.println("Select a valid actin or movement command");
            gameTick();
        }
        b.gameTick(input);
    }
    private String [][] readfile(String[] args) throws FileNotFoundException {
        String target_dir = "levels/";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        File test = files[index];
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
        index++;
        return level;
    }
    private String [] Translate (String c)
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
}
