package matopeli.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import matopeli.domain.SnakeClass;
import matopeli.domain.Direction;
import matopeli.domain.Food;

import javafx.scene.Group; 
import javafx.scene.Node; 
import javafx.collections.ObservableList; 

public class GameWindow extends Application {

    public static int UNIT_SIZE = 20; 
    public static int WINDOW_HEIGHT = 30*UNIT_SIZE;
    public static int WINDOW_WIDTH  = 30*UNIT_SIZE;

    long systemTime = System.nanoTime();

    @Override
    public void start(Stage window) {
        window.setTitle("Matopeli");

        Pane root = new Pane();
        root.setPrefHeight(WINDOW_HEIGHT);
        root.setPrefWidth(WINDOW_WIDTH);

        Circle head = new Circle(UNIT_SIZE/2);
        head.setTranslateX(WINDOW_WIDTH/2);
        head.setTranslateY(WINDOW_HEIGHT/2);

        SnakeClass snake = new SnakeClass(head); 
        
        Food food = new Food(new Rectangle(UNIT_SIZE,UNIT_SIZE)); 

        Group snakeGroup = new Group(); 

        ObservableList snakeBod = snakeGroup.getChildren(); 

        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {    
                if (currentTime-systemTime > 1E7) {

                    for (Circle snakePart : snake.getSnakeBody()) {
                        snake.updateDirection(snakePart);
                    }

                    // snake.updateDirection();
                    systemTime = currentTime;
                }
                if (snake.touchesFood(food)) {
                    
                    food.randomPosition();   

                    double x = snake.getTail().getTranslateX();
                    double y = snake.getTail().getTranslateY();

                    // Movement is the problem 
                    Circle newTest = new Circle(10);
                    
                    newTest.setTranslateX(x);
                    newTest.setTranslateY(y);
                    newTest.setFill(Color.PINK);

                    for (int i = 0; i < 10; i++) { 
                        for (Circle snakePart : snake.getSnakeBody()) {
                            snake.updateDirection(snakePart);
                        }
                    }
 
                    snake.getSnakeBody().add(newTest);
                    snakeBod.add(newTest);
                    snake.setTail(newTest);
                    
                }
            
            }
        
        }.start();

        root.getChildren().add(snake.getSnakeHead());
        root.getChildren().add(food.getFood());
        root.getChildren().add(snakeGroup);

        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK); 
    
        scene.addEventFilter(KeyEvent.KEY_PRESSED, pressedKey -> {
            if (pressedKey.getCode() == KeyCode.W && snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.UP);
            }
            if (pressedKey.getCode() == KeyCode.S && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.DOWN);
            }
            if (pressedKey.getCode() == KeyCode.A && snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.LEFT);
            }
            if (pressedKey.getCode() == KeyCode.D && snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.RIGHT);
            }
        });

        window.setScene(scene);
        window.show();
    }

    

    public static void main(String[] args) {
        launch(GameWindow.class);
    }

    



}
