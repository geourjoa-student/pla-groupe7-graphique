package jeu;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AffichageRessources extends Parent{

	
	public AffichageRessources(int abscisse, int ordonnee, Joueur joueur1, Joueur joueur2){
		final ImageView imageView = new ImageView(Bibliotheque.ressources);
		imageView.setX(abscisse);
		imageView.setY(ordonnee);
		this.getChildren().add(imageView);
		
		Text nourriture = new Text(abscisse+190, ordonnee+20, Integer.toString(joueur1.getNourriture()));
		Text bois = new Text(abscisse+298, ordonnee+20, Integer.toString(joueur1.getBois()));
		Text population = new Text(abscisse+390, ordonnee+20, Integer.toString(joueur1.getNombrePersonnage()));
		
		Text nourriture2 = new Text(abscisse+590, ordonnee+20, Integer.toString(joueur2.getNourriture()));
		Text bois2 = new Text(abscisse+698, ordonnee+20, Integer.toString(joueur2.getBois()));
		Text population2 = new Text(abscisse+790, ordonnee+20, Integer.toString(joueur2.getNombrePersonnage()));
		nourriture.setFill(Color.WHITE);
		bois.setFill(Color.WHITE);
		population.setFill(Color.WHITE);
		nourriture2.setFill(Color.WHITE);
		bois2.setFill(Color.WHITE);
		population2.setFill(Color.WHITE);
		this.getChildren().add(nourriture);
		this.getChildren().add(bois);
		this.getChildren().add(population);
		this.getChildren().add(nourriture2);
		this.getChildren().add(bois2);
		this.getChildren().add(population2);
	}
}
