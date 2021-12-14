package matopeli.ui;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class Ui extends Application {

    private Stage stage;
    private Scene introScene;
    private Scene loginScene;
    private Scene registerScene;
    private Scene gameScene;
    private Scene gameOverScene;
    private GameOverSceneController gameOverController;

    public static void initializeDAO() {

    }

    @Override
    public void init() throws Exception {        


        try {
            FXMLLoader introSceneLoader = new FXMLLoader(getClass().getResource("/fxml/introScene.fxml"));   
            Parent introPane = introSceneLoader.load(); 
            IntroSceneController introController = introSceneLoader.getController(); 
            introController.setApplication(this);
            introScene = new Scene(introPane);
        }
        catch (Exception e) {
            System.out.println("IntroScene Error: " + e);
        }

        try {
            FXMLLoader loginSceneLoader = new FXMLLoader(getClass().getResource("/fxml/loginScene.fxml"));   
            Parent loginPane = loginSceneLoader.load(); 
            LoginSceneController loginController = loginSceneLoader.getController(); 
            loginController.setApplication(this);
            loginScene = new Scene(loginPane);
        }
        catch (Exception e) {
            System.out.println("LoginScene Error: " + e);
        }

        try {
            FXMLLoader registerSceneLoader = new FXMLLoader(getClass().getResource("/fxml/registerScene.fxml"));   
            Parent registerPane = registerSceneLoader.load(); 
            RegisterSceneController registerController = registerSceneLoader.getController(); 
            registerController.setApplication(this);
            registerScene = new Scene(registerPane);
        }
        catch (Exception e) {
            System.out.println("RegisterScene Error: " + e);
        }

        try {
            FXMLLoader gameOverSceneLoader = new FXMLLoader(getClass().getResource("/fxml/gameOverScene.fxml"));  
            Parent gameOverPane = gameOverSceneLoader.load(); 
            gameOverController = gameOverSceneLoader.getController(); 
            gameOverController.setApplication(this);
            gameOverScene = new Scene(gameOverPane);
            
        }
        catch (Exception e) {
            System.out.println("GameOverScene Error: " + e);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Matopeli");
        // setIntroScene();
        setLoginScene();
        // setRegisterScene();
        stage.show();
    }

    public void setLoginScene() {
        stage.setScene(loginScene);   
    }

    public void setRegisterScene() {
        stage.setScene(registerScene); 
    }

    public void setIntroScene() {
        stage.setScene(introScene);   
    }

    public void setGameWindow() {
        gameScene = gameOverController.getGameWindow().getGameScene();
        stage.setScene(gameScene); 
    }

    public void setGameOverScene() {
        stage.setScene(gameOverScene); 
    }

    public static void main(String[] args) {
        launch(args);
    }

    



}
