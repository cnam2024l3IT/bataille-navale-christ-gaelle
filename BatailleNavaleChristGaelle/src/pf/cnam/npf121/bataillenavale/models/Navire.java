package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class Navire {
	protected String nom;
	protected int nombreCellules;
	protected Set<Cellule> cellules = new HashSet<>();
	
	public Navire(String nom, int nombreCellules) {
		this.nom = nom;
		this.nombreCellules = nombreCellules;
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
		return recupererCelluleParPosition(position).isPresent();
	}
	
	protected Optional<Cellule> recupererCelluleParPosition(String position) {
		return cellules.stream().filter(cellule -> cellule.getPosition().equals(position)).findFirst();
	}
	
	protected Cellule getCelluleParPosition(String position) {
		return recupererCelluleParPosition(position).get();
	}
}
