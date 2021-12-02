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
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color; 
import matopeli.domain.SnakeClass;
import matopeli.domain.Direction;
import javafx.geometry.Point2D;


/**
 *
 * @author limi
 */
public class SnakeClassTest {
    
    Pane root;
    Circle head;
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
        head = new Circle(100, 100, UNIT_SIZE/2);
        snake = new SnakeClass(head); 
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
        snake.moveUP(new Point2D(0,-10),snake.getSnakeHead());
        snake.moveDOWN(new Point2D(0,20),snake.getSnakeHead());
        snake.moveLEFT(new Point2D(-10,0),snake.getSnakeHead());
        snake.moveRIGHT(new Point2D(20,0),snake.getSnakeHead());
        
        int y = (int) snake.getSnakeHead().getTranslateY();
        int x = (int) snake.getSnakeHead().getTranslateX();
        
        assertEquals(y, 10);
        assertEquals(x, 10);
    }
}
