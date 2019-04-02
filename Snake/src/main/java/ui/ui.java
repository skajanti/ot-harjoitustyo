package ui;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class ui extends Application{
//    
//    @Override
//    public void start (Stage window){
//        VBox directionPane = new VBox();
//        HBox directionPaneh1 = new HBox();
//        HBox directionPaneh2 = new HBox();
//        HBox directionPaneh3 = new HBox();
//        
//        directionPane.getChildren().addAll(directionPaneh1, directionPaneh2, directionPaneh3);
//        
//        Button up = new Button("Up");
//        Button left = new Button("Left");
//        Button right = new Button("Right");
//        Button down = new Button("Down");
//        
//        String direction;
//        
//        up.setOnAction((event) -> {
//            direction = "up";
//        });
//        
//        directionPaneh1.getChildren().add(up);
//        directionPaneh2.getChildren().addAll(left, right);
//        directionPaneh3.getChildren().add(down);
//        
//        Scene scene = new Scene(directionPane);
//        window.setScene(scene);
//        window.show();
//    }
//}
