package jeu;





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



public class DecorGraphique extends Parent {
	
	
	
	
public  DecorGraphique(int abscisse, int ordonnee, Case lacase, Partie partie){
	final ImageView imageView;
	Type type = lacase.getTypeDeLaCase();
	switch (type) {
	case ARBRE:
		imageView = new ImageView(Bibliotheque.arbre);
		break;
	case HERBE:
		imageView = new ImageView(Bibliotheque.herbe);
		break;
	case ROCHER:
		imageView = new ImageView(Bibliotheque.rocher);
		break;
	case CHAMPS:
		imageView = new ImageView(Bibliotheque.champ);
		break;
		
	case CASERNE:
		imageView = new ImageView(Bibliotheque.caserne);
		break;

	case EGLISE:
		imageView = new ImageView(Bibliotheque.tour);
		break;
		
	case POLYTECH:
		if (lacase.getProprietaire() == partie.getJoueur1()){
		imageView = new ImageView(Bibliotheque.polytech);
		}
		else
		{
			imageView = new ImageView(Bibliotheque.polytechRouge);
		}
		break;
		
	case RUINES:
		imageView = new ImageView(Bibliotheque.ruines);
		break;
		
	case SOUCHE:
		imageView = new ImageView(Bibliotheque.souche);
		break;
		
	case HOPITAL:
		if (lacase.getProprietaire() == partie.getJoueur1()){
		imageView = new ImageView(Bibliotheque.hopital);
		}
		else
		{
			imageView = new ImageView(Bibliotheque.hopitalRouge);
			}
		break;
		
	case FERME:
		if (lacase.getProprietaire() == partie.getJoueur1()){
		imageView = new ImageView(Bibliotheque.ferme);
		}
		else
		{
			imageView = new ImageView(Bibliotheque.fermeRouge);
		}
		break;
		
	default:
		imageView = new ImageView(Bibliotheque.herbe);
		break;
	}
	
	imageView.setX(abscisse);
	imageView.setY(ordonnee);
	this.getChildren().add(imageView);
	
	
	
	
	
	//GridPane gridpane = new GridPane();
	
        /*
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
    */}



}
