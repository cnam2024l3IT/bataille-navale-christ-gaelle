package pf.cnam.npf121.bataillenavale;

import java.util.ArrayList;

import pf.cnam.npf121.bataillenavale.models.BatailleNavale;
import pf.cnam.npf121.bataillenavale.models.GrilleCreator;
import pf.cnam.npf121.bataillenavale.models.Joueur;

public class BatailleNavaleApplication {
	private final static String[] colonnes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	private final static String[] lignes = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

	public static void main(String[] args) {
		GrilleCreator grilleCreator = new GrilleCreator(lignes, colonnes);
		ArrayList<Joueur> joueurs = new ArrayList<>();
		joueurs.add(new Joueur("Joueur 1", grilleCreator.getCellules()));
		joueurs.add(new Joueur("Joueur 2", grilleCreator.getCellules()));
		BatailleNavale batailleNavale = new BatailleNavale(joueurs, grilleCreator);
		batailleNavale.demarrer();
	}

}
