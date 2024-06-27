package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public abstract class Grille {
	protected final String[] colonnes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	protected final String[] lignes = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	protected Set<Cellule> allCellules = new HashSet<>();
	
	protected Grille() {
		initialiser();
	}
	
	protected void initialiser() {
		for(int i = 0; i < colonnes.length; i++)
			for(int j = 0; j < lignes.length; j++)
				allCellules.add(new Cellule(colonnes[i] + lignes[j], new Coordonnee(i, j)));
	}
	
	protected Cellule recupererCellule(String position) throws NonTrouveException {
		return allCellules.stream()
				.filter(c -> c.getPosition().equals(position)).findFirst()
				.orElseThrow(() -> 
					new NonTrouveException("Aucune cellule n'a été trouvée avec la position " + position));
	}

}
