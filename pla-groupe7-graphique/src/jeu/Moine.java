package jeu;

import automate.Automate;

public class Moine extends Homme{

	public Moine(Joueur joueur, Case caseSousLeJoueur, Automate comportement) {
		super(joueur, caseSousLeJoueur, comportement);
		pointsDeVie = 150; // TODO Valeurs arbitraires
		force=5;
		recolte=10;
		soin = 50;
		convertir=33;
	}
}
