package snake.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import snake.gamelogic.GameMap;

public class Ui extends Application {
    GameMap gamemap = new GameMap();
    
    final private double sceneWidth = 640;
    final private double sceneHeight = 640;

    private int n = 32;
    private int m = 32;

    double gridWidth = sceneWidth / n;
    double gridHeight = sceneHeight / m;
    
    GameNode[][] gamefield = new GameNode[n][m];
    
    @Override
    public void start(Stage window) {
        gamemap.initialize();
        Group root = new Group();
        
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        
        new AnimationTimer() {
            private long prev;
            
            @Override
            public void handle(long now) {
                if (now - prev < 100_000_000L) {
                    return;
                }
                
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
                
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.LEFT) {
                        gamemap.setDirection("left");
                    }

                    if (event.getCode() == KeyCode.RIGHT) {
                        gamemap.setDirection("right");
                    }
                    
                    if (event.getCode() == KeyCode.UP) {
                        gamemap.setDirection("up");
                    }
                    
                    if (event.getCode() == KeyCode.DOWN) {
                        gamemap.setDirection("down");
                    }
                });
                
                gamemap.move();
            }
        }.start();
        

        window.setScene(scene);
        window.show();
    }
    
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
    public static void main(String[] args) {
        launch(args);
    }
}