package snake.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import snake.gamelogic.GameMap;

/**
 * For now the main scene, displays the gameboard.
 * @author seppo
 */
public class Ui extends Application {
    GameMap gamemap = new GameMap();
    
    final private double sceneWidth = 640;
    final private double sceneHeight = 640;

    private int n = 32;
    private int m = 32;

    double gridWidth = sceneWidth / n;
    double gridHeight = sceneHeight / m;
    
    GameNode[][] gamefield = new GameNode[n][m];
    
    Timeline timeline;
    Scene gameScene;
    Scene startScene;
    
    int difficulty = 200;
    
    /**
     * Standard javafx with timeline.
     * @param window 
     */
    @Override
    public void start(Stage window) {
        
        VBox initPane = new VBox(10);
        HBox diffPane = new HBox(10);
        Button startButton = new Button("Start");
        
        initPane.setPadding(new Insets(10));
        
        Label diffLabel = new Label("Difficulty");
        TextField diffInput = new TextField();
        Label diffExpLabel = new Label("Difficulty accepts single integer"
                + " inputs\n from 1 to 9, otherwise defaults to 1");
        
        initPane.getChildren().addAll(diffPane, diffExpLabel, startButton);
        diffPane.getChildren().addAll(diffLabel, diffInput);
        
        startButton.setOnAction(e -> {
           int diff = Integer.parseInt(diffInput.getText());
           
           if (1 <= diff && diff <= 9){
               difficulty = 1000 - (diff * 100);
           }
           
           window.setScene(gameScene);
           timeline.play();
        });
        
        startScene = new Scene(initPane, 250, 170);
        window.setScene(startScene);
        window.show();
        
        
        gamemap.initialize();
        Group root = new Group();
        
        gameScene = new Scene(root, sceneWidth, sceneHeight);
        int dif = difficulty;
        timeline = new Timeline(new KeyFrame(Duration.millis(dif), event -> {
            long prev = 0;
            
                
            for (int i = 0; i <= 31; i++) {
                for (int j = 0; j <= 31; j++) {
                    int cell = gamemap.getCell(i, j);
                    GameNode node;
                    if (cell == -500) {
                        node = new GameNode("fruit", i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                        root.getChildren().add(node);
                        gamefield[i][j] = node;
                    } else if (cell > 0) {
                        node = new GameNode("snake", i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                        root.getChildren().add(node);
                        gamefield[i][j] = node;
                    } else if (cell == -999) {
                        node = new GameNode("wall", i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                        root.getChildren().add(node);
                        gamefield[i][j] = node;
                    } else {
                        node = new GameNode("empty", i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                        root.getChildren().add(node);
                        gamefield[i][j] = node;
                    }
                }
            }
                
                
                
            boolean b = gamemap.move();
            if (b == false) {
                timeline.stop();
                window.close();
            }
                
        }));
        
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                boolean b = check180("left", gamemap.getDirection());
                
                if (b == true) {
                    gamemap.setDirection("left");
                }
            }

            if (event.getCode() == KeyCode.RIGHT) {
                boolean b = check180("right", gamemap.getDirection());
                
                if (b == true) {
                    gamemap.setDirection("right");
                }
            }

            if (event.getCode() == KeyCode.UP) {
                boolean b = check180("up", gamemap.getDirection());
                
                if (b == true) {
                    gamemap.setDirection("up");
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                boolean b = check180("down", gamemap.getDirection());
                
                if (b == true) {
                    gamemap.setDirection("down");
                }
            }
        });
        
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    /**
     * The gameboard is drawn as a grid composed of tiles, a rectangle for a snake segment, 
     * a circle for fruit, an x for a wall, and a blank space for empty space.
     */
    public static class GameNode extends StackPane {
        public GameNode(String name, double x, double y, double width, double height) {
            Rectangle rectangle;
            Circle circle;
            Text text;
            
            if (name.equals("snake")) {
                rectangle = new Rectangle(width, height);
                getChildren().add(rectangle);
            } else if (name.equals("fruit")) {
                circle = new Circle(width);
                getChildren().add(circle);
            } else if (name.equals("wall")) {
                text = new Text(width, height, "x");
                getChildren().add(text);
            } else {
                rectangle = new Rectangle(width, height);
                rectangle.setStroke(Color.WHITE);
                rectangle.setFill(null);
                rectangle.setStrokeWidth(20);
                getChildren().add(rectangle);
            }
            
            setTranslateX(x);
            setTranslateY(y);
        }
    }
    
    /**
     * Prevents the snake from doing a 180 degree turn, which would kill it instantly.
     * 
     * @param dir The direction from the input.
     * @param getdir The snake's current direction.
     * @return Returns false if the turn is forbidden.
     */
    public boolean check180(String dir, String getdir) {
        if (dir.equals("right") && getdir.equals("left")) {
            return false;
        } else if (dir.equals("left") && getdir.equals("right")) {
            return false;
        } else if (dir.equals("up") && getdir.equals("down")) {
            return false;
        } else if (dir.equals("down") && getdir.equals("up")) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Main.
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
}