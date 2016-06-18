package jeu;

import java.util.ArrayList;
import java.util.List;

import automate.Automate;

public abstract class Homme extends Personnage {

	public final static int CODE_GUERRIER = 1;
	public final static int CODE_MOINE = 2;
	public final static int CODE_PAYSAN = 3;

	protected Automate comportement;

	public Homme(Joueur joueur, Case caseSousLePersonnage, Automate comportement) {
		this.caseSousLePersonnage = caseSousLePersonnage;
		caseSousLePersonnage.placerPersonnage(this);
		proprietaire = joueur;
		this.comportement = comportement;
	}

	public Action getAction() {
		List<Condition> conditions = new ArrayList<>();

		conditions.add(Condition.AUCUNE_CONDITION);

		if (caseSousLePersonnage.estRecoltable() && !caseSousLePersonnage.caseAllie(proprietaire))
			conditions.add(Condition.RESSOURCE_SOUS_CASE);

		if (caseSousLePersonnage.getTypeDeLaCase()==Type.RUINES && !caseSousLePersonnage.caseAllie(proprietaire))
			conditions.add(Condition.RUINE_ENNEMI_SOUS_CASE);
		
		if (caseSousLePersonnage.getCaseEnHaut().estBatiment() && caseSousLePersonnage.getCaseEnHaut().caseAllie(proprietaire)) {
			conditions.add(Condition.BATIMENT_ALLIE_ADJACENT);
		}

		if (caseSousLePersonnage.getCaseEnBas().estBatiment() && caseSousLePersonnage.getCaseEnBas().caseAllie(proprietaire)) {
			conditions.add(Condition.BATIMENT_ALLIE_ADJACENT);
		}

		if (caseSousLePersonnage.getCaseADroite().estBatiment() && caseSousLePersonnage.getCaseADroite().caseAllie(proprietaire)) {
			conditions.add(Condition.BATIMENT_ALLIE_ADJACENT);
		}

		if (caseSousLePersonnage.getCaseAGauche().estBatiment() && caseSousLePersonnage.getCaseAGauche().caseAllie(proprietaire)) {
			conditions.add(Condition.BATIMENT_ALLIE_ADJACENT);
		}

		if (caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent() != null) {
			if (!allie(caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent()))
				conditions.add(Condition.ENNEMI_ADJACENT);
			else {
				conditions.add(Condition.ALLIE_ADJACENT);
				if(!caseSousLePersonnage.getCaseEnHaut().getPersonnagePresent().estEnPleineSante()){
					conditions.add(Condition.ALLIE_NON_FULL_VIE);
				}
			}

		}

		if (caseSousLePersonnage.getCaseEnBas().getPersonnagePresent() != null) {
			if (!allie(caseSousLePersonnage.getCaseEnBas().getPersonnagePresent()))
				conditions.add(Condition.ENNEMI_ADJACENT);
			else {
				conditions.add(Condition.ALLIE_ADJACENT);
				if(!caseSousLePersonnage.getCaseEnBas().getPersonnagePresent().estEnPleineSante()){
					conditions.add(Condition.ALLIE_NON_FULL_VIE);
				}
			}
		}

		if (caseSousLePersonnage.getCaseAGauche().getPersonnagePresent() != null) {
			if (!allie(caseSousLePersonnage.getCaseAGauche().getPersonnagePresent()))
				conditions.add(Condition.ENNEMI_ADJACENT);
			else {
				conditions.add(Condition.ALLIE_ADJACENT);
				if(!caseSousLePersonnage.getCaseAGauche().getPersonnagePresent().estEnPleineSante()){
					conditions.add(Condition.ALLIE_NON_FULL_VIE);
				}
			}

		}

		if (caseSousLePersonnage.getCaseADroite().getPersonnagePresent() != null) {
			if (!allie(caseSousLePersonnage.getCaseADroite().getPersonnagePresent()))
				conditions.add(Condition.ENNEMI_ADJACENT);
			else {
				conditions.add(Condition.ALLIE_ADJACENT);
				if(!caseSousLePersonnage.getCaseADroite().getPersonnagePresent().estEnPleineSante()){
					conditions.add(Condition.ALLIE_NON_FULL_VIE);
				}
			}
		}

		if (caseSousLePersonnage.getCaseEnHaut().estRecoltable() || caseSousLePersonnage.getCaseADroite().estRecoltable() || caseSousLePersonnage.getCaseAGauche().estRecoltable()
				|| caseSousLePersonnage.getCaseEnBas().estRecoltable()) {
			conditions.add(Condition.RESSOURCE_ADJACENTE);
		}

		return comportement.utiliserAutomate(conditions);

	}

}
