package jeu;

import java.util.Random;

public class Heros extends Personnage {

	// TODO C'est ici que l'on peut paramètrer les capacités de bases
	private static final int FORCE_HEROS_STANDARD = 30;
	public static final int VIE_STANDARD = 200;
	private static final int CAPACITE_RECOLTE_STANDARD = 20;
	private static final int CAPACITE_SOIN_STANDARD = 50;
	private static final int TAUX_DE_REUSSITE_DE_CONVERSION_STANDARD = 20;
	private static final int COUT_REPARATION = 100;

	public Heros(Joueur joueur, Case caseSousLeJoueur) {
		this.caseSousLePersonnage = caseSousLeJoueur;
		caseSousLeJoueur.placerPersonnage(this);
		proprietaire = joueur;
		pointsDeVie = VIE_STANDARD;
		force = FORCE_HEROS_STANDARD;
		recolte = CAPACITE_RECOLTE_STANDARD;
		soin = CAPACITE_SOIN_STANDARD;
		convertir = TAUX_DE_REUSSITE_DE_CONVERSION_STANDARD;

		proprietaire.setHeros(this);
	}

	public Personnage creerUnite() {
		Personnage p = null;
		Case caseBatiment = null;
		Random rand = new Random();

		int nbEssai = 0;

		do {
			switch (rand.nextInt(4)) {
			case 0:
				caseBatiment = caseSousLePersonnage.getCaseEnHaut();
				break;
			case 1:
				caseBatiment = caseSousLePersonnage.getCaseEnBas();
				break;
			case 2:
				caseBatiment = caseSousLePersonnage.getCaseAGauche();
				break;
			case 3:
				caseBatiment = caseSousLePersonnage.getCaseADroite();
				break;
			default:
				break;
			}

			nbEssai++;

		} while (nbEssai < 5
				&& (caseBatiment == null || !caseBatiment.estBatiment() || !caseBatiment.caseAllie(proprietaire)));

		if (nbEssai < 5) {

			Case caseInsertion = null;

			if (caseBatiment.getCaseADroite().estAccessible()) {
				caseInsertion = caseBatiment.getCaseADroite();
			} else if (caseBatiment.getCaseAGauche().estAccessible()) {
				caseInsertion = caseBatiment.getCaseAGauche();
			} else if (caseBatiment.getCaseEnBas().estAccessible()) {
				caseInsertion = caseBatiment.getCaseEnBas();
			} else if (caseBatiment.getCaseEnHaut().estAccessible()) {
				caseInsertion = caseBatiment.getCaseEnHaut();
			}

			if (caseInsertion != null) {

				if (caseBatiment.getTypeDeLaCase() == Type.CASERNE) {
					p = proprietaire.creerGuerrier(caseInsertion);
					
				}
				if (caseBatiment.getTypeDeLaCase() == Type.POLYTECH) {
					p = proprietaire.creerPaysan(caseInsertion);
				}
				if (caseBatiment.getTypeDeLaCase() == Type.EGLISE) {
					p = proprietaire.creerMoine(caseInsertion);
				}

			}
		}
		return p;

	}

	public void reparer() {
		if (proprietaire.getBois() >= COUT_REPARATION && (caseSousLePersonnage.caseAllie(this.proprietaire) || caseSousLePersonnage.getProprietaire() == null)) {
			if(caseSousLePersonnage.reparer());
				proprietaire.retirerBois(COUT_REPARATION);
		}

	}

}
