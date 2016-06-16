package jeu;

import automate.Automate;

public class Guerrier extends Homme {

	public Guerrier(Joueur joueur, Case caseSousLeJoueur, Automate comportement) {
		super(joueur, caseSousLeJoueur, comportement);
		pointsDeVie = 150; // TODO Valeurs arbitraires
		force=20;
		recolte = 10;
		soin = 0;
		convertir = 0;
	}

}
