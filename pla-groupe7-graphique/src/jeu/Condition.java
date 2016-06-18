package jeu;

public enum Condition {

	AUCUNE_CONDITION (0), 
	ENNEMI_ADJACENT(1), 
	ALLIE_ADJACENT(2), 
	RESSOURCE_ADJACENTE(3), 
	RESSOURCE_SOUS_CASE(4), 
	BATIMENT_ALLIE_ADJACENT(5), 
	ALLIE_NON_FULL_VIE(6),
	RUINE_ENNEMI_SOUS_CASE(7),
	BATIMENT_ENNEMI_ADJACENT(8);
	
	
	private int codeCondition;
	
	private Condition (int codeCondition) {
		this.codeCondition=codeCondition;
	}
	
	public int getCodeCondition() {
		return codeCondition;
	}
	
	public static Condition codeToCondition(int code){
		Condition conditions[] = Condition.values();
		for (int i = 0; i < conditions.length; i++) {
			if(conditions[i].getCodeCondition() == code){
				return conditions[i];
			}	
		}
		
		return null;
	}
}
