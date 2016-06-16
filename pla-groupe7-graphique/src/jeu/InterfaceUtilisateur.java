package jeu;

import java.util.List;

public interface InterfaceUtilisateur {

	public abstract void demanderNouvelleAction(String nomJoueur) ;

	public abstract void afficherMap(Case[][] decor) ;
	
	public abstract void afficherPersonnages(List<Personnage> personnages) ;
	
	public abstract void afficherJoueur(Joueur joueur1, Joueur joueur2 );

	public abstract void afficherFinDePartie(Joueur joueur1, Joueur joueur2, int resultat) ;
	
}
