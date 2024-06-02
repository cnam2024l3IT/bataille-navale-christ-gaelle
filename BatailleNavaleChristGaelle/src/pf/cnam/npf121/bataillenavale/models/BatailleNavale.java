package pf.cnam.npf121.bataillenavale.models;

import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;

public class BatailleNavale {
	private Menu menu = new Menu();
	private Joueur joueur1 = new Joueur("Joueur 1");
	private Joueur joueur2 = new Joueur("Joueur 2");

	public BatailleNavale() {}
	
	// déroulement du jeu
	// 1 - les joueurs placent les navires
	// 2 - chaque joueur attaque l'autre par tour
	// 3 - le jeu s'arrête si un joueur a tous ses navires détruits
	
	public void placerNavires() {
		joueurPlacerNavires(joueur1);
		joueurPlacerNavires(joueur2);
	}
	
	private void joueurPlacerNavires(Joueur joueur) {
		Navire navireSelectionne = null;
		do  {
			if(navireSelectionne == null)
				navireSelectionne = menu.demanderSelectionNavire(joueur);
			
			if(navireSelectionne != null) {
				int choix = menu.demanderChoixPlacement();
				
				if(choix == 1)
					joueur.getGrilleNavire().afficher();
				
				if(choix == 2) {
					try {
						joueur.placerNavire(navireSelectionne, menu.demanderPosition(), 
								menu.demanderOrientation());
						navireSelectionne = null;
					} catch (InvalidePositionException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		} while(joueur.getNavires().size() > 0);
	}
	
	public void attaquer() {
		// Chaque joueur attaque l'autre jusqu'à ce que l'un des 2 joueurs n'a plus de navire
		do {
			joueur1.attaquer(joueur2, menu.demanderPosition());
			joueur2.attaquer(joueur1, menu.demanderPosition());
		} while(joueur1.getGrilleNavire().getNavires().size() > 0 
				&& joueur2.getGrilleNavire().getNavires().size() > 0);
	}
	
}
