/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color; 

import matopeli.domain.SnakeClass;
import matopeli.domain.Direction;


/**
 *
 * @author limi
 */
public class SnakeClassTest {
    
    Pane root;
    Rectangle head;
    SnakeClass snake;
    
    
    public SnakeClassTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
        
    @Before
    public void setUp() {
        int UNIT_SIZE = 20; 
        int WINDOW_HEIGHT = 30*UNIT_SIZE;
        int WINDOW_WIDTH  = 30*UNIT_SIZE;
        root = new Pane(); 
        root.setPrefHeight(WINDOW_HEIGHT);
        root.setPrefWidth(WINDOW_WIDTH);
        head = new Rectangle(20, 20);
        snake = new SnakeClass(head, new Group()); 
        root.getChildren().add(this.snake.getSnakeHead());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void startingDirection() {        
        assertEquals(snake.getDirection(), Direction.RIGHT);
    }
    
    @Test
    public void allDirectionsWork() {

        snake.setDirection(Direction.RIGHT);
        assertEquals(snake.getDirection(), Direction.RIGHT);
        snake.setDirection(Direction.LEFT);
        assertEquals(snake.getDirection(), Direction.LEFT);
        snake.setDirection(Direction.UP);
        assertEquals(snake.getDirection(), Direction.UP);
        snake.setDirection(Direction.DOWN);
        assertEquals(snake.getDirection(), Direction.DOWN);
        
    }
    
    @Test
    public void allMovementsWork() {

        int oldY = (int) snake.getSnakeHead().getTranslateY();
        int oldX = (int) snake.getSnakeHead().getTranslateX();

        snake.setDirection(Direction.UP);

        for (int i = 0; i < 6; i++) { 
            snake.updateDirection();
        }
        snake.setDirection(Direction.DOWN);
        snake.updateDirection();

        snake.setDirection(Direction.LEFT);

        for (int i = 0; i < 6; i++) { 
            snake.updateDirection();
        }

        snake.setDirection(Direction.RIGHT);
        snake.updateDirection();

        int newY = (int) snake.getSnakeHead().getTranslateY();
        int newX = (int) snake.getSnakeHead().getTranslateX();

        assertEquals(oldY - newY, 10);
        assertEquals(oldX - newX, 10);
    }
}
