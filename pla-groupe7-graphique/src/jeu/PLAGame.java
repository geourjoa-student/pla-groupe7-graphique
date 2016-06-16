package jeu;
public class PLAGame {
	
	public static void main(String[] args) {
		
		/*JDOM j = new JDOM();
		j.xmlMain(); */
		Partie p = new Partie(new InterfaceConsole(), new JoueurConsoleZQSD("Toto", "automates1.xml"), new JoueurConsoleZQSD("Titi", "automates2.xml"));
	
		while(!p.estTermine()){
			p.jouerTour();
		}
		
		
		
	} 

}
