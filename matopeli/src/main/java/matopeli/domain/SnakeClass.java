package matopeli.domain;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color; 
import java.util.ArrayList;

import javafx.geometry.Point2D;

public class SnakeClass {

    public ArrayList<Circle> snake = new ArrayList<>(); 

    //We're not actually using x and y... at all...
    int x; 
    int y; 

    Circle head;
    Direction direction = Direction.RIGHT; 
     
    public SnakeClass(Circle head) {
        this.head = head; 
        this.head.setFill(Color.BLUE);         
    }

    public Circle getSnakeHead() {
        return head; 
    }

    public void setDirection(Direction direction) {
        this.direction = direction; 
        updateDirection();
    }   

    public Direction getDirection() {
        return this.direction; 
    }

    public void updateDirection() {
        switch(this.direction) {
            case UP:
                moveUP(new Point2D(0, -2));
                break; 
            case DOWN:
                moveDOWN(new Point2D(0, 2));
                break; 
            case LEFT:
                moveLEFT(new Point2D(-2, 0));
                break; 

            case RIGHT:
                moveRIGHT(new Point2D(2, 0));
                break; 
        }
    }


    public void moveUP(Point2D move)    {this.head.setTranslateY(this.head.getTranslateY() + move.getY());} 
    public void moveDOWN(Point2D move)  {this.head.setTranslateY(this.head.getTranslateY() + move.getY());}
    public void moveLEFT(Point2D move)  {this.head.setTranslateX(this.head.getTranslateX() + move.getX());}
    public void moveRIGHT(Point2D move) {this.head.setTranslateX(this.head.getTranslateX() + move.getX());}

}
