package matopeli.ui;


import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.Scene; 


public class GameWindow extends Application {

    @Override
    public void start(Stage window) {
        window.setTitle("Matopeli");

        Group root = new Group();

        Scene scene = new Scene(root);

        window.setScene(scene); 



        window.show();
    }

    public static void main(String[] args) {
        launch(GameWindow.class);
    }
    
}
