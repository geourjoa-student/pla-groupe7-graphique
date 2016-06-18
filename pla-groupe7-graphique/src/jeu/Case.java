package jeu;

import java.util.Random;

public class Case {

	public static final int CAPACITE_VIE_STANDARD = 100;

	public static final int CAPACITE_VIE_POLYTECH = 500;

	public static final int CAPACITE_RESSOURCE_STANDARD = 50;

	private int niveauDeResssource;

	private Joueur proprietaire;

	private Type typeDeLaCase;

	private Type typeOriginal;

	private Case caseADroite;

	public Joueur getProprietaire() {
		return proprietaire;
	}

	private Case caseAGauche;

	private Case caseEnBas;

	private Case caseEnHaut;

	private Personnage personnagePresent;

	// Utilisé uniquement pour l'affichage
	private int positionH;
	private int positionL;

	private int vie;

	public Case(Type typeDeLaCase, int h, int l) {
		this.setTypeDeLaCase(typeDeLaCase);

		personnagePresent = null;
		positionH = h;
		positionL = l;

		if (estBatiment()) {
			vie = CAPACITE_VIE_STANDARD;
		} else {
			vie = 0;
		}
	}

	public int getVie() {
		return vie;
	}

	public Type getTypeDeLaCase() {
		return typeDeLaCase;
	}

	public void setTypeDeLaCase(Type typeDeLaCase) {
		this.typeDeLaCase = typeDeLaCase;
		typeOriginal = typeDeLaCase;
		if (this.typeDeLaCase == Type.ARBRE || this.typeDeLaCase == Type.CHAMPS) {
			niveauDeResssource = CAPACITE_RESSOURCE_STANDARD;
		} else {
			niveauDeResssource = 0;
		}
	}

	public Case getCaseADroite() {
		return caseADroite;
	}

	public void setCaseADroite(Case caseADroite) {
		this.caseADroite = caseADroite;
	}

	public Case getCaseAGauche() {
		return caseAGauche;
	}

	public void setCaseAGauche(Case caseAGauche) {
		this.caseAGauche = caseAGauche;
	}

	public Case getCaseEnBas() {
		return caseEnBas;
	}

	public void setCaseEnBas(Case caseEnBas) {
		this.caseEnBas = caseEnBas;
	}

	public Case getCaseEnHaut() {
		return caseEnHaut;
	}

	public void setCaseEnHaut(Case caseEnHaut) {
		this.caseEnHaut = caseEnHaut;
	}

	public Personnage getPersonnagePresent() {
		return personnagePresent;
	}

	public void retirerPersonnagePresent() {
		this.personnagePresent = null;
	}

	public void placerPersonnage(Personnage personnage) {
		this.personnagePresent = personnage;
	}

	public int getPositionH() {
		return positionH;
	}

	public int getPositionL() {
		return positionL;
	}

	public boolean estAccessible() {
		return (typeDeLaCase != Type.ROCHER && personnagePresent == null && !estBatiment());
	}

	@Override
	public String toString() {

		String s = " ";

		if (personnagePresent != null) {
			if (personnagePresent instanceof Heros)
				s = "H";
			else if (personnagePresent instanceof Guerrier)
				s = "G";
			if (personnagePresent instanceof Moine)
				s = "M";
			if (personnagePresent instanceof Paysan)
				s = "P";
		}

		switch (typeDeLaCase) {
			case HERBE:
				return s + ". ";
			case ARBRE:
				return s + "| ";
			case CHAMPS:
				return s + "# ";
			case ROCHER:
				return s + "o ";
			case SOUCHE:
				return s + "_ ";
			case CASERNE:
				return s + "C ";
			case POLYTECH:
				return s + "P ";
			case FERME:
				return s + "F ";
			case EGLISE:
				return s + "E ";
			case HOPITAL:
				return s + "H ";
			case RUINES:
				return s + "R ";
			default:
				return s + ". ";

		}
	}

	public int getNiveauDeResssource() {
		return niveauDeResssource;
	}

	public int recolter(int capaRecolte) {
		Random rand = new Random();

		int recolte = capaRecolte + rand.nextInt(capaRecolte);

		if (typeDeLaCase == Type.CHAMPS) {
			if (recolte > niveauDeResssource) {
				recolte = niveauDeResssource;
				niveauDeResssource = 0;
				typeDeLaCase = Type.HERBE;
			} else {
				niveauDeResssource -= recolte;
			}

			return recolte;
		}

		if (typeDeLaCase == Type.ARBRE) {
			if (recolte > niveauDeResssource) {
				recolte = niveauDeResssource;
				niveauDeResssource = 0;
				typeDeLaCase = Type.SOUCHE;
			} else {
				niveauDeResssource -= recolte;
			}

			return recolte;
		}

		return 0;

	}

	public boolean caseAllie(Joueur j) {
		return (j == proprietaire);
	}

	public boolean estBatiment() {
		return (typeDeLaCase == Type.POLYTECH || typeDeLaCase == Type.CASERNE || typeDeLaCase == Type.EGLISE || typeDeLaCase == Type.FERME || typeDeLaCase == Type.HOPITAL
				|| typeDeLaCase == Type.ARMURERIE);
	}

	public boolean estRecoltable() {

		return (typeDeLaCase == Type.CHAMPS || typeDeLaCase == Type.ARBRE);
	}

	public void setProprietaire(Joueur j) {
		proprietaire = j;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public void recevoirDegat(int degat) {

		if (estBatiment()) {
			vie -= degat;

			if (vie <= 0) {
				vie = 0;
				typeDeLaCase = Type.RUINES;
			}
		}

	}

	public boolean reparer() {
		boolean tmp = false;
		if (typeOriginal == typeDeLaCase)
			tmp = true;

		switch (typeOriginal) {

			case ARBRE:
				typeDeLaCase = Type.ARBRE;
				niveauDeResssource = CAPACITE_RESSOURCE_STANDARD;
				break;
			case CASERNE:
				typeDeLaCase = Type.CASERNE;
				vie = CAPACITE_VIE_STANDARD;
				break;
			case CHAMPS:
				typeDeLaCase = Type.CHAMPS;
				niveauDeResssource = CAPACITE_RESSOURCE_STANDARD;
				break;
			case EGLISE:
				typeDeLaCase = Type.EGLISE;
				vie = CAPACITE_VIE_STANDARD;
				break;
			case FERME:
				typeDeLaCase = Type.FERME;
				vie = CAPACITE_VIE_STANDARD;
				break;
			case HOPITAL:
				typeDeLaCase = Type.HOPITAL;
				vie = CAPACITE_VIE_STANDARD;
				break;
			case ARMURERIE:
				typeDeLaCase = Type.ARMURERIE;
				vie = CAPACITE_VIE_STANDARD;
				break;
			default:
				return false;
		}
		if (tmp)
			return true;
		return false;
	}

}
