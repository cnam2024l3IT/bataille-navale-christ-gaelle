package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class GrilleManager {
	private static volatile GrilleManager instance;

	public static GrilleManager getInstance() {
		GrilleManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized(GrilleManager.class) {
            if (instance == null) {
                instance = new GrilleManager();
            }
            return instance;
        }
	}
	
	private String[] lignes;
	private String[] colonnes;
	private Cellules cellules;
	private Navires navires = new Navires();

	private GrilleManager() {
	}
	
	public void initialize(String[] lignes, String[] colonnes) {
		this.lignes = lignes;
		this.colonnes = colonnes;
		this.cellules = new Cellules(buildCellules(lignes, colonnes));
	}
	
	public Set<Cellule> getCellules() {
		try {
			return cellules.clone().getCellules();
		} catch (CloneNotSupportedException e) {
			return new HashSet<>();
		}
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
	
	public Set<Navire> getNavires() {
		try {
			return navires.clone().getNavires();
		} catch (CloneNotSupportedException e) {
			return new HashSet<>();
		}
	}
	
	private Set<Cellule> buildCellules(String[] lignes, String[] colonnes) {
		Set<Cellule> cellules = new HashSet<>();
		for(int i = 0; i < colonnes.length; i++)
			for(int j = 0; j < lignes.length; j++)
				cellules.add(new Cellule(colonnes[i] + lignes[j], new Coordonnee(i, j)));
		return cellules;
	}

}
