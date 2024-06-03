package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class Joueur {
	private String nom;
	private GrilleNavire grilleNavire = new GrilleNavire();
	private GrillePion grillePion = new GrillePion();
	private Set<Navire> navires = new HashSet<>();

	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public GrilleNavire getGrilleNavire() {
		return grilleNavire;
	}
	
	public Set<Navire> getNavires() {
		return navires;
	}
	
	public void addNavire(Navire navire) {
		navires.add(navire);
	}
	
	public void afficherNavires() {
		navires.stream().forEach(navire -> navire.afficher());
	}
	
	public Navire trouverNavireParNom(String nom) throws NonTrouveException {
		return navires.stream()
				.filter(navire -> navire.getNom().equals(nom)).findFirst()
				.orElseThrow(() -> new NonTrouveException("Aucun navire n'a été trouvé avec le nom " + nom));
	}
	
	public void placerNavire(Navire navire, Cellule cellule, Orientation orientation) 
			throws InvalidePositionException {
		grilleNavire.placerNavire(navire, cellule, orientation);
		removeNavire(navire);
	}
	
	private void removeNavire(Navire navire) {
		navires.remove(navire);
	}
	
	public void attaquer(Joueur joueur, Cellule cellule) {
		try {
			joueur.getGrilleNavire().attaqueSurCellule(cellule.getPosition());
			grillePion.ajouterCelluleTouchee(cellule);
		} catch (NonTrouveException e) {
			System.out.println("Ratée");
			grillePion.ajouterCelluleRatee(cellule);
		}
	}
	
	public void afficherGrille() {
		System.out.println("Grille : " + nom);
		grilleNavire.afficher();
	}
	
	public void afficherGrilleAdversaire() {
		System.out.println("Grille de votre adversaire");
		grillePion.afficher();
	}

}
