package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class Cellules implements Cloneable {
	private Set<Cellule> cellules;

	public Cellules(Set<Cellule> cellules) {
		this.cellules = cellules;
	}
	
	@Override
	public Cellules clone() throws CloneNotSupportedException {
		Set<Cellule> cells = new HashSet<>();
		cellules.forEach(c -> cells.add(c));
		return new Cellules(cells);
	}
	
	public Set<Cellule> getCellules() {
		return cellules;
	}
	
	public Cellule recupererCellule(String position) throws NonTrouveException {
		return cellules.stream()
				.filter(c -> c.getPosition().equals(position)).findFirst()
				.orElseThrow(() -> 
					new NonTrouveException("Aucune cellule n'a été trouvée avec la position " + position));
	}

}
