package automate;

import jeu.Action;
import jeu.Condition;

public class Transition {
	
	public static final int RESSOURCE = 0;
	
	public static final int ENNEMI_ADJACENT = 1;
	
	public static final int RIEN = 2;
	
	private int depart;
	
	private int arrivee;
	
	private int condition;
	
	private int priorite;
	
	private int action;
	
	public Transition(int depart,int arrivee, int condition, int priorite, int action){
		this.depart = depart;
		this.arrivee = arrivee;
		this.condition=condition;
		this.priorite=priorite;
		this.action=action;
	}
	
	public int getDepart(){
		return this.depart;
	}
	
	public int getArrivee(){
		return this.arrivee;
	}

	public int getCodeCondition() {
		return condition;
	}
	
	public Condition getCondition() {
		return Condition.codeToCondition(condition);
	}

	public int getPriorite() {
		return priorite;
	}

	public int getCodeAction() {
		return action;
	}

	
	public Action getAction() {
		return Action.codeToAction(action);
	}

}
