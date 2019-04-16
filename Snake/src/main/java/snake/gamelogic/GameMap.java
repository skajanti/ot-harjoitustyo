package snake.gamelogic;

import java.util.Random;

public class GameMap {
    int[][] playgrid = new int[32][32];
    int snakeLength;
    public String direction;
    int[] head = new int[2];
    
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
    
    boolean endMethod;
    
    public boolean move() {
        
        endMethod = moveHead();
        if (endMethod = false) {
            return false;
        }
        moveSnake();
        return true;
    }
    
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
    
    public boolean moveHead() {
        if (direction.equals("right")) {
            head[0]++;
            endMethod = checkWall();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else if (direction.equals("down")) {
            head[1]++;
            endMethod = checkWall();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else if (direction.equals("left")) {
            head[0]--;
            endMethod = checkWall();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else if (direction.equals("up")) {
            head[1]--;
            endMethod = checkWall();
            eatFruit();
            playgrid[head[0]][head[1]] = 1;
        } else {
            return false;
        }
        if (endMethod = false) {
            return false;
        }
        return true;
    }
    
    public boolean checkWall() {
        if (playgrid[head[0]][head[1]] == -999) {
            return false;
        }
        return true;
    }
    
    public void eatFruit() {
        if (playgrid[head[0]][head[1]] == -500) {
            snakeLength++;
            addFruit();
        }
    }
    
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
    
    public String getDirection() {
        return direction;
    }
    
    public void setDirection(String dir) {
        direction = dir;
    }
    
    public int getHead(int i) {
        return head[i];
    }
    
    public int getCell(int i, int j) {
        return playgrid[i][j];
    }
}
