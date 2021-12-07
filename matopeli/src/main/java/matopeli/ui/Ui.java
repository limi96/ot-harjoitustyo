package matopeli.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class Ui extends Application {

    private Stage stage;
    private Scene introScene;
    private Scene gameScene;

    @Override
    public void init() throws Exception {        
        try {
            FXMLLoader introSceneLoader = new FXMLLoader(getClass().getResource("/fxml/introScene.fxml"));  
            System.out.println(introSceneLoader.getLocation());  
            Parent introPane = introSceneLoader.load(); 
            // Currently throws nullpointer exception
            introSceneController introController = introSceneLoader.getController(); 
            introController.setApplication(this);
            introScene = new Scene(introPane);
        }
        catch (Exception e) {
            System.out.println("Virhe näkymän lataamisessa: " + e);
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

    public void setGameWindow() {
        gameScene = GameWindow.getGameScene();
        stage.setScene(gameScene); 
    }

    public static void main(String[] args) {
        //launch(GameWindow.class);
        launch(args);
    }

    



}
