package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class Grille {
	protected final String[] colonnes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	protected final String[] lignes = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	protected Set<Cellule> cellules = new HashSet<>();
	
	protected Grille() {
		cellules = initialiser();
	}
	
	protected Set<Cellule> initialiser() {
		Set<Cellule> allCellules = new HashSet<>();
		for(int i = 0; i < colonnes.length; i++)
			for(int j = 0; j < lignes.length; j++)
				allCellules.add(new Cellule(colonnes[i] + lignes[j], new Coordonnee(i, j)));
		return allCellules;
	}
	
	protected abstract void afficher();
	
	protected Optional<Cellule> recupererCellule(String position) {
		return cellules.stream().filter(c -> c.getPosition().equals(position)).findFirst();
	}

}
