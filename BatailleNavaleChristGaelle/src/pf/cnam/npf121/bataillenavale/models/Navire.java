package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.interfaces.AffichageMenu;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public abstract class Navire implements AffichageMenu {
	protected String nom;
	protected int nombreCellules;
	protected Set<Cellule> cellules = new HashSet<>();
	
	public Navire(String nom, int nombreCellules) {
		this.nom = nom;
		this.nombreCellules = nombreCellules;
	}
	
	@Override
	public void afficher() {
		System.out.println("- " + nom + " : " + nombreCellules + " cellule(s)");
	}
	
	protected String getNom() {
		return nom;
	}
	
	protected int getNombreCellule() {
		return nombreCellules;
	}
	
	protected Set<Cellule> getCellules() {
		return cellules;
	}
	
	protected void setCellules(Set<Cellule> cellules) {
		this.cellules = cellules;
	}
	
	protected void retirerCellule(Cellule cellule) {
		cellules.remove(cellule);
	}
	
	protected boolean celluleExisteParPosition(String position) {
		return cellules.stream()
				.map(c -> c.getPosition()).filter(p -> p.equals(position)).findFirst().isPresent();
	}
	
	protected Cellule recupererCelluleParPosition(String position) throws NonTrouveException {
		return cellules.stream()
				.filter(c -> c.getPosition().equals(position)).findFirst()
				.orElseThrow(() -> 
					new NonTrouveException("Aucune cellule n'a été trouvée avec la position " + position));
	}
}
