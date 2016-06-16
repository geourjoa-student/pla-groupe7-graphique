package jeu;
import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import automate.*;
public class JDOM
{
   static org.jdom2.Document document;
   static Element racine;

   private static int[] convertIntegers(List<Integer> integers)
   {
       int[] ret = new int[integers.size()];
       Iterator<Integer> iterator = integers.iterator();
       for (int i = 0; i < ret.length; i++)
       {
           ret[i] = iterator.next().intValue();
       }
       return ret;
   }
   
   static ArrayList<Automate> afficheALL()
   {
	  ArrayList<Automate> listeAuto = new ArrayList<Automate>();
      List listAutomates = racine.getChildren("automate");
      Iterator i = listAutomates.iterator();
      Element courant = (Element)i.next();
      List listTransitions;
      Iterator j;
      int courantDepart,courantCondition,courantPriorite,courantAction,courantArrivee,courantInitial;
      Transition courantTransition;
      Automate courantAutomate;
      int role;
      while(courant != null)
      {
    	  role = Integer.parseInt(courant.getAttributeValue("role"));
    	  // Automate courantAuto = new Automate();
    	  ArrayList<Integer> etats = new ArrayList<Integer>();
    	  ArrayList<Transition> allTransitions = new ArrayList<Transition>();
    	  listTransitions = courant.getChildren("transition");
    	  courantInitial = Integer.parseInt(courant.getChild("initial").getText());
    	  j = listTransitions.iterator();
    	  courant = (Element)j.next();
    	  while(courant != null)
    	  {
    		  courantDepart = Integer.parseInt(courant.getChild("depart").getText());
    		  courantCondition = Integer.parseInt(courant.getChild("condition").getText());
    		  courantPriorite = Integer.parseInt(courant.getChild("priorite").getText());
    		  courantAction = Integer.parseInt(courant.getChild("action").getText());
    		  courantArrivee = Integer.parseInt(courant.getChild("arrivee").getText());
    		  courantTransition = new Transition(courantDepart,courantArrivee,courantCondition,courantPriorite,courantAction);
    		  if(!(etats.contains(courantDepart))){
    			  etats.add(courantDepart);
    		  }
    		  if(!(etats.contains(courantArrivee))){
    			  etats.add(courantArrivee);
    		  }
    		  allTransitions.add(courantTransition);
    		  if(j.hasNext()) {
    			  courant = (Element)j.next();
    		  }
    		  else {
    			  courant = null;
    		  }
    	  }
    	  int[] etatsArray = new int[etats.size()];
    	  etatsArray = convertIntegers(etats);
    	  courantAutomate = new Automate(courantInitial,etatsArray,allTransitions,role);
    	  listeAuto.add(courantAutomate);
		  if(i.hasNext()) {
			  courant = (Element)i.next();
		  }
		  else {
			  courant = null;
		  }
      }
      
      return listeAuto;
   }

   public ArrayList<Automate> xmlMain(String nomFichierJoueur)
   {
      //On crée une instance de SAXBuilder
      SAXBuilder sxb = new SAXBuilder();
      try
      {
         //On crée un nouveau document JDOM avec en argument le fichier XML
         //Le parsing est terminé ;)
         document = sxb.build(new File(nomFichierJoueur));
      }
      catch(Exception e){}

      //On initialise un nouvel élément racine avec l'élément racine du document.
      racine = document.getRootElement();
      //Méthode définie dans la partie 3.2. de cet article
      return afficheALL();
   }
}