package BusinessLayer.BoardPackage;

import BusinessLayer.TilesPackage.Units.Enemies.Trap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatorTest {
    private Creator c;
    @Before
    public void setUp() throws Exception {
        c = new Creator();
    }
    @Test
    public void getCurrPlayerTrue() {
        String[][] s ={{"#"},{"@"},{"s"}};
        c.createBoard(s,1);
        assertEquals("Jon Snow",c.getCurrPlayer().Name);
        c.createBoard(s,2);
        assertEquals("The Hound",c.getCurrPlayer().Name);
        c.createBoard(s,3);
        assertEquals("Melisandre",c.getCurrPlayer().Name);
        c.createBoard(s,4);
        assertEquals("Thoros of Myr",c.getCurrPlayer().Name);
        c.createBoard(s,5);
        assertEquals("Arya Stark",c.getCurrPlayer().Name);
        c.createBoard(s,6);
        assertEquals("Bronn",c.getCurrPlayer().Name);
    }
    @Test
    public void getCurrPlayerFalse() {
        String[][] s ={{"Q"},{"@"},{"s"},{"C"},{"K"},{"B"}};
        c.createBoard(s,1);
        assertNotEquals("White Walker",c.getCurrPlayer().Name);
        c.createBoard(s,2);
        assertNotEquals("Giant-Wright",c.getCurrPlayer().Name);
        c.createBoard(s,3);
        assertNotEquals("The Mountain",c.getCurrPlayer().Name);
        c.createBoard(s,4);
        assertNotEquals("Queen Cersei",c.getCurrPlayer().Name);
        c.createBoard(s,5);
        assertNotEquals("Night’s King",c.getCurrPlayer().Name);
        c.createBoard(s,6);
        assertNotEquals("Bonus Trap",c.getCurrPlayer().Name);
    }

    @Test
    public void getEnemyListTrue() {
        String[][] s ={{"#"},{"@"},{"s"},{"."},{"K"}};
        c.createBoard(s,1);
        assertEquals(2,c.getEnemyList().size());
        s = new String[][]{{"#"}, {"@"}, {"s"}, {"B"}, {"Q"}};
        c.createBoard(s,1);
        assertEquals(3,c.getEnemyList().size());
        s = new String[][]{{"#"}, {"@"}, {"."}, {"#"}, {"."}};
        c.createBoard(s,1);
        assertEquals(0,c.getEnemyList().size());
    }
    @Test
    public void getEnemyListFalse() {
        String[][] s ={{"#"},{"@"},{"s"},{"."},{"K"}};
        c.createBoard(s,1);
        assertNotEquals(3,c.getEnemyList().size());
        s = new String[][]{{"#"}, {"@"}, {"s"}, {"B"}, {"Q"}};
        c.createBoard(s,1);
        assertNotEquals(0,c.getEnemyList().size());
        s = new String[][]{{"#"}, {"@"}, {"."}, {"#"}, {"."}};
        c.createBoard(s,1);
        assertNotEquals(1,c.getEnemyList().size());
    }
}