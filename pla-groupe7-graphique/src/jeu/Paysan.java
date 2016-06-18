package jeu;

import automate.Automate;

public class Paysan extends Homme{

	public static final int VIE_STANDARD = 50;
	
	public Paysan(Joueur joueur, Case caseSousLeJoueur, Automate comportement) {
		super(joueur, caseSousLeJoueur, comportement);
		pointsDeVie = 50; // TODO Valeurs arbitraires
		force=5;
		recolte = 20;
		soin = 0;
		convertir = 0;
	}
	
	@Override
	public boolean estEnPleineSante() {
		return (pointsDeVie==VIE_STANDARD);
	}

}
