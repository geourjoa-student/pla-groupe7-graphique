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
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import jeu.Main;



public class PersonnageGraphique extends Parent {
	
	
public  PersonnageGraphique(int abscisse, int ordonnee, Personnage perso, Partie partie){
	final ImageView imageView;
	
	if (perso instanceof Guerrier){
		if (perso.getProprietaire() == partie.getJoueur1())
		{
		imageView = new ImageView(Bibliotheque.guerrier);
		}
		else
		{
		imageView = new ImageView(Bibliotheque.guerrierRouge);	
		}
	}
	else 
		if (perso instanceof Moine){
			if (perso.getProprietaire() == partie.getJoueur1())
			{
			imageView = new ImageView(Bibliotheque.moine);
			}
			else
			{
			imageView = new ImageView(Bibliotheque.moineRouge);
			}
		}
		else
			if (perso instanceof Paysan){
				if (perso.getProprietaire() == partie.getJoueur1())
				{
				imageView = new ImageView(Bibliotheque.paysan);
				}
				else
				{
				imageView = new ImageView(Bibliotheque.paysanRouge);
				}
				imageView.setTranslateX(15);
				imageView.setTranslateY(8);
			}
			else
				if (perso instanceof Heros){
					if (perso.getProprietaire() == partie.getJoueur1())
					{
					imageView = new ImageView(Bibliotheque.heros);
					}
					else
					{
					imageView = new ImageView(Bibliotheque.herosRouge);
					}
				}
				else
				{
					imageView = new ImageView(Bibliotheque.heros);
				}

	imageView.setX(abscisse);
	imageView.setY(ordonnee);
	this.getChildren().add(imageView);

	
}

}

