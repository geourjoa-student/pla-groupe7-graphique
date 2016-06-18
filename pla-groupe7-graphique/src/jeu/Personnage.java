package jeu;

import java.util.Random;


public abstract class Personnage {

	private static final int NB_TENTATIVE_ATTAQUE_MAX = 5;

	private static final int NB_TENTATIVE_SOIN_MAX = 5;

	private static final int NB_TENTATIVE_CONVERSION_MAX = 5;

	protected Case caseSousLePersonnage;
	
	protected int role;
	
	protected int recolte;

	protected int pointsDeVie;
	
	protected int force;
	
	protected int soin;
	
	protected int convertir;

	protected Joueur proprietaire;

	public int getVie(){
		return pointsDeVie;
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}

	public int getRole(){
		return role;
	}
	
	public abstract boolean estEnPleineSante();
	/*
	 * Fonctions de déplacement
	 * 
	 * -On teste si la case est accessible (qu'il n'y es pas de personnages dessus ou x autres raisons)
	 * -Si c'est possible 
	 * 		- On retire le personnage de la case ou il est actuellement
	 * 		- On recupere la case de destination
	 * 		- on modifie la case courante du personnage
	 * 		- on place notre personnage sur sa nouvelle case
	 */
	public void allerADroite() {
		if (caseSousLePersonnage.getCaseADroite().estAccessible()) {
			caseSousLePersonnage.retirerPersonnagePresent();
			caseSousLePersonnage = caseSousLePersonnage.getCaseADroite();
			caseSousLePersonnage.placerPersonnage(this);
		}
	}

	public void allerAGauche() {
		if (caseSousLePersonnage.getCaseAGauche().estAccessible()) {
			caseSousLePersonnage.retirerPersonnagePresent();
			caseSousLePersonnage = caseSousLePersonnage.getCaseAGauche();
			caseSousLePersonnage.placerPersonnage(this);
		}
	}

	public void allerEnBas() {
		if (caseSousLePersonnage.getCaseEnBas().estAccessible()) {
			caseSousLePersonnage.retirerPersonnagePresent();
			caseSousLePersonnage = caseSousLePersonnage.getCaseEnBas();
			caseSousLePersonnage.placerPersonnage(this);
		}
	}

	public void allerEnHaut() {
		if (caseSousLePersonnage.getCaseEnHaut().estAccessible()) {
			caseSousLePersonnage.retirerPersonnagePresent();
			caseSousLePersonnage = caseSousLePersonnage.getCaseEnHaut();
			caseSousLePersonnage.placerPersonnage(this);
		}
	}
	
	
	/*
	 * Fonction qui représente l'attaque. Je me pose la question de l'interet de créer des classes Guerriers, tout ça tout ça vu qu'au final c'est l'automate leur comportement
	 * 
	 * La fonction en elle meme permet l'attaque d'une case adjacente. Si il il n'y a pas d'ennemi adjacent, la fonction est sans conséquence sur le personnage et son environment.
	 * Pour faire ça je ture NB_TENTATIVE_ATTQUE_MAX fois un nombre qui correspond à l'orientation d'attaque (0 en haut, 1 à droite ..). Si il y a effectivement une personne attaquable
	 * on l'attaque. (Si il reste des tirages, le personnage à attaquer peut changer mais ça restera une personne attaquable)
	 */
	public void attaquer(){
		Random rand = new Random();
			
		Personnage personnageAAttaquer=null;
		
		for(int numeroEssaiAttaque = 0; numeroEssaiAttaque<NB_TENTATIVE_ATTAQUE_MAX ; numeroEssaiAttaque++){
			
			//On choisit l'orientation d'attaque aléatoirement, si il n'y a personne à l'orientation tirée, on recommence
			//Tirage entre 0 et 4 non compris
			switch (rand.nextInt(4)) {
				case 0:
					//Si il y a personnage sur la case choisis et qu'il n'appartient pas au même joueur, je peux le choisir comme cible
					if(caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent()!=null && caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent().getProprietaire()!=proprietaire)
						personnageAAttaquer=caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent();
					break;
				case 1:
					if(caseSousLePersonnage.getCaseADroite().getPersonnagePresent()!=null && caseSousLePersonnage.getCaseADroite().getPersonnagePresent().getProprietaire()!=proprietaire)
						personnageAAttaquer=caseSousLePersonnage.getCaseADroite().getPersonnagePresent();
					break;
				case 2:
					if(caseSousLePersonnage.getCaseEnBas().getPersonnagePresent()!=null && caseSousLePersonnage.getCaseEnBas().getPersonnagePresent().getProprietaire()!=proprietaire)
						personnageAAttaquer=caseSousLePersonnage.getCaseEnBas().getPersonnagePresent();
					break;	
				case 3:
					if(caseSousLePersonnage.getCaseAGauche().getPersonnagePresent()!=null && caseSousLePersonnage.getCaseAGauche().getPersonnagePresent().getProprietaire()!=proprietaire)
						personnageAAttaquer=caseSousLePersonnage.getCaseAGauche().getPersonnagePresent();
					break;	

				default:
					break;
			}
		}
		
		//Si j'ai  trouve quelqu'un a attaquer, je lui défonce sa race
		if(personnageAAttaquer!=null)
			personnageAAttaquer.recevoirDegat(force+rand.nextInt(force)); //J'ai ajouter un peu d'aléatoire à cet epique combat
		
	}
	
	public void soigner(){
		Random rand = new Random();
			
		Personnage personnageASoigner=null;
		
		for(int numeroEssaiSoin = 0; numeroEssaiSoin<NB_TENTATIVE_SOIN_MAX ; numeroEssaiSoin++){
			
			//On choisit l'orientation d'attaque aléatoirement, si il n'y a personne à l'orientation tirée, on recommence
			//Tirage entre 0 et 4 non compris
			switch (rand.nextInt(4)) {
				case 0:
					//Si il y a personnage sur la case choisis et qu'il n'appartient pas au même joueur, je peux le choisir comme cible
					if(caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent()!=null && allie(caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent()))
						personnageASoigner=caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent();
					break;
				case 1:
					if(caseSousLePersonnage.getCaseADroite().getPersonnagePresent()!=null && allie(caseSousLePersonnage.getCaseADroite().getPersonnagePresent()))
						personnageASoigner=caseSousLePersonnage.getCaseADroite().getPersonnagePresent();
					break;
				case 2:
					if(caseSousLePersonnage.getCaseEnBas().getPersonnagePresent()!=null && allie(caseSousLePersonnage.getCaseEnBas().getPersonnagePresent()))
						personnageASoigner=caseSousLePersonnage.getCaseEnBas().getPersonnagePresent();
					break;	
				case 3:
					if(caseSousLePersonnage.getCaseAGauche().getPersonnagePresent()!=null && allie(caseSousLePersonnage.getCaseAGauche().getPersonnagePresent()))
						personnageASoigner=caseSousLePersonnage.getCaseAGauche().getPersonnagePresent();
					break;	

				default:
					break;
			}
		}
		
		if(personnageASoigner!=null)
			personnageASoigner.recevoirSoin(soin+rand.nextInt(soin)); 
		
	}
	
	//Fonction associé à l'attaque
	public void recevoirDegat(int nbDegats){
		pointsDeVie-=nbDegats;
	}
	
	public void recevoirSoin(int nbSoin){
		if(this instanceof Heros){
			if(pointsDeVie + nbSoin >= Heros.VIE_STANDARD)
				pointsDeVie = Heros.VIE_STANDARD;
			else
				pointsDeVie += nbSoin;
		}
		if(this instanceof Guerrier){
			if(pointsDeVie + nbSoin >= Guerrier.VIE_STANDARD)
				pointsDeVie = Guerrier.VIE_STANDARD;
			else
				pointsDeVie += nbSoin;
		}
		if(this instanceof Moine){
			if(pointsDeVie + nbSoin >= Moine.VIE_STANDARD)
				pointsDeVie = Moine.VIE_STANDARD;
			else
				pointsDeVie += nbSoin;
		}
		if(this instanceof Paysan){
			if(pointsDeVie + nbSoin >= Paysan.VIE_STANDARD)
				pointsDeVie = Paysan.VIE_STANDARD;
			else
				pointsDeVie += nbSoin;
		}
	}
	
	public boolean estVivant (){
		if(pointsDeVie <= 0){
			caseSousLePersonnage.retirerPersonnagePresent();
			
		}
		return (pointsDeVie>0);
	}
	
	public void recolter(){
		Random rand = new Random();
		if(caseSousLePersonnage.getTypeDeLaCase()==Type.CHAMPS){
			proprietaire.ajouterNourriture(caseSousLePersonnage.recolter(recolte+rand.nextInt(recolte)));
		} else if(caseSousLePersonnage.getTypeDeLaCase()==Type.ARBRE){
			proprietaire.ajouterBois(caseSousLePersonnage.recolter(recolte+rand.nextInt(recolte)));
		}  
	}
	
	public boolean allie(Personnage p){
		return (this.proprietaire==p.proprietaire);
	}

	public void etreConvertie(Joueur j){
		proprietaire.retirerUnPersonnage();
		this.proprietaire=j;
		proprietaire.ajouterUnPersonnage();
	}
	
	public void convertir(){
		Random rand = new Random();
			
		Personnage personnageAAConvertir=null;
		
		for(int numeroEssaiConversion = 0; numeroEssaiConversion<NB_TENTATIVE_CONVERSION_MAX ; numeroEssaiConversion++){
			
			//On choisit l'orientation d'attaque aléatoirement, si il n'y a personne à l'orientation tirée, on recommence
			//Tirage entre 0 et 4 non compris
			switch (rand.nextInt(4)) {
				case 0:
					//Si il y a personnage sur la case choisis et qu'il n'appartient pas au même joueur, je peux le choisir comme cible
					if(caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent()!=null && !allie(caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent()))
						personnageAAConvertir=caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent();
					break;
				case 1:
					if(caseSousLePersonnage.getCaseADroite().getPersonnagePresent()!=null && !allie(caseSousLePersonnage.getCaseADroite().getPersonnagePresent()))
						personnageAAConvertir=caseSousLePersonnage.getCaseADroite().getPersonnagePresent();
					break;
				case 2:
					if(caseSousLePersonnage.getCaseEnBas().getPersonnagePresent()!=null && !allie(caseSousLePersonnage.getCaseEnBas().getPersonnagePresent()))
						personnageAAConvertir=caseSousLePersonnage.getCaseEnBas().getPersonnagePresent();
					break;	
				case 3:
					if(caseSousLePersonnage.getCaseAGauche().getPersonnagePresent()!=null && !allie(caseSousLePersonnage.getCaseAGauche().getPersonnagePresent()))
						personnageAAConvertir=caseSousLePersonnage.getCaseAGauche().getPersonnagePresent();
					break;	

				default:
					break;
			}
		}
		
		if(personnageAAConvertir!=null && !(personnageAAConvertir instanceof Heros) && convertir >= rand.nextInt(100))
			personnageAAConvertir.etreConvertie(proprietaire);
		
	}
	
	
	
	
	@Override
	public String toString() {

		return proprietaire.getNom() + " : " + getClass().getSimpleName() + " : " + pointsDeVie + " : ("
				+ caseSousLePersonnage.getPositionH() + "," + caseSousLePersonnage.getPositionL() + ")";
	}


	public void seDeplacer() {
		switch (new Random().nextInt(4)) {
			case 0:
				allerEnHaut();
				break;
			case 1:
				allerEnBas();
				break;
			case 2:
				allerADroite();
				break;
			case 3:
				allerAGauche();
				break;
			default:
				break;
		}
		
	}
	
	public void attaquerBatiment(){
		Random rand = new Random();
			
		Case caseAAttaquer=null;
		
		for(int numeroEssaiAttaque = 0; numeroEssaiAttaque<NB_TENTATIVE_ATTAQUE_MAX ; numeroEssaiAttaque++){
			
			//On choisit l'orientation d'attaque aléatoirement, si il n'y a personne à l'orientation tirée, on recommence
			//Tirage entre 0 et 4 non compris
			switch (rand.nextInt(4)) {
				case 0:
					if(caseSousLePersonnage.getCaseEnHaut().estBatiment() &&  !caseSousLePersonnage.getCaseEnHaut().caseAllie(proprietaire))
						caseAAttaquer=caseSousLePersonnage.getCaseEnHaut();
					break;
				case 1:
					if(caseSousLePersonnage.getCaseEnBas().estBatiment() &&  !caseSousLePersonnage.getCaseEnBas().caseAllie(proprietaire))
						caseAAttaquer=caseSousLePersonnage.getCaseEnBas();
					break;
				case 2:
					if(caseSousLePersonnage.getCaseAGauche().estBatiment() &&  !caseSousLePersonnage.getCaseAGauche().caseAllie(proprietaire))
						caseAAttaquer=caseSousLePersonnage.getCaseAGauche();
					break;
				case 3:
					if(caseSousLePersonnage.getCaseADroite().estBatiment() &&  !caseSousLePersonnage.getCaseADroite().caseAllie(proprietaire))
						caseAAttaquer=caseSousLePersonnage.getCaseADroite();
					break;

				default:
					break;
			}
		}
		
	
		if(caseAAttaquer!=null)
			caseAAttaquer.recevoirDegat(force+rand.nextInt(force)); //J'ai ajouter un peu d'aléatoire à cet epique combat
		
	}

}
