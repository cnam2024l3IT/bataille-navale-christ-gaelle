package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public final class GrilleCreator {
	private final String[] lignes;
	private final String[] colonnes;
	private final Cellules cellules;
	
	public GrilleCreator(String[] lignes, String[] colonnes) {
		this.lignes = lignes;
		this.colonnes = colonnes;
		this.cellules = new Cellules(buildCellules(lignes, colonnes));
	}
	
	private Set<Cellule> buildCellules(String[] lignes, String[] colonnes) {
		Set<Cellule> cellules = new HashSet<>();
		for(int i = 0; i < colonnes.length; i++)
			for(int j = 0; j < lignes.length; j++)
				cellules.add(new Cellule(colonnes[i] + lignes[j], new Coordonnee(i, j)));
		return cellules;
	}

	public String[] getLignes() {
		return lignes;
	}

	public String[] getColonnes() {
		return colonnes;
	}	
	
	public Cellule recupererCellule(String position) throws NonTrouveException {
		return cellules.recupererCellule(position);
	}
	
	public String getRowColonnes() {
		String txt = String.format("%3s", "");
		for(int i = 0; i < colonnes.length; i++)
			txt += colonnes[i] + " ";
		return txt;
	}
	
	public Set<Cellule> getCellules() {
		try {
			return cellules.clone().getCellules();
		} catch (CloneNotSupportedException e) {
			return new HashSet<>();
		}
	}

}
