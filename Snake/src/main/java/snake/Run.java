
package snake;

import java.util.Scanner;

public class Run {
    public static void main(String[] args){
        GameMap gamemap = new GameMap();
        Scanner scanner = new Scanner(System.in);
        
        gamemap.initialize();
        while (true){
            gamemap.setDirection(scanner.nextLine());
            gamemap.move();
            System.out.println(gamemap.getHead(0) + ", " + gamemap.getHead(1));
        }
    }
}
