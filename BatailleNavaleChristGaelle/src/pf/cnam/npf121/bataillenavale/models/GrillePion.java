package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

public class GrillePion extends Grille {
	private Set<Cellule> cellulesTouchees = new HashSet<>();
	private Set<Cellule> cellulesRatees = new HashSet<>();

	public GrillePion() {
		super();
	}
	
	public void ajouterCelluleTouchee(Cellule cellule) {
		cellulesTouchees.add(cellule);
	}
	
	public void ajouterCelluleRatee(Cellule cellule) {
		cellulesRatees.add(cellule);
	}

	@Override
	public void afficher() {		
		String txt = String.format("%3s", "");
		for(int i = 0; i < colonnes.length; i++)
			txt += colonnes[i] + " ";
		System.out.println(txt);
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
			System.out.println(txt);
		}
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
