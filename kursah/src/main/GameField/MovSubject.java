package main.GameField;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Controller;
import org.w3c.dom.css.Rect;

public class MovSubject {
    private float x;
    private float y;
    private float radius;

    public MovSubject(Canvas canvas) {
        this.x = (float)canvas.getWidth()/2;
        this.y = (float)canvas.getHeight()-30;
        this.radius = 15;
    }

    public void moveX(int val, int maxWidth) {
        this.x += val;
        if (this.x <= 25) {this.x = 25;}
        else if (this.x >= maxWidth-25) {this.x = maxWidth-25;}
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(new Color(0,0.9,0.8,1));
        gc.fillRect(this.x-25,this.y-25,50,20);
    }

    public float x() {
        return this.x;
    }
    public float y() {
        return this.y;
    }

    public float getRadius() {
        return this.radius;
    }
}
