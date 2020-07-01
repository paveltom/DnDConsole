package ServiceLayer;

import BusinessLayer.BoardPackage.Board;
import BusinessLayer.TilesPackage.Units.Players.Player;
import BusinessLayer.TilesPackage.Units.Unit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class service {
    private int index = 0;
    private Board b;
    private String input;
    private Scanner scan;
    private String[][] level;
    private String[] args;

    public service() {
        scan = new Scanner(System.in);
        b = new Board();
    }

    public void newgame(String[] args) throws FileNotFoundException {
        this.args = args;
        level = readfile(args);
        System.out.println(b.getPlayerSelection());
        selecetplayer();
        for (int a = 0; a < level.length; a++) {
            for (int j = 0; j < level[a].length; j++)
                System.out.print(level[a][j]);
            System.out.println();
        }
        System.out.println(b.getCurrPlayer().actualStats());
    }

    private void selecetplayer() {
        input = "";
        input = scan.nextLine();
        if (!(input.compareTo("0") > 0 & input.compareTo("7") < 0)) {
            System.out.println("Select a player according to numbers 1 to 6");
            selecetplayer();
        }
        b.crateBoard(level, Integer.parseInt(input));
        System.out.println("You Selected:" + '\n' + b.getCurrPlayer().Name);
    }

    public void gameTick() throws FileNotFoundException {
        input = "";
        input = scan.nextLine();
        if (!(input.equals("w") | input.equals("s") | input.equals("a") | input.equals("d") | input.equals("e") | input.equals("q"))) {
            System.out.println("Select a valid action or movement command");
            gameTick();
        }
        List<String> output = b.gameTick(input);
        for (String run : output) {
            System.out.println(run);
        }
        if (b.PlayerAlive()) {////////////////////// necessary???
//            System.out.println(b.toString());
            if (b.getEnemySize() == 0)
                nextLevel(args);
            gameTick();
        }
    }

    private void nextLevel(String[] args) throws FileNotFoundException {
        if (b.getEnemySize() == 0) {
            Player CurrPlayer = b.getCurrPlayer();
            level = readfile(args);
            b.crateBoard(level, 1);
            CurrPlayer.getPosition().setPosition(b.getCurrPlayer().getPosition().getColumnCoordinate(), b.getCurrPlayer().getPosition().getRowCoordinate());
            b.setCurrPlayer(CurrPlayer);
            for (int a = 0; a < level.length; a++) {
                for (int j = 0; j < level[a].length; j++)
                    System.out.print(level[a][j]);
                System.out.println();
            }
            System.out.println(b.getCurrPlayer().actualStats());
        }
    }

    private String[][] readfile(String[] args) throws FileNotFoundException {
        String target_dir = "levels/";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        if (index < files.length) {
            File Currlevel = files[index];
            if (!Currlevel.exists()) System.out.println(Currlevel.getAbsolutePath());/////////////////////
            int i = 0;
            Scanner myscanner = new Scanner(Currlevel);
            Scanner run1 = new Scanner(Currlevel);
            while (run1.hasNextLine()) {
                run1.nextLine();
                i++;
            }
            Scanner run = new Scanner(Currlevel);
            String[][] level = new String[i][];
            String Currline = "";
            int k = 0;
            while (run.hasNextLine()) {
                Currline = run.nextLine();
                String[] trans;
                trans = Translate(Currline);
                level[k] = trans;
                k++;
            }
            index++;
            if (index > 1) {
                System.out.println();
                System.out.println("\033[32m");
                System.out.println("Loading next level...");
                System.out.println("*****************************************************************************************************************");
                System.out.println("*****************************************************************************************************************");
                System.out.println("Next level loaded successfully!");
                System.out.println("\033[0m");
                System.out.println();
            }
            return level;
        }
        System.out.println("YOU WON, Congratulations!");
        System.exit(0);
        return null;
    }

    private String[] Translate(String Currline) {
        int k = 0;
        String[] output = new String[Currline.length()];
        String temp = "@#.skqzbgwMCKBQD";
        for (int i = 0; i < Currline.length(); i++) {
            k = temp.indexOf(Currline.charAt(i));
            output[i] = temp.charAt(k) + "";
        }
        return output;
    }
}
