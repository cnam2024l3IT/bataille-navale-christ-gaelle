package pf.cnam.npf121.bataillenavale.models;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class Joueur {
	private String nom;
	private GrilleNavire grilleNavire = new GrilleNavire();
	private GrilleAdversaire grilleAdversaire = new GrilleAdversaire();

	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean aPerdu() {
		return grilleNavire.estVide();
	}
	
	public void placerNavire(Navire navire, Cellule cellule, Orientation orientation) throws InvalidePositionException {
		grilleNavire.placerNavire(navire, cellule, orientation);
	}
	
	public String[] getStatusGrille() {
		return grilleNavire.getStatus();
	}
	
	public String[] getStatusGrilleAdversaire() {
		return grilleAdversaire.getStatus();
	}
	
	public Cellule recupererCellule(String position) throws NonTrouveException {
		return grilleNavire.recupererCellule(position);
	}
	
	public void ajouterCelluleTouchee(Cellule cellule) {
		grilleAdversaire.ajouterCelluleTouchee(cellule);
	}
	
	public void ajouterCelluleRatee(Cellule cellule) {
		grilleAdversaire.ajouterCelluleRatee(cellule);
	}
	
	public Navire recupererNavireParPosition(String position) throws NonTrouveException {
		return grilleNavire.recupererNavireParCellule(position);
	}
	
	public void removeNavire(Navire navire) {
		grilleNavire.removeNavire(navire);
	}
	
	public void addCelluleDetruite(Cellule cellule) {
		grilleNavire.addCelluleDetruite(cellule);
	}
	
	public void addNavire(Navire navire) {
		grilleNavire.addNavire(navire);
	}
	
	public Navire retirerNavireParPosition(String position) throws NonTrouveException {
		Navire navire = recupererNavireParPosition(position);
		removeNavire(navire);
		Cellule cellule = navire.recupererCelluleParPosition(position);
		navire.retirerCellule(cellule);
		addCelluleDetruite(cellule);
		return navire;
	}
	
}
