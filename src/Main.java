import ServiceLayer.service;
import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        service s = new service();
        s.newgame(args);
        s.gameTick();
    }
}
