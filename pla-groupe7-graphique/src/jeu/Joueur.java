package jeu;

import automate.Automate;

public abstract class Joueur {
	
	private static final int COUT_GUERRIER = 80;

	private static final int COUT_MOINE = 60;

	private static final int COUT_PAYSAN = 40;

	protected String nom;
	
	protected int bois;
	
	protected int nourriture;
	
	protected int nombrePersonnage;
	
	protected String nomFichierAutomate;
	
	protected Automate automateGuerrier;
	
	protected Automate automateMoine;
	
	protected Automate automatePaysan;

	protected Partie partie;
	
	protected Case base;
	
	private Heros heros;
	
	public int getBois() {
		return bois;
	}
	
	public void retirerBois(int quantite) {
		bois-=quantite;
	}

	public int getNourriture() {
		return nourriture;
	}
	
	public void ajouterBois(int quantite){
		bois+=quantite;
	}
	
	public void ajouterNourriture(int quantite){
		nourriture+=quantite;
	}

	public String getNom() {
		return nom;
	}

	public abstract Action getNouvelleAction();
	
	@Override
	public String toString() {
		return nom + " : " + getClass().getSimpleName() + " : " + nourriture + " : " + bois;
	}

	public String getNomFichierAutomate(){
		return nomFichierAutomate;
	}

	public Automate getAutomateGuerrier() {
		return automateGuerrier;
	}

	public void setAutomateGuerrier(Automate automateGuerrier) {
		this.automateGuerrier = automateGuerrier;
	}

	public Automate getAutomateMoine() {
		return automateMoine;
	}

	public void setAutomateMoine(Automate automateMoine) {
		this.automateMoine = automateMoine;
	}

	public Automate getAutomatePaysan() {
		return automatePaysan;
	}

	public void setAutomatePaysan(Automate automatePaysan) {
		this.automatePaysan = automatePaysan;
	}

	public Partie getPartie() {
		return partie;
	}
	
	public void setPartie(Partie p) {
		partie=p;;
	}

	public Case getBase() {
		return base;
	}

	public void setBase(Case base) {
		this.base = base;
	}


	public Personnage creerGuerrier(Case caseInsertion) {
		if(nourriture>=COUT_GUERRIER){
			nourriture-=COUT_GUERRIER;
			nombrePersonnage++;
			return new Guerrier(this, caseInsertion, automateGuerrier);
		}
		return null;
	}
	
	public Personnage creerMoine(Case caseInsertion) {
		if(nourriture>=COUT_MOINE){
			nourriture-=COUT_MOINE;
			nombrePersonnage++;
			return new Moine(this, caseInsertion, automateMoine);
		}
		return null;
	}
	
	public Personnage creerPaysan(Case caseInsertion) {
		if(nourriture>=COUT_PAYSAN){
			nourriture-=COUT_PAYSAN;
			nombrePersonnage++;
			return new Paysan(this, caseInsertion, automatePaysan);
		}
		return null;
	}
	
	
	public boolean estMort(){
		return (base.getTypeDeLaCase()==Type.RUINES || heros==null || !heros.estVivant());
	}

	public void setHeros(Heros heros) {
		this.heros=heros;
		
	}
}
