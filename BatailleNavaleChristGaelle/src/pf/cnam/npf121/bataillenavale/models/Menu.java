package pf.cnam.npf121.bataillenavale.models;

import java.util.Scanner;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;

public class Menu {

	public Menu() {}
	
	@SuppressWarnings("resource")
	public Navire demanderSelectionNavire(Joueur joueur) {
		System.out.println(joueur.getNom() + " - voici votre liste de navires");		
		joueur.afficherNavires();
		System.out.println(joueur.getNom() + " - saisir le nom du navire à ajouter à votre grille : ");
		String nom = "";
		Scanner scanner = new Scanner(System.in);
		
		if(scanner.hasNextLine())
			nom = scanner.nextLine();
		
		if(joueur.navireExisteParNom(nom))
			return joueur.recupererNavireParNom(nom);
		
		return null;
	}
	
	@SuppressWarnings("resource")
	public int demanderChoixPlacement() {
		int choix = 0;
		do {
			System.out.println("Placer le navire, sélectionner votre choix : ");
			System.out.println("1 - Afficher votre grille");
			System.out.println("2 - Saisir position");
			Scanner scanner = new Scanner(System.in);
			
			if(scanner.hasNextInt())
				choix = scanner.nextInt();
			
			if(choix != 1 && choix != 2)
				System.out.println("Choix incorrect");
		} while(choix != 1 && choix != 2);
		return choix;
	}
	
	@SuppressWarnings("resource")
	public String demanderPosition(Joueur joueur) {
		String position = "";
		do {
			System.out.println("Veuillez saisir une position :");
			Scanner scanner = new Scanner(System.in);
			
			if(scanner.hasNextLine())
				position = scanner.nextLine();
		} while(!joueur.getGrilleNavire().positionExiste(position));
		return position;
	}
	
	@SuppressWarnings("resource")
	public Orientation demanderOrientation() {
		int choix = 0;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Veuillez saisir une orientation :");
			for(int i = 0; i < Orientation.values().length; i++)
				System.out.println(Orientation.values()[i].ordre + " - " + Orientation.values()[i]);
			
			if(scanner.hasNextInt())
				choix = scanner.nextInt();
			
			if(Orientation.valueOfOrdre(choix) == null)
				System.out.println("Choix incorrect");
		} while(Orientation.valueOfOrdre(choix) == null);
		return Orientation.valueOfOrdre(choix);
	}
	
	@SuppressWarnings("resource")
	public int demanderChoixAttaque() {
		int choix = 0;
		do {
			System.out.println("Phase d'attaque : ");
			System.out.println("1 - Afficher la grille de l'adversaire");
			System.out.println("2 - Donner une position à attaquer");
			Scanner scanner = new Scanner(System.in);
			
			if(scanner.hasNextInt()) 
				choix = scanner.nextInt();
			
			if(choix != 1 && choix != 2)
				System.out.println("Choix incorrect");
		} while(choix != 1 && choix != 2);
		return choix;
	}

}
