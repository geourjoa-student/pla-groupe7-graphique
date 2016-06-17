package jeu;

import automate.Automate;

public class Guerrier extends Homme {

	public static final int VIE_STANDARD = 150;
	
	public Guerrier(Joueur joueur, Case caseSousLeJoueur, Automate comportement) {
		super(joueur, caseSousLeJoueur, comportement);
		pointsDeVie = 150; // TODO Valeurs arbitraires
		force=20;
		recolte = 10;
		soin = 0;
		convertir = 0;
	}

}
