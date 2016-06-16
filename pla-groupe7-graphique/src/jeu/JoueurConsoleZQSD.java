package jeu;


import java.util.Scanner;


public class JoueurConsoleZQSD extends JoueurConsole {
	
	private static Scanner sc;

	public JoueurConsoleZQSD(String nom, String nomFichierAutomate) {
		if(sc==null){
			sc = new Scanner(System.in);
		}
		this.nom = nom;
		this.bois=200;
		this.nourriture=200;
		this.nombrePersonnage=1;
		this.nomFichierAutomate=nomFichierAutomate;
		this.partie=null;
	}

	@Override
	// Ne devra pas �tre modulable par l'automate?
	public Action getNouvelleAction() {
		
		char codeAction = '0';
		try{
			codeAction = sc.nextLine().charAt(0);
		} catch (Exception e){
			
		}
		
		
		switch (codeAction) {
			
			
			case 'Z':
			case 'z':
				return Action.ALLER_EN_HAUT;
			
			case 'Q':
			case 'q':
				return Action.ALLER_A_GAUCHE;

			case 'S':
			case 's':
				return Action.ALLER_EN_BAS;

			case 'D':
			case 'd':
				return Action.ALLER_A_DROITE;
				
			case '0':
				return Action.NE_RIEN_FAIRE;
				
			case '1':
				return Action.ATTAQUER;

			case '2':
				return Action.RECOLTER;
				
			case '3':
				return Action.SOIGNER;
				
			case '4':
				return Action.CONVERTIR;
				
			case '5':
				return Action.CREER_UNITE;
				
				
			case '6':
				return Action.ATTAQUER_BATIMENT;
				
				
			case '7':
				return Action.REPARER;
				
			case ' ':
				return Action.NE_RIEN_FAIRE;
			default:
				System.out.println("Commande de jeu :");
				System.out.println("z,q,s,d -> haut, gauche, bas, droite");
				System.out.println("0 -> ne rien faire");
				System.out.println("1 -> attaquer");
				System.out.println("2 -> récolter");
				System.out.println("3 -> soigner");
				System.out.println("4 -> convertir");
				System.out.println("5 -> créer unité");
				System.out.println("6 -> attaquer batiment");
				System.out.println("7 -> Réparer, replanter");
				System.out.println("? ou n'importe quelles autres touches -> afficher ce manuel puis saisir une action correcte.");
				return getNouvelleAction();
			}
	}

}
