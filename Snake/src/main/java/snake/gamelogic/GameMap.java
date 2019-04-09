package snake.gamelogic;

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
    }
    
    public boolean move() {
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j <= 31; j++) {
                if (playgrid[i][j] > 0) {
                    playgrid[i][j]++;
                } else if (playgrid[i][j] > snakeLength) {
                    playgrid[i][j] = 0;
                }
            }
        }
        
        if (direction.equals("right")) {
            if (playgrid[head[0] + 1][head[1]] == -999) {
                return false;
            }
            playgrid[head[0] + 1][head[1]] = 1;
            head[0]++;
        } else if (direction.equals("down")) {
            if (playgrid[head[0]][head[1] + 1] == -999) {
                return false;
            }
            playgrid[head[0]][head[1] + 1] = 1;
            head[1]++;
        } else if (direction.equals("left")) {
            if (playgrid[head[0] - 1][head[1]] == -999) {
                return false;
            }
            playgrid[head[0] - 1][head[1]] = 1;
            head[0]--;
        } else if (direction.equals("up")) {
            if (playgrid[head[0]][head[1] - 1] == -999) {
                return false;
            }
            playgrid[head[0]][head[1] - 1] = 1;
            head[1]--;
        } else {
            return false;
        }
        
        return true;
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
