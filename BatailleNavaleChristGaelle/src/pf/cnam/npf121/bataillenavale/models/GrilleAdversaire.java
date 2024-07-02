package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.enumerations.CelluleStatus;

public class GrilleAdversaire extends Grille {
	private Set<Cellule> cellulesTouchees = new HashSet<>();
	private Set<Cellule> cellulesRatees = new HashSet<>();

	public GrilleAdversaire() {
		super();
	}
	
	public void ajouterCelluleTouchee(Cellule cellule) {
		cellulesTouchees.add(cellule);
	}
	
	public void ajouterCelluleRatee(Cellule cellule) {
		cellulesRatees.add(cellule);
	}
	
	private boolean celluleTouchee(String position) {
		return cellulesTouchees.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}
	
	private boolean celluleRatee(String position) {
		return cellulesRatees.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}

	@Override
	protected CelluleStatus getCelluleStatus(String position) {
		return celluleTouchee(position) ? CelluleStatus.TOUCHEE : 
			(celluleRatee(position) ? CelluleStatus.RATEE : CelluleStatus.VIDE);
	}

}
