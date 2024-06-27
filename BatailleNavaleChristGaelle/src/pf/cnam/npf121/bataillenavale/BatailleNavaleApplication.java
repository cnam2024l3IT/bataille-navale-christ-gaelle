package pf.cnam.npf121.bataillenavale;

import java.util.ArrayList;

import pf.cnam.npf121.bataillenavale.models.BatailleNavale;
import pf.cnam.npf121.bataillenavale.models.Joueur;

public class BatailleNavaleApplication {

	public static void main(String[] args) {
		ArrayList<Joueur> joueurs = new ArrayList<>();
		joueurs.add(new Joueur("Joueur 1"));
		joueurs.add(new Joueur("Joueur 2"));
		BatailleNavale batailleNavale = new BatailleNavale(joueurs);
		batailleNavale.demarrer();
	}

}
