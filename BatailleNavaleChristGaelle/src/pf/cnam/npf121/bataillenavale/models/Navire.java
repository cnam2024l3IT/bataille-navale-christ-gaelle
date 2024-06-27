package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.interfaces.Statusable;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public abstract class Navire implements Statusable {
	protected int numero;
	protected String nom;
	protected int nombreCellules;
	protected Set<Cellule> cellules = new HashSet<>();
	
	public Navire(int numero, String nom, int nombreCellules) {
		this.numero = numero;
		this.nom = nom;
		this.nombreCellules = nombreCellules;
	}
	
	public String[] status() {
		return new String[] {numero + " - " + nom + " : " + nombreCellules + " cellule(s)"};
	}
	
	protected int getNombreCellule() {
		return nombreCellules;
	}
	
	protected Set<Cellule> getCellules() {
		return cellules;
	}
	
	protected int getNumero() {
		return numero;
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
	
	protected boolean aCoule() {
		return cellules.size() == 0;
	}
}
