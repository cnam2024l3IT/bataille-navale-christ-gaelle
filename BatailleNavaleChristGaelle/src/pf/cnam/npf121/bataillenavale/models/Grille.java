package pf.cnam.npf121.bataillenavale.models;

import pf.cnam.npf121.bataillenavale.models.enumerations.CelluleStatus;

public abstract class Grille {
	
	protected Grille() {

	}
	
	protected abstract CelluleStatus getCelluleStatus(String position);

}
