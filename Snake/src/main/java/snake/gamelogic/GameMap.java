package snake.gamelogic;

import java.util.Random;

/**
 * This class controls the main gameboard.
 */
public class GameMap {
    int[][] playgrid = new int[32][32];
    int snakeLength;
    public String direction;
    int[] head = new int[2];
    
    /**
     * Initializes the board for use. Creates the snake, a fruit and the walls.
     */
    public void initialize() {
        for (int i = 0; i <= 31; i++) {
            playgrid[0][i] = -999;
            playgrid[31][i] = -999;
            playgrid[i][0] = -999;
            playgrid[i][31] = -999;
        }
        
        playgrid[15][16] = 3;
        playgrid[16][16] = 2;
        playgrid[17][16] = 1;
        
        direction = "right";
        
        head[0] = 17;
        head[1] = 16;
        
        snakeLength = 3;
        
        addFruit();
    }
    
    /**
     * Moves the snake in the set direction.
     * 
     * @see snake.ui.Ui#start(Stage window)
     * 
     * @return Returns false if the snake hits a wall or itself, or if the direction is invalid. Otherwise returns true.
     */
    public boolean move() {
        boolean endMethod;
        moveSnake();
        endMethod = moveHead();
        sideTeleport();
        
        if (endMethod == false) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Incriments the age of the snake's body segments.
     */
    public void moveSnake() {
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j <= 31; j++) {
                if (playgrid[i][j] > 0) {
                    playgrid[i][j]++;
                }
                
                if (playgrid[i][j] > snakeLength) {
                    playgrid[i][j] = 0;
                }
            }
        }
    }
    
    /**
     * Moves the head of the snake, and checks for fruit and collisions.
     * 
     * @return Returns false in case of collision or invalid direction, otherwise returns true.
     */
    public boolean moveHead() {
        boolean ret;
        if (direction.equals("right")) {
            head[0]++;
            ret = checkCollision();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else if (direction.equals("down")) {
            head[1]++;
            ret = checkCollision();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else if (direction.equals("left")) {
            head[0]--;
            ret = checkCollision();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else if (direction.equals("up")) {
            head[1]--;
            ret = checkCollision();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else {
            return false;
        }
        if (ret == false) {
            return false;
        }
        
        return true;
    }
   
    /**
     * Checks if head has collided with wall or snake.
     * @return Returns false if collision occurs.
     */
    public boolean checkCollision() {
        if (playgrid[head[0]][head[1]] == -999) {
            return false;
        } else if (playgrid[head[0]][head[1]] > 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Checks for fruit at the location of the head. If found, increases length of snake and creates a new fruit.
     */
    public void eatFruit() {
        if (playgrid[head[0]][head[1]] == -500) {
            snakeLength++;
            addFruit();
        }
    }
    
    /**
     * Creates a fruit in an unoccupied tile.
     */
    public void addFruit() {
        int x;
        int y;
        while (true) {
            Random random;

            x = new Random().nextInt(32);
            y = new Random().nextInt(32);
            
            if (playgrid[x][y] == 0) {
                break;
            }
        }
        
        playgrid [x][y] = -500;
    }
    
    /**
     * Teleports the head of the snake from one end of the map to the other to prevent overflow.
     */
    public void sideTeleport() {
        if (head[0] == 31) {
            head[0] = 0;
        } else if (head[0] == 0) {
            head[0] = 31;
        } else if (head[1] == 31) {
            head[1] = 0;
        } else if (head[1] == 0) {
            head[1] = 31;
        }
    }
    
    /**
     * @return Returns the direction the snake is facing.
     */
    public String getDirection() {
        return direction;
    }
    
    /**
     * @param dir Sets the direction the snake is facing.
     */
    public void setDirection(String dir) {
        direction = dir;
    }
    
    /**
     * Gets the location of the snake's head, which is used to check for collisions and fruit.
     * 
     * @param i 0 is x-axis, 1 is y-axis.
     * @return Returns the location of the head on the provided axis.
     */
    public int getHead(int i) {
        return head[i];
    }
    
    /**
     * Gets the value of a tile.
     * 
     * @param i x-axis
     * @param j y-axis
     * @return Returns the value of a tile.
     */
    public int getCell(int i, int j) {
        return playgrid[i][j];
    }
    
    /**
     * @return Returns the length of the snake (for high score).
     */
    public int getLength() {
        return snakeLength;
    }
}
