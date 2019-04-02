import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.GameMap;


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
    }
}
