package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;

public class Joueur {
	private String nom;
	private GrilleNavire grilleNavire = new GrilleNavire();
	private GrillePion grillePion = new GrillePion();
	private Set<Navire> navires = new HashSet<>();

	public Joueur(String nom) {
		this.nom = nom;
		initializeNavires();
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
	
	private void initializeNavires() {
		navires.add(new ContreTorpilleur());
		navires.add(new Croiseur());
		navires.add(new PorteAvion());
		navires.add(new SousMarin());
		navires.add(new Torpilleur());
	}
	
	public void afficherNavires() {
		navires.stream().map(navire -> navire.getNom()).forEach(nom -> System.out.println(" - " + nom));
	}
	
	public boolean navireExisteParNom(String nom) {
		return trouverNavireParNom(nom).isPresent();
	}
	
	public Navire recupererNavireParNom(String nom) {
		return trouverNavireParNom(nom).get();
	}
	
	private Optional<Navire> trouverNavireParNom(String nom) {
		return navires.stream().filter(navire -> navire.getNom().equals(nom)).findAny();
	}
	
	public void placerNavire(Navire navire, String position, Orientation orientation) 
			throws InvalidePositionException {
		grilleNavire.placerNavire(navire, position, orientation);
		removeNavire(navire);
	}
	
	private void removeNavire(Navire navire) {
		navires.remove(navire);
	}
	
	public void attaquer(Joueur joueur, String position) {
		if(joueur.getGrilleNavire().celluleOccupee(position)) {
			joueur.getGrilleNavire().retirerNavireCellule(position);
			grillePion.ajouterCelluleTouchee(position);
		} else {
			grillePion.ajouterCelluleRatee(position);
		}
	}

}
