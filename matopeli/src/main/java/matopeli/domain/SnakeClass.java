package matopeli.domain;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.lang.Math; 

import javafx.geometry.Point2D;

public class SnakeClass {

    public ArrayList<Circle> snake;

    Circle head;
    Direction direction = Direction.RIGHT; 
     
    public SnakeClass(Circle head) {
        this.snake = new ArrayList<>();
        this.head = head; 
        this.head.setFill(Color.BLUE);         
        this.snake.add(this.head); 
    }

    public Circle getSnakeHead() {
        return this.head; 
    }

    public ArrayList<Circle> getSnakeBody() {
        return snake; 
    }

    public Circle getTail() {
        return snake.get(getSnakeBody().size() - 1);
    }

    public void setDirection(Direction direction) {
        this.direction = direction; 
        updateDirection();
    }   

    public Direction getDirection() {
        return this.direction; 
    }

    public void updateDirection() {
    
        for (Circle snakePart : snake) {
            switch (this.direction) {
                case UP:
                    moveUP(new Point2D(0, -2), snakePart);
                    break; 
                case DOWN:
                    moveDOWN(new Point2D(0, 2), snakePart);
                    break; 
                case LEFT:
                    moveLEFT(new Point2D(-2, 0), snakePart);
                    break; 
                case RIGHT:
                    moveRIGHT(new Point2D(2, 0), snakePart);
                    break; 
            }
        }
    }

    public void moveUP(Point2D move, Circle part)    { part.setCenterY(part.getCenterY() + move.getY()); } 
    public void moveDOWN(Point2D move, Circle part)  { part.setCenterY(part.getCenterY() + move.getY()); }
    public void moveLEFT(Point2D move, Circle part)  { part.setCenterX(part.getCenterX() + move.getX()); }
    public void moveRIGHT(Point2D move, Circle part) { part.setCenterX(part.getCenterX() + move.getX()); }

    public Boolean touchesFood(Food food) { 
 
        double x = Math.abs(food.getFood().getTranslateX() - this.head.getCenterX());
        double y = Math.abs(food.getFood().getTranslateY() - this.head.getCenterY());

        if (x <= 20 && y <= 20) { return true; }
            
        return false; 
    }

}

