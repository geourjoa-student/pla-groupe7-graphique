package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import automate.Automate;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class Partie extends Parent{

	// Taille du monde, une largeur impaire c'est mieux pour centrer
	public static final int LARGEUR = 27;

	public static final int HAUTEUR = 24;

	public static final int EGALITE = 0;

	public static final int JOUEUR_1_GAGNE = 1;

	public static final int JOUEUR_2_GAGNE = 2;

	private InterfaceUtilisateur interfaceUtilisateur; // Interface du jeu

	private Joueur joueur1;

	private Joueur joueur2;

	private List<Personnage> personnages;

	/*
	 * 
	 * 0,0------------------largeur,0 ------------------------------
	 * hauteur,0------hauteur,largeur
	 */
	private Case decor[][];
	Action bufferJ1 = Action.NE_RIEN_FAIRE;
	Action bufferJ2 = Action.NE_RIEN_FAIRE;
	
	
	public Joueur getJoueur1(){
		return joueur1;
	}

	public Joueur getJoueur2(){
		return joueur2;
	}
	public Partie(InterfaceUtilisateur interfaceUtilisateur, Joueur joueur1, Joueur joueur2, Group root) {
			
		this.interfaceUtilisateur = interfaceUtilisateur;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		
		joueur1.setPartie(this);
		joueur2.setPartie(this);

		personnages = new LinkedList<Personnage>();

		decor = new Case[HAUTEUR][LARGEUR];

		creerDecor(joueur1.getNomFichierAutomate(), joueur2.getNomFichierAutomate());

		personnages.add(new Heros(joueur1, decor[2][(int)((LARGEUR-1)/2)]));
		personnages.add(new Heros(joueur2, decor[HAUTEUR-3][(int)((LARGEUR-1)/2)]));
		
		jouerTour();
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        public void handle(KeyEvent ke){
	            String te = ke.getText().toUpperCase();
	            switch (te) {
	    		
				case "R":
					bufferJ1 = Action.ALLER_EN_HAUT;
					break;
				
				case "E":
					bufferJ1 = Action.ALLER_A_GAUCHE;
					break;

				case "D":
					bufferJ1 = Action.ALLER_EN_BAS;
					break;

				case "F":
					bufferJ1 = Action.ALLER_A_DROITE;
					break;
					
				case "O":
					bufferJ2 = Action.ALLER_EN_HAUT;
					break;
				
				case "I":
					bufferJ2 = Action.ALLER_A_GAUCHE;
					break;

				case "K":
					bufferJ2 = Action.ALLER_EN_BAS;
					break;

				case "L":
					bufferJ2 = Action.ALLER_A_DROITE;
					break;
					
				case "0":
					bufferJ2 = Action.NE_RIEN_FAIRE;
					break;
					
				case "1":
					bufferJ2 =  Action.RECOLTER;
					break;
				case "2":
					bufferJ2 =  Action.ATTAQUER;
					break;
				case "3":
					bufferJ2 =  Action.ATTAQUER_BATIMENT;
					break;
				case "4":
					bufferJ2 =  Action.CREER_UNITE;
					break;
				case "5":
					bufferJ2 =  Action.CONVERTIR;
					break;
				case "6":
					bufferJ2 =  Action.SOIGNER;
					break;
				case "7":
					bufferJ2 =  Action.REPARER;
					break;
					
				case "&":
					bufferJ1 =  Action.RECOLTER;
					break;
				case "É":
					bufferJ1 =  Action.ATTAQUER;
					break;
				case "\"":
					bufferJ1 =  Action.ATTAQUER_BATIMENT;
					break;
				case "'":
					bufferJ1 =  Action.CREER_UNITE;
					break;
				case "(":
					bufferJ1 =  Action.CONVERTIR;
					break;
				case "-":
					bufferJ1 =  Action.SOIGNER;
					break;
				case "È":
					bufferJ1 =  Action.REPARER;
					break;
					
					
				default:
					/*System.out.println("Commande de jeu :");
					System.out.println("z,q,s,d -> haut, gauche, bas, droite");
					System.out.println("0 -> ne rien faire");
					System.out.println("1 -> attaquer");
					System.out.println("2 -> r�colter");
					System.out.println("3 -> soigner");
					System.out.println("4 -> convertir");
					System.out.println("5 -> cr�er unit�");
					System.out.println("6 -> attaquer batiment");
					System.out.println("? ou n'importe quelles autres touches -> afficher ce manuel puis saisir une action correcte.");
					*/
					bufferJ1 = Action.NE_RIEN_FAIRE;
					bufferJ2 = Action.NE_RIEN_FAIRE;
					break;
					
				}
	        
	            if (! estTermine() && !(joueur1.estMort()) && !(joueur2.estMort()) ){
	            jouerTour();
	            }
	            else
	            {
	            	bufferJ1 = Action.NE_RIEN_FAIRE;
					bufferJ2 = Action.NE_RIEN_FAIRE;
	            	jouerTour();
	            	Particule part = new Particule(Color.WHITE, 2000, 500, 1000, 1000, -100, 3500);
	            	final Font font = new Font("Calibri", 75);
	            	if(joueur2.estMort()){
	            		final Text text = new Text("Joueur 1 "); 
	                	text.setLayoutX(430); 
	                	text.setLayoutY(265); 
	                	text.setFill(Color.BLUE); 
	                	text.setFont(font);
	                	root.getChildren().add(text);
	            	}
	            	else{
	            		final Text text = new Text("Joueur 2 "); 
	                	text.setLayoutX(430); 
	                	text.setLayoutY(265); 
	                	text.setFill(Color.RED); 
	                	text.setFont(font);
	                	root.getChildren().add(text);
	            	}
            		final Text text2 = new Text("remporte la partie!"); 
                	text2.setLayoutX(720); 
                	text2.setLayoutY(265);
                	text2.setFont(font);
                	text2.setFill(Color.WHITE); 
                	root.getChildren().add(text2);
	                root.getChildren().add(part);
	                MenuButton btnRejouer = new MenuButton("REJOUER");
	                MenuButton btnRetour = new MenuButton("RETOUR");
	                btnRejouer.setTranslateX(700);
	                btnRejouer.setTranslateY(320);
	                btnRetour.setTranslateX(700);
	                btnRetour.setTranslateY(390);
	                root.getChildren().add(btnRejouer);
	                root.getChildren().add(btnRetour);
	                
	            	/*FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
	            	ft.setFromValue(1.0);
	            	ft.setToValue(0);
	            	ft.setCycleCount(1);
	            	ft.setAutoReverse(true);
	            	ft.play();*/
	            }
	        
	            
	        }
	});
		
	}
	
	
	private void inclureAutomates(ArrayList<Automate> autoJ1,ArrayList<Automate> autoJ2){
		Automate autoTempo;
		Case[][] tableauTempo;
		// JOUEUR 1 
		for(int i=0;i<autoJ1.size();i++){
			autoTempo = autoJ1.get(i);
			tableauTempo = autoTempo.getDecor();
			// Si on � un guerrier
			if(autoTempo.getRole() == 1) {
				joueur1.setAutomateGuerrier(autoTempo);
				for(int j=0;j<tableauTempo.length;j++){
					for(int k=0;k<tableauTempo[j].length;k++){
						tableauTempo[j][k].setProprietaire(joueur1);
						// Le premier automate s'affiche 2 cases � droite du h�ros
						decor[j+2][(int)((LARGEUR-1)/2)+2+k] = tableauTempo[j][k];					
					}
				}
			}
			// Si on � un moine
			if(autoTempo.getRole() == 2) {
				joueur1.setAutomateMoine(autoTempo);
				for(int j=0;j<tableauTempo.length;j++){
					for(int k=0;k<tableauTempo[j].length;k++){
						tableauTempo[j][k].setProprietaire(joueur1);
						// Le premier automate s'affiche (largeur de l'auto +1) cases � gauche du h�ros
						decor[j+2][(int)((LARGEUR-1)/2)-(tableauTempo[j].length+1)+k] = tableauTempo[j][k];						
					}
				}
			}
			// Si on � un paysan
			if(autoTempo.getRole() == 3) {
				joueur1.setAutomatePaysan(autoTempo);
				for(int j=0;j<tableauTempo.length;j++){
					for(int k=0;k<tableauTempo[j].length;k++){
						tableauTempo[j][k].setProprietaire(joueur1);
						// Le premier automate s'affiche � la moiti� de la map - la moiti� de la hauteur du auto, et tout � gauche +1
						decor[((int)((HAUTEUR-1)/2)-((int)tableauTempo.length/2))+j][k] = tableauTempo[j][k];						
					}
				}
			}
		}
		//JOUEUR 2
		for(int i=0;i<autoJ2.size();i++){
			autoTempo = autoJ2.get(i);
			tableauTempo = autoTempo.getDecor();
			// Si on � un guerrier
			if(autoTempo.getRole() == 1) {
				joueur2.setAutomateGuerrier(autoTempo);
				for(int j=0;j<tableauTempo.length;j++){
					for(int k=0;k<tableauTempo[j].length;k++){
						tableauTempo[j][k].setProprietaire(joueur2);
						// Le premier automate s'affiche 2 cases � droite du h�ros
						decor[HAUTEUR-3-j][(int)((LARGEUR-1)/2)-2-k] = tableauTempo[j][k];						
					}
				}
			}
			// Si on � un moine
			if(autoTempo.getRole() == 2) {
				joueur2.setAutomateMoine(autoTempo);
				for(int j=0;j<tableauTempo.length;j++){
					for(int k=0;k<tableauTempo[j].length;k++){
						tableauTempo[j][k].setProprietaire(joueur2);
						// Le premier automate s'affiche (largeur de l'auto +1) cases � gauche du h�ros
						decor[HAUTEUR-3-j][(int)((LARGEUR-1)/2)+(tableauTempo[j].length+1)-k] = tableauTempo[j][k];								
					}
				}
			}
			// Si on � un paysan
			if(autoTempo.getRole() == 3) {
				joueur2.setAutomatePaysan(autoTempo);
				for(int j=0;j<tableauTempo.length;j++){
					for(int k=0;k<tableauTempo[j].length;k++){
						tableauTempo[j][k].setProprietaire(joueur2);
						// Le premier automate s'affiche � la moiti� de la map - la moiti� de la hauteur du auto, et tout � gauche +1
						decor[((int)((HAUTEUR-1)/2)+((int)tableauTempo.length/2))-j][LARGEUR-k-1] = tableauTempo[j][k];								
					}
				}
			}
		}
	}

	private void creerDecor(String nomFichierAutomates1, String nomFichierAutomates2) {

		//On rempli le décror d'herbe et de ligne de champs, d'arbre, 
		for (int h = 0; h < HAUTEUR; h++) {
			for (int l = 0; l < LARGEUR; l++) {
				decor[h][l] = new Case(Type.HERBE, h, l);
			}
		}
		
		for (int h = 0; h < 7; h++) {
			for (int l = 0; l < 6; l++) {
				if (h != 6)
					decor[h][l].setTypeDeLaCase(Type.CHAMPS);
				else
					decor[h][l].setTypeDeLaCase(Type.ROCHER);
			}
		}
		
		decor[6][2].setTypeDeLaCase(Type.HERBE);
		decor[6][3].setTypeDeLaCase(Type.HERBE);
		for(int l = 4; l < 6; l++){
			decor[5][l].setTypeDeLaCase(Type.ROCHER);
		}
		decor[4][5].setTypeDeLaCase(Type.ROCHER);
		
		for (int h = HAUTEUR - 8; h < HAUTEUR - 1; h++) {
			for (int l = LARGEUR - 6; l < LARGEUR; l++) {
				if (h != HAUTEUR - 8)
					decor[h][l].setTypeDeLaCase(Type.CHAMPS);
				else
					decor[h][l].setTypeDeLaCase(Type.ROCHER);
			}
		}
		decor[HAUTEUR-8][LARGEUR-3].setTypeDeLaCase(Type.HERBE);
		decor[HAUTEUR-8][LARGEUR-4].setTypeDeLaCase(Type.HERBE);
		for(int l = LARGEUR-6; l < LARGEUR-4; l++){
			decor[HAUTEUR-7][l].setTypeDeLaCase(Type.ROCHER);
		}
		decor[HAUTEUR-6][LARGEUR-6].setTypeDeLaCase(Type.ROCHER);
		
		for(int x=0;x<LARGEUR;x++){
			decor[0][x].setTypeDeLaCase(Type.ROCHER);
			decor[HAUTEUR-1][x].setTypeDeLaCase(Type.ROCHER);
		}
		for (int h = HAUTEUR - 8; h < HAUTEUR - 1; h++) {
			for (int l = 0; l < 6; l++) {
				if (h != HAUTEUR - 8)
					decor[h][l].setTypeDeLaCase(Type.CHAMPS);
				else
					decor[h][l].setTypeDeLaCase(Type.ROCHER);
			}
		}
		for (int h = 1; h < 7; h++) {
			for (int l = LARGEUR-6; l < LARGEUR; l++) {
				if (h != 6)
					decor[h][l].setTypeDeLaCase(Type.CHAMPS);
				else
					decor[h][l].setTypeDeLaCase(Type.ROCHER);
			}
		}
		for(int k=0;k<3;k++){
			decor[(int)((HAUTEUR-1)/2)+2+k][5].setTypeDeLaCase(Type.ROCHER);
			decor[(int)((HAUTEUR-1)/2)-2-k][LARGEUR-6].setTypeDeLaCase(Type.ROCHER);
		}
		for(int k=6;k<LARGEUR-6;k++){
			decor[1][k].setTypeDeLaCase(Type.ARBRE);
			decor[HAUTEUR-2][k].setTypeDeLaCase(Type.ARBRE);
		}
		for(int l=2;l<HAUTEUR-2;l++){
			decor[l][6].setTypeDeLaCase(Type.ARBRE);
			decor[l][12].setTypeDeLaCase(Type.ARBRE);
			decor[l][14].setTypeDeLaCase(Type.ARBRE);
			decor[l][20].setTypeDeLaCase(Type.ARBRE);
			if( (l == 11) || (l == 12)){
				decor[l][12].setTypeDeLaCase(Type.HERBE);
				decor[l][14].setTypeDeLaCase(Type.HERBE);
			}
			if ( (l == 10) || (l == 13)){
				decor[l][12].setTypeDeLaCase(Type.HERBE);
				decor[l][14].setTypeDeLaCase(Type.HERBE);				
			}
		}
		for(int l=7;l<13;l++){
			decor[l][5].setTypeDeLaCase(Type.ARBRE);
			decor[l+3][21].setTypeDeLaCase(Type.ARBRE);
		}
		for(int k=7;k<20;k++){
			decor[11][k].setTypeDeLaCase(Type.ARBRE);
			decor[12][k].setTypeDeLaCase(Type.ARBRE);
			if(k>10 && k<16){
				decor[11][k].setTypeDeLaCase(Type.HERBE);
				decor[12][k].setTypeDeLaCase(Type.HERBE);
			}
		}
		
		decor[1][(int)((LARGEUR-1)/2)].setTypeDeLaCase(Type.POLYTECH);
		decor[1][(int)((LARGEUR-1)/2)].setVie(Case.CAPACITE_VIE_POLYTECH);
		decor[HAUTEUR-2][(int)((LARGEUR-1)/2)].setTypeDeLaCase(Type.POLYTECH);
		decor[HAUTEUR-2][(int)((LARGEUR-1)/2)].setVie(Case.CAPACITE_VIE_POLYTECH);
		
		joueur1.setBase(decor[1][(int)((LARGEUR-1)/2)]);
		joueur2.setBase(decor[HAUTEUR-2][(int)((LARGEUR-1)/2)]);
		
		decor[1][(int)((LARGEUR-1)/2)].setProprietaire(joueur1);
		decor[HAUTEUR-2][(int)((LARGEUR-1)/2)].setProprietaire(joueur2);
		
		
		
		JDOM jdom = new JDOM();
		inclureAutomates(jdom.xmlMain(nomFichierAutomates1), jdom.xmlMain(nomFichierAutomates2));

		
		// Tout le décor est crée, il faut maintenant chainer les cases entre
		// elles

		/*
		for (int h = 0; h < HAUTEUR; h++) {
			for (int l = 0; l < LARGEUR; l++) {
				// Chainage en haut
				if (h == 0)
					decor[h][l].setCaseEnHaut(decor[HAUTEUR - 1][l]);
				else
					decor[h][l].setCaseEnHaut(decor[h - 1][l]);

				// Chainage en bas
				if (h == HAUTEUR - 1)
					decor[h][l].setCaseEnBas(decor[0][l]);
				else
					decor[h][l].setCaseEnBas(decor[h + 1][l]);

				// Chainage a gauche
				if (l == 0)
					decor[h][l].setCaseAGauche(decor[h][LARGEUR - 1]);
				else
					decor[h][l].setCaseAGauche(decor[h][l - 1]);

				// Chainage a droite
				if (l == LARGEUR - 1)
					decor[h][l].setCaseADroite(decor[h][0]);
				else
					decor[h][l].setCaseADroite(decor[h][l + 1]);
			}
		}*/
		
		for (int h = 0; h < HAUTEUR; h++) {
			for (int l = 0; l < LARGEUR; l++) {
				
				
			decor[h][l].setCaseAGauche(decor[modulo((h-1),HAUTEUR)][modulo((l-((h+1)%2)),LARGEUR)]);
			decor[h][l].setCaseEnHaut(decor[modulo((h-1),HAUTEUR)][modulo((l+1-((h+1)%2)),LARGEUR)]);	
			decor[h][l].setCaseEnBas(decor[modulo((h+1),HAUTEUR)][modulo((l-((h+1)%2)),LARGEUR)]);	
			decor[h][l].setCaseADroite(decor[modulo((h+1),HAUTEUR)][modulo((l+1-((h+1)%2)),LARGEUR)]);	
			}
		}
		
		//Je modifie le décor pour qu'il soit un peu intéressant

	}

	public int modulo(int a, int b){
		return (a % b + b) % b;
	}
	
	public void jouerTour() {

		List<Personnage> tempo = new ArrayList<>();
		Personnage personnage;
	
		int ff =0;
		
		
		for (Iterator<Personnage> iterator = ordonnanceur().iterator(); iterator.hasNext();) {
		
			 personnage = iterator.next();
			 
			 ff++;

			// Un personnage ne peut évoluer que si il n'est pas mort, il sera
			// supprimé à la fin du tour
			// TODO il reste un cas ou ce fonctionnement n'est pas satisfaisant
			// : si un joueur est mort, on peut toujour l'attaquer
			if (personnage.estVivant()) {
				
				

				Action actionAfaire = Action.NE_RIEN_FAIRE;

				if (personnage instanceof Heros) {
					
					/*interfaceUtilisateur.afficherJoueur(joueur1, joueur2);
					interfaceUtilisateur.afficherMap(decor);
					interfaceUtilisateur.afficherPersonnages(personnages);*/
					
					

					//interfaceUtilisateur.demanderNouvelleAction(personnage.getProprietaire().getNom());
					//actionAfaire = personnage.getProprietaire().getNouvelleAction();
					
					if (ff%2 == 1){
						actionAfaire = bufferJ1;
						bufferJ1 = Action.NE_RIEN_FAIRE;
					}
					else{
						actionAfaire = bufferJ2;
						bufferJ2 = Action.NE_RIEN_FAIRE;
					}
					
					
				
					
					
				} else {
					actionAfaire =((Homme) personnage).getAction();
				}
					 

				// TODO Rajouter ici aussi quand on ajoute de nouvelles actions
				switch (actionAfaire) {
					case ALLER_A_DROITE:
						personnage.allerADroite();
						break;
					case ALLER_A_GAUCHE:
						personnage.allerAGauche();
						break;
					case ALLER_EN_HAUT:
						personnage.allerEnHaut();
						break;
					case ALLER_EN_BAS:
						personnage.allerEnBas();
						break;
					case ATTAQUER:
						personnage.attaquer();
						break;	
					case RECOLTER:
						personnage.recolter();
						break;
					case SOIGNER:
						personnage.soigner();
						break;
					case CONVERTIR:
						personnage.convertir();
						break;
					case SE_DEPLACER:
						personnage.seDeplacer();
						break;
					case ATTAQUER_BATIMENT:
						personnage.attaquerBatiment();
						break;
					case CREER_UNITE:
						//On ne peut ajouter les personnages qu'après la fin de l'itération : err java.util.ConcurrentModificationException
						Personnage p =((Heros) personnage).creerUnite();
						if(p!=null)
							tempo.add(p);
						break;
						
					case REPARER:
						((Heros) personnage).reparer();
						break;
					case NE_RIEN_FAIRE:
						
					default:

						break;
				}

			}
			
			
		}

		afficherMap(decor);
		//afficherPersonnages(personnages);
		
		// Je supprime les morts de la liste
		for (int i = 0; i < personnages.size(); i++) {
			if (!personnages.get(i).estVivant()) {
				personnages.get(i).getProprietaire().retirerUnPersonnage();
				personnages.remove(i);
			}

		}
		
		//Je rajoute les nouveaux personnages
		for (Iterator<Personnage> iterator = tempo.iterator(); iterator.hasNext();) {
			personnages.add((Personnage) iterator.next());
			
		}
	}

	private List<Personnage> ordonnanceur() {
		//Ordonnanceur simpliste, on peut l'améliorer ici
		return personnages;
	}


	public boolean estTermine() {
		if(joueur1.getBase().getTypeDeLaCase()==Type.RUINES && joueur2.getBase().getTypeDeLaCase()==Type.RUINES){
			interfaceUtilisateur.afficherJoueur(joueur1, joueur2);
			interfaceUtilisateur.afficherMap(decor);
			interfaceUtilisateur.afficherPersonnages(personnages);
			interfaceUtilisateur.afficherFinDePartie(joueur1, joueur2, EGALITE);
			return true;
		}
		if(joueur2.getBase().getTypeDeLaCase()==Type.RUINES ){
			interfaceUtilisateur.afficherJoueur(joueur1, joueur2);
			interfaceUtilisateur.afficherMap(decor);
			interfaceUtilisateur.afficherPersonnages(personnages);
			interfaceUtilisateur.afficherFinDePartie(joueur1, joueur2, JOUEUR_1_GAGNE);
			return true;
		}
		if(joueur1.getBase().getTypeDeLaCase()==Type.RUINES ){
			interfaceUtilisateur.afficherJoueur(joueur1, joueur2);
			interfaceUtilisateur.afficherMap(decor);
			interfaceUtilisateur.afficherPersonnages(personnages);
			interfaceUtilisateur.afficherFinDePartie(joueur1, joueur2, JOUEUR_2_GAGNE);
			return true;
		}
	
		
		
		return false;
	}
	
	
	/*
public Action getNouvelleAction() {
		
		String derniere_action = this.derniere_action;
		
		switch (derniere_action) {
		
			case "Z":
				this.derniere_action = "null";
				return Action.ALLER_EN_HAUT;
			
			case "Q":
				this.derniere_action = "null";
				return Action.ALLER_A_GAUCHE;

			case "S":
				this.derniere_action = "null";
				return Action.ALLER_EN_BAS;

			case "D":
				this.derniere_action = "null";
				return Action.ALLER_A_DROITE;
				
			case "8":
				this.derniere_action = "null";
				return Action.ALLER_EN_HAUT;
			
			case "4":
				this.derniere_action = "null";
				return Action.ALLER_A_GAUCHE;

			case "5":
				this.derniere_action = "null";
				return Action.ALLER_EN_BAS;

			case "6":
				this.derniere_action = "null";
				return Action.ALLER_A_DROITE;
				
			case '0':
				return Action.NE_RIEN_FAIRE;
				
			case "1":
				return Action.ATTAQUER;

			case "2":
				return Action.RECOLTER;
				
			case "3":
				return Action.SOIGNER;
				
			case "4":
				return Action.CONVERTIR;
				
			case "5":
				return Action.CREER_UNITE;
				
			case "6":
				return Action.ATTAQUER_BATIMENT;
				
				
			default:
				System.out.println("Commande de jeu :");
				System.out.println("z,q,s,d -> haut, gauche, bas, droite");
				System.out.println("0 -> ne rien faire");
				System.out.println("1 -> attaquer");
				System.out.println("2 -> r�colter");
				System.out.println("3 -> soigner");
				System.out.println("4 -> convertir");
				System.out.println("5 -> cr�er unit�");
				System.out.println("6 -> attaquer batiment");
				System.out.println("? ou n'importe quelles autres touches -> afficher ce manuel puis saisir une action correcte.");
				
				return Action.NE_RIEN_FAIRE;
				
			}
	}
	
	*/
public void afficherMap(Case[][] decor ) {
	this.getChildren().clear();
	for (int j = 0; j < HAUTEUR; j++) {
		for (int i = 0; i < LARGEUR; i++) {
			DecorGraphique tile = new DecorGraphique(i*60+(j%2)*30,j*23, decor[j][i], this);
			JaugeVie vie = new JaugeVie(i*60+(j%2)*30,j*23,decor[j][i].getVie());
			this.getChildren().add(vie);
			this.getChildren().add(tile);
		}
	}
	for (int j = 0; j < HAUTEUR; j++) {
		for (int i = 0; i < LARGEUR; i++) {
			if (decor[j][i].getPersonnagePresent() != null){
			PersonnageGraphique perso = new PersonnageGraphique(i*60+(j%2)*30,j*23,decor[j][i].getPersonnagePresent(),this);
			JaugeVie vie = new JaugeVie(i*60+(j%2)*30,j*23,decor[j][i].getPersonnagePresent().getVie());
			this.getChildren().add(vie);
			this.getChildren().add(perso);
			
			}
		}
		
	}
	
	AffichageRessources affichageRessources = new AffichageRessources(10, 400, joueur1, joueur2);
	affichageRessources.getTransforms().add(new Scale(1.62,1.5));
	this.getChildren().add(affichageRessources);

} 


public void afficherMap2(Case[][] decor ){
	this.getChildren().clear();
	int i = 0;
	int j = 0;
	int x = 400;
	int nb_element_max = 1;
	int nb_element_courant = 0;
	int numero_ligne = 0;
	int k;
	for (k = 0; k < (decor.length)*(decor.length); k++) {
		if (nb_element_courant < nb_element_max){
			x = x+60;
			nb_element_courant ++;
		}
		else
		{
			if (numero_ligne+1 <= (decor.length)-1)
			{
			x = 400 - (numero_ligne-1)*30;
			nb_element_max ++;
			}
			else
			{
				x = 400 - 30*((decor.length-2)*2-numero_ligne-1);
				nb_element_max --;
			}
			
			numero_ligne ++;
			nb_element_courant = 1;
		}

		DecorGraphique tile = new DecorGraphique(x, numero_ligne*23,decor[j][i], this);
		this.getChildren().add(tile);
		
		if (decor[j][i].getPersonnagePresent() != null){
			PersonnageGraphique perso = new PersonnageGraphique(x, numero_ligne*23,decor[j][i].getPersonnagePresent(), this);
			this.getChildren().add(perso);
		}
		
		if ((j==0) && (i != decor.length - 1)){
			j = i + 1;
			i = 0;
		}
		else{
			if (i==decor.length - 1)
			{
				i =  j + 1;
				j = decor.length - 1;
			}
			else
			{
				i++;
				j--;
			}
		}
		
	}
	
}


}
