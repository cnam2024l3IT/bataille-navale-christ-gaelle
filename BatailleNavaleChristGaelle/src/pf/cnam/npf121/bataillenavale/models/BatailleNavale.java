package pf.cnam.npf121.bataillenavale.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class BatailleNavale {
	private List<Joueur> joueurs = new ArrayList<>();
	private final BatailleNavaleConsole console = new BatailleNavaleConsole();

	public BatailleNavale(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public void demarrer() {
		console.afficher("Début de la partie");
		console.afficher("Phase 1 : placement des navires");
		placementNavires();
		console.afficher("Phase 2 : attaques");
		attaques();
		afficherResultat();
		console.afficher("Fin de la partie");
	}
	
	private void placementNavires() {
		joueurs.forEach(j -> placerNavires(j, GrilleManager.getInstance().getNavires()));
	}
	
	private void placerNavires(Joueur joueur, Set<Navire> navires) {
		do  {
			try {
				console.afficherGrille(joueur);
				Navire navire = console.obtenirNavire(joueur, navires);
				joueur.placerNavire(navire, console.obtenirCellule(joueur), console.obtenirOrientation(joueur));
				navires.remove(navire);
			} catch (InvalidePositionException e) {
				console.afficher(e.getMessage());
			} 
		} while(navires.size() > 0);	
		console.afficherGrille(joueur);
	}
	
	private void attaques() {
		setAttaque(joueurs.get(0), joueurs.get(1));
	}
	
	private void setAttaque(Joueur attaquant, Joueur cible) {
		if(cible.aPerdu())
			return;
		
		faireAttaque(attaquant, cible);
		setAttaque(cible, attaquant);
	}
	
	private void faireAttaque(Joueur attaquant, Joueur cible) {
		console.afficherGrille(attaquant);
		console.afficherGrilleAdversaire(attaquant);
		Cellule cellule = console.obtenirCellule(attaquant);
		try {
			attaquerCellule(cible, cellule.getPosition());
			attaquant.ajouterCelluleTouchee(cellule);
		} catch (NonTrouveException e) {
			console.afficher(e.getMessage());
			attaquant.ajouterCelluleRatee(cellule);
		}
	}
	
	private void attaquerCellule(Joueur joueur, String position) throws NonTrouveException {
		Navire navire = joueur.retirerNavireParPosition(position);
		console.afficher("Touché");
		
		if(!navire.aCoule())
			joueur.addNavire(navire);
		else
			console.afficher("Coulé");
	}
	
	private void afficherResultat() {
		if(isMatchNul())
			console.afficher("Match nul, aucun joueur n'a gagné !");
		else
			console.afficher(getGagnant().getNom() + ", vous avez gagné.");
	}
	
	private boolean isMatchNul() {;
		return joueurs.stream().filter(j -> !j.aPerdu()).count() == 0;
	}
	
	private Joueur getGagnant() {
		return joueurs.stream().filter(j -> !j.aPerdu()).findFirst().get();
	}
	
}
