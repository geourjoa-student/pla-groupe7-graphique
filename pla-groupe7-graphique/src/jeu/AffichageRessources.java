package jeu;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AffichageRessources extends Parent{

	
	public AffichageRessources(int abscisse, int ordonnee, Joueur joueur){
		final ImageView imageView = new ImageView(Bibliotheque.ressources);
		imageView.setX(abscisse);
		imageView.setY(ordonnee);
		this.getChildren().add(imageView);
		
		Text nourriture = new Text(abscisse+47, ordonnee+20, Integer.toString(joueur.getNourriture()));
		Text bois = new Text(abscisse+150, ordonnee+20, Integer.toString(joueur.getBois()));
		Text population = new Text(abscisse+240, ordonnee+20, Integer.toString(joueur.getNombrePersonnage()));
		nourriture.setFill(Color.WHITE);
		bois.setFill(Color.WHITE);
		population.setFill(Color.WHITE);
		this.getChildren().add(nourriture);
		this.getChildren().add(bois);
		this.getChildren().add(population);
	}
}
