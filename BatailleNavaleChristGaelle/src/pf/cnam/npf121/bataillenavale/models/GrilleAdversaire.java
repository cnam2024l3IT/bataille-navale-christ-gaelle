package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.interfaces.Statusable;

public class GrilleAdversaire extends Grille implements Statusable {
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
	
	public String[] status() {
		String[] status = new String[lignes.length + 1];
		String txt = String.format("%3s", "");
		for(int i = 0; i < colonnes.length; i++)
			txt += colonnes[i] + " ";
		status[0] = txt;
		for(int i = 0; i < lignes.length; i++) {
			txt = String.format("%2s", lignes[i]) + "|";
			for(int j = 0; j < colonnes.length; j++) {
				String position = colonnes[j] + lignes[i];
				
				if(celluleTouchee(position))
					txt += "T|";
				else if(celluleRatee(position))
					txt += "R|";
				else
					txt += "_|";
			}
			status[i + 1] = txt;
		}
		return status;
	}
	
	private boolean celluleTouchee(String position) {
		return cellulesTouchees.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}
	
	private boolean celluleRatee(String position) {
		return cellulesRatees.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}

}
