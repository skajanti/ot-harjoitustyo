import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.gamelogic.GameMap;


public class GameMapTest {
    GameMap gamemap;
    
    public GameMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        gamemap = new GameMap();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initializeTest(){
        gamemap.initialize();
        assertEquals("right", gamemap.getDirection());
        assertTrue(gamemap.getHead(0)==17);
        assertTrue(gamemap.getCell(0, 0) == -999);
        assertTrue(gamemap.getCell(0, 31) == -999);
        assertTrue(gamemap.getCell(31, 0) == -999);
        assertTrue(gamemap.getCell(31, 31) == -999);
        assertTrue(gamemap.getCell(15, 16) == 3);
    }
    
    @Test
    public void moveTest(){
        gamemap.initialize();
        gamemap.move();
        assertTrue(gamemap.getCell(15, 16) == 0);
        assertTrue(gamemap.getHead(0) == 18);
        gamemap.setDirection("up");
        gamemap.move();
        assertTrue(gamemap.getHead(0) == 18);
        gamemap.setDirection("down");
        gamemap.move();
        assertTrue(gamemap.getHead(1) == 16);
        gamemap.setDirection("left");
        gamemap.move();
        assertTrue(gamemap.getHead(0) == 17);
        gamemap.setDirection("blorp");
        assertEquals("blorp", gamemap.getDirection());
    }
}
