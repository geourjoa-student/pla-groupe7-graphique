package jeu;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;

public class JaugeVie extends Parent {
	public JaugeVie(int abscisse,int ordonnee, int vie){
		final ImageView imageView = new ImageView(Bibliotheque.vie);
		imageView.setX(abscisse+7);
		imageView.setY(ordonnee);
		imageView.getTransforms().add(new Scale(vie/100.0,1,abscisse+20,ordonnee));
		
		this.getChildren().add(imageView);
	}
	
}
