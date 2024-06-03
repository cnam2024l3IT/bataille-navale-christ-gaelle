package pf.cnam.npf121.bataillenavale.models;

import java.util.Scanner;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class Menu {

	public Menu() {}
	
	@SuppressWarnings("resource")
	public Navire demanderSelectionNavire(Joueur joueur) {
		String nom = "";
		Navire navire = null;
		do {
			System.out.println(joueur.getNom() + ", choisissez le navire à placer : (saisir le nom)");		
			joueur.afficherNavires();
			Scanner scanner = new Scanner(System.in);
			
			if(scanner.hasNextLine())
				nom = scanner.nextLine();
			
			try {
				navire = joueur.trouverNavireParNom(nom);
			} catch (NonTrouveException e) {
				System.out.println(e.getMessage());
			}
		} while(navire == null);
		return navire;
	}
	
	@SuppressWarnings("resource")
	public Cellule demanderPosition(Joueur joueur) {
		String position = "";
		Cellule cellule = null;
		do {
			System.out.println(joueur.getNom() + ", veuillez saisir une position : ");
			Scanner scanner = new Scanner(System.in);
			
			if(scanner.hasNextLine())
				position = scanner.nextLine();
			
			try {
				cellule = joueur.getGrilleNavire().recupererCellule(position);
			} catch (NonTrouveException e) {
				System.out.println(e.getMessage());
			}
		} while(cellule == null);
		return cellule;
	}
	
	@SuppressWarnings("resource")
	public Orientation demanderOrientation(Joueur joueur) {
		int choix = 0;
		Orientation orientation = null;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println(joueur.getNom() + ", veuillez saisir une orientation :");
			for(int i = 0; i < Orientation.values().length; i++)
				Orientation.values()[i].afficher();
			
			if(scanner.hasNextInt())
				choix = scanner.nextInt();
			
			try {
				orientation = Orientation.valueOfOrdre(choix);
			} catch (NonTrouveException e) {
				System.out.println(e.getMessage());
			}
		} while(orientation == null);
		return orientation;
	}

}
