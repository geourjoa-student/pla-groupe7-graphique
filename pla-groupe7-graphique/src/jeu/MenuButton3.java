package jeu;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton3 extends StackPane {
        private Text text;

        public MenuButton3(String nom) {
            text = new Text(nom);
            //text.setFont(text.getFont().font(30));
            text.setFill(Color.WHITE);
            text.setStyle("-fx-font: 30 defused ;");

            Rectangle fond = new Rectangle(270, 30);
            fond.setOpacity(0.4);
            fond.setFill(Color.BLACK);
            //Flou 
            fond.setEffect(new GaussianBlur(3.5));

            //setAlignment(Pos.CENTER_LEFT);
            
            getChildren().addAll(fond, text);

            setOnMouseEntered(event -> {
                fond.setTranslateX(10);
                text.setTranslateX(10);
                fond.setFill(Color.GREEN);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                fond.setTranslateX(0);
                text.setTranslateX(0);
                fond.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            
            DropShadow drop = new DropShadow(50, Color.BLACK);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }