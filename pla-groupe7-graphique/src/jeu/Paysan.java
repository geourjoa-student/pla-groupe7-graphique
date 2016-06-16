package jeu;

import automate.Automate;

public class Paysan extends Homme{

	public Paysan(Joueur joueur, Case caseSousLeJoueur, Automate comportement) {
		super(joueur, caseSousLeJoueur, comportement);
		pointsDeVie = 50; // TODO Valeurs arbitraires
		force=5;
		recolte = 20;
		soin = 0;
		convertir = 0;
	}

}
