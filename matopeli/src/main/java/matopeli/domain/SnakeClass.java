package matopeli.domain;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 
import javafx.collections.ObservableList; 
import javafx.scene.Group;

import java.lang.Math; 

public class SnakeClass {

    ObservableList snake; 
    Rectangle head;
    Rectangle tail; 
    Direction direction = Direction.RIGHT;
    double speed;  
     
    public SnakeClass(Rectangle head, Group snakeGroup) {
        this.snake = snakeGroup.getChildren();
        this.head = head; 
        this.tail = head;          
        this.snake.add(this.head); 
        this.speed = 2; 
        growSnake();
    }
    public void increaseSpeed() {
        this.speed += Math.pow(2, -this.speed/2);
    }

    public Rectangle getSnakeHead() {
        return (Rectangle) this.snake.get(0); 
    }

    public ObservableList getSnakeBody() {
        return snake; 
    }

    public void setTail(Rectangle tail) {
        this.tail = tail; 
    }

    public Rectangle getTail() {
        return (Rectangle) snake.get(getSnakeBody().size() - 1);
    }

    public void setDirection(Direction direction) {
        this.direction = direction; 
        updateDirection();
    }   
    public Direction getDirection() {
        return this.direction; 
    }

    public void growSnake() {
        for (int i = 0; i < 50; i++) { 
            Rectangle newSnakePart = setNewPosition(getTail(), 0, 0);
            getSnakeBody().add(newSnakePart);
            setTail(newSnakePart);
        }
    }

    public Rectangle setNewPosition(Rectangle snakePart, double dx, double dy) {
        Rectangle newSnakePart = new Rectangle(20, 20);
        double x = snakePart.getTranslateX() + dx;
        double y = snakePart.getTranslateY() + dy;

        newSnakePart.setTranslateX(x);
        newSnakePart.setTranslateY(y);
        newSnakePart.setFill(Color.GREEN);

        return newSnakePart;
    }

    public void updateDirection() {

        for (int i = 0; i < this.speed; i++) { 
            snake.remove(snake.size() - 1);             
            double dy = 0;
            double dx = 0; 
    
            switch (this.direction) {
                case UP:
                    dy = -1;
                    break; 
                case DOWN:
                    dy = 1;
                    break; 
                case LEFT:
                    dx = -1;
                    break; 
                case RIGHT:
                    dx = 1;
                    break; 
            }
            
            Rectangle newTail = setNewPosition(getSnakeHead(), dx, dy);
            snake.add(0, newTail);
            this.head = (Rectangle) snake.get(0); 
         
        }
        

    }

    public Boolean touchesFood(Food food) { 
 
        for (int i = 0; i < snake.size(); i++) { 

            Rectangle snakePart = (Rectangle) snake.get(i);
            double x = Math.abs(food.getFood().getTranslateX() - snakePart.getTranslateX());
            double y = Math.abs(food.getFood().getTranslateY() - snakePart.getTranslateY());

            if (x <= 20 && y <= 20) { 
                return true; 
            }

        }        
       
        return false; 
    }

    public Boolean collideWithSelf() {

        for (int i = 1; i < snake.size(); i++) {     

            Rectangle snakePart = (Rectangle) snake.get(i);
            
            double partY = snakePart.getTranslateY();
            double partX = snakePart.getTranslateX();
            double headY = getSnakeHead().getTranslateY();
            double headX = getSnakeHead().getTranslateX();

            if (partY == headY && partX == headX) {            
                return true; 
            }

        }
    
        return false; 
    }

    public Boolean collideWithBorder() {

        double y = getSnakeHead().getTranslateY();
        double x = getSnakeHead().getTranslateX();

        if (y < 0 || x < 0 || y > 800 || x > 800) {
            return true;
        }
        return false; 
    }
}

