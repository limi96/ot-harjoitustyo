package matopeli.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Group; 

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class UI extends Application {

    private Stage stage;
    private Scene introScene;

    @Override
    public void init() throws Exception {        
        FXMLLoader introSceneLoader = new FXMLLoader(getClass().getResource("/fxml/introScene.fxml");
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
              
        stage.setTitle("TodoApp");
        setIntroScene();
        stage.show();
    }

    public void setIntroScene() {
        stage.setScene(introScene);
    }
        public static void main(String[] args) {
        launch(GameWindow.class);
    }

    



}
