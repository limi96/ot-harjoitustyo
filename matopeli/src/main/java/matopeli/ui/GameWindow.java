package matopeli.ui;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Group; 

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import matopeli.domain.SnakeClass;
import matopeli.domain.Direction;
import matopeli.domain.Food;

public class GameWindow {

    public static int UNIT_SIZE = 20; 
    public static int WINDOW_HEIGHT = 40*UNIT_SIZE;
    public static int WINDOW_WIDTH  = 40*UNIT_SIZE;

    public static Food food; 
    public static SnakeClass snake;
    public static Group snakeGroup;  
    public static Text gameScore; 
    public static boolean gameOver;
    public static AtomicInteger score;
    
    public static Scene gameScene; 

    public static long systemTime = System.nanoTime();

    public static void initialize() {
        Rectangle head = new Rectangle(UNIT_SIZE,UNIT_SIZE);
        head.setTranslateX(WINDOW_WIDTH/2);
        head.setTranslateY(WINDOW_HEIGHT/2);

        gameOver = false;
        snakeGroup = new Group(); 
        snake = new SnakeClass(head, snakeGroup); 

        food = new Food(new Rectangle(UNIT_SIZE,UNIT_SIZE));     
        gameScore = new Text(0, 20, "Score : 0"); 
        gameScore.setFill(Color.BLUE); 
        gameScore.setStyle("-fx-font: 20 arial;");
        score = new AtomicInteger();
    }

    public static Scene getGameScene() {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_WIDTH,WINDOW_HEIGHT);

        initialize();
        startGame();
        
        root.getChildren().add(snakeGroup);
        root.getChildren().add(food.getFood());
        root.getChildren().add(gameScore);
        root.setStyle("-fx-border-color: white");

        gameScene = new Scene(root);
        gameScene.setFill(Color.BLUE); 
        addKeyListener(); 

        return gameScene;
    }

    public static void addKeyListener() {
        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, pressedKey -> {
            if (pressedKey.getCode() == KeyCode.W && snake.getDirection() != Direction.UP && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
            if (pressedKey.getCode() == KeyCode.S && snake.getDirection() != Direction.DOWN && snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
            if (pressedKey.getCode() == KeyCode.A && snake.getDirection() != Direction.LEFT && snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
            if (pressedKey.getCode() == KeyCode.D && snake.getDirection() != Direction.RIGHT && snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }

            //Key used for testing, Will be removed eventually
            if (pressedKey.getCode() == KeyCode.O) {
                snake.growSnake();
                snake.increaseSpeed();
            }
        });
    }

    public static void startGame() {
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {    
                if (currentTime-systemTime > 1E7) {
                    render();
                    systemTime = currentTime;
                }

                if (gameOver) {
                    this.stop();
                }
            }
        };gameTimer.start(); 
    }

    public static void render() {
        
        snake.updateDirection();

        if (snake.touchesFood(food)) {
            food.randomPosition();   
            snake.growSnake();
            gameScore.setText("Score : " + score.addAndGet(100));
            snake.increaseSpeed();
        }   

        if (snake.collideWithSelf() || snake.collideWithBorder()) {
            gameOver = true; 
        }
    }

    // @Override
    // public void start(Stage window) {
    //     window.setTitle("Matopeli");
    //     window.setResizable(false);
        
    //     Pane root = new Pane();
    //     root.setPrefSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        
    //     initialize();

    //     new AnimationTimer() {
    //         @Override
    //         public void handle(long currentTime) {    
    //             if (currentTime-systemTime > 1E7) {
    //                 render();
    //                 systemTime = currentTime;
    //             }

    //             if (gameOver) {
    //                 this.stop();
    //             }
    //         }
            
    //     }.start();

    //     root.getChildren().add(snakeGroup);
    //     root.getChildren().add(food.getFood());
    //     root.getChildren().add(gameScore);
    //     root.setStyle("-fx-border-color: white");
        
    //     Scene scene = new Scene(root);
    //     scene.setFill(Color.BLACK); 

    //     scene.addEventFilter(KeyEvent.KEY_PRESSED, pressedKey -> {
    //         if (pressedKey.getCode() == KeyCode.W && snake.getDirection() != Direction.UP && snake.getDirection() != Direction.DOWN) {
    //             snake.setDirection(Direction.UP);
    //         }
    //         if (pressedKey.getCode() == KeyCode.S && snake.getDirection() != Direction.DOWN && snake.getDirection() != Direction.UP) {
    //             snake.setDirection(Direction.DOWN);
    //         }
    //         if (pressedKey.getCode() == KeyCode.A && snake.getDirection() != Direction.LEFT && snake.getDirection() != Direction.RIGHT) {
    //             snake.setDirection(Direction.LEFT);
    //         }
    //         if (pressedKey.getCode() == KeyCode.D && snake.getDirection() != Direction.RIGHT && snake.getDirection() != Direction.LEFT) {
    //             snake.setDirection(Direction.RIGHT);
    //         }

    //         //Key used for testing, Will be removed eventually
    //         if (pressedKey.getCode() == KeyCode.O) {
    //             snake.growSnake();
    //             snake.increaseSpeed();
    //         }
    //     });
    //     window.setScene(scene);
    //     window.show();
    // }
    
    // public static void main(String[] args) {
    //     launch(GameWindow.class);
    // }

    



}
