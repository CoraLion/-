package main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.GameField.FallSubject;
import main.GameField.GameField;
import main.GameField.MovSubject;
import java.util.ArrayList;


public class Controller  {

    @FXML private Canvas canvas;
    private MovSubject object;
    private GameField field;
    private ArrayList<FallSubject> falling;
    private boolean mRight, mLeft;
    private int counter;
    private Timeline timeline;


    public void exit() {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        object = new MovSubject(canvas);
        falling = new ArrayList<>();
        field = new GameField();
    }

    @FXML
    private void runTime() {
        timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> start()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void start() {
        processKey();
        field.draw(canvas, object, falling);
        field.move(canvas, object, mLeft, mRight);
        field.moveelement(canvas, falling);
        if (counter < 100) {
            counter++;
        } else {
            field.addelementinlist(canvas, falling);
            counter = 0;
        }
        int over = field.collisiondetection(canvas, object, falling);
        if(over == 0) {
            timeline.stop();
            falling = null;
            object = null;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Вы проиграли!");
            alert.setHeaderText("Вы совершили сильно много ошибок!");
            alert.show();
            object = new MovSubject(canvas);
            falling = new ArrayList<>();
            field = new GameField();
            timeline.play();
        }
    }

    private void processKey() {
        canvas.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) {
                mLeft = true;
            }  else if (mouseEvent.isSecondaryButtonDown()) {
                mRight = true;
            }
        });
        canvas.setOnMouseReleased(mouseEvent -> {
            mLeft = false;
            mRight = false;
        });
    }

    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Что надо делать?");
        alert.setHeaderText("Для управления зажимайте правую или левую кнопку миши. \nЛовите зелёные кружки, чтобы получать очки. \n" +
                "За красные кружки с вас снимут жизни. \nТак же за то, что вы пропустили зелёный кружок, с вас снимут жизни. \n" +
                "Если жизни кончатся, вы проиграли. \n" +
                "Для старта игры нажмите правую клавишу мыши в любой части игрового поля!");
        alert.show();
    }

    public void rules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Что надо делать?");
        alert.setHeaderText("Для управления зажимайте правую или левую кнопку миши. \nЛовите зелёные кружки, чтобы получать очки. \n" +
                "За красные кружки с вас снимут жизни. \nТак же за то, что вы пропустили зелёный кружок, с вас снимут жизни. \n" +
                "Если жизни кончатся, вы проиграли. Эти же правила вы можете посмотреть во вкладке help -> about. \n" +
                "Для старта игры нажмите правую клавишу мыши в любой части игрового поля!");
        alert.show();
    }
}
