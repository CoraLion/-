package main.GameField;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.Random;

public class GameField {

    private int score = 0;
    private int over = 3;

    public void draw(Canvas canvas, MovSubject object, ArrayList<FallSubject> fall) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(new Color(0.9,1,1,1));
        gc.fillRect(0,0,400,400);
        object.draw(gc);
        for (FallSubject f : fall) {
            if (f != null) {
                if (f.isWasted()) {
                    f.drawfalse(gc);
                } else {
                    f.drawtrue(gc);
                }
            }
        }
        gc.setFill(new Color(0,0,0,1));
        gc.setLineWidth(1);
        gc.setFont(new Font(12));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Score: " + score, canvas.getWidth()-390, canvas.getHeight()-380);
        gc.fillText("Healt: " + over, canvas.getWidth()-325, canvas.getHeight()-380);
    }



    public void move(Canvas canvas, MovSubject object, boolean mLeft, boolean mRight) {
        if (mLeft) {
            object.moveX(-3, (int) canvas.getWidth());
        }
        if (mRight) {
            object.moveX(3, (int) canvas.getWidth());
        }
    }

    public void moveelement(Canvas canvas, ArrayList<FallSubject> falling) {
        for (FallSubject f : falling) {
            if (f != null) {
                f.move((int) canvas.getHeight());
            }
        }
    }

    public void addelementinlist(Canvas canvas, ArrayList<FallSubject> falling){
        Random rd = new Random();
        int c = rd.nextInt(3);
        boolean bad = false;
        if (c == 2) {
            bad = true;
        }
        falling.add(new FallSubject(canvas, bad));
        }

    public int collisiondetection(Canvas canvas, MovSubject object, ArrayList<FallSubject> falling) {
        FallSubject fall;
        for (int i = 0; i < falling.size(); i++) {
            fall = falling.get(i);
            if (fall != null) {
                float distance = (object.x() - fall.x()) * (object.x() - fall.x()) + (object.y() - fall.y()) * (object.y() - fall.y());
                float radiuses = (object.getRadius() + fall.getRadius()) * (object.getRadius() + fall.getRadius());
                if (Math.abs(distance) < radiuses) {
                        if (!fall.isWasted()) {
                            score += 10;
                            falling.remove(fall);
                        } else {
                            over--;
                            falling.remove(fall);
                        }

                    }
                if(fall.y()==canvas.getHeight()-10) {
                    if(!fall.isWasted()) {
                        over--;
                    }
                    falling.remove(fall);
                }
            }
        }
        return over;
    }
}
