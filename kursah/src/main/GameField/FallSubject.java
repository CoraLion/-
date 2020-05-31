package main.GameField;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class FallSubject {

    private float x;
    private float y;
    private float radius;
    private boolean wasted;

    public FallSubject(Canvas canvas, boolean wastedFood) {
        Random rd = new Random();
        this.x = (float)rd.nextInt(380);
        this.y = 25;
        this.radius = 20;
        this.wasted = wastedFood;
    }

    public void move(int maxHeight) {
        this.y++;
        if (this.y >= maxHeight-5) {this.y = maxHeight-5;}
        if(this.x < 5 && this.x > 400) {this.x -= 30;}
    }

    public void drawfalse(GraphicsContext gc) {
        gc.setFill(new Color(0.7,0,0,1));
        gc.fillOval(this.x-10,this.y-5,20,20);
    }

    public void drawtrue(GraphicsContext gc) {
        gc.setFill(new Color(0,0.7,0,1));
        gc.fillOval(this.x-10,this.y-5,20,20);
    }

    public float x() {
        return this.x;
    }
    public float y() {
        return this.y;
    }
    public boolean isWasted() {
        return this.wasted;
    }
    public float getRadius() {
        return this.radius;
    }
}
