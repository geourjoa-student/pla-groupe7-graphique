
package jeu;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class InterfaceGraphique extends Parent implements InterfaceUtilisateur {
	
	
String derniere_action = "null";
	/*
	InterfaceGraphique(){
		this.setOnKeyPressed(new EventHandler<KeyEvent>(){
        public void handle(KeyEvent ke){
            String te = ke.getText().toUpperCase();
            derniere_action = te;
            System.out.println(te);
        }
});
}
	*/
	public void afficherFinDePartie(Joueur joueur1, Joueur joueur2, int resultat) {};

	public void demanderNouvelleAction(String nomJoueur){} ;

	/*
	public void afficherMap2(int[][] decor ){
		int i = 0;
		int j = 0;
		int x = 400;
		int nb_element_max = 1;
		int nb_element_courant = 0;
		int numero_ligne = 0;
		for (int k = 0; k < (decor.length)*(decor.length); k++) {
			if (nb_element_courant < nb_element_max){
				x = x+60;
				nb_element_courant ++;
			}
			else
			{
				if (numero_ligne+1 <= (decor.length)-1)
				{
				x = 400 - (numero_ligne-1)*30;
				nb_element_max ++;
				}
				else
				{
					x = 400 - 30*((decor.length-2)*2-numero_ligne-1);
					nb_element_max --;
				}
				
				numero_ligne ++;
				nb_element_courant = 1;
			}
			System.out.println(decor[j][i]);
			DecorGraphique tile = new DecorGraphique(x, numero_ligne*23, decor[j][i]);
			this.getChildren().add(tile);
			
			if ((j==0) && (i != decor.length - 1)){
				j = i + 1;
				i = 0;
			}
			else{
				if (i==decor.length - 1)
				{
					i =  j + 1;
					j = decor.length - 1;
				}
				else
				{
					i++;
					j--;
				}
			}
		}
		 InputStream uri = Main.class.getResourceAsStream("/images/80190922.gif");
		 Image image = new Image(uri); 
		 ImageView imageView = new ImageView(image); 
		 imageView.setX(500);
		 imageView.setY(400);
		 this.getChildren().add(imageView);
	} */
	
	/*
	public void afficherMap(Case[][] decor ) {
		for (int j = 0; j < decor.length; j++) {
			for (int i = 0; i < decor.length; i++) {
				DecorGraphique tile = new DecorGraphique(i*60+(j%2)*30,j*23, decor[j][i].getTypeDeLaCase(),);
				this.getChildren().add(tile);
			}
			
		}
	} 
	*/
	
	
	public void afficherPersonnages(List<Personnage> personnages ) {
		/*int x;
		int y;
		for (Iterator<Personnage> iterator = personnages.iterator(); iterator.hasNext();) {
			Personnage personnage = iterator.next();
			x = personnage.getCaseSousLePersonnage().getPositionL();
			y = personnage.getCaseSousLePersonnage().getPositionH();
			PersonnageGraphique persoGraphique = new PersonnageGraphique(x*60+(y%2)*30,y*23, personnage.getRole());
			this.getChildren().add(persoGraphique);
		}
		*/
		
	} 

	
	public void afficherJoueur(Joueur joueur1, Joueur joueur2 ){};

	
public Action getNouvelleAction() {
		
		String derniere_action = this.derniere_action;
		
		switch (derniere_action.charAt(0)) {
		
			case 'Z':
				return Action.ALLER_EN_HAUT;
			
			case 'Q':
				return Action.ALLER_A_GAUCHE;

			case 'S':
				return Action.ALLER_EN_BAS;

			case 'D':
				return Action.ALLER_A_DROITE;
				
			/*case '0':
				return Action.NE_RIEN_FAIRE;
				
			case "1":
				return Action.ATTAQUER;

			case "2":
				return Action.RECOLTER;
				
			case "3":
				return Action.SOIGNER;
				
			case "4":
				return Action.CONVERTIR;
				
			case "5":
				return Action.CREER_UNITE;
				
			case "6":
				return Action.ATTAQUER_BATIMENT;
				
			case " ":
				return Action.NE_RIEN_FAIRE;*/
			default:
				/*System.out.println("Commande de jeu :");
				System.out.println("z,q,s,d -> haut, gauche, bas, droite");
				System.out.println("0 -> ne rien faire");
				System.out.println("1 -> attaquer");
				System.out.println("2 -> r�colter");
				System.out.println("3 -> soigner");
				System.out.println("4 -> convertir");
				System.out.println("5 -> cr�er unit�");
				System.out.println("6 -> attaquer batiment");
				System.out.println("? ou n'importe quelles autres touches -> afficher ce manuel puis saisir une action correcte.");
				*/
				
				return Action.NE_RIEN_FAIRE;
			}

	}

@Override
public void afficherMap(Case[][] decor) {
	// TODO Auto-generated method stub
	
}
	
}
