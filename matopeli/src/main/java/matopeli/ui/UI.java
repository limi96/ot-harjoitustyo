package matopeli.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.net.URL;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Group; 

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import matopeli.ui.introSceneController;

public class UI extends Application {

    private Stage stage;
    private Scene introScene;

    @Override
    public void init() throws Exception {        

        try {
            FXMLLoader introSceneLoader = new FXMLLoader(getClass().getResource("/fxml/introScene.fxml"));  
            System.out.println(introSceneLoader.getLocation());  
            Parent introPane = introSceneLoader.load(); 
            // Currently throws nullpointer exception
            // introSceneController introController = introSceneLoader.getController(); 
            // introController.setApplication(this);
            introScene = new Scene(introPane);
        }
        catch (Exception e) {
            System.out.println("Virhe näkymän lataamisessa " + e);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Matopeli");
        setIntroScene();
        stage.show();
    }

    public void setIntroScene() {
        stage.setScene(introScene);
    }



    public static void main(String[] args) {
        //launch(GameWindow.class);
        launch(args);
    }

    



}
