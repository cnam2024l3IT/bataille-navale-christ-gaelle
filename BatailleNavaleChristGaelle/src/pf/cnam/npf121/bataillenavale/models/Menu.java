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
	public String demanderPosition() {
		String position = "";
		do {
			System.out.println("Veuillez saisir une position :");
			Scanner scanner = new Scanner(System.in);
			
			if(scanner.hasNextLine())
				position = scanner.nextLine();
		} while(position.isEmpty());
		return position;
	}
	
	@SuppressWarnings("resource")
	public Orientation demanderOrientation() {
		int choix = 0;
		do {
			System.out.println("Veuillez saisir une orientation :");
			System.out.println("1 - " + Orientation.VERTICAL);
			System.out.println("2 - " + Orientation.HORIZONTAL);
			Scanner scanner = new Scanner(System.in);
			
			if(scanner.hasNextInt())
				choix = scanner.nextInt();
			
			if(choix != 1 && choix != 2)
				System.out.println("Choix incorrect");
		} while(choix != 1 && choix != 2);
		return choix == 1 ? Orientation.VERTICAL : Orientation.HORIZONTAL;
	}

}
