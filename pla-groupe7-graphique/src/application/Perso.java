package application;





import java.io.File;
import java.io.InputStream;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import jeu.Main;



public class Perso extends Parent {
	
	
public  Perso(){
	
	GridPane gridpane = new GridPane();
	
        Rectangle fond_clavier = new Rectangle();
        fond_clavier.setWidth(400);
        fond_clavier.setHeight(200);
        fond_clavier.setArcWidth(30);
        fond_clavier.setArcHeight(30);
        fond_clavier.setFill(Color.BLACK);
        this.getChildren().add(fond_clavier);//on ajoute le rectangle au groupe
        
        
        Timeline  timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(fond_clavier.translateXProperty(), 10)),
            new KeyFrame(new Duration(5000), new KeyValue(fond_clavier.translateXProperty(), 500))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	fond_clavier.setFill(Color.GREY);
            }
        });
        
        this.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	fond_clavier.setFill(Color.BLUE);
            }  });

        final String imageURI = new File("C://image.gif").toURI().toString();
    	InputStream uri = Main.class.getResourceAsStream("/images/80190922.gif");
    	final Image image = new Image(uri); 
    final ImageView imageView = new ImageView(image); 

   gridpane.add(imageView,1,0);
   ImageView im2 = new ImageView();
   im2 = imageView;
   //gridpane.add(im2,1,1);
    gridpane.add(fond_clavier,1,1);
    gridpane.setVgap(15);
      
   this.getChildren().add(gridpane);
    }



}
