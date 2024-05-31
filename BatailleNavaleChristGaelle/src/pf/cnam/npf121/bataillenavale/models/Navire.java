package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

public abstract class Navire {
	protected int nombreCellules;
	protected Set<Cellule> cellules = new HashSet<>();
	
	public Navire(int nombreCellules) {
		this.nombreCellules = nombreCellules;
	}
	
	protected int getNombreCellule() {
		return nombreCellules;
	}
	
	protected void setCellules(Set<Cellule> cellules) {
		this.cellules = cellules;
	}
	
	protected void removeCellule(Cellule cellule) {
		cellules.remove(cellule);
	}
}
