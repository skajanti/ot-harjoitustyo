package snake;

public class GameMap {
    int[][] GameMap = new int[32][32];
    int snakeLength;
    public String direction;
    int[] head = new int[2];
    
    public void initialize(){
        for (int i = 0; i <=31; i++){
            GameMap[0][i] = -999;
            GameMap[31][i] = -999;
            GameMap[i][0] = -999;
            GameMap[i][31] = -999;
        }
        
        GameMap[15][16] = 3;
        GameMap[16][16] = 2;
        GameMap[17][16] = 1;
        
        direction = "right";
        
        head[0] = 17;
        head[1] = 16;
        
        snakeLength = 3;
    }
    
    public boolean move(){
        for (int i = 0; i <= 31; i++){
            for (int j = 0; j <= 31; j++){
                if (GameMap[i][j] > snakeLength){
                    GameMap[i][j]++;
                    GameMap[i][j] = 0;
                }
            }
        }
        
        if (direction.equals("right")){
            if (GameMap[head[0] + 1][head[1]] == -999){
                return false;
            }
            GameMap[head[0] + 1][head[1]] = 1;
            head[0]++;
        } else if (direction.equals("down")){
            if (GameMap[head[0]][head[1] + 1] == -999){
                return false;
            }
            GameMap[head[0]][head[1] + 1] = 1;
            head[1]++;
        } else if (direction.equals("left")){
            if (GameMap[head[0] - 1][head[1]] == -999){
                return false;
            }
            GameMap[head[0] - 1][head[1]] = 1;
            head[0]--;
        } else if (direction.equals("up")){
            if (GameMap[head[0]][head[1] - 1] == -999){
                return false;
            }
            GameMap[head[0]][head[1] - 1] = 1;
            head[1]--;
        } else{
            return false;
        }
        
        return true;
    }
    
    public String getDirection(){
        return direction;
    }
    
    public void setDirection(String dir){
        direction = dir;
    }
    
    public int getHead(int i){
        return head[i];
    }
}
