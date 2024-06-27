package pf.cnam.npf121.bataillenavale.models;

import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;

public class BatailleNavale {
	private Menu menu = new Menu();
	private Joueur joueur1 = new Joueur("Joueur 1");
	private Joueur joueur2 = new Joueur("Joueur 2");

	public BatailleNavale() {
		initializeNavires(joueur1);
		initializeNavires(joueur2);
	}
	
	public void placerNavires() {
		System.out.println("Joueurs, veuillez placer vos navires");
		joueurPlacerNavires(joueur1);
		joueurPlacerNavires(joueur2);
	}
	
	private void initializeNavires(Joueur joueur) {
		joueur.addNavire(new ContreTorpilleur());
		joueur.addNavire(new Croiseur());
		joueur.addNavire(new PorteAvion());
		joueur.addNavire(new SousMarin());
		joueur.addNavire(new Torpilleur());
	}
	
	private void joueurPlacerNavires(Joueur joueur) {
		System.out.println("Placement navire " + joueur.getNom());
		do  {
			try {
				joueur.afficherGrille();
				joueur.placerNavire(menu.demanderSelectionNavire(joueur), menu.demanderPosition(joueur), 
						menu.demanderOrientation(joueur));
			} catch (InvalidePositionException e) {
				System.out.println(e.getMessage());
			} 
		} while(joueur.getNavires().size() > 0);
		joueur.afficherGrille();
	}
	
	public void attaquer() {
		System.out.println("Joueurs, passez à l'attaque");
		do {
			// revoir l'enchainement des tours
			joueurAttaquer(joueur1, joueur2);
			joueurAttaquer(joueur2, joueur1);
		} while(joueur1.getGrilleNavire().getNavires().size() > 0 
				&& joueur2.getGrilleNavire().getNavires().size() > 0);
		
		if(joueur1.getGrilleNavire().getNavires().size() > 0) {
			System.out.println(joueur1.getNom() + ", vous avez gagné.");
		} else {
			System.out.println(joueur2.getNom() + ", vous avez gagné.");
		}
	}
	
	private void joueurAttaquer(Joueur joueur, Joueur adversaire) {
		joueur.afficherGrille();
		joueur.afficherGrilleAdversaire();
		joueur.attaquer(adversaire, menu.demanderPosition(joueur));
	}
	
}
