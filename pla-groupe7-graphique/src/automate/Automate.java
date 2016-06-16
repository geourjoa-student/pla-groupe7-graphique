package automate;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import jeu.*;

public class Automate {

	private static final int PAS_DE_TRANSIITON = -1;

	private static final int PAS_DE_PRIORITE = -1;
	
	private  int[] etats;
	
	private int role;
	
	private int[][] transitions;
	
	private int[][] priorite;
	
	private Case[][] actions;
	
	private int courant;
	
	private int initial;

	public Automate(int initial, int[] etats, List<Transition> transitionsAutomate, int role) {

		this.initial=initial;
		courant=initial;
		this.etats = etats;
		this.role = role;
		priorite=new int[Condition.values().length][etats.length];
		transitions=new int[Condition.values().length][etats.length];
		actions=new Case[Condition.values().length][etats.length];
		
		//Pour chaque condition
		for (int h = 0; h < Condition.values().length; h++) {
			//Pour chaque état de l'automate
			for (int l = 0; l < etats.length; l++) {
				int i=0; 
				while(i<transitionsAutomate.size() && (transitionsAutomate.get(i).getDepart()!=etats[l] || transitionsAutomate.get(i).getCondition()!=Condition.values()[h])){
					i++;
				}
					
				//Il n'y a pas de transition pour cet état
				if(i==transitionsAutomate.size()){
					//On remplit avec une boucle sur soi meme et pas d'actions
					actions[h][l]=new Case(Type.HERBE, h, l);
					transitions[h][l]= PAS_DE_TRANSIITON; 
					priorite[h][l]=PAS_DE_PRIORITE;
				} else {
					transitions[h][l]=transitionsAutomate.get(i).getArrivee();
					actions[h][l]=new Case(transitionsAutomate.get(i).getAction().getTypeCaseAssociee(), h, l);
					priorite[h][l]=transitionsAutomate.get(i).getPriorite();
				}
			}
		}
		
		
		
	}
	
	
	public Action utiliserAutomate(List<Condition> conditions){
		
		int prioriteCourante = PAS_DE_PRIORITE;
		int conditionRetenue=0; //Initialisation inutile mais sinon il y a un warning
		
		for (Iterator<Condition> iterator = conditions.iterator(); iterator.hasNext();) {
			Condition c = (Condition) iterator.next();
			if(priorite[c.getCodeCondition()][courant]>=PAS_DE_PRIORITE){
				if(priorite[c.getCodeCondition()][courant]>=PAS_DE_PRIORITE){
				}
				prioriteCourante=priorite[c.getCodeCondition()][courant];
				conditionRetenue=c.getCodeCondition();
			} 
		}
		
		
		//Pas de transition trouvée
		if(prioriteCourante==PAS_DE_PRIORITE){
			return Action.NE_RIEN_FAIRE;
		}else {
			Case caseDeLAction = actions[conditionRetenue][courant];
			courant=transitions[conditionRetenue][courant];
			return Action.typeToAction(caseDeLAction.getTypeDeLaCase());
		}
		
		
		
		
	}
	
	public int getRole(){
		return role;
	}
	
	public Case[][] getDecor(){
		return actions;
	}

	
	
	
	private Automate(int initial, int[] etats, int role, int[][] transitions, int[][] priorite, Case[][] actions) {
		this.etats = etats;
		this.role = role;
		this.transitions = transitions;
		this.priorite = priorite;
		this.actions = actions;
		this.courant = initial;
		this.initial = initial;
	}


	public Automate clone(){
		return new Automate(initial,etats, role, transitions, priorite, actions);
	
	}
	
}
