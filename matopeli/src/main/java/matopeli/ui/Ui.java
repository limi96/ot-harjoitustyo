package matopeli.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class Ui extends Application {

    public Stage stage;
    public Scene introScene;
    public Scene loginScene;
    public Scene registerScene;
    public Scene highScoresScene;
    public Scene gameScene;
    public Scene gameOverScene;

    public GameSceneController gameSceneController;
    public LoginSceneController loginController; 
    public HighScoresSceneController highScoresController;

    /**
     * Luo Ui-pohjan kaikille näkymien luokille. Käsittelee näkymien latauksen sekä siirtymisen näkymästä toiseen
     * @throws Exception tapahtuu, jos tiedosto ei lataudu oikein tai jos FXML-tiedostossa on vikoja
     */

    @Override
    public void init() throws Exception {        

        FXMLLoader introSceneLoader = new FXMLLoader(getClass().getResource("/fxml/introScene.fxml"));   
        Parent introPane = introSceneLoader.load(); 
        IntroSceneController introController = introSceneLoader.getController(); 
        introController.setApplication(this);
        introScene = new Scene(introPane);

        FXMLLoader loginSceneLoader = new FXMLLoader(getClass().getResource("/fxml/loginScene.fxml"));   
        Parent loginPane = loginSceneLoader.load(); 
        loginController = loginSceneLoader.getController(); 
        loginController.setApplication(this);
        loginScene = new Scene(loginPane);

        FXMLLoader registerSceneLoader = new FXMLLoader(getClass().getResource("/fxml/registerScene.fxml"));   
        Parent registerPane = registerSceneLoader.load(); 
        RegisterSceneController registerController = registerSceneLoader.getController(); 
        registerController.setApplication(this, loginController);
        registerScene = new Scene(registerPane);

        FXMLLoader highScoresSceneLoader = new FXMLLoader(getClass().getResource("/fxml/highScoresScene.fxml"));   
        AnchorPane highScoresPane = highScoresSceneLoader.load(); 
        highScoresController = highScoresSceneLoader.getController(); 
        highScoresController.setApplication(this, loginController);
        highScoresScene = new Scene(highScoresPane);

        FXMLLoader gameOverSceneLoader = new FXMLLoader(getClass().getResource("/fxml/gameOverScene.fxml"));  
        Parent gameOverPane = gameOverSceneLoader.load(); 
        gameSceneController = gameOverSceneLoader.getController(); 
        gameSceneController.setApplication(this, loginController);
        gameOverScene = new Scene(gameOverPane);
    }

    /**
     * Avaa näkymän esille 
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);
        stage.setTitle("Matopeli");
        setLoginScene();
        stage.show();
    }

    public void setLoginScene() {
        stage.setScene(loginScene);   
    }

    public void setRegisterScene() {
        stage.setScene(registerScene); 
    }

    public void setHighScoresScene() throws Exception {
        highScoresController.start();
        stage.setScene(highScoresScene); 
    }

    public void setIntroScene() {
        stage.setScene(introScene);   
    }

    /**
     * Vaihtaa pelinäkymään ja välittää värivaihtoehdot eteenpäin
     * 
     * @param snakeColor madon väri
     * @param foodColor ruuan väri
     * @param backgroundIsBlack taustan väri 
     */
    public void setGameWindow(Color snakeColor, Color foodColor, boolean backgroundIsBlack) {
        gameScene = gameSceneController.getGameWindow().getGameScene(snakeColor, foodColor, backgroundIsBlack);
        stage.setScene(gameScene); 
    }

    public void setGameOverScene() {
        stage.setScene(gameOverScene); 
    }

    public static void main(String[] args) {
        launch(args);
    }

    



}
