(* LES TYPES *)
    
type cellule =
  | C (* case *)| N (* nord *) | S | E | O

type action =
  | Ne_rien_faire
  | Deplacer of cellule
  | Deplacer_Aleatoire
  | Attaquer
  | Recolter
  | Soigner
  | Convertir 
  | Creer_unite
  | Attaquer_batiment
  | Restaurer_case

type condition =
  | Aucune_condition
  | Ennemi_adjacent
  | Ennemi_adjacent_menacant
  | Ressource_adjacente
  | Ressource_sous_case
  | Batiment_allie_adjacent
  | Allie_non_full_vie
  | Ruine_ennemie_sous_case
  | Batiment_ennemi_adjacent

type role =
  |Guerrier
  |Moine
  |Paysan
  |Heros

type joueur = JOUEUR1 | JOUEUR2

type etat = int
type priorite = int
type transition = etat * condition * priorite * action * etat
type automate = joueur * role * transition list * etat (*etat initial*)


(* TRADUCTION DES CONDITIONS ET ACTION EN ENTIERS *)

let joueur_to_int joueur = match joueur with
  | JOUEUR1 -> 1
  | JOUEUR2 -> 2
;;

let role_to_int role = match role with
  |Guerrier -> 1
  |Moine -> 2
  |Paysan -> 3
  |Heros -> 4
;;

let condition_to_int condition = match condition with
  | Aucune_condition -> 0
  | Ennemi_adjacent -> 1
  | Ennemi_adjacent_menacant -> 2
  | Ressource_adjacente -> 3
  | Ressource_sous_case -> 4
  | Batiment_allie_adjacent -> 5
  | Allie_non_full_vie -> 6
  | Ruine_ennemie_sous_case -> 7
  | Batiment_ennemi_adjacent -> 8
;;


let action_to_int action = match action with
  | Ne_rien_faire -> 0
  | Deplacer(O) -> 1
  | Deplacer(E) -> 2
  | Deplacer(N) -> 3
  | Deplacer(S) -> 4
  | Deplacer_Aleatoire -> 5
  | Attaquer -> 6
  | Recolter -> 7
  | Soigner -> 9
  | Convertir -> 10
  | Creer_unite -> 11
  | Attaquer_batiment -> 12
  | Restaurer_case -> 13
  | _ -> -1
;;

let transition_to_ints transition = match transition with
  |(depart, condition, priorite, action, arrivee) -> (depart, condition_to_int condition, priorite, action_to_int action, arrivee);; 

let rec transitions_to_ints transitions = match transitions with
  |t::q -> (transition_to_ints t) :: (transitions_to_ints q)
  |[] -> [];;

let rec valider_action_paysan action = match action with
  |Deplacer(cel) -> Deplacer(cel)
  |Attaquer -> Attaquer (*les paysans peuvent attaquer de base*)
  |Recolter -> Recolter
  |_ -> Ne_rien_faire;; (* si l'action n'est pas valide alors on la remplace par attendre*)

let rec valider_action_guerrier action = match action with
  |Deplacer(cel) -> Deplacer(cel)
  |Attaquer -> Attaquer
  |_ -> Ne_rien_faire;;

let rec valider_action_moine action = match action with
  |Deplacer(cel) -> Deplacer(cel)
  |Convertir -> Convertir
  |Soigner -> Soigner
  |_ -> Ne_rien_faire;;

let valider_action action role = match role with
  |Guerrier -> valider_action_guerrier action
  |Paysan -> valider_action_paysan action
  |Moine -> valider_action_moine action
  |Heros -> action;;


let rec valider_transitions transitions role = match transitions with
  |(depart, condition, priorite, action, arrivee) :: q -> (depart, condition, priorite, valider_action action role, arrivee) :: (valider_transitions q role) 
  |[] -> [];;

let valider_automate automate = match automate with
  |(joueur, role, transitions, etat) ->  (joueur, role, valider_transitions transitions role, etat);;



(* EXEMPLEs D'AUTOMATE *)

let paysan_joueur1 = 
	(JOUEUR1, (* le propriétaire de l'automate*)
	Paysan, (* le rôle de l'automate *)
	[(0, Ressource_sous_case, 2, Recolter, 0); (* la liste des transitions *)
	(0, Aucune_condition, 1, Deplacer_Aleatoire, 0);],
    0) (*l'état final*);;

let guerrier_joueur1 = 
	(JOUEUR1,
	Guerrier,
	[(0, Ennemi_adjacent, 4, Attaquer, 0);
	(0, Batiment_ennemi_adjacent, 3, Attaquer_batiment, 0); 
	(0, Ruine_ennemie_sous_case, 2, Ne_rien_faire, 0);
	(0, Aucune_condition, 1, Deplacer_Aleatoire, 0);],
    0);;

let moine_joueur1 = 
	(JOUEUR1,
	Moine,
	[(0, Ennemi_adjacent, 3, Convertir, 1);
	(1, Allie_non_full_vie, 3, Soigner, 0);
	(1, Ennemi_adjacent, 2, Convertir, 0); 
	(1, Aucune_condition, 1,  Deplacer_Aleatoire, 0);
	(0, Allie_non_full_vie, 2, Soigner, 0);
	(0, Aucune_condition, 1, Deplacer_Aleatoire, 0);],
    0);;

let paysan_joueur2 = 
	(JOUEUR2, (* le propriétaire de l'automate*)
	Paysan, (* le rôle de l'automate *)
	[(0, Ressource_sous_case, 2, Recolter, 0); (* la liste des transitions *)
	(0, Aucune_condition, 1, Deplacer_Aleatoire, 0);],
    0) (*l'état final*);;


let guerrier_joueur2 = 
	(JOUEUR2,
	Guerrier,
	[(0, Ennemi_adjacent, 4, Attaquer, 0);
	(0, Batiment_ennemi_adjacent, 3, Attaquer_batiment, 0); 
	(0, Ruine_ennemie_sous_case, 2, Ne_rien_faire, 0);
	(0, Aucune_condition, 1, Deplacer_Aleatoire, 0);],
    0);;

let moine_joueur2 = 
	(JOUEUR1,
	Moine,
	[(0, Ennemi_adjacent, 3, Convertir, 1);
	(1, Allie_non_full_vie, 3, Soigner, 0);
	(1, Ennemi_adjacent, 2, Convertir, 0); 
	(1, Aucune_condition, 1,  Deplacer_Aleatoire, 0);
	(0, Allie_non_full_vie, 2, Soigner, 0);
	(0, Aucune_condition, 1, Deplacer_Aleatoire, 0);],
    0);;

(* Traduction vers un fichier xml *)

let rec transitions_to_xml liste_transitions fichier = match liste_transitions with
|[]-> ()
|(depart, condition, priorite, action, arrivee)::q -> 
	output_string fichier "<transition>";
	output_string fichier "<depart>";
	output_string fichier (string_of_int depart);
	output_string fichier "</depart>";
	output_string fichier "<condition>";
	output_string fichier (string_of_int condition);
	output_string fichier "</condition>";
	output_string fichier "<priorite>";
	output_string fichier (string_of_int priorite);
	output_string fichier "</priorite>";
	output_string fichier "<action>";
	output_string fichier (string_of_int action);
	output_string fichier "</action>";
	output_string fichier "<arrivee>";
	output_string fichier (string_of_int arrivee);
	output_string fichier "</arrivee>";
	output_string fichier "</transition>";
	transitions_to_xml q fichier;;


let automate_to_xml automate fichier = match automate with
  |(joueur, role, transitions, etat) -> 
  	output_string fichier "<automate role=\"";
  	output_string fichier (string_of_int (role_to_int role));
  	output_string fichier "\">";
  	(transitions_to_xml (transitions_to_ints transitions) fichier);
  	output_string fichier "<initial>";
  	output_string fichier (string_of_int etat);
  	output_string fichier "</initial>";
  	output_string fichier "</automate>";;


let fic1 = open_out "automates1.xml";;
output_string fic1 "<partie>";
automate_to_xml guerrier_joueur1 fic1;
automate_to_xml moine_joueur1 fic1;
automate_to_xml paysan_joueur1 fic1;
output_string fic1 "</partie>";
close_out fic1;;

let fic2 = open_out "automates2.xml";;
output_string fic2 "<partie>";
automate_to_xml guerrier_joueur2 fic2;
automate_to_xml moine_joueur2 fic2;
automate_to_xml paysan_joueur2 fic2;
output_string fic2 "</partie>";
close_out fic2;;
  
