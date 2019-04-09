package snake.ui;

import java.awt.Color;
import java.awt.Paint;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import snake.gamelogic.GameMap;

public class ui extends Application{
    GameMap gamemap = new GameMap();
    
    final private double sceneWidth = 640;
    final private double sceneHeight = 640;

    private int n = 32;
    private int m = 32;

    double gridWidth = sceneWidth / n;
    double gridHeight = sceneHeight / m;
    
    GameNode[][] gamefield = new GameNode[n][m];
    
    @Override
    public void start (Stage window){
        gamemap.initialize();
        Group root = new Group();
        
        for (int i = 0; i <= 31; i++){
            for (int j = 0; j <= 31; j++){
                int cell = gamemap.getCell(i, j);
                GameNode node;
                if (cell == -500){
                    node = new GameNode("fruit", i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                    root.getChildren().add(node);
                    gamefield[i][j] = node;
                } else if (cell > 0){
                    node = new GameNode("snake", i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                    root.getChildren().add(node);
                    gamefield[i][j] = node;
                } else if (cell == -999){
                    node = new GameNode("wall", i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                    root.getChildren().add(node);
                    gamefield[i][j] = node;
                }
            }
        }
        Scene scene = new Scene( root, sceneWidth, sceneHeight);

        window.setScene( scene);
        window.show();
    }
    
    public static class GameNode extends StackPane{
        public GameNode(String name, double x, double y, double width, double height){
            Rectangle rectangle;
            Circle circle;
            Text text;
            
            if (name.equals("snake")){
                rectangle = new Rectangle(width, height);
                getChildren().add(rectangle);
            } else if (name.equals("fruit")){
                circle = new Circle(width);
                getChildren().add(circle);
            } else {
                text = new Text(width, height, "x");
                getChildren().add(text);
            }
            
            setTranslateX(x);
            setTranslateY(y);
        }
    }
    public static void main(String[] args){
//        GameController gamecontroller = new GameController();
//        
//        gamecontroller.run();
    launch(args);
    }
}