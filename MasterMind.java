package MasterMind;

import java.util.Scanner;

public class Projet_MasterMind {

	static String [] TAB_REF_COLORS = {
			"rouge",
			"jaune",
			"vert",
			"bleu",
			"orange",
			"blanc",
			"violet",
			"fuschia"
	};

static String [] generateRandomCombination() {
	String [] combination = new String[4];

	for (int i=0;i<4;i++) {
		int index = 0;
		String randomColor = "";
		boolean alreadyIn = true;

		do 
		{
			index = (int)(7*Math.random());
			randomColor = TAB_REF_COLORS[index];
			if(!isIn(combination,randomColor)) alreadyIn = false;
		} while (alreadyIn);

		combination[i] = randomColor;
	}

	return combination;
}

static boolean isIn(String [] iTab, String iStringToFind)
{
	boolean output = false;
	int size = iTab.length;

	for(int i=0;i<size;i++)
	{
		if(iStringToFind.equals(iTab[i]))
		{
			output=true;
			break;
		}
	}

	return output;
}

public static void jeu() {
	
System.out.println("Bienvenue dans mon Mastermind !");
System.out.println();
System.out.println("[RÈGLES DU JEU]");
System.out.println("-> Vous disposez de 12 essais pour trouver la bonne combinaison. ");
System.out.println("-> La combinaison comporte 4 couleurs. ");
System.out.println("-> Les couleurs seront affichées avant chaque début de nouvel essai.");
System.out.println();
System.out.println();
 
boolean partieGagnee=false;
int count=0, i=0, j=0, couleursBienPlacees=0, couleursMalPlacees=0;
String [] combinaisonSecrete = generateRandomCombination();
String []combinaisonProposee= new String[4];
int size=combinaisonSecrete.length;
Scanner sc=new Scanner(System.in);
	
	do {
		
		do {
			System.out.println("Essai n°"+(count+1)+"/12 ");
			System.out.println("Voici les huit couleurs possibles :");
		 
			for (i=0; i<8;i++) {
				System.out.println(TAB_REF_COLORS[i]);
				}
			
			System.out.println();
			
			// Création de la combinaison choisie par le joueur ;
			
			for(i=0;i<size;i++) {
				
				System.out.println("Veuillez saisir la couleur n° "+(i+1)+" : ");
				combinaisonProposee[i]=sc.nextLine().toLowerCase();
				if (!(combinaisonProposee[i].equals(TAB_REF_COLORS[0]) || combinaisonProposee[i].equals(TAB_REF_COLORS[1]) || combinaisonProposee[i].equals(TAB_REF_COLORS[2]) || combinaisonProposee[i].equals(TAB_REF_COLORS[3]) || combinaisonProposee[i].equals(TAB_REF_COLORS[4]) || combinaisonProposee[i].equals(TAB_REF_COLORS[5]) || combinaisonProposee[i].equals(TAB_REF_COLORS[6]) || combinaisonProposee[i].equals(TAB_REF_COLORS[7]))) 
				{
				System.out.println("Veuillez saisir une couleur valide.");
				i--;
				}
				
				else continue;
				
			}
		 
			// Affichage de la combinaison choisie par le joueur ;
			
			System.out.print("Voici la combinaison que vous proposez -> ");
			
			for (i=0;i<4;i++) {
				System.out.print(combinaisonProposee[i]+ " ; ");
				}
			
			// Recherche des couleurs bien placées/mal placées ;
			
			for (i=0;i<4;i++) {
				if(combinaisonSecrete[i].equals(combinaisonProposee[i]))
				
				{
					combinaisonProposee[i] = "";
					couleursBienPlacees++;
                }	
			}
			
			for (j=0;j<4;j++) {
				
				for (i=0;i<4;i++) {
					
					if (combinaisonSecrete[j].equals(combinaisonProposee[i]) && !combinaisonSecrete[j].equals(""))
					
					{
						couleursMalPlacees++;
						combinaisonProposee[i] = "";
						break;
						}
					}
				}
			
			if(couleursBienPlacees==4) {
				partieGagnee=true;
				System.out.println("Vous avez trouvé la bonne combinaison, bravo !");  
			    return;
			    }
			
			else {
				System.out.println();
				System.out.println("Vous avez trouvé " + Integer.toString(couleursBienPlacees) + " couleur(s) exacte(s) et bien placée(s).");
                System.out.println("Vous avez trouvé " + Integer.toString(couleursMalPlacees) + " couleur(s) exacte(s) mais mal placée(s).");
                }
			
			count++;
			couleursMalPlacees=0;
			couleursBienPlacees=0;
			System.out.println();
			System.out.println();
			
		} while(partieGagnee=false);
		
	} while(!partieGagnee && count<12);
	
	if (partieGagnee) {
		System.out.println("Vous avez trouvé la bonne combinaison, bravo !");
		}
	
	else {
		System.out.println("Vous n'avez pas réussi à trouver la bonne combinaison !");
		System.out.println("La bonne combinaison était :");
		
		for(i=0;i<4;i++)
			System.out.println(combinaisonSecrete[i]);
	}	
	
	sc.close();
	
}

public static void main(String[] args) {
	
	Scanner sc=new Scanner(System.in);
	boolean nouvellePartie=true;
	String reponse="";
	do {
		jeu();
		System.out.print("Souhaitez-vous refaire une partie ? (o/n)");
		reponse=sc.nextLine();
		reponse=reponse.toLowerCase();
		if(reponse.contentEquals("o")) nouvellePartie=true;
		else nouvellePartie=false;
		} while(nouvellePartie);
	
	System.out.println("À bientôt !");
	sc.close();
	
	}
}
